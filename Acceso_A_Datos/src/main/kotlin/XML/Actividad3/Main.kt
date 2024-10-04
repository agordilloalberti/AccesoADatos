package XML.Actividad3

import java.io.BufferedReader
import java.io.FileReader
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

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

        val id = document.createElement(tags[0])
        val apellido = document.createElement(tags[1])
        val departamento = document.createElement(tags[2])
        val salario = document.createElement(tags[3])

        val textId = document.createTextNode(empleados[i][0])
        val textApel = document.createTextNode(empleados[i][1])
        val textDep = document.createTextNode(empleados[i][2])
        val textSal = document.createTextNode(empleados[i][3])

        id.appendChild(textId)
        apellido.appendChild(textApel)
        departamento.appendChild(textDep)
        salario.appendChild(textSal)

        empleado.appendChild(id)
        empleado.appendChild(apellido)
        empleado.appendChild(departamento)
        empleado.appendChild(salario)

    }

    val source = DOMSource(document)

    val r = StreamResult(Path.of("C:\\Users\\UsuarioT\\IdeaProjects\\AccesoADatos\\Acceso_A_Datos\\src\\main\\resources\\XML\\Actividad 3\\empleados.xml").toFile())

    val optimus = TransformerFactory.newInstance().newTransformer()

    optimus.setOutputProperty(OutputKeys.INDENT, "yes")

    optimus.transform(source,r)
}