package Hibernate.Stock.Clases

import jakarta.persistence.*

@Entity
data class Usuario(
    val nombreUsuario : String,
    val password : String,
) {


}