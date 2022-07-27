package services

import common.Extension.toString
import model.Customer

class CustomerInfo {
    companion object{
        fun show(item : Customer) {
            println("========== Customer info ==========" +
                    "\n| Name: ${item.name}                   " +
                    "\n| Gender: ${item.gender}               " +
                    "\n| Date Of Birth: ${item.dateOfBirth?.toString(pattern = "dd-MM-yyyy")}   " +
                    "\n| Account: ${item.account}           " +
                    "\n| Balance: ${item.balance}            " +
                    "\n| Address: ${item.address}           " +
                    "\n==================================="
            )
        }
    }
}