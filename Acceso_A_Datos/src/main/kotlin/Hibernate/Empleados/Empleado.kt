package Hibernate.Empleados

import jakarta.persistence.*

@Entity
@Table(name="Empleados")
class Empleado(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(name="nombre")
    val nombre:String,

    @Column
    val edad:Int,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "id_departamento")
    val dpto: Departamento
) {
}