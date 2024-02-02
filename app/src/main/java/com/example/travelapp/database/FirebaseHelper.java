package com.example.travelapp.database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class FirebaseHelper {
    private DatabaseReference getReference(String child) {
        if (child.isEmpty())
            return FirebaseDatabase.getInstance().getReference("cities");
        else
            return FirebaseDatabase.getInstance().getReference("cities").child(child);
    }

    public Task<Void> addRatings(String city, String id, double rating) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("rating", rating);

        return getReference(city).child(id).child("ratings")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(hashMap);
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

    private static final String TAG = "FirebaseHelper";

    public void unFavourite(String key) {
        getFavReference().child(key).removeValue().addOnCompleteListener(task -> {
            Log.d(TAG, "deleted successfully.");
        });
    }

    public void addFav(String placeID) {
        DatabaseReference reference = getFavReference();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("place_id", placeID);
        reference.push().setValue(hashMap).addOnCompleteListener(task -> {
            Log.d(TAG, "added to fav list");
        });
    }

    public void readFav(IFirebaseHelper iFirebaseHelper) {
        DatabaseReference reference = getFavReference();
        reference.addValueEventListener(new ValueEventListener() {
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
