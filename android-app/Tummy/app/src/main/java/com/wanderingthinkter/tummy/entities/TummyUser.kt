package com.wanderingthinkter.tummy.entities

import com.wanderingthinkter.tummy.appfactorylib.CustomDataTypes.*

data class TummyUser(val username: String, val firstName: String, val lastName: String, val password: String
                     , val roles: Set<Roles>, val dob: String, val nationality: String) {

    var userId: String? = null
    var joinedDate = ""

    fun isMandatoryFieldsPresent() : Boolean {
        return username.isNotBlank() && password.isNotBlank() && firstName.isNotBlank() && lastName.isNotBlank()
                && nationality.isNotBlank() && dob.isNotBlank()
    }

    fun isPasswordsMatching(pwd: String): Boolean {
        println("Pwd: $pwd")
        println("Password: $password")
        return pwd == password
    }
}