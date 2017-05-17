package com.example.souvikroy.meal.activiy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.souvikroy.meal.R;
import com.example.souvikroy.meal.others.ApplicationConstant;
import com.example.souvikroy.meal.others.Cus_Details;
import com.example.souvikroy.meal.others.SetGet;

import java.util.ArrayList;

/**
 * Created by SOUVIK ROY on 5/12/2016.
 */
public class Billing extends ActionBarActivity {
    ApplicationConstant app;
    Cus_Details cus;
    ArrayList<SetGet> arrlst;
    ListView lv;
    MyAdapter adpt;
    int sum;
    TextView tvsum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billing_list);
        app=(ApplicationConstant)getApplication();
        app.ReadyApplicationDatabase(Billing.this);
        cus=new Cus_Details(app);
        arrlst=cus.fetchdata();
        if(arrlst==null)
        {
            Toast.makeText(getApplicationContext(), "ADD MEAL TO CART BEFORE PAYAMENT", Toast.LENGTH_LONG).show();
        }
        else {
            sum = cus.sum();
            lv = (ListView) findViewById(R.id.itembillinglist);
            adpt = new MyAdapter(arrlst);
            lv.setAdapter(adpt);
            tvsum = (TextView) findViewById(R.id.totalsum);
            tvsum.setText("" + sum);
            Button btnpayment = (Button) findViewById(R.id.btnpayment);
            btnpayment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(Billing.this, Payment.class);
                    in.putExtra("amount", sum);
                    startActivity(in);
                }
            });
        }

    }
    public class MyAdapter extends BaseAdapter
    {
        public MyAdapter(ArrayList<SetGet> arrlst1) {
            arrlst=arrlst1;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return arrlst.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater lif=getLayoutInflater();
            View v=lif.inflate(R.layout.biling_item, parent, false);
            TextView id=(TextView) v.findViewById(R.id.itemid);
            TextView name=(TextView) v.findViewById(R.id.itemname);
            TextView price=(TextView) v.findViewById(R.id.itemprice);
            TextView sum=(TextView) v.findViewById(R.id.totalsum);
            SetGet obj=arrlst.get(position);
            String id1=obj.getItemid();
            id.setText(id1);
            String name1=obj.getItemname();
            name.setText(name1);
            String price1=obj.getItemprice();
            price.setText(price1);
            /*app.sum+=Integer.parseInt(price1);
            String a=""+app.sum;
            sum.setText(a);*/

            return v;

        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }
    public void actionlogout(MenuItem item) {
        Intent in=new Intent(Billing.this,Login.class);
        startActivity(in);
    }
}
