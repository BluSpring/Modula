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
        if (AudioUtils.getMixerInfo(MixerSupportType.VIRTUAL_CABLE).isNullOrEmpty()) {
            alert(Alert.AlertType.ERROR, "Modula : A fatal error has occurred!", "You do not have VB-Audio Virtual Cable and/or VoiceMeeter installed. (if you have any alternatives to add on, please submit a pull request or issue on https://github.com/BluSpring/Modula to add another virtual audio cable)")

        }

        launch<ModulaApp>()
    }

    fun save() {

    }
}