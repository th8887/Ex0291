package com.example.ex029;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FBref {
    public static FirebaseDatabase FBDB= FirebaseDatabase.getInstance();
    public static DatabaseReference refStudents= FBDB.getReference("Students");
}
