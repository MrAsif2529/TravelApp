package com.example.travelapp.database;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseHelper {
    private DatabaseReference getReference(String child) {
        if (child.isEmpty())
            return FirebaseDatabase.getInstance().getReference("cities");
        else
            return FirebaseDatabase.getInstance().getReference("cities").child(child);
    }

    private DatabaseReference getFavReference() {
        return FirebaseDatabase.getInstance().getReference("favorites")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }


    public void readChild(String child, IFirebaseHelper iFirebaseHelper) {
        DatabaseReference reference = getReference(child);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                iFirebaseHelper.onSuccess(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                iFirebaseHelper.onError(error.getMessage());
            }
        });

    }

    public void readFav(IFirebaseHelper iFirebaseHelper) {
        DatabaseReference reference = getFavReference();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                iFirebaseHelper.onSuccess(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                iFirebaseHelper.onError(error.getMessage());
            }
        });

    }

    public static boolean isNewUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user == null;
    }

    public interface IFirebaseHelper {
        void onSuccess(DataSnapshot snapshot);

        void onError(String message);
    }
}
