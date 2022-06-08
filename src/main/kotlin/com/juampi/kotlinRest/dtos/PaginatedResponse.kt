package com.juampi.kotlinRest.dtos

import com.juampi.kotlinRest.models.Product

class PaginatedResponse(
    //val no modficables, var si modificables
    var data: List<Product>,
    var total: Int,
    var page: Int,
    var last_page: Int,

){
    init {

        for (product in this.data){
            product.description="Modified description"
        }
    }
}