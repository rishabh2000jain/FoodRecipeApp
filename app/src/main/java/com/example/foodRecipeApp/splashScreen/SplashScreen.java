package com.example.foodRecipeApp.splashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodRecipeApp.R;
import com.example.foodRecipeApp.homeScreen.HomeScreenActivity;

public class SplashScreen extends AppCompatActivity {
    ImageView imageView;
    TextView textView;
    Animation animationImage, animationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        // set an exit transition
        getWindow().setExitTransition(new Explode());
        setContentView(R.layout.activity_splash_screen);
        //hooks
        imageView = findViewById(R.id.splash_screen_image);
        textView = findViewById(R.id.splash_screen_text);
        //loading animation
        animationText = AnimationUtils.loadAnimation(this, R.anim.splash_screen_text_animation);
        animationImage = AnimationUtils.loadAnimation(this, R.anim.splash_screen_image_animation);
        imageView.setAnimation(animationImage);
        textView.setAnimation(animationText);
        Handler handler = new Handler();

        handler.postDelayed(() -> {
            Intent intent = new Intent(this, HomeScreenActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}