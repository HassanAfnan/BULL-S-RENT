package change.com.animationwithsplash;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class slide extends AppCompatActivity {
    ViewFlipper v_flipper;
    int[] images ={
            R.drawable.slider1,
            R.drawable.slider2,
            R.drawable.slider3,
            R.drawable.slider4
    };
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        v_flipper = findViewById(R.id.v_flipper);
        for (int i = 0 ; i < images.length ; i++)
        {
            flip_images(images[i]);
        }
        }

    private void flip_images(int image) {
        ImageView view = new ImageView(this);
        view.setBackgroundResource(image);
        v_flipper.addView(view);
        v_flipper.setFlipInterval(2000);
        v_flipper.setAutoStart(true);
        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }
}

