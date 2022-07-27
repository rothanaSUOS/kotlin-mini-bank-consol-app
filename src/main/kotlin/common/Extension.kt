package common

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.regex.Pattern

object Extension {
    fun LocalDate.toString(pattern: String) : String? {
        return this.format(DateTimeFormatter.ofPattern(pattern))
    }
    fun LocalDate.customFormat(pattern: String) : String {
        return this.format(DateTimeFormatter.ofPattern(pattern))
    }
    fun String?.throwIfNull(name: String) : String {
        return  this ?: throw RuntimeException("This variable[$name] is required")
    }
    fun String?.input(name: String): String {
        print(name)
        var data = readLine()
        while (data.isNullOrBlank()) {
            println("This $name is required")
            print(name)
            data = readLine()
        }
        return data
    }
}