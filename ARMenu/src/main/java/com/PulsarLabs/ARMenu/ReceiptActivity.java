package com.PulsarLabs.ARMenu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

/** ReceiptActivity.java - Displays the content for the food chosen in the format of a receipt
 *
 *  With the values being passed from the intent, it initializes all of the xml element and show
 *  the corresponding information as food name, food price, food image and total food price. The
 *  receipt number on the top, picks a random receipt number. Note that the design is intended
 *  for a possible business case, there is NO actual payment transactions #ThisIsAnAndroidExperiment.
 *
 */

public class ReceiptActivity extends Activity implements View.OnClickListener {
    private View mContentView;
    private static final int UI_ANIMATION_DELAY = 0;
    private final Handler mHideHandler = new Handler();
    private RelativeLayout mPayRL;
    private TextView mReceiptNumber;
    private TextView mReceiptFoodName;
    private TextView mReceiptFoodPrice;
    private TextView mReceiptTotalPrice;
    private ImageView mReceiptFoodImage;
    private String mFoodName;
    private String mMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        // Retrieving the name of the food that has been selected
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            mFoodName = extras.getString("FoodName");
        }

        mContentView = findViewById(R.id.container_rl);
        mPayRL = (RelativeLayout) findViewById(R.id.pay_now_rl);
        mReceiptNumber = (TextView) findViewById(R.id.receipt_number_tv);
        mReceiptFoodName = (TextView) findViewById(R.id.food_name_tv);
        mReceiptFoodPrice = (TextView) findViewById(R.id.food_price_tv);
        mReceiptTotalPrice = (TextView) findViewById(R.id.total_price_tv);
        mReceiptFoodImage = (ImageView) findViewById(R.id.food_image_iv);

        setReceiptValues(mFoodName); // Parse in values for receipt

        mPayRL.setOnClickListener(this);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }


    //The menu attributes and its corresponding prices
    private void setReceiptValues(String foodName){
        mReceiptNumber.setText("Receipt #" + getRandomOrderNumber());

        switch (foodName){
            case "Burger":
                mReceiptFoodName.setText("Classic Burger");
                mReceiptFoodPrice.setText("$3.99");
                mReceiptTotalPrice.setText("Total $3.99");
                mReceiptFoodImage.setBackgroundResource(R.drawable.hamburger_asset);
                mMenuItem = "Classic Burger";
                break;

            case "Lamb":
                mReceiptFoodName.setText("Lamb Chops");
                mReceiptFoodPrice.setText("$11.99");
                mReceiptTotalPrice.setText("Total $11.99");
                mReceiptFoodImage.setBackgroundResource(R.drawable.lamb_chops_asset);
                mMenuItem = "Lamb Chops";
                break;

            case "Bacon":
                mReceiptFoodName.setText("Bacon & Eggs");
                mReceiptFoodPrice.setText("$5.99");
                mReceiptTotalPrice.setText("Total $5.99");
                mReceiptFoodImage.setBackgroundResource(R.drawable.bacon_eggs_asset);
                mMenuItem = "Bacon & Eggs";
                break;

            case "Sushi":
                mReceiptFoodName.setText("Sushi Platter");
                mReceiptFoodPrice.setText("$7.99");
                mReceiptTotalPrice.setText("Total $7.99");
                mReceiptFoodImage.setBackgroundResource(R.drawable.sushi_asset);
                mMenuItem = "Sushi Platter";
                break;

            case "BSplit":
                mReceiptFoodName.setText("Banana Split");
                mReceiptFoodPrice.setText("$4.99");
                mReceiptTotalPrice.setText("Total $4.99");
                mReceiptFoodImage.setBackgroundResource(R.drawable.dessert_asset);
                mMenuItem = "Banana Split";
                break;

            default:
                break;
        }

    }


    // onClickListener for pay now relative layout, to proceed to the ThankYouActivity.java
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.pay_now_rl:
                Intent intent = new Intent(ReceiptActivity.this, ThankYouActivity.class);
                intent.putExtra("FoodName",mMenuItem);
                startActivity(intent);
                break;

            default:
                break;
        }
    }


    // Return a string representation of a random integer between 0 and 500
    private String getRandomOrderNumber(){
        Random random = new Random();
        return String.valueOf(random.nextInt(500));
    }


    // Makes the activity full screen and hides navigation bar
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
}
