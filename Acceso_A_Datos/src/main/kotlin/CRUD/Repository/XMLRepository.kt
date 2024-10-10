package CRUD.Repository

import CRUD.Model.Empleado
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import kotlin.io.path.notExists

class XMLRepository {

    fun insert(e:Empleado,rutaXML: Path){
        if (rutaXML.notExists()){
            throw Exception("Ruta xml no existe")
        }

        if (e.id.isBlank() || e.apellido.isBlank() || e.dpto.isBlank()){
            throw Exception("Atributos incorrectos")
        }

        val document = parseXML(rutaXML)
        val root: Element = document.documentElement
        root.normalize()

        val nEmpleado = document.createElement("empleado")
        nEmpleado.setAttribute("id",e.id)

        root.appendChild(nEmpleado)

        val eApellido = document.createElement("apellido")
        val eDpto = document.createElement("dpto")
        val eSalario = document.createElement("salario")

        val textApellido = document.createTextNode(e.apellido)
        val textDpto = document.createTextNode(e.dpto)
        val textSalario = document.createTextNode(e.salario.toString())

        eApellido.appendChild(textApellido)
        eDpto.appendChild(textDpto)
        eSalario.appendChild(textSalario)

        nEmpleado.appendChild(eApellido)
        nEmpleado.appendChild(eDpto)
        nEmpleado.appendChild(eSalario)

        val source =DOMSource(document)
        val result = StreamResult(rutaXML.toFile())
        val optimus = TransformerFactory.newInstance().newTransformer()
        optimus.setOutputProperty(OutputKeys.INDENT, "yes")
        optimus.transform(source,result)
    }

    private fun parseXML(rutaXML: Path):Document{
        val dbf = DocumentBuilderFactory.newInstance()
        val db = dbf.newDocumentBuilder()
        return db.parse(rutaXML.toFile())
    }

    fun delete(id:String,rutaXML: Path){
        if (rutaXML.notExists()){
            throw Exception("Ruta xml no existe")
        }
        val document = parseXML(rutaXML)

        val root: Element = document.documentElement

        root.normalize()
        val listNodos = root.getElementsByTagName("empleado")

        val lista = mutableListOf<Empleado>()

        for (i in 0..<listNodos.length){
            var nodo = listNodos.item(i)
            var apellido= ""
            var departamento=""
            var salario=""
            if (nodo.nodeType == Node.ELEMENT_NODE) {
                nodo = nodo as Element
                val napellido = nodo.getElementsByTagName("apellido")
                for (j in 0..<napellido.length) {
                    val node = napellido.item(j)
                    if (node.nodeType == Node.ELEMENT_NODE) {
                        apellido = node.textContent
                    }
                }
                val ndepartamento = nodo.getElementsByTagName("dep")
                for (j in 0..<ndepartamento.length) {
                    val node = ndepartamento.item(j)
                    if (node.nodeType == Node.ELEMENT_NODE) {
                        departamento = node.textContent
                    }
                }
                val nsalario = nodo.getElementsByTagName("salario")
                for (j in 0..<nsalario.length) {
                    val node = nsalario.item(j)
                    if (node.nodeType == Node.ELEMENT_NODE) {
                        salario = node.textContent
                    }
                }

            }
            lista.add(Empleado(id,apellido, departamento, salario))
        }
        for (e in lista){
            if (e.id==id){
                lista.remove(e)
                break
            }
        }
    }

    fun create(rutaXML: Path,empleados:MutableList<Empleado>){
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

        val r = StreamResult(Path.of("C:\\Users\\UsuarioT\\IdeaProjects\\AccesoADatos\\Acceso_A_Datos\\src\\main\\resources\\XML\\CRUD\\XML.xml").toFile())

        val optimus = TransformerFactory.newInstance().newTransformer()

        optimus.setOutputProperty(OutputKeys.INDENT, "yes")

        optimus.transform(source,r)
    }
}