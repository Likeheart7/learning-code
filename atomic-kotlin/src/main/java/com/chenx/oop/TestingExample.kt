package com.chenx.oop

import com.chenx.oop.atomictest.*

fun main() {
    /*
        11
        Ontology
        [Error]: Ontology != ontology
        Ontology
     */
    val v1 = 11;
    val v2 = "Ontology"

    v1 eq 11
    v2 eq "ontology"

    v2 neq "Eclipse"

    trace("line 1")
    trace(47)
    trace eq """
        line 1
        47
    """

    capture {
        "4s".toInt()
    } eq "NumberFormatException: For input string: \"4s\""

}