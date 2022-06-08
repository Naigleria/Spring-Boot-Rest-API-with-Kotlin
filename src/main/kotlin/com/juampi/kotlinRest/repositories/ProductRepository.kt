package com.juampi.kotlinRest.repositories

import com.juampi.kotlinRest.models.Product
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface ProductRepository : JpaRepository<Product,Int> {
    //@Query("select * from  product where  title like %?1% or description like %?1%", nativeQuery = true)
    //fun search(s:String, sort: Sort):List<Product>

    //@Query("select p from  Product p where  p.title like %?1% or p.description like %?1%")
    //fun search(s:String, sort: Sort):List<Product>

    @Query("select p from  Product p where  p.title like %?1% or p.description like %?1%")
    fun search(s:String, pageable: Pageable):List<Product>

    @Query("select COUNT(p) from  Product p where  p.title like %?1% or p.description like %?1%", countQuery = "")
    fun countSearch(s:String):Int

    @Query("{call getDescriptionProductById(:productId)}", nativeQuery = true)
    fun productDescription(productId:Int):String
}