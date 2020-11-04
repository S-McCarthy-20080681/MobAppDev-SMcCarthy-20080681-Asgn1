package asgn.astro.console.models

interface AstroListStore {

    fun findAllLists(): List<AstroListModel>
    fun findOneList(listID: Long): AstroListModel?
    fun createList(astroList: AstroListModel)
    fun updateList(astroList: AstroListModel)

}