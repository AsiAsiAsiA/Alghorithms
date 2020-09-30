package week1

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.*

private const val INPUT = "input.txt"
private const val OUTPUT = "output.txt"

fun main() {
    val scanner = Scanner(File(INPUT))

    while (scanner.hasNext()) {
        writeToFile(scanner.nextLong(), scanner.nextLong())
    }
}

private fun writeToFile(a: Long, b: Long) {
    val file = File(OUTPUT)
    file.createNewFile()

    val result = a + b * b

    BufferedWriter(FileWriter(file)).apply {
        write(result.toString())
        close()
    }
}