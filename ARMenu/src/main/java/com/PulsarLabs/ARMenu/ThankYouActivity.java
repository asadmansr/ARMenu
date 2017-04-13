package com.PulsarLabs.ARMenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/** ThankYouActivity.java - Displays that the experiment has finished and the user can share their order
 *
 *  With the values being passed from the intent, it initializes all of the xml element and show
 *  the corresponding information as food name with the generated social media format that users can
 *  share on social media. The place another order button will restart the experiment.
 *
 */

public class ThankYouActivity extends AppCompatActivity implements View.OnClickListener{
    private View mContentView;
    private static final int UI_ANIMATION_DELAY = 0;
    private final Handler mHideHandler = new Handler();
    private String mFoodName;
    private String mGeneratedShareMsg;
    private TextView mShareMsgTV;
    private ImageView mPlatesIV;
    private Button mPlaceOrderBtn;
    private RelativeLayout mShareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);

        // Retrieving the name of the food that has been selected
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            mFoodName = extras.getString("FoodName");
        }

        mContentView = findViewById(R.id.container_rl);
        mPlatesIV = (ImageView) findViewById(R.id.plates);
        mShareMsgTV = (TextView) findViewById(R.id.share_message_tv);
        mShareButton = (RelativeLayout) findViewById(R.id.share_rl);
        mPlaceOrderBtn = (Button) findViewById(R.id.place_another_order_btn);

        startTaglineAnimation(this,R.anim.slide_anim); // start Animation
        mGeneratedShareMsg = generateShareMessage(mFoodName);
        mShareMsgTV.setText(mGeneratedShareMsg); // Generate string and display

        mShareButton.setOnClickListener(this);
        mPlaceOrderBtn.setOnClickListener(this);
        mContentView.setOnClickListener(this);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }


    // Set AnimationListener on the imageView (plates) and allow it to repeat
    private void startTaglineAnimation (Context context, int resID){
        final Animation mAnimationSlide = AnimationUtils.loadAnimation(context, resID);
        mAnimationSlide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mPlatesIV.startAnimation(mAnimationSlide);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mPlatesIV.startAnimation(mAnimationSlide);

    }


    // Generate a string that showcases what the user has ordered as a social media post
    private String generateShareMessage(String foodName){
        String initMessage = "I've just ordered " ;
        String postMessage = " using AR Menu, an augmented reality menu #androidexperiments" ;

        return initMessage + foodName + postMessage;
    }


    // onClickListener to initiate a share intent to post on social media and start the experiment again
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.share_rl:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, mGeneratedShareMsg);
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "Share Your Order"));
                break;

            case R.id.place_another_order_btn:
                Intent intent = new Intent(ThankYouActivity.this, UnityPlayerNativeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

            case R.id.container_rl:
                hide();
                break;

            default:
                break;
        }
    }

    private void hide(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
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
