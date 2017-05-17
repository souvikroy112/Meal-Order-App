package com.example.souvikroy.meal.activiy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.souvikroy.meal.R;
import com.example.souvikroy.meal.others.ApplicationConstant;
import com.example.souvikroy.meal.others.Cus_Details;


public class Login extends ActionBarActivity {
    ApplicationConstant app;

    EditText eUsername,ePassword;
    Button b_login,b_register;
    Boolean b;
    Cus_Details cus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        app=(ApplicationConstant)getApplication();
        app.ReadyApplicationDatabase(Login.this);

        eUsername=(EditText)findViewById(R.id.log_username);
        ePassword=(EditText)findViewById(R.id.log_password);

        b_login=(Button)findViewById(R.id.btn_login);
        b_register=(Button) findViewById(R.id.btn_reg);
        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gUsername=eUsername.getText().toString();
                String gPassword=ePassword.getText().toString();


                cus=new Cus_Details(app);

                b=cus.authenticateData(gUsername,gPassword);

                if(b==true){
                    cus.deleteMealItem();
                    Intent in=new Intent(Login.this,GetCatagory.class);
                    startActivity(in);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Login is not successful", Toast.LENGTH_LONG).show();
                }
            }
        });
        b_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(Login.this,Registration.class);
                startActivity(in);
            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
