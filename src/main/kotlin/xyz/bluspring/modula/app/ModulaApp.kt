package xyz.bluspring.modula.app

import javafx.application.Platform
import javafx.scene.control.Alert
import javafx.stage.Stage
import tornadofx.*
import xyz.bluspring.modula.util.AudioUtils
import xyz.bluspring.modula.util.InitExceptionTypes
import xyz.bluspring.modula.util.MixerSupportType

class ModulaApp: App(HomeView::class) {
    private var exceptionInInit = InitExceptionTypes.NONE

    override fun init() {
        when {
            AudioUtils.getMixerInfo(MixerSupportType.VIRTUAL_CABLE).isNullOrEmpty() -> exceptionInInit = InitExceptionTypes.NO_VIRTUAL_CABLE
            AudioUtils.getMixerInfo(MixerSupportType.PLAYBACK).isNullOrEmpty() -> exceptionInInit = InitExceptionTypes.NO_PLAYBACK
            AudioUtils.getMixerInfo(MixerSupportType.RECORDING).isNullOrEmpty() -> exceptionInInit = InitExceptionTypes.NO_RECORDING
        }
    }

    override fun start(stage: Stage) {
        when (exceptionInInit) {
            InitExceptionTypes.NO_VIRTUAL_CABLE -> {
                val al = alert(
                    Alert.AlertType.ERROR,
                    "A fatal error has occurred!",
                    "You do not have VB-Audio Virtual Cable and/or VoiceMeeter installed. \n\nIf you have any alternatives to add on, please submit a pull request or issue on https://github.com/BluSpring/Modula to add another virtual audio cable."
                )
                al.title = "Modula"
                al.showAndWait()

                Platform.exit()
            }

            InitExceptionTypes.NO_RECORDING -> {
                val al = alert(
                    Alert.AlertType.ERROR,
                    "A fatal error has occurred!",
                    "You do not have any input/recording audio devices! Please plug in a microphone."
                )
                al.title = "Modula"
                al.showAndWait()

                Platform.exit()
            }

            InitExceptionTypes.NO_PLAYBACK -> {
                val al = alert(
                    Alert.AlertType.WARNING,
                    "No output devices detected!",
                    "You do not have any output/playback audio devices. This will not affect Modula, however you will not be able to hear yourself when testing."
                )
                al.title = "Modula"
                al.show()
            }
        }
    }
}