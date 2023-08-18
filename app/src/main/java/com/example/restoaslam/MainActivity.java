package com.example.restoaslam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import com.google.firebase.database.DatabaseError;
import com.example.restoaslam.model.Food;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


// code utnuk menambhakna data ke firebase saat di run


//        // Dapatkan referensi ke node "foods" di Firebase Database
//        DatabaseReference foodsRef = FirebaseDatabase.getInstance().getReference().child("foods");
//
//        // Buat objek Food baru
//        Food newFood = new Food();
//        newFood.setImageResource("ongklok"); // Ganti dengan nama gambar makanan
//        newFood.setName("Mie Ongklok");
//        newFood.setDescription("Mie Ongklok dari Wonosobo");
//        newFood.setPrice(8000);
//
//        // Buat kunci unik menggunakan push() dan setel nilai makanan baru
//        DatabaseReference newFoodRef = foodsRef.push();
//        newFoodRef.setValue(newFood, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                if (error == null) {
//                    Log.d("MainActivity", "Data berhasil ditambahkan ke Firebase");
//                    // Atau tampilkan pesan toast
//                    // Toast.makeText(MainActivity.this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
//                } else {
//                    Log.e("MainActivity", "Gagal menambahkan data ke Firebase: " + error.getMessage());
//                }
//            }
//        });






        // Inisialisasi button
        ImageButton btEmail = findViewById(R.id.btEmail);
        ImageButton btTelfon = findViewById(R.id.btTelfon);
        ImageButton btLocation = findViewById(R.id.btLocation);

        btEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        btTelfon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber();
            }
        });

        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        // Inisialisasi ListView
        ListView listViewMenu = findViewById(R.id.listViewMenu);

        // Data daftar makanan tanpa database

        String[] items = {"Nasi liwet", "Mie Ongklok", "Nasi Gandul", "Mendoan", "Nasi Campur"};

//        ArrayList<String> menuList = new ArrayList<>();
//        menuList.add("Nasi Liwet");
//        menuList.add("Mie Ongklok");
//        menuList.add("Nasi Gandul");

        // Buat ArrayAdapter untuk menampilkan daftar makanan dalam ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, items){
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.WHITE); // Ganti dengan warna yang Anda inginkan
                return view;
            }
        };

        // Set ArrayAdapter ke ListView
        listViewMenu.setAdapter(adapter);

        // Hitung total tinggi item dalam ListView
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listViewMenu);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        // Set tinggi ListView sesuai total tinggi item
        listViewMenu.getLayoutParams().height = totalHeight + (listViewMenu.getDividerHeight() * (adapter.getCount() - 1));
        listViewMenu.requestLayout();






//        btEmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Log.d("MainActivity", "Button btEmail clicked");
//
//                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
//                emailIntent.setData(Uri.parse("mailto:aslamthariq01@gmail.com"));
//                if (emailIntent.resolveActivity(getPackageManager()) != null ) {
//                    startActivity(emailIntent);
//                }
//            }
//        });

//        btTelfon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Log.d("MainActivity", "Button btTelfon udah di click");
//
//                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
//                phoneIntent.setData(Uri.parse("tel:081325080083"));
//                if (phoneIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(phoneIntent);
//                }
//            }
//        });


    }

    private void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:aslamthariq01@gmail.com")); // Ganti dengan alamat email yang diinginkan
        intent.putExtra(Intent.EXTRA_SUBJECT, "Tanya Seputar Resto");
        startActivity(intent);
    }

    private void dialPhoneNumber() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:08132508123"));
        startActivity(intent);
    }

    private void openMap() {
        Uri gmmIntentUri = Uri.parse("geo:-6.9826264,110.4066259?q=UDINUS"); // Ganti dengan koordinat yang diinginkan
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }



    public void Menu(View v){
        Intent it = new Intent(this, menu.class);
        startActivity(it);
    }

    public void MenuRiwayat(View v){
        Intent it = new Intent(this, RiwayatPesanan.class);
        startActivity(it);
    }


}