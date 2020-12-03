package com.example.gameofthrones_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  NetworkingClass.APIDataListner, CharacterAdapter.OnItemClickListener {

    ArrayList<Character> characters;
    JsonManager jsonManager;
    RecyclerView recyclerView;
    CharacterAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NetworkingClass networkingClass = new NetworkingClass(this, getApplicationContext());
        jsonManager = new JsonManager();

         recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //Second Technique
        AssetsReader reader = new AssetsReader();
        characters = (ArrayList<Character>) reader.getCharacters(this);
        adapter = new CharacterAdapter(this,characters);
        adapter.listner = this;
        recyclerView.setAdapter(adapter);
        //Second Technique



        //First Technique
       // networkingClass.getGOTCharactersInfo();

    }

    @Override
    public void returnAPIData(String data) {
        characters =  jsonManager.parseGOTData(data);
        adapter = new CharacterAdapter(this,characters);
        adapter.listner = this;
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void returnBitmapImage(Bitmap image) {

    }


    @Override
    public void onItemClick(Character item) {


        Intent intent = new Intent(MainActivity.this, ImageActivity.class);
        intent.putExtra("image", item.getCharacterImageThumb());
        startActivity(intent);
    }
}
