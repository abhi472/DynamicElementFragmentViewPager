package com.example.abhishek.dynamicelementfragmentviewpager.Activities;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abhishek.dynamicelementfragmentviewpager.Adapters.CustomPagerAdapter;
import com.example.abhishek.dynamicelementfragmentviewpager.Callbacks.ICallBack;
import com.example.abhishek.dynamicelementfragmentviewpager.Models.ContestContent;
import com.example.abhishek.dynamicelementfragmentviewpager.Models.ParentContent;
import com.example.abhishek.dynamicelementfragmentviewpager.R;
import com.example.abhishek.dynamicelementfragmentviewpager.Singletons.ApiManager;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class FirstPage extends AppCompatActivity implements ICallBack, ViewPager.OnPageChangeListener {
    ArrayList<ContestContent> resources = new ArrayList<>();
    ViewPager viewPager;
    ObjectMapper om = new ObjectMapper();
    ProgressDialog progressDialog;
    LinearLayout linearLayout;
    int dotsCount;
    ImageView[] dots;
    String url = "http://staging.lafalafa.com/api/getContestList/IN";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.mToolbar);
        setSupportActionBar(toolbar);

        linearLayout = (LinearLayout) findViewById(R.id.viewPagerCountDots);

        progressDialog = new ProgressDialog(this);
        viewPager = (ViewPager) findViewById(R.id.tab_viewpager);
        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(16);
        viewPager.addOnPageChangeListener(this);
        progressDialog.show();
        ApiManager.getInstance().sendReq(this, url);
    }

    ///this function inflates the linearlayout with imageviews with circular dots depending upon the size of adapter
    private void setUiPageViewController(CustomPagerAdapter mAdapter) {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT

            );


            params.setMargins(4, 0, 4, 0);

            linearLayout.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
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

    @Override
    public void onResult(String result) {
        ParentContent parentContent = null;
        String reqresult = result.trim();

        try {
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jsonParser = jsonFactory.createJsonParser(reqresult);
            parentContent = om.readValue(jsonParser, ParentContent.class);            // txtShowJson.setText(cust.getCatn());
        } catch (Exception e) {
            e.printStackTrace();
        }
        resources = parentContent.contest;

        CustomPagerAdapter vpa = new CustomPagerAdapter(getSupportFragmentManager(), resources);
        progressDialog.dismiss();
        setUiPageViewController(vpa);
        viewPager.setAdapter(vpa);


    }


    @Override
    public void onError(String error) {
        progressDialog.dismiss();
        String ErrorData = error;
        Log.v("NOT GETTING", ErrorData);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotsCount; i++) {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
