package XML.Actividad3

import java.io.BufferedReader
import java.io.FileReader
import javax.xml.parsers.DocumentBuilderFactory

fun main(){
    val fr = FileReader("C:\\Users\\UsuarioT\\IdeaProjects\\AccesoADatos\\Acceso_A_Datos\\src\\main\\resources\\XML\\Actividad 3\\CSV.txt")
    val br = BufferedReader(fr)

    var l: String
    val empleados = arrayListOf<ArrayList<String>>()

    br.use {
        l=br.readLine()
        while (l!=null){
            empleados.add(l.split(",").toMutableList() as ArrayList<String>)
            l=br.readLine()
        }

    }

    val factory = DocumentBuilderFactory.newInstance()

    val builder = factory.newDocumentBuilder()

    val imp = builder.domImplementation

    val document = imp.createDocument(null,"empleados",null)

    val tags = arrayListOf<String>()
    for (s: String in empleados[0]){
        tags.add(s)
    }

    for (i in 1..<empleados.size){
        val empleado = document.createElement("empleado")

        document.documentElement.appendChild(empleado)


    }
}