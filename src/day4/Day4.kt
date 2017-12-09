package day4

fun main(args: Array<String>) {
    println("*** Part One ***")
    println("""aa bb cc dd ee:  """ + validPassword("""aa bb cc dd ee"""))
    println("""aa bb cc dd aa:  """ + validPassword("""aa bb cc dd aa"""))
    println("""aa bb cc dd aaa: """ + validPassword("""aa bb cc dd aaa"""))

    var validPasswords = input.lines().sumBy { if (validPassword(it)) 1 else 0 }
    println("Valid Passwords: $validPasswords")

    println("*** Part Two ***")
    println("""abcde fghij: """ + validPasswordAnagram("""abcde fghij"""))
    println("""abcde xyz ecdab: """ + validPasswordAnagram("""abcde xyz ecdab"""))
    println("""a ab abc abd abf abj: """ + validPasswordAnagram("""a ab abc abd abf abj"""))
    println("""iiii oiii ooii oooi oooo: """ + validPasswordAnagram("""iiii oiii ooii oooi oooo"""))
    println("""oiii ioii iioi iiio: """ + validPasswordAnagram("""oiii ioii iioi iiio"""))

    validPasswords = input.lines().sumBy { if (validPasswordAnagram(it)) 1 else 0 }
    println("Valid Passwords Anagram: $validPasswords")
}

fun validPasswordAnagram(passwords: String): Boolean {
    val words: List<String> = passwords.split(" ")
    val sortedWords: List<String> = words.map({
        val temp = it.toCharArray()
        temp.sort()
        String(temp)
    })
    return sortedWords.size == sortedWords.distinct().size
}

fun validPassword(passwords: String): Boolean {
    val words: List<String> = passwords.split(" ")
    return words.size == words.distinct().size

}