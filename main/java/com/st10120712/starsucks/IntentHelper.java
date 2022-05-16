package com.st10120712.starsucks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class IntentHelper {

    public static void openIntent(Context context, String order, Class passTo){
        //declare intent with context and class to pass to
        Intent i=new Intent(context,passTo);
        //pass through the  the string value with key order
        i.putExtra("order" ,order);
        //start the activity
        context.startActivity(i);
    }

    public static void shareIntent(Context context, String order) {
        Intent sendIntent = new Intent();
        // set the action to indicate what to do - send in this case
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, order);
        // we are sending plain text
        sendIntent.setType("text/plain");
        // show the share sheet
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        context.startActivity(shareIntent);
    }

    public static void shareIntent(Context context, String productName,String customerName,String customerCell){
        Intent sendIntent=new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        //create a bundle and add multiple details to it
        Bundle shareOrderDetails=new Bundle();
        shareOrderDetails.putString("productNme",productName);
        shareOrderDetails.putString("Customer Name",customerName);
        shareOrderDetails.putString("Customer Cell",customerCell);
        //share the whole bundle
        sendIntent.putExtra(Intent.EXTRA_TEXT,shareOrderDetails);
        sendIntent.setType("text/plain");
        Intent shareIntent=Intent.createChooser(sendIntent,null);
        context.startActivity(shareIntent);
    }
}

