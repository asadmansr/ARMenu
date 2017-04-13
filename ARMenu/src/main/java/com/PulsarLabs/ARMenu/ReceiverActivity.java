package com.PulsarLabs.ARMenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


/** ReceiverActivity.java - Receives the intent command from unity and initiates the ReceiptActivity
 *
 *  Upon clicking the order button in the Augmented Unity view, the state of the system transitions
 *  from the Unity activity to Receipt activity with the following arguments of food name that is being
 *  passed as an intent as well as the context.
 *
 */

public class ReceiverActivity extends Activity {

    private static String mFoodName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);
    }

    //Uses: foodName to be passed onto the following activities with context to start a new activity
    public static void ReceiveIntent(String foodName, Context context){
        mFoodName = foodName;
        Intent intent = new Intent(context, ReceiptActivity.class);
        intent.putExtra("FoodName",mFoodName);
        context.startActivity(intent);
    }
}
