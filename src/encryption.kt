import kotlin.math.pow
import java.io.File

fun main(){
    val s =readLine().toString().split(" ").toTypedArray()
    Parser.main(s)
}

class encryption(_key: String, inputFile:String, outputFile:String)  {
    private var key: Int
    private var fileInput: String
    private var fileOutput: String
    init{
        key = key(_key)
        fileInput=inputFile
        fileOutput=outputFile
    }

    fun fileCoding() {
        val fileOutput1 = buildString {
            if (fileInput == fileOutput) {
                append("notEternal.txt")
            } else {
                append(fileOutput)
            }
        }
        val writer = File(fileOutput1).bufferedWriter()
        for (line in File(fileInput).readLines()) {
            val chr = line.toCharArray()
            val sumStr = buildString {
                for (kod in chr){
                    val o = kod.toInt()
                    val num = xor(kod.toInt(), key)
                    append("$num ")
                }
            }
            writer.write(sumStr + "\n")
        }
        writer.close()
        if (fileInput == fileOutput) {
            val writerTemporary = File(fileOutput).bufferedWriter()
            for (line in File(fileOutput1).readLines()){
                writerTemporary.write(line + "\n")
            }
            writerTemporary.close()
        }
        File("notEternal.txt").delete()
    }

    fun fileDecoding() {
        val fileOutput1 = buildString {
            if (fileInput == fileOutput) {
                append("notEternal.txt")
            } else {
                append(fileOutput)
            }
        }
        val writer = File(fileOutput1).bufferedWriter()
        for (line in File(fileInput).readLines()) {
            val chr = line.trim().split(" ")
            if (chr[0] == "" && chr.size < 2) {
                writer.newLine()
                continue
            }
            val sumStr = buildString {
                for (kod in chr){
                    val num = xor(kod.toInt(), key)
                    append("${num.toChar()}")
                }
            }
            writer.write(sumStr + "\n")
        }
        writer.close()
        if (fileInput == fileOutput) {
            val writerTemporary = File(fileOutput).bufferedWriter()
            for (line in File("notEternal.txt").readLines()){
                writerTemporary.write(line + "\n")
            }
            writerTemporary.close()
        }
        File("notEternal.txt").delete()
    }

    fun xor(arg1: Int, arg2: Int): Int{
        var binaryNumber = ""
        var binaryKey = ""
        var tempNumber = arg1
        var tempKey = arg2
        while (tempNumber > 0){
            binaryNumber += tempNumber % 2
            tempNumber /= 2
        }
        while (tempKey > 0){
            binaryKey += tempKey % 2
            tempKey /= 2
        }
        binaryKey = binaryKey.reversed()
        binaryNumber = binaryNumber.reversed()
        val counter = binaryKey
        while(binaryKey.length < binaryNumber.length){
            binaryKey += counter
        }
        tempNumber = arg1
        tempKey = 0
        binaryKey = binaryKey.reversed()
        for (i in 0 until binaryKey.length){
            tempKey += (binaryKey[i].toString().toInt() * 2.0.pow(i)).toInt()
        }
        return tempNumber xor tempKey
    }

    fun key(key: String): Int{
        val map = mapOf('A' to 10, 'B' to 11, 'C' to 12, 'D' to 13,'E' to 14, 'F' to 15)
        val simbolKey = key.toCharArray()
        var degree = key.length
        var sum = 0
        for (i in simbolKey){
            degree -=1
            if (i.toUpperCase() in map.keys){
                sum += map.getValue(i.toUpperCase()) * 16.0.pow(degree).toInt()
                continue
            }
            sum += try{
                i.toString().toInt() * 16.0.pow(degree).toInt()
            }catch (e:NumberFormatException){
                0
            }
        }
        return sum
    }
}