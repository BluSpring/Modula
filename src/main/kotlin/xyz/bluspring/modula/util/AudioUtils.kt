package xyz.bluspring.modula.util

import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Mixer

object AudioUtils {
    fun getMixerInfo(mixerSupportType: MixerSupportType): List<Mixer.Info> {
        val mixerInfos = mutableListOf<Mixer.Info>()

        for (mixerInfo in AudioSystem.getMixerInfo()) {
            if (mixerSupportType == MixerSupportType.VIRTUAL_CABLE && (mixerInfo.name.contains("VB-Audio")) && AudioSystem.getMixer(mixerInfo).sourceLineInfo.isNotEmpty()) {
                mixerInfos.add(mixerInfo)
            }

            if (mixerSupportType == MixerSupportType.RECORDING && AudioSystem.getMixer(mixerInfo).targetLineInfo.isNotEmpty()) {
                mixerInfos.add(mixerInfo)
            }

            if (mixerSupportType == MixerSupportType.PLAYBACK && AudioSystem.getMixer(mixerInfo).sourceLineInfo.isNotEmpty())
                mixerInfos.add(mixerInfo)
        }

        return mixerInfos
    }
}