import model.Customer
import model.TransactionHistory

private val listCustomer = mutableListOf<Customer>()
private val transactionHistory = mutableListOf<TransactionHistory>()
private var isAuth : Boolean = false
private var accountNumber : String? = null
private var accountSecretKey : String? = null

fun appManu() {
    services.AppMenu.show()

    print("Input menu Number: ")
    var menuOption: String? = readLine()
    when (menuOption) {
         "1" -> customerAccount()
         "2" -> transferFund()
         "3" -> listTransactionHistory()
         "4" -> topUp()
        else -> appManu()
    }
}

fun main(args: Array<String>) {
    appManu()
}


fun customerAccount() {
    services.AppMenu.customerMenu()

    print("Input Customer menu: ")
    var customerMenu : String? = readLine()
    when (customerMenu) {
        "1" -> registerAccount()
        "2" -> listAccount()
        "3" -> appManu()
        else -> customerAccount()
    }
}

fun registerAccount() {
    var newCustomer = services.Register.registerAccount()

    listCustomer.add(newCustomer)

    services.CustomerInfo.show(newCustomer)

    customerAccount()
}

fun listAccount() {
    if(isAuth){
        val customerBySecretKey = listCustomer.filter { it.secretkey == accountSecretKey }[0]
        services.CustomerInfo.show(customerBySecretKey)
    } else {
        login()
        listAccount()
    }
    customerAccount()
}

fun transferFund() {
    if(isAuth){
        print("Destiny Account number: ")
        val destinationAccount : String? = readLine()
        print("Amount: ")
        val amount : String? = readLine()

        val isValidAmount = listCustomer.filter { it.Account == accountNumber }[0].Balance

        val customerByAccount = listCustomer.filter { it.Account == destinationAccount }[0]
        if (customerByAccount != null && amount?.toInt()!! <= isValidAmount!! && destinationAccount != accountNumber) {
            listCustomer!!.find { it.Account == destinationAccount }!!.Balance = listCustomer!!.find { it.Account == destinationAccount }!!.Balance?.plus(
                amount!!.toInt()!!
            )
            listCustomer!!.find { it.Account == accountNumber }!!.Balance = listCustomer!!.find { it.Account == accountNumber }!!.Balance?.minus(
                amount!!.toInt()!!
            )
            println("Transfer successfully.")
            val transaction = TransactionHistory(accountNumber, destinationAccount, amount.toInt())
            transactionHistory.add(transaction)
            println("============ Select menu =============" +
                    "\n| 1. See balance                       |" +
                    "\n| 2. Back                              |" +
                    "\n======================================")
            var menu : String? = readLine()
            if(menu == "1") {
                checkBalance()
                appManu()
            } else {
                appManu()
            }
        } else {
            when(accountNumber) {
                destinationAccount -> println("You can't send to your own account.")
                null -> println("Invalid Destination account number.")
                else -> println("Your Amount is insufficient.")
            }
            println("============ Select menu =============" +
                    "\n| 1. Try Again                       |" +
                    "\n| 2. Back                            |" +
                    "\n======================================")
            var menu : String? = readLine()
            if(menu == "1") {
                transferFund()
            } else {
                appManu()
            }
        }
    } else {
        login()
        transferFund()
    }
}
fun checkBalance() {
    println("Your balance is: " + listCustomer!!.find { it.Account == accountNumber }!!.Balance.toString())
}
fun login() {
    println(" Please Log in your account.")
    print("Please input your secret key to log in: ")
    val secretKey : String = readLine()!!
    val customerBySecretKey = listCustomer.filter { it.secretkey == secretKey }[0]
    if (customerBySecretKey == null) {
        println("Sorry your account $secretKey is not exist.")
    } else {
        println("login Successfully.")
        isAuth = true
        accountNumber = customerBySecretKey.Account.toString()
        accountSecretKey = customerBySecretKey.secretkey.toString()
    }
}

fun listTransactionHistory() {
    if(isAuth){
        val accountTransaction = transactionHistory.filter { it.FromAccount == accountNumber || it.ToAccount == accountNumber }

        if(accountTransaction != null) {
            services.HistoryList.show(accountTransaction)
            appManu()
        } else {
            println("You have no transaction yet!")
            appManu()
        }
    } else {
        login()
        listTransactionHistory()
    }
}

fun topUp() {
    if(isAuth) {
        println("How much you want to top up?")
        print("Input Amount: ")
        val amount : String? = readLine()

        listCustomer!!.find { it.Account == accountNumber }!!.Balance = listCustomer!!.find { it.Account == accountNumber }!!.Balance?.plus(
            amount!!.toInt()!!
        )
        println("Top up successfully.")
        checkBalance()
        appManu()
    } else {
        login()
        topUp()
    }
}