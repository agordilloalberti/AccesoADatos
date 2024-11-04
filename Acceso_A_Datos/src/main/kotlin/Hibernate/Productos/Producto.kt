package Hibernate.Productos

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name="Producto")
class Producto(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(name="nombre")
    val nombre:String,

    @Column
    val descripción:String?,

    @Column
    val precio: Double,

    @Column
    val fecha_alta: Date,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "id_proveedor")
    val proveedor: Proveedor
) {
}