package com.fake.shopee.shopeefake;

import android.content.SharedPreferences;

/**
 * Created by Riandy on 12/11/2017.
 */

public class session_class {
    public static String username="";
    public static SharedPreferences shp;

    String svr = shp.getString("svr","");
    String db = shp.getString("db","");
    String id = shp.getString("id","sa" );
    String pass = shp.getString("pass","12345");
    String port = shp.getString("port","");
    String instance = shp.getString("instance","");

     edit.putString("svr", server);
                    edit.commit();
                    edit.putString("db", db);
                    edit.commit();
}
