package com.example.ex029;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.ex029.FBref.refStudents;

/**
 * The type Sorting.
 *
 * @author Tahel Hazan <th8887@bs.amalnet.k12.il>
 * @version 1.1.1
 * @since 18/04/2021
 * This activity organizes information according to 4 options.
 */
public class Sorting extends AppCompatActivity{
    Query query;
    ListView info;

    ArrayList<String> stuList= new ArrayList<String>();
    ArrayList<Students> stuValues= new ArrayList<Students>();

    String str1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting);
        info=(ListView) findViewById(R.id.info);
    }

    /**
     * Sort By Class.
     *
     * @param view
     * Sorts the students by their classes(ABC).
     */
    public void sbc(View view) {
        query= refStudents.orderByChild("cla");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                stuList.clear();
                stuValues.clear();
                for(DataSnapshot data : ds.getChildren()) {
                    Students stuTmp = data.getValue(Students.class);
                    stuValues.add(stuTmp);
                    str1 = stuTmp.getName();
                    if (stuTmp.getV1() == null && stuTmp.getV2()==null)
                        stuList.add(str1 + ", " + stuTmp.getCla() + ", " + stuTmp.getGrade() + ", " +"None v1, None v2.");
                    else if (stuTmp.getV2()==null)
                        stuList.add(str1 + ", " + stuTmp.getCla() + ", " + stuTmp.getGrade() + ", " + stuTmp.getV1().toStringVaccine()+"None v2.");
                    else if((stuTmp.getV2().getStatus()==0)&&(stuTmp.getV1().getStatus()==0))
                        stuList.add(str1+", "+stuTmp.getCla()+", "+stuTmp.getGrade() +",Can't Take.");
                    else {
                        stuList.add(str1 +", " +stuTmp.getCla() + ", " + stuTmp.getGrade()
                                + ", " + stuTmp.getV1().toStringVaccine() + "," + stuTmp.getV2().toStringVaccine()+".");
                    }
                }
                CustomAdapter cta= new CustomAdapter(Sorting.this, stuList);
                info.setAdapter(cta);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    /**
     * Sort By Grade.(ABC)
     *
     * @param view
     * Sorts the students by their grades(ABC).
     */
    public void sbg(View view) {
        query= refStudents.orderByChild("grade");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                stuList.clear();
                stuValues.clear();
                for(DataSnapshot data : ds.getChildren()) {
                    Students stuTmp = data.getValue(Students.class);
                    stuValues.add(stuTmp);
                    str1 = stuTmp.getName();
                    if((stuTmp.getV2()==null)&&(stuTmp.getV1()==null))
                        stuList.add(str1+", "+stuTmp.getCla()+", "+stuTmp.getGrade() +",None.");
                    else if (stuTmp.getV2()==null)
                        stuList.add(str1 + ", " + stuTmp.getCla() + ", " + stuTmp.getGrade() + ", " + stuTmp.getV1().toStringVaccine()+"None v2");
                    else if((stuTmp.getV2().getStatus()==0)&&(stuTmp.getV1().getStatus()==0))
                        stuList.add(str1+", "+stuTmp.getCla()+", "+stuTmp.getGrade() +",Can't Take.");
                    else {
                        stuList.add(str1 +", " +stuTmp.getCla() + ", " + stuTmp.getGrade()
                                + ", " + stuTmp.getV1().toStringVaccine() + ", " + stuTmp.getV2().toStringVaccine()+".");
                    }
                }
                CustomAdapter cta= new CustomAdapter(Sorting.this, stuList);
                info.setAdapter(cta);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    /**
     * Sort By Took Vaccine.
     *
     * @param view the view
     * Sort the information according to all the people who took the vaccine(1st, 2nd or both).
     */
    public void sbtv(View view) {
        query= refStudents.orderByChild("status");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                stuList.clear();
                stuValues.clear();
                for(DataSnapshot data : ds.getChildren()) {
                    Students stuTmp = data.getValue(Students.class);
                    stuValues.add(stuTmp);
                    str1 = stuTmp.getName();
                    if((stuTmp.getV2()==null)||(stuTmp.getV1()==null))
                        stuList.add(str1+", "+stuTmp.getCla()+", "+stuTmp.getGrade() +",None.");
                    else {
                        stuList.add(str1 +", " +stuTmp.getCla() + ", " + stuTmp.getGrade()
                                + ", " + stuTmp.getV1().toStringVaccine() + ", " + stuTmp.getV2().toStringVaccine()+".");
                    }
                }
                CustomAdapter cta= new CustomAdapter(Sorting.this, stuList);
                info.setAdapter(cta);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    /**
     * Sort By Can't Take Vaccine.
     *
     * @param view the view
     * Sorts the information according to the people who can't take the vaccine.
     */
    public void sbctv(View view) {
        query= refStudents.orderByChild("status");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {
                stuList.clear();
                stuValues.clear();
                for(DataSnapshot data : ds.getChildren()) {
                    Students stuTmp = data.getValue(Students.class);
                    if (stuTmp.getV1().getStatus()==0&&stuTmp.getV2().getStatus()==0) {
                        stuValues.add(stuTmp);
                        str1 = stuTmp.getName();
                        stuList.add(str1 + ", " + stuTmp.getCla() + ", " + stuTmp.getGrade()
                                + ", " + "None.");
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        CustomAdapter cta= new CustomAdapter(Sorting.this, stuList);
        info.setAdapter(cta);
    }


    /**
     * Ma.
     *
     * @param item
     * Moves the user to MainActivity activity.
     */
    public void ma(MenuItem item) {
        Intent c= new Intent(this, MainActivity.class);
        startActivity(c);
    }

    /**
     * 2.
     *
     * @param item
     * Moves the user to Input2 activity.
     */
    public void i2(MenuItem item) {
        Intent c= new Intent(this,Input2.class);
        startActivity(c);
    }

    /**
     * Sf.
     *
     * @param item
     * Moves the user to ShowAndFix activity.
     */
    public void sf(MenuItem item) {
        Intent c= new Intent(this,showAndFix.class);
        startActivity(c);
    }

    /**
     * Or.
     *
     * @param item
     * The item Sends a Toast to let the user know he is in the current page he chose from the OptionMenu.
     */
    public void or(MenuItem item) {
        Toast.makeText(this, "Psst! You already here :)", Toast.LENGTH_SHORT).show();
    }

    /**
     * Cr.
     *
     * @param item
     * Moves the user to Credits activity.
     */
    public void cr(MenuItem item) {
        Intent c= new Intent(this,Credits.class);
        startActivity(c);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}