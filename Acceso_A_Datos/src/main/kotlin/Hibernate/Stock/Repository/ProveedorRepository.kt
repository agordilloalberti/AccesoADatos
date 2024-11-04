package Hibernate.Stock.Repository

import Hibernate.Stock.Clases.Proveedor
import Hibernate.Stock.Utils

class ProveedorRepository {

    fun createProveedor(nom :String, dir :String){
        val em = Utils.getEntityManager("StockControl")

        em.transaction.begin()

        em.persist(Proveedor(null,nom,dir,null))

        em.transaction.commit()

        em.close()
    }

    fun deleteProveedor(id:Long){

        val em = Utils.getEntityManager("StockControl")

        em.transaction.begin()

        em.transaction.commit()

        em.close()
    }

    fun editProveedor(id:Long,nombre:String,direccion:String):Boolean{

        val em = Utils.getEntityManager("StockControl")

        em.transaction.begin()

        var state = false
        var n = nombre
        var d = direccion
        val p = em.find(Proveedor::class.java,id)

        if (n.isBlank()){
            n=p.nombre
        }

        if (direccion.isBlank()){
            d=p.direccion
        }

        if (p!=null) {
            em.createQuery("UPDATE Proveedores SET Nombre = '$n' AND Dirección = '$d' where Id=$id").executeUpdate()
            state=true
        }
        em.transaction.commit()

        em.close()

        return state
    }

    fun readProveedor(id:Long): Proveedor?{

        val em = Utils.getEntityManager("StockControl")

        em.transaction.begin()

        val p = em.find(Proveedor::class.java,id)

        em.transaction.commit()

        em.close()

        return p
    }
}