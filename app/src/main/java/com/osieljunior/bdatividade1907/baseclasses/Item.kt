package com.osieljunior.bdatividade1907.baseclasses

data class Item(
    val cnpj: String = "",
    val name: String = "",
    val email: String = "",
    val imageUrl: String = "",
    val endereco: String = "",
    val userId: String = "",
    val latitude: String = "",
    val longitude: String = "",
    var distancia: String = ""
)

