package com.bw.erzhoumoni.utile;

import android.content.Context;
import android.content.SharedPreferences;

/*
 *@Auther:cln
 *@Date: 2020/3/28
 *@Time:10:27
 *@Description:
 * */public class SpUtile {
     public static final String USERINFO="userinfo";
     public static final String USERID="userId";
     public static final String USERSESSIONID="usersessionId";
     public static void putString(Context context,String name,String key,String value){
         SharedPreferences sharedPreferences = context.getSharedPreferences(name,Context.MODE_PRIVATE);
         SharedPreferences.Editor edit = sharedPreferences.edit();
         edit.putString(key,value);
         edit.commit();

     }
     public static String getString(Context context,String name,String key){
         SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
         return sharedPreferences.getString(key,"");
     }
}
