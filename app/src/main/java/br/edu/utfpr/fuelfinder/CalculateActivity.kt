package br.edu.utfpr.fuelfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class CalculateActivity : AppCompatActivity() {

    private lateinit var etConsumo1: EditText
    private lateinit var etValor1: EditText
    private lateinit var etConsumo2: EditText
    private lateinit var etValor2: EditText
    private lateinit var tvResult1: TextView
    private lateinit var tvResult2: TextView
    private lateinit var tvResult: TextView
    private lateinit var btLimpar: Button
    private lateinit var btCalcular: Button
    private lateinit var ivSearch1: ImageButton
    private lateinit var ivSearch2: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate)

        etConsumo1 = findViewById(R.id.etConsumo1)
        etValor1 = findViewById(R.id.etValor1)
        etConsumo2 = findViewById(R.id.etConsumo2)
        etValor2 = findViewById(R.id.etValor2)
        tvResult1 = findViewById(R.id.tvResult1)
        tvResult2 = findViewById(R.id.tvResult2)
        tvResult = findViewById(R.id.tvResult)
        btLimpar = findViewById(R.id.btLimpar)
        btCalcular = findViewById(R.id.btCalcular)
        ivSearch1 = findViewById(R.id.ivSearch1)
        ivSearch2 = findViewById(R.id.ivSearch2)

        btCalcular.setOnClickListener {
            btCalcularOnClick()
        }

        btLimpar.setOnClickListener {
            btLimparOnClick()
        }

        ivSearch1.setOnClickListener {
            btSearchOnClick1()
        }

        ivSearch2.setOnClickListener {
            btSearchOnClick2()
        }
    }

    private fun btSearchOnClick1() {
        val intent = Intent(this, ListarActivity::class.java)
        getResult1.launch(intent)
    }

    private fun btSearchOnClick2() {
        val intent = Intent(this, ListarActivity::class.java)
        getResult2.launch(intent)
    }

    private val getResult1 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            val codSelecionado = it.data?.getIntExtra("codRetorno", -1)

            if (codSelecionado != null) {
                val item = when (codSelecionado) {
                    0 -> 12.0  // Gasolina
                    1 -> 10.0   // Etanol
                    2 -> 8.0   // Diesel
                    3 -> 7.0   // Gás
                    else -> null
                }

                if (item != null) {
                    etConsumo1.setText(item.toString())
                }
            }
        }
    }

    private val getResult2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            val codSelecionado = it.data?.getIntExtra("codRetorno", -1)

            if (codSelecionado != null) {
                val item = when (codSelecionado) {
                    0 -> 12.0  // Gasolina
                    1 -> 10.0   // Etanol
                    2 -> 8.0   // Diesel
                    3 -> 7.0   // Gás
                    else -> null
                }

                if (item != null) {
                    etConsumo2.setText(item.toString())
                }
            }
        }
    }

    private fun btLimparOnClick() {
        etConsumo1.setText("")
        etValor1.setText("")
        etConsumo2.setText("")
        etValor2.setText("")
        tvResult1.text = getString(R.string.zeros)
        tvResult2.text = getString(R.string.zeros)
        tvResult.text = getString(R.string.zeros)
        etConsumo1.requestFocus()
        Toast.makeText(this, getString(R.string.toast_limpar), Toast.LENGTH_LONG).show()
    }

    private fun btCalcularOnClick() {
        if (etConsumo1.text.toString().isEmpty()) {
            etConsumo1.error = getString(R.string.erro_consumo)
            etConsumo1.requestFocus()
            return
        }
        if (etConsumo2.text.toString().isEmpty()) {
            etConsumo2.error = getString(R.string.erro_consumo)
            etConsumo2.requestFocus()
            return
        }

        if (etValor1.text.toString().isEmpty()) {
            etValor1.error = getString(R.string.erro_preco)
            etValor1.requestFocus()
            return
        }
        if (etValor2.text.toString().isEmpty()) {
            etValor2.error = getString(R.string.erro_preco)
            etValor2.requestFocus()
            return
        }

        try {
            val consumo1 = etConsumo1.text.toString().toDouble()
            val consumo2 = etConsumo2.text.toString().toDouble()
            val valor1 = etValor1.text.toString().toDouble()
            val valor2 = etValor2.text.toString().toDouble()

            val result1 = valor1 / consumo1
            val result2 = valor2 / consumo2

            val nf = NumberFormat.getNumberInstance(Locale.US)
            tvResult1.text = nf.format(result1).toDouble().toString()
            tvResult2.text = nf.format(result2).toDouble().toString()

            tvResult.text = when {
                result1 < result2 -> "Combustível 1"
                result1 > result2 -> "Combustível 2"
                else -> "Ambos"
            }
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Erro ao calcular: valores inválidos.", Toast.LENGTH_LONG).show()
        }
    }
}
