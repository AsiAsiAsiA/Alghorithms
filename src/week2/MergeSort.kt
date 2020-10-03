package week2

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.*

private const val INPUT = "input.txt"
private const val OUTPUT = "output.txt"

private val file = File(OUTPUT)
private val writer = BufferedWriter(FileWriter(file))
private var rawList = mutableListOf<Int>()
private val sortedArray = mutableListOf<Int>()

fun main() {
    file.createNewFile()

    val scanner = Scanner(File(INPUT))
    scanner.nextInt()

    while (scanner.hasNext()) {
        val number = scanner.nextInt()
        rawList.add(number)
        sortedArray.add(number)
    }

    writeToFile(getStringFromList(mergeSort()))
    writer.close()
}

//Реализация сортировки слиянием
private fun mergeSort(): MutableList<Int> {
    var step = 1
    val size = rawList.size
    while (size > step) {

        var startIndex = 0
        var endIndex = startIndex + step

        while (endIndex <= size) {
            //Влазят 2 массива одинакового размера
            val firstArray = rawList.subList(startIndex, endIndex)

            if (endIndex == size) {
                firstArray.forEachIndexed { index, value -> sortedArray[index + startIndex] = value }
            } else {
                val endSecondArray = if (endIndex + step < size) endIndex + step else size
                val secondArray = rawList.subList(endIndex, endSecondArray)
                mergeArrays(firstArray, secondArray).forEachIndexed { index, value -> sortedArray[index + startIndex] = value }
                writeToFile("${startIndex + 1} $endSecondArray ${sortedArray[startIndex]} ${sortedArray[endSecondArray - 1]}\r\n")
            }

            startIndex += step * 2
            endIndex = startIndex + step
        }

        rawList = sortedArray.toMutableList()
        step *= 2
    }

    return sortedArray
}

//Слияние массивов
private fun mergeArrays(firstArray: MutableList<Int>, secondArray: MutableList<Int>): MutableList<Int> {
    var i = 0
    var j = 0
    val firstSize = firstArray.size
    val secondSize = secondArray.size
    val mergedArray = mutableListOf<Int>()

    while (i < firstSize || j < secondSize) {
        if (j == secondSize || (i < firstSize && firstArray[i] <= secondArray[j])) {
            mergedArray.add(firstArray[i])
            i++
        } else {
            mergedArray.add(secondArray[j])
            j++
        }
    }
    return mergedArray
}

private fun writeToFile(line: String): Unit = writer.write(line)

private fun getStringFromList(insertionSequence: List<Number>): String {
    val stringBuilder = StringBuilder()
    insertionSequence.forEach { stringBuilder.append("$it ") }
    return stringBuilder.toString().trim()
}