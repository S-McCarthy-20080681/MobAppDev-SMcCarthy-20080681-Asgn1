package asgn.astro.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

var lastListId = 0L
var lastEventId = 0L

internal fun getListId(): Long {
    return lastListId++
}

internal fun getEventId(): Long {
    return lastEventId++
}

class AstroMemStore: AstroStore {

    val lists = ArrayList<AstroListModel>()
    val events = ArrayList<AstroEventModel>()

    override fun findAllLists(): List<AstroListModel> {
        return lists
    }

    override fun findAllEvents(): List<AstroEventModel> {
        return events
    }

    override fun findOneList(listID: Long): AstroListModel? {
        var foundList: AstroListModel? = lists.find { l -> l.listID == listID }
        return foundList
    }

    override fun findOneEvent(eventID: Long): AstroEventModel? {
        var foundEvent: AstroEventModel? = events.find { e -> e.eventID == eventID }
        return foundEvent
    }

    override fun createList(astroList: AstroListModel) {
        astroList.listID = getListId()
        lists.add(astroList)
        logAllLists()
    }

    override fun createEvent(astroEvent: AstroEventModel) {
        astroEvent.eventID = getEventId()
        events.add(astroEvent)
        logAllEvents()
    }

    override fun updateList(astroList: AstroListModel) {
        var foundList = findOneList(astroList.listID!!)
        if (foundList != null) {
            foundList.list = astroList.list
        }
    }

    override fun updateEvent(astroEvent: AstroEventModel) {
        var foundEvent = findOneEvent(astroEvent.eventID!!)
        if (foundEvent != null) {
            foundEvent.title = astroEvent.title
            foundEvent.category = astroEvent.category
            foundEvent.closestTime = astroEvent.closestTime
            foundEvent.nextTime = astroEvent.nextTime
        }
    }

    internal fun logAllLists() {
        lists.forEach { logger.info("${it}") }
    }

    internal fun logAllEvents() {
        events.forEach { logger.info("${it}") }
    }

}