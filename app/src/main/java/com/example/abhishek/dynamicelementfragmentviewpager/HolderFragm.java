package com.example.abhishek.dynamicelementfragmentviewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



public class HolderFragm extends Fragment {
    ImageView img ;
    TextView textView;
    Integer colorval;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        colorval = bundle.getInt("color");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_holder,container,false);
        textView=(TextView)rootView.findViewById(R.id.textView);
        img =(ImageView)rootView.findViewById(R.id.iMage);
        img.setBackgroundColor(colorval);
        return rootView;
    }

}





