package asgn.astro.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

var lastListId = 0L

internal fun getListId(): Long {
    return lastListId++
}

class AstroListMemStore : AstroListStore {

    val lists = ArrayList<AstroListModel>()

    override fun findAllLists(): List<AstroListModel> {
        return lists
    }

    override fun findOneList(listID: Long): AstroListModel? {
        var foundList: AstroListModel? = lists.find { l -> l.listID == listID }
        return foundList
    }

    override fun createList(astroList: AstroListModel) {
        astroList.listID = getListId()
        lists.add(astroList)
        logAllLists()
    }

    override fun updateList(astroList: AstroListModel) {
        var foundList = findOneList(astroList.listID!!)
        if (foundList != null) {
            foundList.list = astroList.list
        }
    }

    override fun deleteList(astroList: AstroListModel) {
        lists.remove(astroList)
    }

    internal fun logAllLists() {
        lists.forEach { logger.info("${it}") }
    }

}