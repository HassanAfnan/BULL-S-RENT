package change.com.animationwithsplash;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefrence;
    private List<Information> info = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoader(List<Information> books,List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mRefrence = mDatabase.getReference("CarData");
    }

    public void ReadCars(final DataStatus dataStatus) {
        mRefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                info.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot KeyNode : dataSnapshot.getChildren()) {
                    keys.add(KeyNode.getKey());
                    Information information = KeyNode.getValue(Information.class);
                    info.add(information);
                }
                dataStatus.DataIsLoader(info, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateBook(String key,Information book,final DataStatus dataStatus){
        mRefrence.child(key).setValue(book)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });
    }

    public void deleteBook(String key,final DataStatus dataStatus){
        mRefrence.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }
}
