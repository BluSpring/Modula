package xyz.bluspring.modula

import javafx.scene.control.Alert
import tornadofx.alert
import tornadofx.launch
import xyz.bluspring.modula.app.ModulaApp
import xyz.bluspring.modula.util.AudioUtils
import xyz.bluspring.modula.util.MixerSupportType

object Main {
    @JvmStatic
    fun main(args: Array<out String>) {
        launch<ModulaApp>()
    }

    fun save() {

    }
}