package services

import common.Extension.input
import common.Extension.toString
import model.Address
import model.Customer
import model.Gender
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Register {
    companion object {
        private val dateFormat : DateTimeFormatter? = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        fun registerAccount(): Customer {
            println("Please input customer info: ")

            val name = services.InputField.input("Name: ")
            var gender = services.InputField.input("Gender=> MALE or FEMALE: ")?.uppercase().toString()
            while (Gender.values().find { it.name == gender } == null){
                println("Your gender out of this world! Choose one in this world.")
                gender = services.InputField.input("Gender=> MALE or FEMALE: ")?.uppercase().toString()
            }
            var dateOfBirth = services.InputField.input("Date of birth(dd-MM-yyyy): ").toString()
            while (!isValidDate(dateOfBirth)){
                println("Your date of birth is invalid format!")
                dateOfBirth = services.InputField.input("Date of birth(dd-MM-yyyy): ").toString()
            }
            var validBd = LocalDate.parse(dateOfBirth, dateFormat)

            var account = services.InputField.input("Account: ")
            var balance = services.InputField.input("Balance: ")?.toIntOrNull()
            while (balance == null || balance < 0){
                println("Invalid balance! please input again")
                balance = services.InputField.input("Balance: ")?.toIntOrNull()
            }
            var secretKey = services.InputField.input("Secret Key: ")
            println("========== Customer Address ==========")
            var streetNumber = services.InputField.input("Street Number:")
            var unitNumber = services.InputField.input("Unit Number: ")
            var district = services.InputField.input("District: ")
            var commune = services.InputField.input("Commune: ")
            var city = services.InputField.input("City: ")

            var address = Address(streetNumber, unitNumber, district, commune, city)

            return Customer(name, Gender.valueOf(gender), validBd, account?.filterNot { it.isWhitespace() }, balance?.toInt(), secretKey, address)
        }

        private fun isValidDate(DB: String): Boolean {
            try {
                dateFormat?.parse(DB.trim())
            } catch(error: Exception) {
                return false
            }
            return true
        }
    }
}
