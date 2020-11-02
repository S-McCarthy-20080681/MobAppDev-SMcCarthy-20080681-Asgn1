package asgn.astro.console.main

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main (args: Array<String>) {

    logger.info { "Launching AstroTracker App" }
    println("AstroTracker App Version 1.0")
    println("Susan McCarthy, 20080681")

    var input: Int
    var subInput: Int

    do {
        input = mainMenu()
        when (input) {
            1 -> println("You chose to create a new tracker list")
            2 -> println("You chose to delete an existing tracker list")
//            3 -> println("You chose to view all existing tracker lists - this will prompt an internal menu")
            3 -> internalMenu()
            0 -> {
                println("Exiting App, Goodbye :)")

            }
            else -> println("Invalid option, try again!")
        }
        println()
    } while (input != 0)
    logger.info { "Shutting Down AstroTracker App" }

    do {
        subInput = internalMenu()
        when (subInput) {
            1 -> println("Here are all tracker lists currently in the system")
            2 -> println("Here's where you can add a single Event to a pre-existing list")
            3 -> println("Here's where you can delete a single Event from a pre-existing list using its ID")
            4 -> println("Here's where you can change the details of an Event already in a list")
            -1 -> {
                println("Returning to Main Menu")
                mainMenu()
                }
            else -> println("Invalid option, try again!")
        }
        println()
    } while (subInput != -1)
    logger.info { "Returning to AstroTracker Main Menu" }

}

fun mainMenu() : Int {

    val option : Int
    var input : String? = null

    println("Welcome to the Main Menu")
    println("1. Create a new tracker list")
    println("2. Delete an existing tracker list")
    println("3. View all existing tracker lists")
    println("0. Exit App")
    println("Please select an option: ")
    input = readLine()!! //takes in the user's choice
    option = if (input.toIntOrNull() != null && !input.isEmpty()) //validates to check the input exists and is not null
        input.toInt() //converts the input String to an Int
    else
        -9

    return option

}

fun internalMenu(): Int {
    var subOption : Int
    var subInput : String? = null

    println("You are now viewing the tracker list Edit Menu")
    println("1. Expand a list")
    println("2. Add an Event to an existing list")
    println("3. Remove an Event from an existing list")
    println("4. Update/Change an Event")
    println("-1. Return to Main Menu")
    subInput = readLine()!!
    subOption = if (subInput.toIntOrNull() != null && !subInput.isEmpty())
        subInput.toInt()
    else
        -9

    return subOption
}