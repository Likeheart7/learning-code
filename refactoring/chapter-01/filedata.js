import { readFileSync } from "../utils/fileutils.js"
// node执行路径问题的解决
import { fileURLToPath } from 'node:url'
import {join,dirname} from "node:path"
const __dirname = dirname(fileURLToPath(import.meta.url))
// 读取json文件内容至变量并export
// 路径取决于引入的文件的路径
const plays = JSON.parse(readFileSync(join(__dirname, "./plays.json")))
const invoices = JSON.parse(readFileSync(join(__dirname,'./invoices.json')))[0]

export {plays, invoices}