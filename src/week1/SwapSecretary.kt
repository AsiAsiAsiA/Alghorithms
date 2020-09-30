package week1

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.*

private const val INPUT = "input.txt"
private const val OUTPUT = "output.txt"

private val swapDescriptions = mutableListOf<String>()

fun main() {
    val scanner = Scanner(File(INPUT))

    val size = scanner.nextInt()
    val sortedArray = mutableListOf<Long>()

    while (scanner.hasNext()) {
        sortedArray.add(scanner.nextLong())
    }
    choosingSort(sortedArray, size)
    swapDescriptions.add("No more swaps needed.")
    writeToFile(swapDescriptions)
}

//Реализация сортировки выбором
private fun choosingSort(sortedArray: MutableList<Long>, size: Int) {
    var minValue = sortedArray.min()
    var i = 0 //Количество отсортированных позиций
    while (i < size && minValue != null) {
        getMinimalValuePositions(minValue, sortedArray).forEach { position ->
            swap(sortedArray, i, position)
            i++
        }
        minValue = sortedArray.subList(i, size).min()
    }
}

private fun swap(sortedArray: MutableList<Long>, i: Int, position: Int) {
    if (sortedArray[i] != sortedArray[position]) {
        val temp = sortedArray[i]
        sortedArray[i] = sortedArray[position]
        sortedArray[position] = temp

        swapDescriptions.add("Swap elements at indices ${i + 1} and ${position + 1}.\r\n")
    }
}

private fun getMinimalValuePositions(value: Long, sortedArray: MutableList<Long>): List<Int> {
    val results = mutableListOf<Int>()
    sortedArray.forEachIndexed { index, number -> if (value == number) results.add(index) }
    return results
}

private fun writeToFile(swapDescriptions: MutableList<String>) {
    val file = File(OUTPUT)
    file.createNewFile()
    BufferedWriter(FileWriter(file)).apply {
        swapDescriptions.forEach {
            write(it)
        }
        close()
    }
}
