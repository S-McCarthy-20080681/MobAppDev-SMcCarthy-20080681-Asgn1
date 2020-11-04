package asgn.astro.console.helpers

import mu.KotlinLogging
import java.io.*
import java.lang.StringBuilder

val logger = KotlinLogging.logger {}

fun write (fileName: String, data: String) {
    val file = File(fileName)
    try {
        val outputStreamWriter = OutputStreamWriter(FileOutputStream(file))
        outputStreamWriter.write(data)
        outputStreamWriter.close() //avoids resource leak
    } catch (e: Exception) {
        logger.error { "Cannot read file: " + e.toString() }
    }
}

fun read (fileName: String) : String {
    val file = File(fileName)
    var str = ""

    try {
        val inputStreamReader = InputStreamReader(FileInputStream(file))
        if (inputStreamReader != null) {
            val bufferedReader = BufferedReader(inputStreamReader)
            val partialStr = StringBuilder()
            var done = false
            while (!done) {
                var line = bufferedReader.readLine()
                done = (line == null); //done returns true when there are no more lines to read
                if (line != null) partialStr.append(line)
            }
            inputStreamReader.close()
            str = partialStr.toString()
        }
    } catch (e: FileNotFoundException) {
        logger.error { "Cannot find file: " + e.toString() }
    } catch (e: IOException) {
        logger.error { "Cannot read file: " + e.toString() }
    }
    return str
}

fun exists (fileName: String) : Boolean { //the file either exists or it doesn't
    val file = File(fileName)
    return file.exists() //true if file is there, false if it isn't
}