package Hibernate.Empleados

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "departamentos")
class Departamento(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val num: Long?,

    @Column(name = "nombre", nullable = false, unique = true)
    val nombre: String,

    @OneToMany(mappedBy = "dpto", cascade = [CascadeType.ALL], orphanRemoval = true)
    val empleados: List<Empleado>? = listOf(),

    @Column(name = "fecha_creación")
    @Temporal(TemporalType.DATE)
    val fechaCreacion: Date
) {

}