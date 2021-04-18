package com.example.ex029;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.ex029.FBref.refStudents;

/**
 * The type Main activity.
 *
 * @author Tahel Hazan <th8887@bs.amalnet.k12.il>
 * @version 1.1.1
 * @since 18/04/2021
 *
 * This activity meant for personal information on the student(Name, Last Name, Class, Grade).
 */
public class MainActivity extends AppCompatActivity {
    /**
     * @param fn - First Name
     * @param ln - Last Name
     * @param c - Class (שכבה)
     * @param g - Grade (כיתה)
     */
    EditText fn, ln, c,g;
    /**
     * @param n - string that contains First Name+Last Name
     * @param cl - string for c
     * @param gr - string for g
     */
    String n,cl,gr;
    /**
     * The Cla.
     *
     * @param cla - int for cl
     * @param grade -int for gr
     */
    int cla, grade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fn=(EditText) findViewById(R.id.fn);
        ln=(EditText) findViewById(R.id.ln);
        c=(EditText) findViewById(R.id.c);
        g=(EditText) findViewById(R.id.g);
    }

    /**
     * Sub.
     *
     * @param view
     *
     * Creates a child in Firebase and adds the information that was inserted in the EditTexts.
     */
    public void sub(View view) {
        if(fn.equals("")||ln.equals("")||c.equals("")||g.equals("")) {
            Toast.makeText(this, "You have to enter information", Toast.LENGTH_SHORT).show();
        }
        else{
            n = fn.getText().toString() + " " + ln.getText().toString();
            cl = c.getText().toString();
            gr = g.getText().toString();
            grade = Integer.parseInt(gr);
            cla = Integer.parseInt(cl);
            if (((cla >= 1) && (cla <= 12)) && ((grade >= 1) && (grade <= 10))) {
                Students student = new Students(n, cla, grade);
                refStudents.child(n).setValue(student);
                fn.setText(" ");
                ln.setText(" ");
                c.setText(" ");
                g.setText(" ");
                fn.setHint("First Name");
                ln.setHint("Last Name");
                c.setHint("Class");
                g.setHint("Grade");
            }
            else{
                Toast.makeText(this, "Enter legal numbers.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Ma.
     *
     * @param item
     * The item Sends a Toast to let the user know he is in the current page he chose from the OptionMenu.
     *
     */
    public void ma(MenuItem item) {
        Toast.makeText(this, "Psst! You already here :)", Toast.LENGTH_SHORT).show();
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
        Intent c= new Intent(this, showAndFix.class);
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
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}