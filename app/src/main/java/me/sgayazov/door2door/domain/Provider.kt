package me.sgayazov.door2door.domain

data class Provider(
        val provider_icon_url: String, //https://d3m2tfu2xpiope.cloudfront.net/providers/google.svg
        val disclaimer: String, //Our data is as live and real-time as possible
        val ios_itunes_url: String, //https://itunes.apple.com/app/car2go/id514921710?mt=8
        val ios_app_url: String, //car2go://
        val android_package_name: String, //com.car2go
        val display_name: String //Car2go
)