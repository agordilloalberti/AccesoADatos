package Hibernate.Stock.Clases

import jakarta.persistence.*
import java.time.Instant
import java.util.Date

@Entity
@Table(name = "Productos")
data class Producto(

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long?,

    @Column(name = "Nombre")
    var nombre : String,

    @Column(name = "Descripción")
    val categoria : String,

    @Column(name = "Descripción")
    val descripcion : String,

    @Column(name = "Precio sin IVA")
    val precioSinIva : Float,

    @Column(name = "Precio con IVA")
    val precioConIva: Float,

    @Column(name = "Fecha de alta")
    val fechaAlta : Date,

    @Column(name = "Stock")
    val stock : Int,

    @ManyToOne
    @JoinColumn(name = "Id_proveedor")
    val proveedor: Proveedor?
) {
}