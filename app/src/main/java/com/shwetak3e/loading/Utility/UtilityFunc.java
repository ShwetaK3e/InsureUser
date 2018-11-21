package com.shwetak3e.loading.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.shwetak3e.loading.MainActivity;
import com.shwetak3e.loading.model.Booking_1;

import java.util.LinkedList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by shwetakumar on 7/27/17.
 */

public class UtilityFunc {


   public static void managePermissions(Activity activity, String[] permissionsReqd){

        List<String> permissions=new LinkedList<>();

        for(String permission:permissionsReqd){
            if(ActivityCompat.checkSelfPermission(activity, permission)!= PackageManager.PERMISSION_GRANTED){
                permissions.add(permission);
            }
        }

        if(permissions.size()!=0){
            ActivityCompat.requestPermissions(activity,permissions.toArray(new String[permissions.size()]),0);
        }
    }

    public static String getStringFromSharedPreference(Context context,String key){
        SharedPreferences pref=context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString(key,null);
    }

    public  static void setStringinPreference(Context context, String key, String value){
        if(value!=null) {
            SharedPreferences pref = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
            pref.edit().putString(key, value).apply();
        }
    }

    public static void hideKeyboard(Activity activity, boolean show) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        if(show) {
            imm.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
        }else{
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void updateShipmentID(final Context context){
        MainActivity.updateShipmentID.getSubject().subscribe(new Subscriber<Booking_1.Item>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Booking_1.Item item) {
              setStringinPreference(context,Constants.SHIPMENT_ID,item.getId().toUpperCase());
            }
        });
    }


}
