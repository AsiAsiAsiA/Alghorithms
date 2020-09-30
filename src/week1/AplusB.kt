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
        writeToFile(scanner.nextInt() + scanner.nextInt())
    }
}

private fun writeToFile(sum: Int) {
    val file = File(OUTPUT)
    file.createNewFile()
    BufferedWriter(FileWriter(file)).apply {
        write(sum.toString())
        close()
    }
}
