package Hibernate.Productos

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import java.time.Instant
import java.util.*

fun main(){
    val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("unidadMySQL")
    var em : EntityManager = emf.createEntityManager()

    em.transaction.begin()

    val prov = Proveedor(null,"Jonathan",null)

    val p1 = Producto(null,"Pan",null,1.25, Date.from(Instant.now()),prov)
    val p2 = Producto(null,"azucar",null,2.34, Date.from(Instant.now()),prov)

    em.persist(p1)
    em.persist(p2)

    em.transaction.commit()

    em.close()
}