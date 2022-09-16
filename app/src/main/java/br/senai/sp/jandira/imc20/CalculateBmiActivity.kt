package br.senai.sp.jandira.imc20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.senai.sp.jandira.imc20.databinding.ActivityCalculateBmiBinding
import br.senai.sp.jandira.imc20.databinding.ActivityMainBinding
import model.User

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
            UpdateUser()
            bmiCalculate()
        }

    }

    private fun UpdateUser(){
    val usuario = User();
    val dados = getSharedPreferences("dados", MODE_PRIVATE);
    val editor = dados.edit()

    if(binding.editWeight.text.isEmpty()){
        binding.editWeight.error = "You need to put your weight!!"
    }
    usuario.weight = binding.editWeight.text.toString().toInt();
    editor.putInt("weight", usuario.weight);
    if (editor.commit()){
        Toast.makeText(this, "Weight was changed", Toast.LENGTH_SHORT).show();
        finish()
    } else {
        Toast.makeText(this, "Weight had a error", Toast.LENGTH_SHORT).show();
    }

    if(binding.editHeight.text.isNotEmpty()){
        usuario.height = binding.editHeight.text.toString().toDouble();
        editor.putFloat("height", usuario.height.toFloat());
    }
        if(editor.commit()){
            Toast.makeText(this, "Height was changed", Toast.LENGTH_SHORT).show();
            finish()
        } else {
            Toast.makeText(this, "Height had a error", Toast.LENGTH_SHORT).show();
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