package me.sgayazov.door2door.domain

data class CarSharingProperty(
        val address: String,
        val model: String,
        val license_plate: String,
        val fuel_level: Int,
        val engine_type: String,
        val internal_cleanliness: String,
        val description: String
)