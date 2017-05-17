package com.example.souvikroy.meal.activiy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.souvikroy.meal.R;
import com.example.souvikroy.meal.others.JSONParser;
import com.example.souvikroy.meal.others.SetGet;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by SOUVIK ROY on 5/9/2016.
 */
public class ShowMeal extends ActionBarActivity {
    String weburl=new String("http://220.225.80.177/MealOrderApp/showmeal.asmx/GetMealItem?cat_id=");
    ArrayList<SetGet> arrlist=new ArrayList<SetGet>();
    ListView lv;
    ProgressDialog pd;
    DisplayImageOptions op;
    ImageView imagemeal;
    protected ImageLoader loader = ImageLoader.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catagory1);

       op = new DisplayImageOptions.Builder()

                .showStubImage(R.drawable.ic_action_name)

                .showImageForEmptyUri(R.drawable.ic_action_name)

                .cacheInMemory()

                .cacheOnDisc()

                .displayer(new RoundedBitmapDisplayer(20))

                .build();
        String id=getIntent().getExtras().getString("id");
        weburl=weburl+id;
        FindItem fitem=new FindItem();
        fitem.execute();
        lv=(ListView) findViewById(R.id.catagory1);

    }


    public void loadJson()
    {
        try
        {
            JSONParser jsonparser=new JSONParser();
            JSONObject jsonobject=jsonparser.getJsonFromURL(weburl);
            JSONArray jsonarry=jsonobject.getJSONArray("MealItem");
            for(int i=0;i<jsonarry.length();i++)
            {
                JSONObject jobj=jsonarry.getJSONObject(i);
                String id=jobj.getString("ItemId");
                String name=jobj.getString("ItemName");
                String description=jobj.getString("ItemDescription");
                String price=jobj.getString("ItemPrice");
                String imageurl=jobj.getString("ItemImage");
                SetGet setget=new SetGet();
                setget.setItemid(id);
                setget.setItemname(name);
                setget.setItemdescription(description);
                setget.setItemprice(price);
                setget.setItemimage(imageurl);
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
            LayoutInflater lif=(LayoutInflater) ShowMeal.this.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View v=lif.inflate(R.layout.menudetails, arg2, false);
            TextView mealname=(TextView)v.findViewById(R.id.mealname);
            TextView mealprice=(TextView)v.findViewById(R.id.mealprice);
            imagemeal=(ImageView)v.findViewById(R.id.imgviewer);
            SetGet obj;
            obj=arrlist.get(arg0);
            String mealname1 =obj.getItemname();
            mealname.setText(mealname1);
            String mealprice1 =obj.getItemprice();
            mealprice.setText(mealprice1);
            String mealimage1=obj.getItemimage();
            loader.displayImage(mealimage1, imagemeal, op,null);
            //Toast.makeText(getApplicationContext(), mealimage1, Toast.LENGTH_LONG).show();
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
                    Intent in=new Intent(ShowMeal.this,MealDetail.class);
                    SetGet obj;
                    obj=arrlist.get(i);
                    in.putExtra("mealid",obj.getItemid());
                    in.putExtra("mealname",obj.getItemname());
                    in.putExtra("mealprice",obj.getItemprice());
                    in.putExtra("mealimage",obj.getItemimage());
                    startActivity(in);
                }
            });


        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            pd=new ProgressDialog(ShowMeal.this);
            pd.setMessage("loading.....");
            pd.show();
        }

    }
   /* public void actionlogout(MenuItem item) {
        Intent in=new Intent(ShowMeal.this,Login.class);
        startActivity(in);
    }*/
}
