package Hibernate.Empleados

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import java.time.Instant
import java.util.Date

fun main(){

    val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("unidadMySQL")
    var em : EntityManager = emf.createEntityManager()

    em.transaction.begin()

    val dep = Departamento(null,"Informatica",null,Date.from(Instant.now()))

    val e = Empleado(null,"Pepe",39, dep)

    val e2 = Empleado(null,"Juan",29,dep)

    em.persist(e)
    em.persist(e2)

    em.transaction.commit()

    em.close()


    /*em=emf.createEntityManager()

    em.transaction.begin()

    val e2 = em.find(Empleado::class.java, "1")
    println(e2)

    em.transaction.commit()
    em.close()*/
}