// 引入node提供的文件读取功能
import fs from 'fs'
// 文件读取
function readFileSync(path) {
    return fs.readFileSync(path, 'utf8', (err, data) => {
        if (err) {
            console.log(err)
            return 'File read failed!'
        }
        return data
    })
}

export {readFileSync}