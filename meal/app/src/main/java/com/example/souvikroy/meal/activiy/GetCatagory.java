package com.example.souvikroy.meal.activiy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.souvikroy.meal.R;
import com.example.souvikroy.meal.others.JSONParser;
import com.example.souvikroy.meal.others.SetGet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by SOUVIK ROY on 5/9/2016.
 */
public class GetCatagory extends ActionBarActivity {
    String weburl="http://220.225.80.177/MealOrderApp/showmeal.asmx/GetCategory";
    ArrayList<SetGet> arrlist=new ArrayList<SetGet>();
    ListView lv;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catagory);
        FindItem fitem=new FindItem();
        fitem.execute();
        lv=(ListView) findViewById(R.id.catagory);
    }
    public void loadJson()
    {
        try
        {
            JSONParser jsonparser=new JSONParser();
            JSONObject jsonobject=jsonparser.getJsonFromURL(weburl);
            JSONArray jsonarry=jsonobject.getJSONArray("Category");
            for(int i=0;i<jsonarry.length();i++)
            {
                JSONObject jobj=jsonarry.getJSONObject(i);
                String id=jobj.getString("CategoryId");
                String name=jobj.getString("CategoryName");
                SetGet setget=new SetGet();
                setget.setCategoryId(id);
                setget.setCategoryName(name);
                arrlist.add(setget);
            }
        }catch(Exception e)
        {

        }
    }
public class MyAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return arrlist.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        // TODO Auto-generated method stub
        LayoutInflater lif=(LayoutInflater) GetCatagory.this.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View v=lif.inflate(R.layout.menu, arg2, false);
        TextView catagoryname=(TextView)v.findViewById(R.id.catagoryname);
        SetGet obj;
        obj=arrlist.get(arg0);
        String catagoryname1 =obj.getCategoryName();
        catagoryname.setText(catagoryname1);
        return v;
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

        MyAdapter adpt=new MyAdapter();
        lv.setAdapter(adpt);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in=new Intent(GetCatagory.this,ShowMeal.class);
                SetGet obj;
                obj=arrlist.get(i);
                in.putExtra("id",obj.getCategoryId());

                startActivity(in);
            }
        });
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        pd=new ProgressDialog(GetCatagory.this);
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
        Intent in=new Intent(GetCatagory.this,Login.class);
        startActivity(in);
    }

}

