package com.chenx;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.logging.Logger;

/**
 * 一个简单的多线程下载多个文件的示例
 */
public class SimpleMultiDownload {
    private static final Logger log = Logger.getLogger("Logger");

    public static void main(String[] args) {
        String[] urls = {
                "https://js.t.sinajs.cn/t1/star-pc/assets/js/h5.cd88aa02.js", "https://js.t.sinajs.cn/t1/star-pc/assets/css/h5.66a65acc.css"
        };
        for (String url : urls) {
            // 给每个url启动一个线程
            new Thread(new FileDownloader(url)).start();
        }
    }


    static class FileDownloader implements Runnable {
        private final String fileUrl;

        public FileDownloader(String url) {
            fileUrl = url;
        }

        @Override
        public void run() {
            log.info("Downloading from " + fileUrl);
            String fileBaseName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            try {
                URL url = new URL(fileUrl);
//                StringBuilder localFileName = new StringBuilder(System.getProperty("java.io.tmpdir"));// 获取临时文件地址
                StringBuilder localFileDir = new StringBuilder("C:\\Users\\chenxing\\Desktop\\");
                localFileDir.append("likeheart-").append(fileBaseName);
                log.info("Saving to: " + localFileDir);
                downloadFile(url, new FileOutputStream(localFileDir.toString()), 1024);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("Done download from " + fileUrl);
        }

        /**
         * 从指定URL下载文件，将其保存到指定的输出流
         */
        private void downloadFile(URL url, FileOutputStream fileOutputStream, int bufSize) throws IOException {
            // 建立HTTP连接
            final HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            ReadableByteChannel inChannel = null;
            WritableByteChannel outChannel = null;
            try {
                // 获取响应码
                int responseCode = httpConn.getResponseCode();
                // 如果不是2开头，说明响应有问题
                if (2 != responseCode / 100) {
                    throw new IOException("Error: HTTP " + responseCode);
                }
                // 没有内容，直接结束下载
                if (0 == httpConn.getContentLength()) {
                    log.info("Nothing to be downloaded " + fileUrl);
                    return;
                }
                inChannel = Channels.newChannel(new BufferedInputStream(httpConn.getInputStream()));
                outChannel = Channels.newChannel(new BufferedOutputStream(fileOutputStream));
                ByteBuffer buf = ByteBuffer.allocate(bufSize);
                while (inChannel.read(buf) != -1) {
                    buf.flip();
                    outChannel.write(buf);
                    buf.clear();
                }
            } finally {
                // 关闭通道，断开HTTP连接
                if (inChannel != null) {
                    try {
                        inChannel.close();
                    } catch (Exception ignored) {
                    }
                }
                if (outChannel != null) {
                    try {
                        outChannel.close();
                    } catch (Exception ignored) {
                    }
                }
                httpConn.disconnect();
            }
        }

    }
}