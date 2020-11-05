package asgn.astro.console.controllers

import asgn.astro.console.models.*
import mu.KotlinLogging
import asgn.astro.console.views.AstroView
import kotlin.system.exitProcess

class AstroController {

    val lists = AstroJSONStore()
    val events = AstroJSONStore()
    val astroView = AstroView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching AstroTracker App" }
        println("AstroTracker App Version 1.0")
        println("Susan McCarthy, 20080681")
        println()
    }

    fun start() {
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

        if (astroView.addListData(aList)) {
            lists.createList(aList)
            println("List added!")
        }
        else
            logger.info("List Not Added")
    }

    fun addEvent() {
        var anEvent = AstroEventModel()

        if (astroView.addEventData(anEvent)) {
            events.createEvent(anEvent)
            println("Event added!")
        }
        else
            logger.info("Event Not Added")
    }

    fun updateList() {
        astroView.displayAllLists(lists)
        var searchId = astroView.getListID()
        val aList = listSearch(searchId)

        if (aList != null) {
            if (astroView.updateListData(aList)) {
                lists.updateList(aList)
                println("List Updated: $aList")
            }
            else
                logger.info("List Not Updated")
        }
        else
            println("List Not Updated...")
    }

    fun updateEvent() {
        astroView.expandLists(events)
        var searchId = astroView.getEventID()
        val anEvent = eventSearch(searchId)

        if (anEvent != null) {
            if (astroView.updateEventData(anEvent)) {
                events.updateEvent(anEvent)
                println("Event Updated: \n $anEvent")
            }
            else
                logger.info("Event Not Updated")
        }
        else
            println("Event Not Updated...")
    }

    fun deleteList() {
        astroView.displayAllLists(lists)
        var searchId = astroView.getListID()
        val aList = listSearch(searchId)

        if (aList != null) { //if the list ID searched exists, delete that list
            lists.deleteList(aList)
            println("List Deleted!")
//            astroView.displayAllLists(lists)
        }
        else
            println("List not deleted...")
    }

    fun deleteEvent() {
        astroView.expandLists(events)
        var searchId = astroView.getEventID()
        val anEvent = eventSearch(searchId)

        if (anEvent != null) { //if the event ID searched exists, delete that event
            events.deleteEvent(anEvent)
            println("Event Deleted!")
        }
        else {
            println("Event not deleted...")
        }
    }

    fun searchList() {
        lists.logAllLists()
        val aList = listSearch(astroView.getListID())!!
        astroView.showList(aList)
    }

    fun searchEvent() {
        events.logAllEvents()
        val anEvent = eventSearch(astroView.getEventID())!!
        astroView.showEvent(anEvent)
    }

    fun listSearch(listID: Long ) : AstroListModel? {
        var foundList = lists.findOneList(listID)
        return foundList
    }

    fun eventSearch(eventID: Long) : AstroEventModel? {
        var foundEvent = events.findOneEvent(eventID)
        return foundEvent
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

}