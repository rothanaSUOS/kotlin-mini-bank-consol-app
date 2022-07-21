package model

import java.time.LocalDate
enum class Gender {
    MALE,
    FEMALE
}

data class Customer(
    var Name: String?,
    var Gender: Gender,
    var DateOfBirth: LocalDate?,
    var Account: String?,
    var Balance: Int?,
    var secretkey: String?,
    var Address: Address
)