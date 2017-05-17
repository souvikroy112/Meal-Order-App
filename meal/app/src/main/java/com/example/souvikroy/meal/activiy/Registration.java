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

/**
 * Created by SOUVIK ROY on 5/6/2016.
 */
public class Registration extends ActionBarActivity{
    ApplicationConstant app;
    EditText eName,eAddress,eEmail,ePhone,eUsername,ePassword;
    Button btn_rg;
    boolean b_reg;
    Cus_Details bbd;
    String error="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        app=(ApplicationConstant)getApplication();
        app.ReadyApplicationDatabase(Registration.this);
        eName=(EditText)findViewById(R.id.cus_name);
        eAddress=(EditText)findViewById(R.id.cus_address);
        eEmail=(EditText)findViewById(R.id.cus_email);
        ePhone=(EditText)findViewById(R.id.cus_phone);
        eUsername=(EditText)findViewById(R.id.usename);
        ePassword=(EditText)findViewById(R.id.password);

        btn_rg= (Button) findViewById(R.id.btn_user_reg);
        btn_rg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gName=eName.getText().toString();
                String gAddress=eAddress.getText().toString();
                String gEmail=eEmail.getText().toString();
                String gPhone=ePhone.getText().toString();
                String gUsername=eUsername.getText().toString();
                String gPassword=ePassword.getText().toString();
                if(gName.length()==0)
                {
                    error=error+"ENTER NAME\n";
                }
                if (gAddress.length()==0)
                {
                    error=error+"ENTER ADDRESS\n";
                }
                if((gPhone.length()!=10))
                {
                    error=error+"ENTER CORRECT PHONE NUMBER\n";
                }
                if (gUsername.length()==0)
                {
                    error=error+"ENTER USERNAME\n";
                }
                if (gPassword.toString().length()==0)
                {
                    error=error+"ENTER PASSWORD\n";
                }
                if(error=="")
                {
                    bbd=new Cus_Details(app);
                    b_reg=bbd.insertData(gName,gAddress,gEmail,gPhone,gUsername,gPassword);

                    if(b_reg==true){
                        Toast.makeText(getApplicationContext(), "Registration Successfully", Toast.LENGTH_LONG).show();
                        Intent in=new Intent(Registration.this,Login.class);
                        startActivity(in);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Registration Not Successful", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),error, Toast.LENGTH_LONG).show();
                    error="";
                }


            }
        });
    }
}
