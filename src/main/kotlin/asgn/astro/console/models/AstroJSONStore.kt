package asgn.astro.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import asgn.astro.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_LIST_FILE = "lists.json"
val JSON_EVENT_FILE = "events.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listListType = object : TypeToken<java.util.ArrayList<AstroListModel>>() {}.type
val eventListType = object : TypeToken<java.util.ArrayList<AstroEventModel>>() {}.type

fun generateRandomId() : Long {
    return Random().nextLong()
}

class AstroJSONStore : AstroListStore, AstroEventStore {

    var lists = mutableListOf<AstroListModel>()
    var events = mutableListOf<AstroEventModel>()

    init {
        if (exists(JSON_LIST_FILE)) {
            deserializeList()
        }
        if (exists(JSON_EVENT_FILE)) {
            deserializeEvent()
        }
    }

    override fun findAllLists(): MutableList<AstroListModel> {
        return lists
    }

    override fun findAllEvents(): MutableList<AstroEventModel> {
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
        astroList.listID = generateRandomId()
        lists.add(astroList)
        serializeList()
    }

    override fun createEvent(astroEvent: AstroEventModel) {
        astroEvent.eventID = generateRandomId()
        events.add(astroEvent)
        serializeEvent()
    }

    override fun updateList(astroList: AstroListModel) {
        var foundList = findOneList(astroList.listID!!)
        if (foundList != null) { //if the searched list is found
            foundList.list = astroList.list
        }
        serializeList()
    }

    override fun updateEvent(astroEvent: AstroEventModel) {
        var foundEvent = findOneEvent(astroEvent.eventID!!)
        if (foundEvent != null) { //if the searched event is found
            foundEvent.title = astroEvent.title
            foundEvent.category = astroEvent.category
            foundEvent.closestTime = astroEvent.closestTime
            foundEvent.nextTime = astroEvent.nextTime
        }
        serializeEvent()
    }

    override fun deleteList(astroList: AstroListModel) {
        lists.remove(astroList)
        serializeList()
    }

    override fun deleteEvent(astroEvent: AstroEventModel) {
        events.remove(astroEvent)
        serializeEvent()
    }

    internal fun logAllLists() {
        lists.forEach { logger.info("${it}") }
    }

    internal fun logAllEvents() {
        events.forEach { logger.info("${it}") }
    }

    private fun serializeList() {
        val jsonListString = gsonBuilder.toJson(lists, listListType)
        write(JSON_LIST_FILE, jsonListString)
    }

    private fun deserializeList() {
        val jsonListString = read(JSON_LIST_FILE)
        lists = Gson().fromJson(jsonListString, listListType)
    }

    private fun serializeEvent() {
        val jsonEventString = gsonBuilder.toJson(events, eventListType)
        write(JSON_EVENT_FILE, jsonEventString)
    }

    private fun deserializeEvent() {
        val jsonEventString = read(JSON_EVENT_FILE)
        events = Gson().fromJson(jsonEventString, eventListType)
    }

}