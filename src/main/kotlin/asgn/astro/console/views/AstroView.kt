package asgn.astro.console.views

import asgn.astro.console.models.*

class AstroView {

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
        println("7. Delete a List")
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
        println("6. Delete an Event")
        println("9. Return to Main Menu")
        input = readLine()!!
        subOption = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9

        return subOption
    }

    fun displayAllLists(lists: AstroJSONStore) {
//        println("Current Lists:")
//        println("ID: ${AstroListMemStore.listID}, Name: ${astroList.list}")
        lists.logAllLists()
        println()
    }

    fun expandLists(events: AstroJSONStore) {
//        println("Events in each List:")
//        println("Name: ${astroEvent.title}, Category: ${astroEvent.category}," +
//                "Occurring: ${astroEvent.closestTime}, Next Occurring: ${astroEvent.nextTime}")
        events.logAllEvents()
        println()
    }

    fun showList(astroList: AstroListModel){ //what is returned to console if searchList option is chosen
        if (astroList != null)
            println("List Details: '$astroList'")
        else
            println("List not found...")
    }

    fun showEvent(astroEvent: AstroEventModel) { //what is returned to console if searchEvent option is chosen
        if (astroEvent != null)
            println("Event Details: \n $astroEvent")
        else
            println("Event not found...")
    }

    fun addListData(astroList: AstroListModel): Boolean {
        println()
        print("Name your list: ")
        astroList.list = readLine()!!

        return astroList.list.isNotEmpty()
        println("List added!")
    }

    fun addEventData(astroEvent: AstroEventModel): Boolean {
        println("Add an Event")
        println()

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

        return astroEvent.title.isNotEmpty() && astroEvent.category.isNotEmpty() &&
                astroEvent.closestTime.isNotEmpty() && astroEvent.nextTime.isNotEmpty()

        println("Event added!")
    }

    fun updateListData(astroList: AstroListModel) : Boolean {
        var tempList: String?

        if (astroList != null) {
            print("Current List name: ${astroList.list} \n")
            print("Please choose a new List name: ")
            tempList = readLine()!!

            if (!tempList.isNullOrEmpty()) {
                astroList.list = tempList
                return true
                println("List updated!")
            }
        }
        return false
        println("List not updated...")
    }

    fun updateEventData(astroEvent: AstroEventModel) : Boolean {
        var tempTitle: String?
        var tempCat: String?
        var tempCloseTime: String?
        var tempNextTime: String?

        if (astroEvent != null) {
            print("Enter a new name for your '${astroEvent.title}' Event: \n")
            tempTitle = readLine()!!
            println()

            print("Enter a new category for your '${astroEvent.category}' Event: Meteor Shower|Lunar Eclipse|Solar Eclipse|Supermoon \n")
            tempCat = readLine()!!
            println()

            print("Enter a new date for your Event on '${astroEvent.closestTime}': [dd/mm/yyyy] \n")
            tempCloseTime = readLine()!!
            println()

            print("Currently, this Event will next occur on: ${astroEvent.nextTime}")
            print("Enter a new next-date for your Event: [dd/mm/yyyy] \n")
            tempNextTime = readLine()!!
            println()

            if (!tempTitle.isNullOrEmpty() && !tempCat.isNullOrEmpty() && !tempCloseTime.isNullOrEmpty() &&
                !tempNextTime.isNullOrEmpty()) {
                    astroEvent.title = tempTitle
                    astroEvent.category = tempCat
                    astroEvent.closestTime = tempCloseTime
                    astroEvent.nextTime = tempNextTime
                return true
                println("Event updated!")
            }
        }
        return false
        println("Event not updated...")
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

}