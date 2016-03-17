package com.example.abhishek.dynamicelementfragmentviewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class FirstPage extends AppCompatActivity {
    ArrayList<Integer> imageResources = new ArrayList<>();
    ArrayList<HolderFragm> fragms = new ArrayList<>();
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(toolbar);

        imageResources.add(Color.BLACK);
        imageResources.add(Color.BLUE);
        imageResources.add(Color.GREEN);
        imageResources.add(Color.GRAY);
        imageResources.add(Color.YELLOW);
        imageResources.add(Color.RED);
        viewPager=(ViewPager)findViewById(R.id.tab_viewpager);
        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(12);

        CustomPagerAdapter vpa=new CustomPagerAdapter(getSupportFragmentManager(),imageResources);
        viewPager.setAdapter(vpa);
        viewPager.setCurrentItem(0,true);









    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
