package com.example.ex029;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.ex029.FBref.refStudents;

/**
 * The type Input 2.
 *
 * @author Tahel Hazan <th8887@bs.amalnet.k12.il>
 * @version 1.1.1
 * @since 18/04/2021
 * This activity meant for the medical information for the chosen student (chosen by Spinner).
 */
public class Input2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner names;
    EditText when,where;

    Students s, student;
    Vaccine v1, v2;

    ValueEventListener stuListener;
    ArrayList<String> stuList= new ArrayList<String>();
    ArrayList<Students> stuValues= new ArrayList<Students>();

    String str2,wh,we,n;
    int status,op,op0;
    ArrayAdapter<String> adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input2);

        names=(Spinner) findViewById(R.id.names);
        when=(EditText) findViewById(R.id.when);
        where= (EditText) findViewById(R.id.where);

        showNames();
        names.setOnItemSelectedListener(this);
    }

    /**
     * Show names.
     *
     * Creates the list for names and for the names information and put there the information from Firebase.
     */
    public void showNames(){
        stuListener= new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot ds) {
             stuList.clear();
             stuValues.clear();
             stuList.add("Names");
             for (DataSnapshot data : ds.getChildren()){
                 Students stuTmp= data.getValue(Students.class);
                 stuValues.add(stuTmp);
                 str2= stuTmp.getName();
                 stuList.add(str2);
             }
             adp= new ArrayAdapter<String>(Input2.this, R.layout.
                     support_simple_spinner_dropdown_item, stuList);
             names.setAdapter(adp);
         }
         @Override
         public void onCancelled(@NonNull DatabaseError error) {
         }
        };
        refStudents.addValueEventListener(stuListener);


    }

    /**
     * Sub 2.
     *
     * @param view
     *
     * Takes the medical information and adds it to the chosen child (the chosen student) in Firebase.
     */
    public void sub2(View view) {
        if(when.getText().equals(" ")||where.getText().equals(" ")||(op0==0)){
            Toast.makeText(this, "Please write the correct information.", Toast.LENGTH_SHORT).show();
        }
        else{
            wh= when.getText().toString();
            we= where.getText().toString();
            switch (status){
                case 0:
                    v1=new Vaccine(status,null,null);
                    v2=new Vaccine(status,null,null);
                    student=new Students(n, s.getCla(), s.getGrade());
                    student.setV1(v1);
                    student.setV2(v2);
                    refStudents.child(n).setValue(student);
                    Toast.makeText(this, "Information received.", Toast.LENGTH_SHORT).show();break;
                case 1:
                    showNames();
                    v1= new Vaccine(status,we,wh);
                    student=new Students(n, s.getCla(), s.getGrade());
                    student.setV1(v1);
                    student.setV2(stuValues.get(op).getV2());
                    refStudents.child(n).setValue(student);
                    Toast.makeText(this, "Information received.", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    showNames();
                    v2= new Vaccine(status,we,wh);
                    student=new Students(n, s.getCla(), s.getGrade());
                    student.setV2(v2);
                    student.setV1(stuValues.get(op).getV1());
                    refStudents.child(n).setValue(student);
                    Toast.makeText(this, "Information received.", Toast.LENGTH_SHORT).show();
                    break;
            }
            where.setText(" ");
            when.setText(" ");
            where.setHint("Where?");
            when.setHint("DDMMYY");
        }
    }

    public void v0(View view) {
        status=0;
    }
    public void v1(View view) {
        status=1;
    }
    public void v2(View view) {
        status=2;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if (pos!=0) {
            s = stuValues.get(pos - 1);
            n = stuList.get(pos);
            op=pos-1;
        }
        else{
            op0=0;
        }

    }

    /**
     * Ma.
     *
     * @param item
     * Moves the user to MAinActivity activity.
     */
    public void ma(MenuItem item) {
        Intent c= new Intent(this,MainActivity.class);
        startActivity(c);
    }


    /**
     * i2.
     *
     * @param item
     * The item Sends a Toast to let the user know he is in the current page he chose from the OptionMenu.
     */
    public void i2(MenuItem item) {
        Toast.makeText(this, "Psst! You already here :)", Toast.LENGTH_SHORT).show();
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
     * Moves the user to Sorting activity.
     */
    public void or(MenuItem item) {
        Intent c= new Intent(this,Sorting.class);
        startActivity(c);
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
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (stuListener!=null) {
            refStudents.removeEventListener(stuListener);
        }
    }
}