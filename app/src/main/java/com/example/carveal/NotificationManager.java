package com.example.carveal;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NotificationManager {
    private static final String PREFS_NAME = "notifications";
    private static final String KEY_NOTIFICATIONS = "notification_list";

    public static void saveNotification(Context context, Notification notification) {
        List<Notification> notifications = loadNotifications(context);
        notifications.add(0, notification);
        saveNotifications(context, notifications);
    }

    public static List<Notification> loadNotifications(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_NOTIFICATIONS, null);
        if (json == null) return new ArrayList<>();
        Type type = new TypeToken<List<Notification>>() {}.getType();
        return new Gson().fromJson(json, type);
    }

    private static void saveNotifications(Context context, List<Notification> notifications) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String json = new Gson().toJson(notifications);
        editor.putString(KEY_NOTIFICATIONS, json);
        editor.apply();
    }
}
