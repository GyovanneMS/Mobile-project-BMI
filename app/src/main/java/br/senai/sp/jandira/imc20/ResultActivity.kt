package br.senai.sp.jandira.imc20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.senai.sp.jandira.imc20.databinding.ActivityCalculateBmiBinding
import br.senai.sp.jandira.imc20.databinding.ActivityResultBinding
import br.senai.sp.jandira.imc20.utils.getBmi
import br.senai.sp.jandira.imc20.utils.getStatusBmi

class ResultActivity : AppCompatActivity() {

    lateinit var binding: ActivityResultBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        supportActionBar!!.hide()

        binding = ActivityResultBinding.inflate(layoutInflater);
        setContentView((binding.root));

        //Recuperando valores que estão na Intent
        val peso = intent.getIntExtra("peso",0)
        var altura = intent.getFloatExtra("altura", 0.0f);

        if(altura == 0.0f){
            val dados = getSharedPreferences("dados", MODE_PRIVATE);
            altura = dados.getFloat("height", 0.0f);
        } else {
            val dados = getSharedPreferences("dados", MODE_PRIVATE);
            dados.get
        }

        val bmi = getBmi(peso, altura.toDouble()).toString().toFloat()
        val bmiResult = String.format("%.2f", bmi)

        binding.textViewResult.text = "$bmiResult"
        binding.textViewStatus.text = getStatusBmi(bmiResult.toDouble(), this)
    }
}