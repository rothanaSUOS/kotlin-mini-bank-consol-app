package services

import model.Customer

class CustomerInfo {
    companion object{
        fun show(item : Customer) {
            println("========== Customer info ==========" +
                    "\n| Name: ${item.Name}                   " +
                    "\n| Gender: ${item.Gender}               " +
                    "\n| Date Of Birth: ${item.DateOfBirth}   " +
                    "\n| Account: ${item.Account}           " +
                    "\n| Balance: ${item.Balance}            " +
                    "\n| Address: ${item.Address}           " +
                    "\n==================================="
            )
        }
    }
}