package com.harshit.covidtracker

data class Response(
    val statewise:List<Statewise>
)
data class Statewise(

    val active: String,
    val confirmed: String,
    val deaths: String,
    val deltaconfirmed: String,
    val deltadeaths: String,
    val deltarecovered: String,
    val deltaactive:String,
    val lastupdatedtime: String,
    val migratedother: String,
    val recovered: String,
    val state: String,
    val statecode: String,
    val statenotes: String

)