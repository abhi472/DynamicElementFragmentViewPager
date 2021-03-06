package com.example.abhishek.dynamicelementfragmentviewpager.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.abhishek.dynamicelementfragmentviewpager.Callbacks.ICallBack;
import com.example.abhishek.dynamicelementfragmentviewpager.Models.ContestsDetail;
import com.example.abhishek.dynamicelementfragmentviewpager.Models.ParentContent;
import com.example.abhishek.dynamicelementfragmentviewpager.R;
import com.example.abhishek.dynamicelementfragmentviewpager.Singletons.ApiManager;
import com.example.abhishek.dynamicelementfragmentviewpager.Singletons.AppController;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ContestDetail extends AppCompatActivity implements ICallBack{

    TextView contestTitle,contestDesc;
    Button storeButton;
    ObjectMapper om = new ObjectMapper();
    String id;
    Bundle bundle;
    NetworkImageView banner;
    ProgressDialog progressDialog;
    StringBuilder url = new StringBuilder("http://staging.lafalafa.com/api/getContestApi/");
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    WebView tableContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(this);
        progressDialog.isIndeterminate();
        progressDialog.setCancelable(false);
        progressDialog.show();
        contestTitle = (TextView)findViewById(R.id.contestTitle);
        contestDesc = (TextView)findViewById(R.id.contestDesc);
        storeButton = (Button)findViewById(R.id.storeButton);
        banner = (NetworkImageView)findViewById(R.id.banner);
        tableContent = (WebView)findViewById(R.id.tableContent);
        tableContent.getSettings().setJavaScriptEnabled(true);
        bundle =getIntent().getExtras();
        id=bundle.getString("id");
        url.append(id);
        progressDialog.show();
        ApiManager.getInstance().sendReq(this, url.toString());








    }

    @Override
    public void onResult(String result) {

        ContestsDetail contestsDetail=null;
        String reqresult=result.trim();
        String imageval;
        String TOC;
        String Desc;



        try {
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jsonParser = jsonFactory.createJsonParser(reqresult);
            contestsDetail = om.readValue(jsonParser, ContestsDetail.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


        contestTitle.setText(contestsDetail.contest.title);
        imageval = contestsDetail.contest.image;
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        banner.setImageUrl(imageval, imageLoader);
        TOC = contestsDetail.contest.contestTOC;
        Desc = contestsDetail.contest.shortDesc;
        contestDesc.setText(Desc);
        tableContent.loadData(TOC, "text/html", "UTF-8");
        progressDialog.dismiss();






    }

    @Override
    public void onError(String error) {

        progressDialog.dismiss();
        String ErrorData = error;
        Log.v("NOT GETTING", ErrorData);

    }
}