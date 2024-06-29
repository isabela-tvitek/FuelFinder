package br.edu.utfpr.fuelfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btIniciar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btIniciar = findViewById( R.id.btIniciar )

        btIniciar.setOnClickListener {
            btCalculateOnClick();
        }
    }
    private fun btCalculateOnClick() {
        val intent = Intent( this, CalculateActivity::class.java )
        startActivity( intent )
    }
}