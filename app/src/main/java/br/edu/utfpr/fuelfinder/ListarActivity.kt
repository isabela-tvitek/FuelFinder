package br.edu.utfpr.fuelfinder

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListarActivity : AppCompatActivity() {

    private lateinit var lvCombustiveis: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)

        lvCombustiveis = findViewById(R.id.lvCombustiveis)

        lvCombustiveis.setOnItemClickListener { parent, view, position, id ->
            intent.putExtra("codRetorno", position)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}
