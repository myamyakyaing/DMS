package com.tat.dms.vos

data class CustomerData(
    val `data`: String,
    val date: String,
    val password: String,
    val route: Int,
    val site_activation_key: String,
    val tablet_activation_key: String,
    val user_id: String
)