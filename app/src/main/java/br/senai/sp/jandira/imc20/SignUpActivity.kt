package br.senai.sp.jandira.imc20

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import model.User

class SignUpActivity : AppCompatActivity() {

    lateinit var editName: EditText
    lateinit var editEmail: EditText
    lateinit var editPassword: EditText
    lateinit var editWeight: EditText
    lateinit var editHeight: EditText
    lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar!!.hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        //instânciar os componentes(view)
        editName = findViewById(R.id.editTextName);
        editEmail = findViewById(R.id.editTextE_mail);
        editPassword = findViewById(R.id.editTextPassword);
        editWeight = findViewById(R.id.editTextWeight);
        editHeight = findViewById(R.id.editTextHeight);
        buttonSave = findViewById(R.id.buttonSave);

        //Clique no Botão
        buttonSave.setOnClickListener {
            saveUser()
        }

    }

    private fun saveUser() {

        val user = User();
        user.id = 1;
        user.name = editName.text.toString();
        user.email = editEmail.text.toString();
        user.password = editPassword.text.toString();
        user.weight = editWeight.text.toString().toInt();
        user.height = editHeight.text.toString().toDouble();

        //Gravar usuário no shared preferences
        //Step 1 - Obter uma instância do sharedPreferences
        val dados = getSharedPreferences("dados", MODE_PRIVATE)

        //Step 2 - Criar um editor para o arquivo

        val editor = dados.edit()

        //step 3 - Inserir dados no arquivo sharedPrefereces
        editor.putInt("id", user.id)
        editor.putString("name", user.name)
        editor.putString("email", user.email)
        editor.putString("password", user.password)
        editor.putInt("weight", user.weight)
        editor.putFloat("height", user.height.toFloat())

        if(editor.commit()){
            Toast.makeText(this, "Usuário gravado com sucesso!", Toast.LENGTH_SHORT).show();
            finish(); //Fecha a Activity
        } else{
            Toast.makeText(this, "Ocorreu um erro na gravação!", Toast.LENGTH_SHORT).show();
        }

    }
}