package asgn.astro.console.main

import asgn.astro.console.models.AstroEventMemStore
import asgn.astro.console.models.AstroEventModel
import asgn.astro.console.models.AstroListModel
import asgn.astro.console.models.AstroListMemStore
import com.sun.java.accessibility.util.EventID
import mu.KotlinLogging
import kotlin.collections.ArrayList
import kotlin.system.exitProcess

private val logger = KotlinLogging.logger {}

//val lists = ArrayList<AstroListModel>()
val lists = AstroListMemStore()
var astroList = AstroListModel()
//val events = ArrayList<AstroEventModel>()
val events = AstroEventMemStore()
var astroEvent = AstroEventModel()

fun main (args: Array<String>) {

//  -> -> -> BUG: Need to double-enter 0 before the program will close. <- <- <-  //

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
            5 -> while (input == 5) {
                do {
                    input = internalMenu()
                    when (input) {
                        1 -> expandLists()
                        2 -> addEvent()
                        3 -> deleteEvent()
                        4 -> updateEvent()
                        5 -> searchEvent()
                        9 -> {
                            println("Returning to Main Menu")
                            mainMenu()
                        }
                        else -> println("Invalid option, try again!")
                    }
                    println()
                } while (input != 9)
                logger.info { "Returning to AstroTracker Main Menu" }
            }

            6 -> searchList()
            0 -> {
                println("Exiting App, Goodbye :)")

            }
            else -> println("Invalid option, try again!")
        }
        println()
    } while (input != 0)
    logger.info { "Shutting Down AstroTracker App" }

    var subInput: Int

//    while (input == 5) {
//        do {
//            input = internalMenu()
//            when (input) {
//                1 -> expandLists()
//                2 -> addEvent()
//                3 -> deleteEvent()
//                4 -> updateEvent()
//                9 -> {
//                    println("Returning to Main Menu")
//                    mainMenu()
//                }
//                else -> println("Invalid option, try again!")
//            }
//            println()
//        } while (input != 9)
//        logger.info { "Returning to AstroTracker Main Menu" }
//    }

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
    println("6. Search for a List")
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
    var input : String? = null

    println("You are now viewing the tracker list Event Editor Menu")
    println("1. Expand all Lists") //used as a version of display, to listAllEvents WITHIN each List
    println("2. Add an Event to an existing List")
    println("3. Remove an Event from an existing List")
    println("4. Update/Change an Event")
    println("5. Search for an Event")
    println("9. Return to Main Menu")
    input = readLine()!!
    subOption = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9

    return subOption
}

fun addList() {

    println("Create a new AstroTracker List")
    println()

    print("Name your list: ")
    astroList.list = readLine()!!


    if (astroList.list.isNotEmpty()) {
        astroList.listID++
        lists.add(astroList.copy())
        println("You have created the AstroTracker list '${astroList.list}'. Now add some events!")
       // internalMenu() //brings the user back to the Edit Menu so they can add events to their new list
    } else
        logger.info { "List was not created..." }

}

fun deleteList() {
    TODO()
}

fun updateList() {

    println("Change the name of your AstroTracker List")
    println()
    //code to display all lists and ask user to choose one to rename (an "if {list.equals}" maybe???}
    print("Current List name: ${astroList.list}")
    print("Please choose a new List name: ")
    astroList.list = readLine()!!
    print("Your new List name is: ${astroList.list}")

}

fun displayAllLists() {
    println("Current Lists:")
    lists.forEach{ println("ID: ${astroList.listID}, Name: ${astroList.list}") }
  //  lists.forEach { logger.info("${it}") }
    println()
}

fun expandLists() {
    println("Events in each List:")
    println()
//    lists.forEach { //print each Event within each List when they are changed to Arrays later on
//        events.forEach { logger.info("${it}") }
    events.forEach{ println("Name: ${astroEvent.title}, Category: ${astroEvent.category}, " +
            "Occurring: ${astroEvent.closestTime}, Next Occurring: ${astroEvent.nextTime}") }
//    }
    println()
}

