package services

class InputField {
    companion object {
        fun input(title : String): String? {
            print(title)
            var value : String? = readLine()
            return value
        }
    }
}