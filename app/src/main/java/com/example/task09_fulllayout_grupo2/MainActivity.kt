package com.example.task09_fulllayout_grupo2

import android.animation.ObjectAnimator
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.ImageButton
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE = 123 //Para el google Assistant
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Declaración de variables
        val cardViewNavigate = findViewById<ImageButton>(R.id.imageButton3)
        val cardViewNavigate2 = findViewById<CardView>(R.id.cardView4)
        val btnOpenPhone= findViewById<ImageButton>(R.id.imageButton4)
        val btnOpenWeather = findViewById<ImageButton>(R.id.imageButton2)
        val btnOpenPhone2 = findViewById<CardView>(R.id.cardView5)
        val btnGoogleAssistant = findViewById<ImageButton>(R.id.imageButton)
        val btnGoogleAssistant2 = findViewById<ImageButton>(R.id.imageButton10)
        val btnOpenMusicPlayer= findViewById<CardView>(R.id.cardView6)
        val btnOpenMusicPlayer2= findViewById<ImageButton>(R.id.imageButton5)
        val btnFlechita= findViewById<ImageButton>(R.id.imageButton11)


        //Pulsar para abrir el reproductor por defecto de musica
        btnOpenMusicPlayer.setOnClickListener {abrirYoutube()}
        btnOpenMusicPlayer2.setOnClickListener {abrirYoutube()}

        //Pulsar para abrir google maps con la ubicación de centro afuera
        cardViewNavigate.setOnClickListener {navigate()}
        cardViewNavigate2.setOnClickListener {navigate()}

        //Pulsar para llamar
        btnOpenPhone.setOnClickListener {call()}
        btnOpenPhone2.setOnClickListener {call()}


        //Pulsar widget de tiempo, para mandarte a google con la información del tiempo
        btnOpenWeather.setOnClickListener {
            val url = "https://www.google.com/search?q=tiempo"
            val abrirUrl = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(abrirUrl)
        }

        //Pulsar para abrir el google assistant
        btnGoogleAssistant.setOnClickListener {googleAssistant()}
        btnGoogleAssistant2.setOnClickListener{googleAssistant()}

        //Girar el imageButton 90 grados a la izq
        btnFlechita.setOnClickListener{
            val rotateAnimator = ObjectAnimator.ofFloat(btnFlechita, "rotation", btnFlechita.rotation, btnFlechita.rotation + 90f)
            rotateAnimator.duration = 0
            rotateAnimator.repeatCount = ObjectAnimator.REVERSE
            rotateAnimator.start()
        }
    }

    fun call(){
        val phoneNumber = "tel:640362212"
        val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))
        startActivity(dialIntent)
    }

    fun navigate(){
        val latitude = 37.7749
        val longitude = -122.4194

        val gmmIntentUri: Uri = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude(label)")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }


    fun abrirYoutube() {
        val videoId = "HIcSWuKMwOw"
        val youtubeIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))

        // Dejamos la condicion en caso de que youtube de algun problema lo abra desde el navegador
        if (youtubeIntent.resolveActivity(packageManager) != null) {
            startActivity(youtubeIntent)
        } else {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$videoId"))
            startActivity(webIntent)
        }
    }

    fun googleAssistant(){
        val intent = Intent(RecognizerIntent.ACTION_VOICE_SEARCH_HANDS_FREE)
        intent.putExtra(RecognizerIntent.EXTRA_SECURE, false)
        startActivityForResult(intent, REQUEST_CODE)
    }


}