fun addEvent() {

    println("Add an Event")
    println()

    astroEvent.eventID++

    print("What's happening? \n")
    astroEvent.title = readLine()!!
    println()

    print("Choose a category: Meteor Shower|Lunar Eclipse|Solar Eclipse|Supermoon \n")
    astroEvent.category = readLine()!!
    println()

    print("When will it happen? [dd/mm/yyyy] \n")
    astroEvent.closestTime = readLine()!!
    println()

    print("When will it next happen? [dd/mm/yyyy] \n")
    astroEvent.nextTime = readLine()!!
    println()

    if (astroEvent.title.isNotEmpty() && astroEvent.category.isNotEmpty() && astroEvent.closestTime.isNotEmpty()
        && astroEvent.nextTime.isNotEmpty()) {

//        astroEvent.eventID++
        events.add(astroEvent.copy())

        print("Here is your new Event:\n")
        print(
            "Title: ${astroEvent.title} \n Category: ${astroEvent.category} \n " +
                    "Occurring: ${astroEvent.closestTime} \n Next Occurrence: ${astroEvent.nextTime} \n"
        )

    } else
        logger.info { "Event was not created..." }

}

fun deleteEvent() {
    TODO()
}

fun updateEvent() {

    println("Update an Event")
    println()

    //code to ask user to choose a list, then display all events of that list for user to pick and update one

    print("Enter a new name for your '${astroEvent.title}' Event: \n")
    astroEvent.title = readLine()!!
    println()

    print("Enter a new category for your '${astroEvent.category}' Event: Meteor Shower|Lunar Eclipse|Solar Eclipse|Supermoon \n")
    astroEvent.category = readLine()!!
    println()

    print("Enter a new date for your Event on '${astroEvent.closestTime}': [dd/mm/yyyy] \n")
    astroEvent.closestTime = readLine()!!
    println()

    print("Currently, this Event will next occur on: ${astroEvent.nextTime}")
    print("Enter a new next-date for your Event: [dd/mm/yyyy] \n")
    astroEvent.nextTime = readLine()!!
    println()

    print("Here is your updated Event: ")
    print("Title: ${astroEvent.title} \n Category: ${astroEvent.category} \n " +
            "Occurring: ${astroEvent.closestTime} \n Next Occurrence: ${astroEvent.nextTime}")
    println()

}

fun getListID(): Long {

    var strID : String? //String to hold user input
    var searchID : Long //Long to hold converted string ID
    print("Enter List ID: ")
    strID = readLine()!!
    searchID = if (strID.toLongOrNull() != null && !strID.isEmpty())
        strID.toLong()
    else
        -9
    return searchID

}

fun listSearch(listID: Long): AstroListModel? {

    var foundList: AstroListModel? = lists.find { l -> l.listID == listID } //checks to see if there's a list ID stored that matches what's being searched
    return foundList

}

fun getEventID(): Long {

    var strID : String? //String to hold user input
    var searchID : Long //Long to hold converted string ID
    print("Enter Entry ID: ")
    strID = readLine()!!
    searchID = if (strID.toLongOrNull() != null && !strID.isEmpty())
        strID.toLong()
    else
        -9
    return searchID

}

fun eventSearch(eventID: Long): AstroEventModel? {

    var foundEvent: AstroEventModel? = events.find { e -> e.eventID == eventID } //checks to see if there's an event ID stored that matches what's being searched
    return foundEvent

}

fun searchList() {

    var searchID = getListID()
    val aList = listSearch(searchID)

    if (aList != null)
        println("List Found: $aList")
    else
        println("List not found...")

}

fun searchEvent() {

    var searchID = getEventID()
    val anEvent = eventSearch(searchID)

    if (anEvent != null)
        println("Event Found: $anEvent")
    else
        println("Event not found...")

}

fun dummyListData() {
    lists.add(AstroListModel(1, "testList1"))
    lists.add(AstroListModel(2, "testList2"))
    lists.add(AstroListModel(3, "testList3"))
}

fun dummyEventData() {
    events.add(AstroEventModel(1, "Total Lunar Eclipse", "Lunar Eclipse", "23/04/2021", "05/11/2023"))
    events.add(AstroEventModel(2, "Perseids", "Meteor Shower", "03/11/2020", "14/02/2021"))
    events.add(AstroEventModel(3, "Draconids", "Meteor Shower", "01/01/2021", "30/07/2021"))
}