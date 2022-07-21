package services

class AppMenu {
    companion object{
        fun show() {
            println(
                "========== Welcome to Mini Bank App ==========" +
                "\n| 1. Customer Accounts                       |" +
                "\n| 2. Transfer Fund                           |" +
                "\n| 3. Transfer History                        |" +
                "\n| 4. Top Up                                  |" +
                "\n=============================================="
            )
        }
        fun customerMenu() {
            println(
                "============= Customer Account Menu ============" +
                        "\n| 1. Register Account                        |" +
                        "\n| 2. List Account                            |" +
                        "\n| 3. Back                                    |" +
                        "\n=============================================="
            )
        }
    }

}