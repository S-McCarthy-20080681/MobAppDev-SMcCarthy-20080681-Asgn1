package asgn.astro.console.main

import mu.KotlinLogging
import java.text.DateFormat
import java.util.*
import kotlin.system.exitProcess

private val logger = KotlinLogging.logger {}

var list = "" //to be replaced with an actual List later
var title = "" //such as Perseids, total lunar eclipse, etc
var category = "" //such as meteor shower, eclipse, etc
var closestTime = "" //when that event will next happen; to be replaced by Date
var nextTime = "" //when the event will happen again after the closest time; to be replaced by Date


fun main (args: Array<String>) {

    logger.info { "Launching AstroTracker App" }
    println("AstroTracker App Version 1.0")
    println("Susan McCarthy, 20080681")

    var input: Int

    do {
        input = mainMenu()
        when (input) {
            1 -> addList()
            2 -> deleteList()
            3 -> displayAllLists()
            4 -> updateList()
            5 -> internalMenu()
            0 -> {
                println("Exiting App, Goodbye :)")

            }
            else -> println("Invalid option, try again!")
        }
        println()
    } while (input != 0)
    logger.info { "Shutting Down AstroTracker App" }

    var subInput: Int

    while (input == 5) {
        do {
            subInput = internalMenu()
            when (subInput) {
                1 -> expandList()
                2 -> addEvent()
                3 -> deleteEvent()
                4 -> updateEvent()
                9 -> {
                    println("Returning to Main Menu")
                    mainMenu()
                }
                else -> println("Invalid option, try again!")
            }
            println()
        } while (subInput != 9)
        logger.info { "Returning to AstroTracker Main Menu" }
    }

    if (input == 0) {
        exitProcess(0)
    }

}

fun mainMenu() : Int {

    val option : Int
    var input : String? = null

    println("Welcome to the Main Menu")
    println("1. Create a new tracker list")
    println("2. Delete an existing tracker list")
    println("3. View all existing tracker lists")
    println("4. Rename an existing list")
    println("5. Event Editor Menu")
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
    println("9. Return to Main Menu")
    subInput = readLine()!!
    subOption = if (subInput.toIntOrNull() != null && !subInput.isEmpty())
        subInput.toInt()
    else
        -9

    return subOption
}

fun addList() {

    println("Create a new AstroTracker List")
    println()

    print("Name your list: ")
    list = readLine()!!
    println("You have created the AstroTracker list '$list'. Now add some events!")

    internalMenu() //brings the user back to the Edit Menu so they can add events to their new list

}

fun deleteList() {
    TODO()
}

fun updateList() {

    println("Change the name of your AstroTracker List")
    println()
    //code to display all lists and ask user to choose one to rename (an "if {list.equals}" maybe???}
    print("Current List name: $list")
    print("Please choose a new List name: ")
    list = readLine()!!
    print("Your new List name is: $list")

}

fun displayAllLists() {
    println("This will return all lists currently stored by the app.")
}

fun expandList() {
    TODO()
}

fun addEvent() {

    println("Add an Event")
    println()

    print("What's happening?")
    title = readLine()!!
    println()

    print("Choose a category: Meteor Shower|Lunar Eclipse|Solar Eclipse|Supermoon")
    category = readLine()!!
    println()

    print("When will it happen? [dd/mm/yyyy]")
    closestTime = readLine()!!
    println()

    print("When will it next happen? [dd/mm/yyyy]")
    nextTime = readLine()!!
    println()

    print("Here is your new Event: ")
    print("Title: $title \n Category: $category \n Occurring: $closestTime \n Next Occurrence: $nextTime")

    internalMenu() //brings the user back to the Edit Menu in case they want to add another event

}

fun deleteEvent() {
    TODO()
}

fun updateEvent() {

    println("Update an Event")
    println()

    //code to ask user to choose a list, then display all events of that list for user to pick and update one

    print("Enter a new name for your '$title' Event: ")
    title = readLine()!!
    println()

    print("Enter a new category for your '$category' Event: Meteor Shower|Lunar Eclipse|Solar Eclipse|Supermoon")
    category = readLine()!!
    println()

    print("Enter a new date for your Event on '$closestTime': [dd/mm/yyyy]")
    closestTime = readLine()!!
    println()

    print("Currently, this Event will next occur on: $nextTime")
    print("Enter a new next-date for your Event: [dd/mm/yyyy]")
    nextTime = readLine()!!
    println()

    print("Here is your updated Event: ")
    print("Title: $title \n Category: $category \n Occurring: $closestTime \n Next Occurrence: $nextTime")

    internalMenu() //returns to Edit Menu
}