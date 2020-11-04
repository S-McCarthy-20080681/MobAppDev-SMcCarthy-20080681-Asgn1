package asgn.astro.console.models

interface AstroEventStore {

    fun findAllEvents(): List<AstroEventModel>

    fun findOneEvent(eventID: Long): AstroEventModel?

    fun createEvent(astroEvent: AstroEventModel)

    fun updateEvent(astroEvent: AstroEventModel)

}