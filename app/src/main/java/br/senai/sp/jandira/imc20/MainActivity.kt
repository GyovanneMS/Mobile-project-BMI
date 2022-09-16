package br.senai.sp.jandira.imc20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import br.senai.sp.jandira.imc20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var textSignUp: TextView

    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide();

        binding.textSignUp.setOnClickListener {
            var abrirSignUpActivity = Intent(this, SignUpActivity::class.java)
            startActivity(abrirSignUpActivity)
        }

        binding.buttonLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {

        if(validar()){
            val email = binding.editTextEmailAddress.text.toString();
            val pass = binding.editTextPassword.text.toString();

            //Abrir o SHarePreferences
            val dados = getSharedPreferences("dados", MODE_PRIVATE)

            val emailSp = dados.getString("email", "E-mail não encontrado")
            val passSp = dados.getString("password", "Senha não encontrada")

            //Verificar se os dados de autenticação estão corretos
            if(email == emailSp && pass == passSp){
                val openCalculate = Intent(this, CalculateBmiActivity::class.java)
                startActivity(openCalculate);
            } else {
                Toast.makeText( this, "Authentication failed!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validar(): Boolean {
        if(binding.editTextEmailAddress.text.isEmpty()){
            binding.editTextEmailAddress.error = "E-mail is required!!"
            return false;
        }
        if(binding.editTextPassword.text.isEmpty()){
            binding.editTextPassword.error = "Password is required!!"
            return false;
        }
        return true
    }
}


