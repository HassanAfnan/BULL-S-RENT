package change.com.animationwithsplash;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class NewCar extends AppCompatActivity {
    EditText name,cname,model,rent;
    Button but;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_car);

        name = findViewById(R.id.edit1);
        cname = findViewById(R.id.edit2);
        model = findViewById(R.id.edit3);
        rent = findViewById(R.id.edit4);
        but = findViewById(R.id.btn);

        pd = new ProgressDialog(this);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String CName = cname.getText().toString();
                String Model = model.getText().toString();
                String Rent = rent.getText().toString();

                if(Name.isEmpty() || CName.isEmpty() || Model.isEmpty() || Rent.isEmpty() ){
                    Toast.makeText(NewCar.this,"Empty Cardinals",Toast.LENGTH_LONG).show();
                }

                else{
                    pd.show();
                    HashMap<String,Object> map = new HashMap<>();
                    map.put("Name",Name);
                    map.put("Car",CName);
                    map.put("Model",Model);
                    map.put("Rent",Rent);
                    pd.dismiss();
                    FirebaseDatabase.getInstance().getReference().child("CarData").child(Name+CName+Rent).updateChildren(map);
                    Intent intent = new Intent(NewCar.this,HomePage.class);
                    startActivity(intent);
                    Toast.makeText(NewCar.this,"New car added",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
