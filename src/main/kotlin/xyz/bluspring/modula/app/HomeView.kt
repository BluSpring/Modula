package xyz.bluspring.modula.app

import tornadofx.*

class HomeView : View() {
    private val inputDeviceController: InputDeviceController by inject()
    private val outputDeviceController: OutputDeviceController by inject()
    private val virtualCableController: VirtualCableController by inject()

    override val root = vbox {
        label("Select Input (Microphone) Device")
        val inputDeviceList = listview(inputDeviceController.values)

        label("Select Output (Speaker) Device")
        val outputDeviceList = listview(outputDeviceController.values)

        label("Select Virtual Cable Device")
        val virtualCableList = listview(virtualCableController.values)


    }
}