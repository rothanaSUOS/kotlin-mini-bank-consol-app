package services

import model.TransactionHistory

class HistoryList {
    companion object {
        fun show(items: List<TransactionHistory>) {
            print("============ Tran =============")
            for(item in items) {
                println("\n| From Account : ${item.fromAccount}     " +
                        "\n| To Account: ${item.toAccount}          " +
                        "\n| Amount: ${item.amount}$                " +
                        "\n======================================")
            }
        }
    }
}