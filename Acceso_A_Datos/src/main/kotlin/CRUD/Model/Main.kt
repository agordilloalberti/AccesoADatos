package CRUD.Model

import CRUD.Repository.XMLRepository
import java.nio.file.Path

fun main(){
    val ficheroXML = Path.of("C:\\Users\\UsuarioT\\IdeaProjects\\AccesoADatos\\Acceso_A_Datos\\src\\main\\resources\\XML\\CRUD\\XML.xml")

    val e =Empleado("19","Gordillo","IT","1.1")
    val r = XMLRepository()
    //r.insert(e,ficheroXML)
    //r.delete("3",ficheroXML)
}