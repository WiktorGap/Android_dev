import java.io.File
import java.util.Scanner
import kotlin.random.Random

fun main() {
 
    val fileName = "szablon.txt"
    
    val dataArray: Array<String> = File(fileName).readLines().toTypedArray()

 
    println("Zawartość tablicy:")
    dataArray.forEach { println(it) }

    val scanner = Scanner(System.`in`)

    print("Podaj liczbę słów: ")
    val numOfWords = scanner.nextInt()
    scanner.nextLine() 

    val wordsList = dataArray.map { parseLine(it) }.toMutableList()
    val wordsPool = weightedRandomSelection(wordsList, numOfWords).toMutableList()

    while (wordsPool.isNotEmpty()) {
        val word = wordsPool.random()
        println("Podaj poprawną literę dla: ${word.wordWithPlaceholder} (Opcje: ${word.choices.joinToString("/")})")
        val userAnswer = scanner.nextLine().trim()

        word.totalAttempts++
        if (userAnswer == word.correctLetter) {
            word.correctAttempts++
            wordsPool.remove(word)
        } else {
            word.correctAttempts++ 
            println("Błąd! Poprawna odpowiedź to: ${word.correctLetter}")
        }
    }

    File(fileName).writeText(wordsList.joinToString("\n") { formatLine(it) })
    println("Wszystkie słowa powtórzone! Dane zostały zapisane do pliku.")
}

data class WordEntry(
    var wordWithPlaceholder: String,
    val choices: List<String>,
    var correctLetter: String,
    var totalAttempts: Int,
    var correctAttempts: Int
)

fun parseLine(line: String): WordEntry {
    val parts = line.split(";")
    return WordEntry(
        parts[0],
        parts[1].split("/"),
        parts[2],
        parts[3].toInt(),
        parts[4].toInt()
    )
}

fun formatLine(entry: WordEntry): String {
    return "${entry.wordWithPlaceholder};${entry.choices.joinToString("/")};${entry.correctLetter};${entry.totalAttempts};${entry.correctAttempts}"
}

fun weightedRandomSelection(words: List<WordEntry>, count: Int): List<WordEntry> {
    val weightedWords = words.flatMap { word ->
        val weight = 1.0 - (word.correctAttempts.toDouble() / (word.totalAttempts + 1)) 
        List((weight * 100).toInt().coerceAtLeast(1)) { word }
    }
    return weightedWords.shuffled().take(count)
}
