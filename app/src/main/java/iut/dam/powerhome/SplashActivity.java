package iut.dam.powerhome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import iut.dam.powerhome.R;

public class SplashActivity extends AppCompatActivity {

    ImageView logoImage;
    private static final int SPLASH_DURATION = 5000; // 5 secondes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Récupère le logo
        logoImage = findViewById(R.id.logoImage);

        // Lance l’animation de rotation
        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        logoImage.startAnimation(rotate);

        // Redirection après 5 secondes
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Démarre LoginActivity
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Ferme SplashActivity pour ne pas revenir dessus avec le bouton "back"
            }
        }, SPLASH_DURATION);
    }
}
