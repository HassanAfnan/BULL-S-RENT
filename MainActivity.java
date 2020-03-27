package change.com.animationwithsplash;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    Animation topAnim,bottomAnim,top1;
    ImageView image;
    TextView logo,slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        top1 = AnimationUtils.loadAnimation(this,R.anim.top1);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        image = findViewById(R.id.imageView2);
        logo = findViewById(R.id.textView);
        slogan = findViewById(R.id.textView2);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        Thread splash = new Thread(){
            public void run() {
                try
                {
                    sleep(3*1000);
                    Intent i = new Intent(getBaseContext(),Login.class);
                    startActivity(i);
                    finish();
                }
                catch (Exception e) {

                }
            }
        };
        splash.start();
    }
}
