package com.exequieltiglao.covid.entity

data class Total(
    val confirmed: Int,
    val deaths: Int,
    val recovered: Int,
    val active: Int
)