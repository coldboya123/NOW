package com.example.now.Model.Object;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.now.R;

public class MyFragmentManager {
    public static FragmentManager getFragmentManager(Context context){
        return ((AppCompatActivity)context).getSupportFragmentManager();
    }

    public static FragmentManager getChildFragmentManager(Context context){
        return getFragmentManager(context).getFragments().get(0).getChildFragmentManager();
    }

    public static void addFragment(Context context, int frameID, Fragment fragment, String tag){
        getFragmentManager(context).beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                        android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .add(frameID, fragment, tag)
                .addToBackStack(null)
                .commit();
    }

    public static void addOtherFragment(Context context, int frameID, Fragment oldFragment, Fragment newFragment, String tag){
        getFragmentManager(context).beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                        android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .detach(oldFragment)
                .add(frameID, newFragment, tag)
                .addToBackStack(null)
                .commit();
    }

    public static void replaceFragment(Context context, int frameID, Fragment fragment, String tag){
        getFragmentManager(context).beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
                android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(frameID, fragment, tag)
                .addToBackStack(null)
                .commit();
    }

    public static Fragment getFragmentByTag(Context context, String tag){
        return getFragmentManager(context).findFragmentByTag(tag);
    }

    public static int getBackStackEntryCount(Context context){
        return getFragmentManager(context).getBackStackEntryCount();
    }

    public static int getChildBackStackEntryCount(Context context){
        return getChildFragmentManager(context).getBackStackEntryCount();
    }

    public static void popBackStack(Context context){
        getFragmentManager(context).popBackStack();
    }

    public static void popBackStackChild(Context context){
        getChildFragmentManager(context).popBackStack();
    }
}
