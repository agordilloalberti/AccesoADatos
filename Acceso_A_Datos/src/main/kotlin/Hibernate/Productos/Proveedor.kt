package Hibernate.Productos

import jakarta.persistence.*

@Entity
@Table(name = "Proveedor")
class Proveedor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(name = "nombre", nullable = false, unique = true)
    val nombre: String,

    @OneToMany(mappedBy = "proveedor", cascade = [CascadeType.ALL], orphanRemoval = true)
    val empleados: List<Producto>? = listOf()
) {

}