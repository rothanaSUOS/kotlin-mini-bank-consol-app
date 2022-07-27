package model

import java.time.LocalDate
enum class Gender {
    MALE,
    FEMALE
}

data class Customer(
    var name: String?,
    var gender: Gender,
    var dateOfBirth: LocalDate?,
    var account: String?,
    var balance: Int?,
    var secretkey: String?,
    var address: Address
)