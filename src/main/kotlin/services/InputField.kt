package services

class InputField {
    companion object {
        fun input(title : String): String? {
            print(title)
            var value : String? = readLine()
            while (value.isNullOrBlank()){
                println("This $title is repaired")
                print(title)
                value = readLine()
            }
            return value
        }
    }
}