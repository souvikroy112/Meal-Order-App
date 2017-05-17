package com.example.souvikroy.meal.others;

/**
 * Created by SOUVIK ROY on 5/6/2016.
 */

import android.app.Application;
import android.content.Context;
import android.database.SQLException;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.IOException;

public class ApplicationConstant extends Application {   //Applicaton is a global class


    public DbHelper myDbHelper;



    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
    }


    // Create database if 1st time run the application
    public void ReadyApplicationDatabase(Context context) {

        myDbHelper = new DbHelper(context);

        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            myDbHelper.openDataBase();

        } catch (SQLException sqle) {

            throw sqle;

        }
    }

    public static void initImageLoader(Context context) {



        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)

                .threadPriority(Thread.NORM_PRIORITY - 2)

                .denyCacheImageMultipleSizesInMemory()

                .discCacheFileNameGenerator(new Md5FileNameGenerator())

                .tasksProcessingOrder(QueueProcessingType.LIFO)

                .enableLogging()

                .build();



        ImageLoader.getInstance().init(config);

    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }



}