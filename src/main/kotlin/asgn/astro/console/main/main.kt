package asgn.astro.console.main

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main (args: Array<String>) {

    logger.info { "Launching AstroTracker App" }
    println("AstroTracker App Version 1.0")
    println("Susan McCarthy, 20080681")

    var input: Int
    input = mainMenu()

}

fun mainMenu() : Int {

    var option : Int
    var input : String? = null

    println("Welcome to the Main Menu")
    println("1. Create a new tracker list")
    println("-1. Exit App")
    println("Please select an option: ")
    input = readLine()!! //takes in the user's choice
    option = input.toInt() //converts it from a String to an Int

    return option

}