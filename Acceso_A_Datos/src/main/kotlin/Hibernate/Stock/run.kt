package Hibernate.Stock

import Hibernate.Stock.Repository.UsuarioRepository

fun main(){
    println("Usuario")
    val user: String = readln()
    println("Passw")
    val passwod: String



    val repo = UsuarioRepository()
    val usuarioBD = repo.getUser(user)

    if(usuarioBD.nombreUsuario == user && usuarioBD.password == passwod) {
        println("Exito")
    }



}