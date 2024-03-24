package org.example.task.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank
import org.example.task.validator.NameRestriction

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @NotBlank(message = "Username cannot be empty")
    @NameRestriction("Root")
    var username: String,
    @NotBlank(message = "Password cannot be empty")
    var password: String
) {
    constructor(username: String, password: String) : this(null, username, password)
    constructor() : this(null, "", "")

}