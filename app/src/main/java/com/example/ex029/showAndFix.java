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

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import static com.example.ex029.FBref.refStudents;

/**
 * The type Show and fix.
 *
 * @author Tahel Hazan <th8887@bs.amalnet.k12.il>
 * @version 1.1.1
 * @since 18/04/2021
 *This activity shows the information from the charts with an option to fix information for the students.
 */
public class showAndFix extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner names,v11,v22;
    EditText clas, grad, where1, where2, when1, when2;

    ValueEventListener stuListener;
    Students s, student;
    Vaccine v1, v2;

    String str2, n, g, c, wh1, wh2, we1, we2;
    int cla, grade, op1, op2, status1, status2,op;

    ArrayList<String> stuList= new ArrayList<String>();
    ArrayList<Students> stuValues= new ArrayList<Students>();
    String [] s1= {"Can't Take","First Vaccine","Second Vaccine"};
    ArrayAdapter<String> adp,adp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_and_fix);

        names=(Spinner) findViewById(R.id.names);
        v11=(Spinner) findViewById(R.id.v1);
        v22=(Spinner) findViewById(R.id.v2);
        clas=(EditText) findViewById(R.id.clas);
        grad=(EditText) findViewById(R.id.grad);
        where1=(EditText) findViewById(R.id.where1);
        where2=(EditText) findViewById(R.id.where2);
        when1=(EditText) findViewById(R.id.when1);
        when2=(EditText) findViewById(R.id.when2);
        showNames();
        names.setOnItemSelectedListener(this);
        adp1= new ArrayAdapter<String>(showAndFix.this, R.layout.
                support_simple_spinner_dropdown_item, s1);
        v11.setAdapter(adp1);
        v22.setAdapter(adp1);
    }

    /**
     * ShowNames.
     *
     * Creates the list for names and for the names information and put there the information from Firebase.
     */
    private void showNames() {
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
                adp= new ArrayAdapter<String>(showAndFix.this, R.layout.
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
     * Fix.
     *
     * @param view
     *
     * Takes the information and updates the chosen student.
     */
    public void fix(View view) {
        if(clas.getText().equals("")||grad.getText().equals("")||(op==0)){
            Toast.makeText(this, "You have to write information.", Toast.LENGTH_SHORT).show();
        }
        else{
            if (((cla >= 1) && (cla <= 12)) || ((grade >= 1) && (grade <= 10))){
                c = clas.getText().toString();
                g = grad.getText().toString();
                cla = Integer.parseInt(c);
                grade = Integer.parseInt(g);
                if ((status1 == 0) && (status2 == 0)) {
                    wh1 = " ";
                    wh2 = " ";
                    we1 = " ";
                    we2 = " ";
                } else if ((status2 == 0) || (status1 == 0))
                    Toast.makeText(this, "You have to check your information.", Toast.LENGTH_SHORT).show();
                else {
                    if (when1.getText().equals(" ") || when2.getText().equals(" ") ||
                            where1.getText().equals(" ") || where2.getText().equals(" ")) {
                        Toast.makeText(this, "Please check your information", Toast.LENGTH_SHORT).show();
                    } else {
                        wh1 = when1.getText().toString();
                        wh2 = when2.getText().toString();
                        we1 = where1.getText().toString();
                        we2 = where2.getText().toString();
                    }
                    v1 = new Vaccine(status1, we1, wh1);
                    v2 = new Vaccine(status2, we2, wh2);
                }
                student = new Students(n, cla, grade);
                student.setV1(v1);
                student.setV2(v2);
                refStudents.child(n).setValue(student);
            }
            else {
                Toast.makeText(this, "Enter correct information.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     *
     * @param p
     * @param v
     * @param pos
     * @param id
     *
     * Sets the information for the chosen student in the correct places so the user could fix it if necessary.
     */
    @Override
    public void onItemSelected(AdapterView<?> p, View v, int pos, long id) {
        switch(p.getId()){
            case R.id.names:
                if(pos!=0) {
                    s = stuValues.get(pos-1);
                    clas.setText(String.valueOf(s.getCla()));
                    grad.setText(String.valueOf(s.getGrade()));
                    if(s.getV1()==null){
                        where1.setText(" ");
                        when1.setText(" ");
                    }
                    if (s.getV2()==null){
                        where2.setText(" ");
                        when2.setText(" ");
                    }
                    if(s.getV1()==null&&s.getV2()==null){
                        where1.setText(" ");
                        when1.setText(" ");
                        where2.setText(" ");
                        when2.setText(" ");
                    }
                    else {
                        v1 = s.getV1();
                        v2 = s.getV2();
                        op1 = v1.getStatus();
                        op2 = v2.getStatus();
                        v11.setSelection(op1);
                        v22.setSelection(op2);
                        where1.setText(v1.getWhere());
                        where2.setText(v2.getWhere());
                        when1.setText(v1.getWhen());
                        when2.setText(v2.getWhen());
                        n = stuList.get(pos);
                    }
                }
                else{
                    op=0;
                }
                break;
            case R.id.v1:
                status1=pos;
                break;
            case R.id.v2:
                status2=pos;
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
     * The item Sends a Toast to let the user know he is in the current page he chose from the OptionMenu.
     */
    public void sf(MenuItem item) {
        Toast.makeText(this, "Psst! You already here :)", Toast.LENGTH_SHORT).show();
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