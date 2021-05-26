package xyz.bluspring.modula.effects

import be.tarsos.dsp.AudioEvent
import be.tarsos.dsp.AudioProcessor
import kotlin.math.*

class BassTrebleEffect(var frequency: Int, var db_boost: Int, var sampleRate: Int) : AudioProcessor {
    private var xn1 = 0.0F
    private var xn2 = 0.0F
    private var yn1 = 0.0F
    private var yn2 = 0.0F

    private var omega = (2 * Math.PI * frequency / sampleRate).toFloat()
    private var sn = sin(omega)
    private var cs = cos(omega)
    private var a = exp(ln(10.0) * db_boost / 40).toFloat()
    private var shape = 1.0F
    private var beta = sqrt((a * a + 1) / shape - ((a - 1).pow(2)))

    // Coefficients
    private var b0 = a * ((a + 1) - (a - 1) * cs + beta * sn);
    private var b1 = 2 * a * ((a - 1) - (a + 1) * cs);
    private var b2 = a * ((a + 1) - (a - 1) * cs - beta * sn);
    private var a0 = ((a + 1) + (a - 1) * cs + beta * sn);
    private var a1 = -2 * ((a - 1) + (a + 1) * cs);
    private var a2 = (a + 1) + (a - 1) * cs - beta * sn;

    fun update() {
        omega = (2 * Math.PI * frequency / sampleRate).toFloat()
        sn = sin(omega)
        cs = cos(omega)
        a = exp(ln(10.0) * db_boost / 40).toFloat()
        shape = 1.0F
        beta = sqrt((a * a + 1) / shape - ((a - 1).pow(2)))

        // Coefficients
        b0 = a * ((a + 1) - (a - 1) * cs + beta * sn);
        b1 = 2 * a * ((a - 1) - (a + 1) * cs);
        b2 = a * ((a + 1) - (a - 1) * cs - beta * sn);
        a0 = ((a + 1) + (a - 1) * cs + beta * sn);
        a1 = -2 * ((a - 1) + (a + 1) * cs);
        a2 = (a + 1) + (a - 1) * cs - beta * sn;
    }

    override fun process(ev: AudioEvent): Boolean {
        val audioBuffer = ev.floatBuffer.clone()

        var out: Float
        var `in`: Float

        for (i in 0..audioBuffer.size) {
            `in` = audioBuffer[i]
            out = (b0 * `in` + b1 * xn1 + b2 * xn2 - a1 * yn1 - a2 * yn2) / a0

            xn2 = xn1
            xn1 = `in`
            yn2 = yn1
            yn1 = out

            if (out < -1.0F)
                out = -1.0F
            else if (out > 1.0)
                out = 1.0F

            audioBuffer[i] = out
        }

        ev.floatBuffer = audioBuffer

        return true
    }

    override fun processingFinished() {}
}