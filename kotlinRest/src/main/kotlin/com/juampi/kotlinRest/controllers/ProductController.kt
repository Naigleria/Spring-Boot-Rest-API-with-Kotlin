package com.juampi.kotlinRest.controllers

import com.juampi.kotlinRest.dtos.PaginatedResponse
import com.juampi.kotlinRest.models.Product
import com.juampi.kotlinRest.repositories.ProductRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class ProductController(private val productRepository: ProductRepository) {

    @GetMapping("products/frontend")
    fun frontend() : ResponseEntity<List<Product>>{
        return ResponseEntity.ok(
            this.productRepository.findAll()

        )
    }

    @GetMapping("products/backend")
    fun backend(
        @RequestParam("s", defaultValue = "") s:String,
        @RequestParam("sort", defaultValue = "") sort :String,
        @RequestParam("page", defaultValue = "1") page :Int
    ) : ResponseEntity<Any>{
        var direction= Sort.unsorted()

        if(sort=="asc"){
            direction=Sort.by(Sort.Direction.ASC, "price")
        }else if(sort=="desc"){
            direction=Sort.by(Sort.Direction.DESC, "price")
        }
        val perPage=9
        val total= this.productRepository.countSearch(s)
        return ResponseEntity.ok(
            PaginatedResponse(
                data=this.productRepository.search(s,PageRequest.of(page-1, perPage, direction)),
                total,
                page,
                last_page = (total/perPage)+1
            )
        )
    }

    @GetMapping("/products/getProductDescriptionById")
    fun getProductDescriptionById(
        @RequestParam("productId") productId:Int,
    ):ResponseEntity<Any> {
        //return ResponseEntity.ok(product_description=this.productRepository.productDescription(productId))
        return ResponseEntity.ok(this.productRepository.productDescription(productId))
    }
}