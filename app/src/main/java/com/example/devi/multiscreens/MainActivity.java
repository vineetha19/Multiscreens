package com.example.devi.multiscreens;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId()){
        case R.id.item1:
            Toast.makeText(this,"hello",Toast.LENGTH_LONG).show();
            Intent i=new Intent();
            i.setAction(Intent.ACTION_DIAL);
            startActivity(i);
            break;
            case R.id.item2:
                Toast.makeText(this,"hello",Toast.LENGTH_LONG).show();
                Intent i2=new Intent();
                i2.setAction(Intent.ACTION_DIAL);
                startActivity(i2);
                break;

    }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView alpha =(TextView)findViewById(R.id.alpha);
        TextView fruits =(TextView)findViewById(R.id.fruits);
        TextView numbers =(TextView)findViewById(R.id.numbers);
        TextView family =(TextView)findViewById(R.id.family);
        TextView colors =(TextView)findViewById(R.id.colors);
        TextView pharses =(TextView)findViewById(R.id.pharses);
        TextView vegetables =(TextView)findViewById(R.id.vegetables);
        TextView contact =(TextView)findViewById(R.id.contactus);





        alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i =new Intent(MainActivity.this,Alpha.class);
                startActivity(i);
            }
        });



        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this,Fruits.class);
                startActivity(i);
            }
        });


        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this,Numbers.class);
                startActivity(i);
            }
        });

        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this,colors.class);
                startActivity(i);
            }
        });
        pharses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this,phrases.class);
                startActivity(i);
            }
        });
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this,Family.class);
                startActivity(i);
            }
        });
        vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this,Vegetables.class);
                startActivity(i);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this,Contactus.class);
                startActivity(i);
            }
        });


    }

}
