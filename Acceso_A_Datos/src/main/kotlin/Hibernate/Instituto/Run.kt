package Hibernate.Instituto

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import java.time.Instant

fun main(){
    val emf: EntityManagerFactory = Persistence.createEntityManagerFactory("unidadMySQL")
    val em : EntityManager = emf.createEntityManager()

    em.transaction.begin()

    em.transaction.commit()

    em.close()
}