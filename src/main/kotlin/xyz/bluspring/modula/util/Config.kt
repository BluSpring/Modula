package xyz.bluspring.modula.util

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val inputDevice: String,
    val outputDevice: String,
    val virtualCableDevice: String,

    val bassTrebleLevel: Double,
    val pitchCentsLevel: Double,
    val preserveTempo: Boolean
)
