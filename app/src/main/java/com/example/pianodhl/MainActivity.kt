package com.example.pianodhl

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    private lateinit var soundPool: SoundPool
    private var soundMap = HashMap<Int, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()

        soundPool.setOnLoadCompleteListener { soundPool, sampleId, status ->
            if (status == 0) {
                Log.d("PIANODHL", "Sonido con ID $sampleId cargado correctamente")
            } else {
                Log.e("PIANODHL", "Error al cargar el sonido con ID $sampleId")
            }
        }

        loadSounds()
        setContent {
            PianoKeyboard(mainActivity = this) // Asegúrate de que esta función esté disponible
        }
    }

    private fun loadSounds() {
        // Cambia los nombres aquí para que coincidan con tus archivos reales
        soundMap[0] = soundPool.load(this, R.raw.doo, 1)
        soundMap[1] = soundPool.load(this, R.raw.re, 1)
        soundMap[2] = soundPool.load(this, R.raw.mi, 1)
        soundMap[3] = soundPool.load(this, R.raw.fa, 1)
        soundMap[4] = soundPool.load(this, R.raw.sol, 1)
        soundMap[5] = soundPool.load(this, R.raw.la, 1)
        soundMap[6] = soundPool.load(this, R.raw.si, 1)

        // Verificación de carga
        soundMap.forEach { (key, value) ->
            if (value == 0) {
                Log.e("PIANODHL", "Error al cargar el sonido para la nota $key")
            } else {
                Log.d("PIANODHL", "Sonido para la nota $key cargado con ID $value")
            }
        }
    }

    fun playSound(note: Int) {
        val soundId = soundMap[note]
        if (soundId != null && soundId != 0) {
            Log.d("PIANODHL", "Reproduciendo sonido con ID $soundId para la nota $note")
            soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
        } else {
            Log.e("PIANODHL", "Error: Sonido para la nota $note no encontrado o no cargado correctamente")
        }
    }
}
