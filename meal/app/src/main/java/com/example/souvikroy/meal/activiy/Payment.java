package com.example.souvikroy.meal.activiy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.souvikroy.meal.R;
import com.example.souvikroy.meal.others.JSONParser;

import org.json.JSONObject;

/**
 * Created by SOUVIK ROY on 5/12/2016.
 */
public class Payment extends ActionBarActivity {
    String weburl="http://220.225.80.177/MealOrderApp/showmeal.asmx/Transaction?";
    EditText card,cvc,expdate,address,email;
    ProgressDialog pd;
    Button pay;
    String error="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymentopt);
        card= (EditText) findViewById(R.id.card);
        cvc= (EditText) findViewById(R.id.cvc);
        expdate= (EditText) findViewById(R.id.expdate);
        address= (EditText) findViewById(R.id.address);
        email= (EditText) findViewById(R.id.email);


        String amt=getIntent().getExtras().getString("amount");
        weburl=weburl+"cardNo="+card.getText().toString()+"&cvvCode="+cvc.getText().toString()+"&expdate="+expdate.getText().toString()+"&amount="+amt+"&emailid="+email.getText().toString()+"&address="+address.getText().toString();
        pay= (Button) findViewById(R.id.btnpay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(card.getText().toString().length()!=16)
                {
                    error=error+"ENTER CORRECT CARD NUMBER\n";
                }
                if (cvc.getText().toString().length()!=3)
                {
                    error=error+"ENTER CORRECT CCV NUMBER\n";
                }
                if((expdate.getText().toString().length()!=2)||(Integer.parseInt(expdate.getText().toString())<=0)||(Integer.parseInt(expdate.getText().toString())>31))
                {
                    error=error+"ENTER CORRECT EXPIRE NUMBER\n";
                }
                if (email.getText().toString().length()==0)
                {
                    error=error+"ENTER EMAIL ID\n";
                }
                if (address.getText().toString().length()==0)
                {
                    error=error+"ENTER ADDRESS\n";
                }
                if(error.equals(""))
                {
                    FindItem fitem=new FindItem();
                    fitem.execute();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),error, Toast.LENGTH_LONG).show();
                    error="";
                }

            }
        });


    }
    public void loadJson()
    {
        try
        {
            JSONParser jsonparser=new JSONParser();
            JSONObject jsonobject=jsonparser.getJsonFromURL(weburl);
            }catch(Exception e)
        {

        }
    }
    public class FindItem extends AsyncTask<String, String, String>
    {

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            loadJson();
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            pd.dismiss();
            Intent in=new Intent(Payment.this,Login.class);
            startActivity(in);
            Toast.makeText(getApplicationContext(), "Transaction successful", Toast.LENGTH_LONG).show();

        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd=new ProgressDialog(Payment.this);
            pd.setMessage("loading.....");
            pd.show();
        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }
   public void actionlogout(MenuItem item) {
        Intent in=new Intent(Payment.this,Login.class);
        startActivity(in);
    }
}
