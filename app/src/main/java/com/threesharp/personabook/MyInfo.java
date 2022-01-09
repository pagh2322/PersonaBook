package com.threesharp.personabook;

import android.content.Context;
import android.content.SharedPreferences;

public final class MyInfo {
    private static SharedPreferences prefs;
    Context context;
    MyInfo(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences("me", Context.MODE_PRIVATE);
    }
    public static void setType(int type) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("type", type);
        editor.apply();
    }
    public static void setSex(int sex) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("sex", sex);
        editor.apply();
    }
    public static int getSex() {
        return prefs.getInt("sex", 0);
    }
    public static int getType() {
        return prefs.getInt("type", 0);
    }
}
