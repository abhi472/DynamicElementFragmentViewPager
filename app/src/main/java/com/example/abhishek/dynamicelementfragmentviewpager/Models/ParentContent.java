package com.example.abhishek.dynamicelementfragmentviewpager.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by abhishek on 21/3/16.
 */
public class ParentContent implements Serializable {

    public String api_key;
    public String message;
    public String errorMsg;
    public ArrayList<ContestContent> contest;


}
