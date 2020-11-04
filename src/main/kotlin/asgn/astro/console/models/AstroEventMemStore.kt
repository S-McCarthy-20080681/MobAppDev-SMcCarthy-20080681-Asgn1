package asgn.astro.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

var lastEventId = 0L

internal fun getEventId(): Long {
    return lastEventId++
}

class AstroEventMemStore: AstroEventStore {

    val events = ArrayList<AstroEventModel>()

    override fun findAllEvents(): List<AstroEventModel> {
        return events
    }

    override fun findOneEvent(eventID: Long): AstroEventModel? {
        var foundEvent: AstroEventModel? = events.find { e -> e.eventID == eventID }
        return foundEvent
    }

    override fun createEvent(astroEvent: AstroEventModel) {
        astroEvent.eventID = getEventId()
        events.add(astroEvent)
        logAllEvents()
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

    internal fun logAllEvents() {
        events.forEach { logger.info("${it}") }
    }

}