package com.example.bookingin.wishlist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookingin.MainActivity;
import com.example.bookingin.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Wishlist extends AppCompatActivity implements View.OnClickListener{

    RecyclerView recyclerView;
    ImageButton add_button;
    ImageButton _btnBack;
    ImageView empty_imageview;
    TextView no_data;

    MyDatabaseHelper myDB;
    ArrayList<String> wishlist_id, wishlist_title, wishlist_budget;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        _btnBack = findViewById(R.id.btn_back);
//        empty_imageview = findViewById(R.id.empty_imageview);
//        no_data = findViewById(R.id.no_data);

        add_button.setOnClickListener(this);
        _btnBack.setOnClickListener(this);

        myDB = new MyDatabaseHelper(Wishlist.this);
        wishlist_id = new ArrayList<>();
        wishlist_title = new ArrayList<>();
        wishlist_budget = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(Wishlist.this,this, wishlist_id, wishlist_title, wishlist_budget);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Wishlist.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
//            empty_imageview.setVisibility(View.VISIBLE);
//            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                wishlist_id.add(cursor.getString(0));
                wishlist_title.add(cursor.getString(1));
                wishlist_budget.add(cursor.getString(2));
            }
//            empty_imageview.setVisibility(View.GONE);
//            no_data.setVisibility(View.GONE);
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.my_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId() == R.id.delete_all){
//            confirmDialog();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Wishlist.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(Wishlist.this, Wishlist.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    @Override
    public void onClick(View view) {
        if ( view == add_button){
            Intent intent = new Intent(Wishlist.this, ActivityAdd.class);
            startActivity(intent);
        }if ( view == _btnBack) {
            Intent intent = new Intent(Wishlist.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
