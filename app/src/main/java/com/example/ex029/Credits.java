package com.example.ex029;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The type Credits.
 *
 * @author Tahel Hazan <th8887@bs.amalnet.k12.il>
 * @version 1.1.1
 * @since 18/04/2021
 * The credit page shows the author and ways to contact him in case something doesn't work.
 */
public class Credits extends AppCompatActivity {

    TextView credits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        credits=(TextView) findViewById(R.id.credits);

        SharedPreferences settings= getSharedPreferences("stm", MODE_PRIVATE);
        SharedPreferences.Editor editor=settings.edit();
        String s = "Author: Tahel Hazan            Contact= th8887@bs.amalnet.k12.il        " +
                "    version 1.1.4        since 25.11.2020   contact if something doesn't work!";
        editor.putString("contact",s);
        editor.commit();
        credits.setText(settings.getString("contact",null));
    }

    /**
     * Ma.
     *
     * @param item
     * Moves the user to MainActivity activity.
     */
    public void ma(MenuItem item) {
        Intent c= new Intent(this,MainActivity.class);
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
     * The item Sends a Toast to let the user know he is in the current page he chose from the OptionMenu.
     */
    public void cr(MenuItem item) {
        Toast.makeText(this, "Psst! You already here :)", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}