package com.example.souvikroy.meal.others;

/**
 * Created by SOUVIK ROY on 5/6/2016.
 */

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

public class Cus_Details {

    ApplicationConstant app;

    public Cus_Details(ApplicationConstant app) {
        //super();
        this.app = app;
    }

    public boolean insertData(String name,String address,String email,String phone,String username,String password)
    {
        ContentValues cv=new ContentValues();
        cv.put("cus_name", name);
        cv.put("cus_address", address);
        cv.put("cus_email", email);
        cv.put("cus_phone",phone);
        cv.put("usename", username);
        cv.put("password", password);

        long success=-1;

        try{
            success=app.myDbHelper.MyDB().insert("Customer_Details", null, cv);
        }
        catch(Exception e){
            e.printStackTrace();
            success=-1;
        }

        if(success>-1){
            return true;
        }
        else{
            return false;
        }



    }
    public boolean insertCartDetails(String itemId,String itemName,String itemPrice)
    {
        ContentValues cv=new ContentValues();
        cv.put("item_id", itemId);
        cv.put("item_name",itemName);
        cv.put("item_price",itemPrice);
        long success=-1;

        try{
            success=app.myDbHelper.MyDB().insert("cart_details", null, cv);
        }
        catch(Exception e){
            e.printStackTrace();
            success=-1;
        }

        if(success>-1){
            return true;
        }
        else{
            return false;
        }

    }
    public ArrayList<SetGet> fetchdata()
    {
        if(app.myDbHelper.MyDB()==null)
        {
            app.myDbHelper.openDataBase();
        }
        ArrayList<SetGet> arrlist = null;
        String sql="select item_id,item_name,item_price from cart_details";
        Cursor cursor=null;
            try
            {
                cursor=app.myDbHelper.MyDB().rawQuery(sql, null);
                if(cursor.getCount()>0)
                {
                    arrlist=new ArrayList<SetGet>();
                    while(cursor.moveToNext())
                    {
                    SetGet obj=new SetGet();
                    obj.setItemid(cursor.getString(cursor.getColumnIndex("item_id")));
                    obj.setItemname(cursor.getString(cursor.getColumnIndex("item_name")));
                    obj.setItemprice("" + cursor.getInt(cursor.getColumnIndex("item_price")));
                    arrlist.add(obj);
                    }
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            if(cursor!=null && cursor.isClosed())
            {
            cursor.close();
        }

        return arrlist;

    }
   public int sum()
   {
       String sql="select sum(item_price) from cart_details";
       int summation=0;
       Cursor cursor=null;
       try
       {
           cursor=app.myDbHelper.MyDB().rawQuery(sql, null);
           if(cursor.getCount()>0)
           {
               while(cursor.moveToNext())
               {
                   summation=cursor.getInt(cursor.getColumnIndex("sum(item_price)"));
               }
           }
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       if(cursor!=null && cursor.isClosed())
       {
           cursor.close();
       }

       return summation;
   }
    public boolean authenticateData(String username,String password)
    {
        String username_DB,password_DB;
        boolean flag=false;
        if(app.myDbHelper.MyDB()==null)
        {
            app.myDbHelper.openDataBase();
        }
        String sql="select usename,password from Customer_Details";
        Cursor cursor=null;
        try
        {
            cursor=app.myDbHelper.MyDB().rawQuery(sql, null);
            if (cursor.getCount()>0)
            {
               while(cursor.moveToNext())
                {

                    username_DB=cursor.getString(cursor.getColumnIndex("usename"));
                    password_DB=cursor.getString(cursor.getColumnIndex("password"));
                    if(username_DB.equals(username)&&password_DB.equals(password))
                    {
                        flag=true;
                    }

                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if(cursor!=null && cursor.isClosed())
        {
            cursor.close();
        }

        return flag;
    }
    public void deleteMealItem() {
        try {

            app.myDbHelper.MyDB().delete("cart_details",null,null);
            //setTitle("title");
        } catch (Exception e) {

        }

    }
   /* public boolean updateData(String id,String name,String email,String phno,String password)
    {
        ContentValues cv=new ContentValues();
        cv.put("Emp_Name", name);
        cv.put("Emp_Email",email);
        cv.put("Emp_Phno", phno);
        cv.put("Emp_Password", password);
        long success=-1;
        try
        {
            success=app.myDbHelper.MyDB().update("Emp_Details",cv, "Emp_Id"+"=?", new String[]{id});
        }catch(Exception e)
        {
            e.printStackTrace();
            success=-1;
        }
        if(success>-1)
            return true;
        else
            return false;

    }*/


}
