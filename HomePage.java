package change.com.animationwithsplash;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

public class HomePage extends AppCompatActivity {

    Button profile,education,health,goal,finance,comfort;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        profile = findViewById(R.id.buttonProfile);
        education = findViewById(R.id.buttonEducation);
        health = findViewById(R.id.buttonHealth);
        goal = findViewById(R.id.buttonGoal);
        finance = findViewById(R.id.buttonFinance);
        comfort = findViewById(R.id.buttonComfort);

        profile.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(HomePage.this, Profile.class));
                    }
                }
        );

        education.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(HomePage.this, NewCar.class));
                    }
                }
        );

        health.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(HomePage.this, DeleteCar.class));
                    }
                }
        );

        goal.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(HomePage.this, UpdateCar.class));
                    }
                }
        );

        finance.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(HomePage.this, ShowCar.class));
                    }
                }
        );

        comfort.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(HomePage.this, Email.class));
                    }
                }
        );

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, ChatsActivity.class));
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
              R.id.nav_tools, R.id.nav_share , R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

                int menuId = destination.getId();

                switch (menuId) {
//                        case R.id.nav_home:
//                            startActivity(new Intent(MainActivity.this,home.class));
//                        break;

                    case R.id.nav_gallery:
                        startActivity(new Intent(HomePage.this, test.class));
                        break;

                    case R.id.nav_slideshow:
                        startActivity(new Intent(HomePage.this, slide.class));
                        break;

                    case R.id.nav_tools:
                        startActivity(new Intent(HomePage.this, tools.class));
                        break;

                    case R.id.nav_share:
//                        Toast.makeText(HomePage.this, "Share", Toast.LENGTH_LONG).show();
                        Intent shareintent = new Intent(Intent.ACTION_SEND);
                        shareintent.setType("text/plain");
                        shareintent.putExtra(Intent.EXTRA_TEXT," Read android Share Function at https://play.google.com/store/apps/details?id=colorsfx.android.smart.courses&hl=en");
                        shareintent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Bulls Rent");
                        startActivity(Intent.createChooser(shareintent, "Share via"));
                        break;

                    case R.id.nav_send:
//                        Toast.makeText(HomePage.this, "Send", Toast.LENGTH_LONG).show();
                        Intent chooser = null;
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("geo:19.076,72.8777"));
                        chooser = Intent.createChooser(intent,"Launch Maps");
                        startActivity(chooser);
                        break;

                    default:
                        //Toast.makeText(MainActivity.this,"Sorry",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.action_settings:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomePage.this,Login.class));
                finish();
                return true;
        }
        return false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
