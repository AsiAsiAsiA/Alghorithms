package week1

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.*

private const val INPUT = "input.txt"
private const val OUTPUT = "output.txt"

fun main() {
    val scanner = Scanner(File(INPUT))

    val size = scanner.nextInt()
    val insertionSequence = mutableListOf<Int>(1)
    val sortedArray = mutableListOf<Long>()

    while (scanner.hasNext()) {
        sortedArray.add(scanner.nextLong())
    }
    insertionSort(sortedArray, insertionSequence, size)
    writeToFile(insertionSequence, sortedArray)
}

//Реализация сортировки вставками
private fun insertionSort(sortedArray: MutableList<Long>, insertionSequence: MutableList<Int>, size: Int) {
    for (j in 1 until size) {
        var i = j - 1
        while (i + 1 > 0 && sortedArray[i] > sortedArray[i + 1]) {
            val temp = sortedArray[i]
            sortedArray[i] = sortedArray[i + 1]
            sortedArray[i + 1] = temp
            i--
        }
        insertionSequence.add(i + 2)
    }
}

private fun writeToFile(insertionSequence: List<Number>, sortedArray: List<Number>) {
    val file = File(OUTPUT)
    file.createNewFile()
    BufferedWriter(FileWriter(file)).apply {
        write(getStringFromList(insertionSequence) + "\r\n")
        write(getStringFromList(sortedArray))
        close()
    }
}

private fun getStringFromList(insertionSequence: List<Number>): String {
    val stringBuilder = StringBuilder()
    insertionSequence.forEach { stringBuilder.append("$it ") }
    return stringBuilder.toString().trim()
}
