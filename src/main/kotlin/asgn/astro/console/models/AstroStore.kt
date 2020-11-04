package asgn.astro.console.models

interface AstroStore {

    fun findAllLists(): List<AstroListModel>
    fun findAllEvents(): List<AstroEventModel>

    fun findOneList(listID: Long): AstroListModel?
    fun findOneEvent(eventID: Long): AstroEventModel?

    fun createList(astroList: AstroListModel)
    fun createEvent(astroEvent: AstroEventModel)

    fun updateList(astroList: AstroListModel)
    fun updateEvent(astroEvent: AstroEventModel)

}