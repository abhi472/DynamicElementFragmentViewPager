package com.example.abhishek.dynamicelementfragmentviewpager.Adapters;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;


import com.example.abhishek.dynamicelementfragmentviewpager.Fragments.HolderFragm;
import com.example.abhishek.dynamicelementfragmentviewpager.Models.ContestContent;

import java.util.ArrayList;


public class CustomPagerAdapter extends FragmentStatePagerAdapter {

   public ArrayList<ContestContent> imageResources;


    public CustomPagerAdapter(FragmentManager fm, ArrayList<ContestContent> imageResources) {
        super(fm);
        this.imageResources=imageResources;
    }


    @Override
    public Fragment getItem(int position) {
        HolderFragm fragm = new HolderFragm();
        Bundle bundle = new Bundle();
        bundle.putString("image",imageResources.get(position).image);
        bundle.putString("desc",imageResources.get(position).shortDesc);
        bundle.putString("title",imageResources.get(position).title);
        bundle.putString("id",imageResources.get(position).id);
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

