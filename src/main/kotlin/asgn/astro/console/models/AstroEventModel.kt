package asgn.astro.console.models

data class AstroEventModel(var eventID: Long = 0,
                           var title: String = "",
                           var category: String = "",
                           var closestTime: String = "",
                           var nextTime: String = "")

//var title = such as Perseids, total lunar eclipse, etc
//var category = such as meteor shower, eclipse, etc
//var closestTime = when that event is due to happen; to be replaced by Date
//var nextTime = when the event will happen again after the closest time; to be replaced by Date