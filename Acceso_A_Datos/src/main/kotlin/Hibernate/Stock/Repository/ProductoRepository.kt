package Hibernate.Stock.Repository

import Hibernate.Stock.Clases.Producto
import java.time.Instant
import java.util.*

class ProductoRepository {
    val productos = mutableListOf<Producto>()

    fun createProducto(nom :String,cat :String,des :String,prec :Float){

        productos.add(Producto(null,nom,cat,des,prec,prec*1.21.toFloat(), Date.from(Instant.now()),0,null))
    }

    fun deleteProducto(id:Long){
        productos.remove(productos[id.toInt()])
    }

    fun editProducto(id:Long){

    }

    fun readProducto(id:Long){

    }
}