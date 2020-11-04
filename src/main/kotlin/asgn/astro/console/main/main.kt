package asgn.astro.console.main

import asgn.astro.console.controllers.AstroController
import asgn.astro.console.models.*
import asgn.astro.console.views.AstroView
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

val lists = AstroJSONStore()
var astroList = AstroListModel()
val events = AstroJSONStore()
var astroEvent = AstroEventModel()
val astroView = AstroView()
val runner = AstroController()

fun main (args: Array<String>) {

//-> -> BUG: Once returned to mainMenu, need to double-enter inputs after that before any function will run. <- <-//

    logger.info { "Launching AstroTracker App" }
    println("AstroTracker App Version 1.0")
    println("Susan McCarthy, 20080681")

    runner.start()

}