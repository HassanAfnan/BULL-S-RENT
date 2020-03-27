package change.com.animationwithsplash;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class tools extends AppCompatActivity {

    private android.widget.VideoView VideoView;
    private int position = 0;
    private ProgressDialog progressDialog;
    private MediaController mediaControls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
        if (mediaControls == null) {
            mediaControls = new MediaController(tools.this);
        } // Find your VideoView in your video_main.xml layout
        VideoView = (VideoView) findViewById(R.id.videoView);
        // Create a progressbar
        progressDialog = new ProgressDialog(tools.this);
        // Set progressbar message
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        // Show progressbar
        progressDialog.show();
        try {
            VideoView.setMediaController(mediaControls);
            VideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.simple));
            //myVideoView.setVideoURI(Uri.parse("https://www.youtube.com/watch?v=efgv9h4Feok"));
        }
        catch (Exception e)
        {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        VideoView.requestFocus();
        VideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                progressDialog.dismiss();
                VideoView.seekTo(position);
                if (position == 0) {
                    VideoView.start();
                } else {
                    VideoView.pause();
                }
            }
        });
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("Position", VideoView.getCurrentPosition());
        VideoView.pause();
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        position = savedInstanceState.getInt("Position"); VideoView.seekTo(position);
    }
}
