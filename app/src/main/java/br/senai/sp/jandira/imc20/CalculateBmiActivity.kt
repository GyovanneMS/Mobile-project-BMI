package br.senai.sp.jandira.imc20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.senai.sp.jandira.imc20.databinding.ActivityCalculateBmiBinding
import br.senai.sp.jandira.imc20.databinding.ActivityMainBinding

class CalculateBmiActivity : AppCompatActivity() {

    lateinit var binding: ActivityCalculateBmiBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalculateBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding = ActivityCalculateBmiBinding.inflate(layoutInflater)
        setContentView(binding.root);

        loadProfile()

        binding.buttonCalculate.setOnClickListener {
            bmiCalculate()
        }

    }

    private fun bmiCalculate() {
        val openResult = Intent(this, ResultActivity::class.java)

        //Enviar dados de uma Activity para outra
        openResult.putExtra("peso", binding.editWeight.text.toString().toInt())
        if(binding.editHeight.text.isEmpty()){
            openResult.putExtra("altura", 0.0)
        } else{
            openResult.putExtra("altura", binding.editHeight.text.toString().toFloat())
        }
        startActivity(openResult)
    }

    private fun loadProfile() {
        //Abrir o arquivo SharedPreferences
        val dados = getSharedPreferences("dados", MODE_PRIVATE);

        binding.textViewUserName.text = dados.getString("name", "")
        binding.textViewUserEmail.text = dados.getString("email", "")
        binding.textViewWeight.text = "${getString(R.string.weight)} ${dados.getInt("weight", 0)} Kg"
        binding.textViewHeight.text = "${getString(R.string.height)} ${dados.getFloat("height", 0.0f)} m"

    }
}