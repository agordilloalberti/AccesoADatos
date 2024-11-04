package Hibernate.Stock.Repository

import Hibernate.Stock.Clases.Usuario

class UsuarioRepository {


    fun getUser(nombreUsuario: String): Usuario {
        // Factory
        // Entitymanager
        // ...
        return Usuario("pepito", "123")
    }
}