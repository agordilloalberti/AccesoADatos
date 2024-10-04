package XML.Leer_XML

import org.w3c.dom.Element
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilderFactory
import java.nio.file.Path

fun main(){

        val listaEmpleados = empleados("XML/Leer/empleados.xml")

        for (e: Empleado in listaEmpleados){
            println(e.apellido+" "+e.dep+" "+e.salario)
        }
    }

    fun empleados(nombreFich: String): List<Empleado>{
        val dbf = DocumentBuilderFactory.newInstance()

        val db = dbf.newDocumentBuilder()

        val ficheroXML = Path.of("src").resolve("main/resources/$nombreFich")

        val document = db.parse(ficheroXML.toFile())

        val root: Element = document.documentElement

        root.normalize()

        val listNodos = root.getElementsByTagName("empleado")

        val lista = mutableListOf<Empleado>()

        for (i in 0..<listNodos.length){
            var nodo = listNodos.item(i)
            var apellido: String = ""
            var departamento: Int = 0
            var salario: Double = 0.0
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
                        departamento = node.textContent.toInt()
                    }
                }
                val nsalario = nodo.getElementsByTagName("salario")
                for (j in 0..<nsalario.length) {
                    val node = nsalario.item(j)
                    if (node.nodeType == Node.ELEMENT_NODE) {
                        salario = node.textContent.toDouble()
                    }
                }

            }
            lista.add(Empleado(apellido,departamento,salario))
    }
        return lista
}