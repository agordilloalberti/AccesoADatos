package Hibernate.Stock.Clases

import jakarta.persistence.*

@Entity
@Table(name = "Proveedores")
class Proveedor(

    @Column(name = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long?,

    @Column(name = "Nombre")
    val nombre:String,

    @Column(name = "Dirección")
    val direccion : String,

    @Column(name = "Productos")
    @OneToMany(mappedBy = "proveedor", cascade = [CascadeType.ALL])
    val productos: List<Producto>?
) {
}