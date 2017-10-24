package me.sgayazov.door2door.domain

data class Route(
        val type: String, //public_transport
        val provider: String, //vbb
        val segments: List<Segment>,
        val properties: Any, //null
        val price: Price
)