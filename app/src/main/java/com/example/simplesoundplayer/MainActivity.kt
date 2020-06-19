package com.example.simplesoundplayer

import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //inisialisasi objek
    private lateinit var soundPool: SoundPool
    //inisialisasi variable
    private var soundId: Int = 0
    private var soundPoolLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        soundPool = SoundPool.Builder()
            .setMaxStreams(10) //menentukan jumlah streams secara simultan yang dapat diputar secara bersamaan.
            .build()

        //soundpool hanya bisa memainkan berkas yg telah dimuat secara sempurna
        //maka untuk mengecek menggunakan listener ini
        soundPool.setOnLoadCompleteListener { soundPool, sampleId, status ->
            if (status == 0){
                soundPoolLoaded = true
            }else{
                Toast.makeText(this@MainActivity, "Gagal memuat berkas suara", Toast.LENGTH_SHORT).show()
            }
        }
        //variable yg menampung id dari audio yang Anda muat.
        soundId = soundPool.load(this, R.raw.blade_sharpen, 1)

        //implementasi btn
        btn_soundpool.setOnClickListener {
            if (soundPoolLoaded){
                soundPool.play(soundId, 1f, 1f, 0,0,1f)
                //Parameter soundID merupakan id dari audio yang Anda muat.
                //LeftVolume dan RightVolume merupakan parameter float dari besar kecilnya volume yang range-nya dimulai dari 0.0 - 1.0.
                //Priority merupakan urutan prioritas. Semakin besar nilai priority, semakin tinggi prioritas audio itu untuk dijalankan.
                //Paremeter loop digunakan untuk mengulang audio ketika telah selesai dimainkan. Nilai -1 menunjukkan bahwa audio akan diulang-ulang tanpa berhenti. Nilai 0 menunjukkan audio akan dimainkan sekali. Nilai 1 menunjukkan audio akan dimainkan sebanyak 2 kali.
                //Parameter rate mempunyai range dari 0.5 - 2.0. Rate 1.0 akan memainkan audio secara normal, 0.5 akan memainkan audio dengan kecepatan setengah, dan 2.0 akan memainkan audio 2 kali lipat lebih cepat.
            }
        }
    }
}