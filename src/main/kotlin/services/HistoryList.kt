package services

import model.TransactionHistory

class HistoryList {
    companion object {
        fun show(items: List<TransactionHistory>) {
            print("============ Tran =============")
            for(item in items) {
                println("\n| From Account : ${item.FromAccount}     " +
                        "\n| To Account: ${item.ToAccount}          " +
                        "\n| Amount: ${item.Amount}$                " +
                        "\n======================================")
            }
        }
    }
}