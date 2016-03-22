package com.example.abhishek.dynamicelementfragmentviewpager.Fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.abhishek.dynamicelementfragmentviewpager.R;
import com.example.abhishek.dynamicelementfragmentviewpager.Singletons.AppController;


public class HolderFragm extends Fragment {
    NetworkImageView img ;
    TextView title,description;
    String imageval;
    String Desc;
    String Title;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        imageval = bundle.getString("image");
        Desc = bundle.getString("desc");
        Title = bundle.getString("title");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_holder,container,false);
        title=(TextView)rootView.findViewById(R.id.textView);
        img =(NetworkImageView)rootView.findViewById(R.id.iMage);
        description=(TextView)rootView.findViewById(R.id.textView2);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/RobotoCondensed-Regular.ttf");
        Typeface font2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        title.setTypeface(font2);
        description.setTypeface(font);


        title.setText(Title);
        description.setText(Desc);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        img.setImageUrl(imageval, imageLoader);

        return rootView;
    }

}





