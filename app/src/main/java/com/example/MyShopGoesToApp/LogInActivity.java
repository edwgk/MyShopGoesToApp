package com.example.MyShopGoesToApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LogInActivity extends AppCompatActivity {


    TextInputEditText username,password;
    private TextView tv2;
    Button btnlogin;
    DBHelper DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        tv2=(TextView) findViewById(R.id.tvRegistroNuevoUsuario);
        tv2.setClickable(true);
        String texto1="<a href=''>Ir a registro</a>";
        tv2.setText(Html.fromHtml(texto1));

        username=(TextInputEditText) findViewById(R.id.tilRegistroUsuario);
        password=(TextInputEditText) findViewById(R.id.tilRegistroUsuarioPassword);
        btnlogin=(Button) findViewById(R.id.btnRegistroUsuarioLogIn);
        DB=new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LogInActivity.this, "Ingresa todos los campos", Toast.LENGTH_SHORT).show();
                    else{
                        Boolean checkuserpass=DB.checkusernamepassword(user,pass);
                        if(checkuserpass==true){
                            Toast.makeText(LogInActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LogInActivity.this, "Datos no válidos", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


        });

    }

    public void goToRegistroActivity(View view){
        Intent newIntent=new Intent(this, RegistroUsuarioActivity.class);
        startActivity(newIntent);
    }








}