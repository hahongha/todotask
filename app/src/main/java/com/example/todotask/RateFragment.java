package com.example.todotask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

public class RateFragment extends Fragment {
    private float userRate = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_rate,container,false);
        // Ánh xạ các thành phần giao diện từ layout XML (nếu cần)
        final AppCompatButton rateNowbtn = v.findViewById(R.id.rateNowbtn);
        final AppCompatButton laterbtn = v.findViewById(R.id.laterbtn);
        final RatingBar ratingBar = v.findViewById(R.id.ratingBar);
        final ImageView ratingImage = v.findViewById(R.id.ratingImage);

        rateNowbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        laterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getActivity().onBackPressed();

            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(rating <= 1){
                    ratingImage.setImageResource(R.drawable.one_star);
                }
                else if(rating <= 2){
                    ratingImage.setImageResource(R.drawable.two_star);
                }
                else if(rating <= 3){
                    ratingImage.setImageResource(R.drawable.three_star);
                }
                else if(rating <= 4){
                    ratingImage.setImageResource(R.drawable.four_star);
                }
                else if(rating <= 5){
                    ratingImage.setImageResource(R.drawable.five_star);
                }
                animateImage(ratingImage);
                userRate = rating;
            }
        });
        return v;
    }
    private void animateImage(ImageView ratingImage){
        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1f, 0,1f,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(200);
        ratingImage.startAnimation(scaleAnimation);
    }

}
