package change.com.animationwithsplash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.List;

public class DeleteCar extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_car);
        mRecyclerView = (RecyclerView) findViewById(R.id.res);
        new FirebaseDatabaseHelper().ReadCars(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoader(List<Information> books, List<String> keys) {
                findViewById(R.id.progressBar).setVisibility(View.GONE);
                new RecyclerViewConfig().setConfig(mRecyclerView,DeleteCar.this,books,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }
}
