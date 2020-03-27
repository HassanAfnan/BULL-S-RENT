package change.com.animationwithsplash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Detail extends AppCompatActivity {

    private EditText mAuthor;
    private EditText mTitle;
    private EditText mIsbn;
    private EditText mCatagory;

    private Button update;
    private Button delete;
    private Button back;

    private String key;
    private String author;
    private String title;
    private String category;
    private String isbn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        key = getIntent().getStringExtra("key");
        author = getIntent().getStringExtra("Name");
        title = getIntent().getStringExtra("Car");
        isbn = getIntent().getStringExtra("Model");
        category = getIntent().getStringExtra("Rent");

        mAuthor = findViewById(R.id.edit1);
        mAuthor.setText(title);
        mTitle = findViewById(R.id.edit2);
        mTitle.setText(author);
        mIsbn = findViewById(R.id.edit3);
        mIsbn.setText(isbn);
        mCatagory = findViewById(R.id.edit4);
        mCatagory.setText(category);

        update = findViewById(R.id.button);
        delete = findViewById(R.id.button2);
        back = findViewById(R.id.button3);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Information info = new Information();
                info.setName(mAuthor.getText().toString());
                info.setCar(mTitle.getText().toString());
                info.setModel(mIsbn.getText().toString());
                info.setRent(mCatagory.getText().toString());

                new FirebaseDatabaseHelper().updateBook(key, info, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoader(List<Information> books, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(Detail.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelper().deleteBook(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoader(List<Information> books, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(Detail.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }
                });
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                return;
            }
        });
    }
}
