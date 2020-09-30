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
    val sortedArray = mutableListOf<Double>()
    val rawArray = mutableListOf<Double>()

    while (scanner.hasNext()) {
        scanner.next().toDouble().let {
            rawArray.add(it)
            sortedArray.add(it)
        }
    }
    insertionSort(sortedArray, size)

    val min = rawArray.indexOf(sortedArray[0]) + 1
    val middle = rawArray.indexOf(sortedArray[size / 2]) + 1
    val max = rawArray.indexOf(sortedArray[size - 1]) + 1
    writeToFile(min, middle, max)
}

//Реализация сортировки вставками
private fun insertionSort(sortedArray: MutableList<Double>, size: Int) {
    for (j in 1 until size) {
        var i = j - 1
        while (i + 1 > 0 && sortedArray[i] > sortedArray[i + 1]) {
            val temp = sortedArray[i]
            sortedArray[i] = sortedArray[i + 1]
            sortedArray[i + 1] = temp
            i--
        }
    }
}

private fun writeToFile(min: Int, middle: Int, max: Int) {
    val file = File(OUTPUT)
    file.createNewFile()
    BufferedWriter(FileWriter(file)).apply {
        write("$min $middle $max")
        close()
    }
}

private fun getStringFromList(insertionSequence: List<Number>): String {
    val stringBuilder = StringBuilder()
    insertionSequence.forEach { stringBuilder.append("$it ") }
    return stringBuilder.toString().trim()
}
