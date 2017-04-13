package com.PulsarLabs.ARMenu;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;


/** OnBoardingFragment.java - Displays the onboarding process while the camera loads in the background
 *
 *  After the Unity splash screen, the dialog fragment uses the opportunity to onboard the user
 *  with the interaction in the augmented reality setting, while the augmented reality camera loads.
 *  The following dialog fragment show three views, in which each shows a certain piece of information
 *
 */

public class OnBoardingFragment extends DialogFragment implements View.OnClickListener {

    private View mRootView;
    private RelativeLayout mContainerRL;
    private int mViewState = 0;
    private Context mContext;
    private int[] mFoodImageArray = {R.drawable.hamburger_asset,R.drawable.lamb_chops_asset,R.drawable.sushi_asset,
            R.drawable.bacon_eggs_asset,R.drawable.dessert_asset};
    private String[] mOrderTypeArray = {"+ Take-Out","+ Reservation", "+ Dine-In"};
    private ImageView mAnimIV;
    private TextView mAnimTV;
    private Animation mAnimation;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        setCancelable(false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);


        mRootView = inflater.inflate(R.layout.onboard_fragment, container, false);
        mContainerRL = (RelativeLayout) mRootView.findViewById(R.id.container_rl);
        Button mNextButton = (Button) mRootView.findViewById(R.id.order_btn);
        ImageView mInfoButton = (ImageView) mRootView.findViewById(R.id.info_iv);
        ImageView mBackButton = (ImageView) mRootView.findViewById(R.id.back_iv);
        mAnimIV = (ImageView) mRootView.findViewById(R.id.anim_iv);

        mContext = getActivity();

        mNextButton.setOnClickListener(this);
        mInfoButton.setOnClickListener(this);
        mBackButton.setOnClickListener(this);

        startImageViewTaglineAnimation(mContext, R.anim.slide_anim); //start animation for cloud imageView

        return mRootView;
    }


    // Ensure that the dialog fragment is full screen
    @Override
    public void onResume(){
        super.onResume();
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }


    // onClickListener to keep a track of the order, info and back button. mViewState keeps a track of which state its in
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.order_btn:
                mViewState = mViewState + 1;
                setFragmentView(mViewState);
                break;

            case R.id.info_iv:
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.AppCompatAlertDialogStyle);
                builder.setTitle("About AR Menu");
                builder.setMessage(R.string.about_string);
                builder.setPositiveButton("Done", null);
                builder.show();
                break;

            case R.id.back_iv:
                mViewState = mViewState - 1;
                setFragmentView(mViewState);
                break;

            default:
                break;
        }
    }



    private void setFragmentView(int viewState){
        LayoutInflater inflater = LayoutInflater.from(mContext);

        if (viewState == 0){
            View inflateView = inflater.inflate(R.layout.onboard_fragment, null);
            mContainerRL.addView(inflateView);
            inflateView.findViewById(R.id.order_btn).setOnClickListener(this);
            inflateView.findViewById(R.id.back_iv).setOnClickListener(this);
            mAnimIV = (ImageView) inflateView.findViewById(R.id.anim_iv);
            mAnimIV.clearAnimation();

            startImageViewTaglineAnimation(mContext, R.anim.slide_anim);


        } else if (viewState == 1){
            View inflateView = inflater.inflate(R.layout.restaurant_fragment, null);
            mContainerRL.addView(inflateView);
            inflateView.findViewById(R.id.order_btn).setOnClickListener(this);
            inflateView.findViewById(R.id.back_iv).setOnClickListener(this);
            mAnimTV = (TextView) inflateView.findViewById(R.id.anim_tv);
            mAnimIV.clearAnimation();

            startTextViewTaglineAnimation(mContext, R.anim.fade_anim);


        } else if (viewState == 2){

            View inflateView = inflater.inflate(R.layout.dining_fragment, null);
            mContainerRL.addView(inflateView);
            inflateView.findViewById(R.id.order_btn).setOnClickListener(this);
            inflateView.findViewById(R.id.back_iv).setOnClickListener(this);

            final ImageView mFoodIV = (ImageView) inflateView.findViewById(R.id.test);
            final Animation mFoodAnimSlide = AnimationUtils.loadAnimation(mContext, R.anim.food_slide_anim);

            mFoodAnimSlide.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    //select a random food image from the selection
                    Random random = new Random();
                    int randomIndex = random.nextInt(5);
                    mFoodIV.setBackgroundResource(mFoodImageArray[randomIndex]);
                    mFoodIV.startAnimation(mFoodAnimSlide);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });

            mFoodIV.startAnimation(mFoodAnimSlide);


        } else if (viewState == 3){
            dismiss();
        }
    }


    // Start repeating animation for an imageView granted its animation sequence
    private void startImageViewTaglineAnimation (Context context, int resID){

        if (!(mAnimation == null)) {
            mAnimation.reset();
            mAnimation = null;
        }
        mAnimation = AnimationUtils.loadAnimation(context, resID);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mAnimIV.startAnimation(mAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mAnimIV.startAnimation(mAnimation);
    }


    // Start repeating animation for a textView granted its animation sequence
    private void startTextViewTaglineAnimation (Context context, int resID){
        if (!(mAnimation == null)) {
            mAnimation.reset();
            mAnimation = null;
        }
        mAnimation = AnimationUtils.loadAnimation(context, resID);
        mAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mAnimTV.startAnimation(mAnimation);
                Random random = new Random();
                int randomIndex = random.nextInt(3);
                mAnimTV.setText(mOrderTypeArray[randomIndex]);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mAnimTV.startAnimation(mAnimation);
    }
}