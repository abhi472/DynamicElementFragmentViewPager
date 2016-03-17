package com.example.abhishek.dynamicelementfragmentviewpager;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;


import java.util.ArrayList;


public class CustomPagerAdapter extends FragmentStatePagerAdapter {

   public ArrayList<Integer> imageResources;


    public CustomPagerAdapter(FragmentManager fm, ArrayList<Integer> imageResources) {
        super(fm);
        this.imageResources=imageResources;
    }


    @Override
    public Fragment getItem(int position) {
        HolderFragm fragm = new HolderFragm();
        Bundle bundle = new Bundle();
        bundle.putInt("color",imageResources.get(position));
        fragm.setArguments(bundle);
        return fragm;
    }

    @Override
    public int getCount() {

        if (imageResources.size() == 0) {
            return 0;
        }
        return imageResources.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return super.isViewFromObject(view, object);
    }



}

