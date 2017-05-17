package com.example.souvikroy.meal.activiy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.souvikroy.meal.R;
import com.example.souvikroy.meal.others.ApplicationConstant;
import com.example.souvikroy.meal.others.Cus_Details;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by SOUVIK ROY on 5/11/2016.
 */
public class MealDetail extends ActionBarActivity {
    ApplicationConstant app;
    boolean b_reg;
    Cus_Details bbd;
   DisplayImageOptions op;
   ImageView imagemeal;
    protected ImageLoader loader = ImageLoader.getInstance();
    TextView name,price;
    Button btn_cart1,btn_payment1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menudecription);
        app=(ApplicationConstant)getApplication();
        app.ReadyApplicationDatabase(MealDetail.this);
        op = new DisplayImageOptions.Builder()

                .showStubImage(R.drawable.ic_action_name)

                .showImageForEmptyUri(R.drawable.ic_action_name)

                .cacheInMemory()

                .cacheOnDisc()

                .displayer(new RoundedBitmapDisplayer(20))

                .build();
        final String itemid=getIntent().getExtras().getString("mealid");
        final String itemname=getIntent().getExtras().getString("mealname");
        final String itemprice=getIntent().getExtras().getString("mealprice");
        String mealimage=getIntent().getExtras().getString("mealimage");
        imagemeal= (ImageView) findViewById(R.id.imgviewinner);
        loader.displayImage(mealimage, imagemeal, op,null);
        name= (TextView) findViewById(R.id.innername);
        price= (TextView) findViewById(R.id.innerprice);
        name.setText(itemname);
        price.setText(itemprice);
        btn_cart1=(Button)findViewById(R.id.btncart);
        btn_payment1= (Button) findViewById(R.id.btnbilling);
        btn_cart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bbd=new Cus_Details(app);
                b_reg=bbd.insertCartDetails(itemid,itemname,itemprice);

                if(b_reg==true){
                    Toast.makeText(getApplicationContext(), "Added to the cart", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Not Added to the cart", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_payment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MealDetail.this,Billing.class);
                startActivity(in);
            }
        });
        }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }
    public void actionlogout(MenuItem item) {
        Intent in=new Intent(MealDetail.this,Login.class);
        startActivity(in);
    }
}
