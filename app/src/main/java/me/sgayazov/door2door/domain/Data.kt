package me.sgayazov.door2door.domain

data class Data(
        val routes: List<Route>,
        val provider_attributes: ProviderAttributes
) : ExpiringData()