package com.example.MyShopGoesToApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class RegistroUsuarioActivity extends AppCompatActivity {

    TextInputEditText username, password,repassword;
    Button signup,signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=(TextInputEditText) findViewById(R.id.tilRegistroNuevoUsuario);
        password=(TextInputEditText) findViewById(R.id.tilRegistroNuevoUsuarioiPassword);
        repassword=(TextInputEditText) findViewById(R.id.tilRegistroNuevoRepassword);

        signup=(Button) findViewById(R.id.btnRegistroNuevoUsuarioiSignup);
        signin=(Button) findViewById(R.id.btnRegistroNuevoUsuarioiSignin);
        DB=new DBHelper(this);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String repass=repassword.getText().toString();


                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(RegistroUsuarioActivity.this, "Ingresa todos los campos", Toast.LENGTH_SHORT).show();
                else {
                    if(pass.equals(repass)){
                        Boolean checkuser=DB.checkusername(user);
                        if (checkuser==false){
                            Boolean insert=DB.insertData(user,pass);
                            if (insert==true){
                                Toast.makeText(RegistroUsuarioActivity.this, "Registro Finalizado", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegistroUsuarioActivity.this, "Proceso de registro falló", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(RegistroUsuarioActivity.this, "El usuario existe, inicie sesión", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegistroUsuarioActivity.this, "Contraseñas no concuerdan", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LogInActivity.class);
                startActivity(intent);

            }
        });




    }

}