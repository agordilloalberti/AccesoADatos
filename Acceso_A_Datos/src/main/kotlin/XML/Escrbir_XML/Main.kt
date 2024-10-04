package XML.Escrbir_XML

import Ayudas
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.Text
import java.math.BigDecimal
import java.math.RoundingMode
import java.nio.file.Path
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

fun main() {
    val dbf = DocumentBuilderFactory.newInstance()

    val db = dbf.newDocumentBuilder()

    val ficheroXML = Path.of("src").resolve("main\\resources\\Escribir\\Escritura.xml")

    val document = db.parse(ficheroXML.toFile())

    val root: Element = document.documentElement

    root.normalize()

    val listNodos = root.getElementsByTagName("producto")

    val nombresA = arrayListOf<String>()
    val preciosA = arrayListOf<String>()

    for (i in 0..<listNodos.length) {
        val nodoProducto = listNodos.item(i)
        if (nodoProducto.nodeType == Node.ELEMENT_NODE) {

            nodoProducto as Element
            val nombres = nodoProducto.getElementsByTagName("nombre")
            val precios = nodoProducto.getElementsByTagName("precio")

            for (j in 0..<nombres.length){
                val nodoNombre = nombres.item(j)

                if (nodoNombre.nodeType == Node.ELEMENT_NODE){
                    nombresA.add(nodoNombre.textContent)
                }

            }

            for (j in 0..<precios.length){
                val nodoPrecio = precios.item(j)

                if (nodoPrecio.nodeType == Node.ELEMENT_NODE){
                    var num = nodoPrecio.textContent.toDouble()
                    num = Ayudas.round(num,2)
                    println(num)
                    preciosA.add(num.toString())
                }

            }
        }


    }
    crearXML(nombresA.size,nombresA,preciosA)
}

fun crearXML(nodos: Int, nombresA: ArrayList<String>, preciosA: ArrayList<String>){
    val factory = DocumentBuilderFactory.newInstance()

    val builder = factory.newDocumentBuilder()

    val imp = builder.domImplementation

    val document = imp.createDocument(null,"productos",null)


    for (i in 0..<nodos) {
        val producto = document.createElement("producto")

        document.documentElement.appendChild(producto)

        val nomb = document.createElement("nombre")
        val prec = document.createElement("precio")

        val textoNom: Text = document.createTextNode(nombresA[i])
        val textoprec: Text = document.createTextNode(preciosA[i])

        nomb.appendChild(textoNom)
        prec.appendChild(textoprec)

        producto.appendChild(nomb)
        producto.appendChild(prec)

    }

    val source = DOMSource(document)

    val r = StreamResult(Path.of("C:\\Users\\UsuarioT\\IdeaProjects\\XML\\src\\main\\resources\\Escribir\\Escritura.xml").toFile())

    val optimus = TransformerFactory.newInstance().newTransformer()

    optimus.setOutputProperty(OutputKeys.INDENT,"yes")

    optimus.transform(source,r)
}