import model.Customer
import model.TransactionHistory

private val listCustomer = mutableListOf<Customer>()
private val transactionHistory = mutableListOf<TransactionHistory>()
private var isAuth : Boolean = false
private var accountNumber : String? = null
private var accountSecretKey : String? = null

fun appManu() {
    services.AppMenu.show()
    when (services.InputField.input("Input Customer menu: ")) {
         "1" -> customerAccount()
         "2" -> transferFund()
         "3" -> listTransactionHistory()
         "4" -> topUp()
        else -> appManu()
    }
}

fun main() {
    appManu()
}


fun customerAccount() {
    services.AppMenu.customerMenu()
    when (services.InputField.input("Input Customer menu: ")) {
        "1" -> registerAccount()
        "2" -> listAccount()
        "3" -> appManu()
        else -> customerAccount()
    }
}

fun registerAccount() {
    val newCustomer = services.Register.registerAccount()

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
        val destinationAccount = services.InputField.input("Destiny Account number: ")
        val amount = services.InputField.input("Amount: ")
        val customerByAccount = listCustomer.filter { it.account == destinationAccount }

        val isValidAmount = if(customerByAccount.isNotEmpty()) {
            listCustomer.filter { it.account == accountNumber }[0].balance
        } else {
            0
        }

        if (customerByAccount.isNotEmpty() && (amount?.toInt()!! <= isValidAmount!!) && (destinationAccount != accountNumber)) {
            listCustomer.find { it.account == destinationAccount }!!.balance = listCustomer.find { it.account == destinationAccount }!!.balance?.plus(
                amount.toInt()
            )
            listCustomer.find { it.account == accountNumber }!!.balance = listCustomer.find { it.account == accountNumber }!!.balance?.minus(
                amount.toInt()
            )
            println("Transfer successfully.")
            val transaction = TransactionHistory(accountNumber, destinationAccount, amount.toInt())
            transactionHistory.add(transaction)
            println("============ Select menu =============" +
                    "\n| 1. See balance                       |" +
                    "\n| 2. Back                              |" +
                    "\n======================================")

            if(services.InputField.input("Input Customer menu: ") == "1") {
                checkBalance()
                appManu()
            } else {
                appManu()
            }
        } else {
            when {
                accountNumber == destinationAccount -> println("You can't send to your own account.")
                accountNumber == null -> println("Invalid Destination account number.")
                customerByAccount.isNotEmpty() -> println("Your Amount is insufficient.")
            }
            println("============ Select menu =============" +
                    "\n| 1. Try Again                       |" +
                    "\n| 2. Back                            |" +
                    "\n======================================")
            if(services.InputField.input("Input number menu: ") == "1") {
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
    println("Your balance is: " + listCustomer.find { it.account == accountNumber }!!.balance.toString())
}
fun login() {
    println("Please Log in your account.")
    val secretKey = services.InputField.input("Please input your secret key to log in: ")
    val customerBySecretKey = listCustomer.filter { it.secretkey == secretKey }
    if (customerBySecretKey.isEmpty()) {
        println("Sorry your account $secretKey is not exist.")
    } else {
        println("login Successfully.")
        isAuth = true
        accountNumber = customerBySecretKey[0].account.toString()
        accountSecretKey = customerBySecretKey[0].secretkey.toString()
    }
}

fun listTransactionHistory() {
    if(isAuth){
        val accountTransaction = transactionHistory.filter { it.fromAccount == accountNumber || it.toAccount == accountNumber }

        if(accountTransaction.isNotEmpty()) {
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
        val amount = services.InputField.input("Input Amount: ")

        listCustomer.find { it.account == accountNumber }!!.balance = listCustomer.find { it.account == accountNumber }!!.balance?.plus(
            amount!!.toInt()
        )
        println("Top up successfully.")
        checkBalance()
        appManu()
    } else {
        login()
        topUp()
    }
}