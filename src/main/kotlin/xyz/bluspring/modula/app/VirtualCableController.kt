package xyz.bluspring.modula.app

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import tornadofx.*
import xyz.bluspring.modula.util.AudioUtils
import xyz.bluspring.modula.util.MixerSupportType

class VirtualCableController : Controller() {
    val values: ObservableList<String> = FXCollections.observableArrayList()

    init {
        values.addAll(AudioUtils.getMixerInfo(MixerSupportType.VIRTUAL_CABLE).map { it.name })
    }
}