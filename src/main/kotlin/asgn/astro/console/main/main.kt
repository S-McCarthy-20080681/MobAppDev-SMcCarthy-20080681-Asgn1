package asgn.astro.console.main

import asgn.astro.console.models.*
import asgn.astro.console.views.AstroView
import mu.KotlinLogging
import kotlin.system.exitProcess

private val logger = KotlinLogging.logger {}

val lists = AstroJSONStore()
var astroList = AstroListModel()
val events = AstroJSONStore()
var astroEvent = AstroEventModel()
val astroView = AstroView()

fun main (args: Array<String>) {

//  -> -> -> BUG: Need to double-enter 0 before the program will close. <- <- <-  //

    logger.info { "Launching AstroTracker App" }
    println("AstroTracker App Version 1.0")
    println("Susan McCarthy, 20080681")

    var input: Int

    do {
        input = astroView.mainMenu()
        when (input) {
            1 -> addList()
            2 -> deleteList()
            3 -> astroView.displayAllLists(lists)
            4 -> updateList()
            5 -> while (input == 5) {
                do {
                    input = astroView.internalMenu()
                    when (input) {
                        1 -> astroView.expandLists(events)
                        2 -> addEvent()
                        3 -> deleteEvent()
                        4 -> updateEvent()
                        5 -> searchEvent()
                        9 -> {
                            println("Returning to Main Menu")
                            astroView.mainMenu()
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

    if (input == 0) {
        exitProcess(0)
    }

}

fun addList() {

    var aList = AstroListModel()

    if (astroView.addListData(aList))
        lists.createList(aList)
    else
        logger.info("List Not Added")

}

fun deleteList() {
    TODO()
}

fun updateList() {

    astroView.displayAllLists(lists)
    var searchId = astroView.getListID()
    val aList = listSearch(searchId)

    if (aList != null) {
        if (astroView.updateListData(aList)) {
            lists.updateList(aList)
            astroView.showList(aList)
            logger.info("List Updated: '$aList'")
        }
        else
            logger.info("List Not Updated")
    }
    else
        println("List Not Updated...")

}

fun addEvent() {

    var anEvent = AstroEventModel()

    if (astroView.addEventData(anEvent))
        events.createEvent(anEvent)
    else
        logger.info("Event Not Added")

}

fun deleteEvent() {
    TODO()
}

fun updateEvent() {

    astroView.expandLists(events)
    var searchId = astroView.getEventID()
    val anEvent = eventSearch(searchId)

    if (anEvent != null) {
        if (astroView.updateEventData(anEvent)) {
            events.updateEvent(anEvent)
            astroView.showEvent(anEvent)
            logger.info("Event Updated: \n $anEvent")
        }
        else
            logger.info("Event Not Updated")
    }
    else
        println("Event Not Updated...")

}

fun listSearch(listID: Long): AstroListModel? {

    var foundList = lists.findOneList(listID) //checks to see if there's a list ID stored that matches what's being searched
    return foundList

}

fun eventSearch(eventID: Long): AstroEventModel? {

    var foundEvent = events.findOneEvent(eventID) //checks to see if there's an event ID stored that matches what's being searched
    return foundEvent

}

fun searchList() {

    val aList = listSearch(astroView.getListID())!!
    astroView.showList(aList)

}

fun searchEvent() {

    val anEvent = eventSearch(astroView.getEventID())!!
    astroView.showEvent(anEvent)

}

fun dummyListData() {
    lists.createList(AstroListModel(listID = 1, list = "testList1"))
    lists.createList(AstroListModel(listID = 2, list = "testList2"))
    lists.createList(AstroListModel(listID = 3, list = "testList3"))
}

fun dummyEventData() {
    events.createEvent(AstroEventModel(eventID = 1, title = "Total Lunar Eclipse", category = "Lunar Eclipse", closestTime = "23/04/2021", nextTime = "05/11/2023"))
    events.createEvent(AstroEventModel(eventID = 2, title = "Perseids", category = "Meteor Shower", closestTime = "03/11/2020", nextTime = "14/02/2021"))
    events.createEvent(AstroEventModel(eventID = 3, title = "Draconids", category = "Meteor Shower", closestTime = "01/01/2021", nextTime = "30/07/2021"))
}