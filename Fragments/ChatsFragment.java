package change.com.animationwithsplash.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import change.com.animationwithsplash.Adapter.UsersAdapter;
import change.com.animationwithsplash.Models.Chats;
import change.com.animationwithsplash.Models.User;
import change.com.animationwithsplash.R;


public class ChatsFragment extends Fragment {

    private RecyclerView recyclerView;
    private UsersAdapter usersAdapter;
    private List<User> mUsers;

    FirebaseUser fuser;
    DatabaseReference reference;

    private List<String> usersList;
        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_chats,container,false);
            recyclerView = view.findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            fuser = FirebaseAuth.getInstance().getCurrentUser();
            usersList = new ArrayList<>();

            reference = FirebaseDatabase.getInstance().getReference("Chats");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    usersList.clear();

                    for(DataSnapshot snapshot : dataSnapshot.getChildren())
                    {
                        Chats chat = snapshot.getValue(Chats.class);
                        if(chat.getSender().equals(fuser.getUid()))
                        {
                            usersList.add(chat.getReceiver());
                        }
                        if(chat.getReceiver().equals(fuser.getUid()))
                        {
                            usersList.add(chat.getSender());
                        }
                    }
                    readChats();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            return view;
    }
    private void readChats()
    {
        mUsers = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    User user = snapshot.getValue(User.class);

                    //display user1 from chats
                    for(String id : usersList)
                    {
                        if(user.getId().equals(id)){
                            if(mUsers.size() != 0){
                                for(User user1 : mUsers){
                                    if(!user.getId().equals(user1.getId())){
                                        mUsers.add(user);
                                    }
                                }
                            }
                            else
                            {
                                mUsers.add(user);
                            }
                        }
                    }
                }
                usersAdapter = new UsersAdapter(getContext(),mUsers);
                recyclerView.setAdapter(usersAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

