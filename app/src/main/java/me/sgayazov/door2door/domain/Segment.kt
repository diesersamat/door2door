package me.sgayazov.door2door.domain

data class Segment(
        val name: Any, //null
        val num_stops: Int, //0
        val stops: List<Stop>,
        val travel_mode: String, //walking
        val description: Any, //null
        val color: String, //#b1becc
        val icon_url: String, //https://d3m2tfu2xpiope.cloudfront.net/vehicles/walking.svg
        val polyline: String //uvr_I{yxpABuAFcAp@yHvAwNr@iGPwAh@a@jAg@
)