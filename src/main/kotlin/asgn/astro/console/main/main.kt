package asgn.astro.console.main

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main (args: Array<String>) {

    logger.info { "Launching AstroTracker App" }
    println("AstroTracker App Version 1.0")
    println("Susan McCarthy, 20080681")

    var input: Int

    do {
        input = mainMenu()
        when (input) {
            1 -> println("You chose to create a new tracker list")
            2 -> println("You chose to delete an existing tracker list")
            3 -> println("You chose to view all existing tracker lists - this will prompt an internal menu")
            -1 -> println("Exiting App, Goodbye :)")
            else -> println("Invalid option, try again!")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down AstroTracker App" }

}

fun mainMenu() : Int {

    var option : Int
    var input : String? = null

    println("Welcome to the Main Menu")
    println("1. Create a new tracker list")
    println("2. Delete an existing tracker list")
    println("3. View all existing tracker lists")
    println("-1. Exit App")
    println("Please select an option: ")
    input = readLine()!! //takes in the user's choice
    option = if (input.toIntOrNull() != null && !input.isEmpty()) //validates to check the input exists and is not null
        input.toInt() //converts the input String to an Int
    else
        -9

    return option

}