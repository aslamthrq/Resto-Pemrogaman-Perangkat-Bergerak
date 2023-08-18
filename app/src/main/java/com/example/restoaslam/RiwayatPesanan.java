package com.example.restoaslam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restoaslam.adapter.RiwayatPesananAdapter;
import com.example.restoaslam.model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RiwayatPesanan extends AppCompatActivity {

    private RecyclerView riwayatRecyclerView;
    private RiwayatPesananAdapter riwayatPesananAdapter;
    private List<Order> riwayatPesananList;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pesanan);

        riwayatPesananList = new ArrayList<>();
        riwayatRecyclerView = findViewById(R.id.riwayatRecyclerView);
        backButton = findViewById(R.id.backButton);

        riwayatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        riwayatPesananAdapter = new RiwayatPesananAdapter(riwayatPesananList);
        riwayatRecyclerView.setAdapter(riwayatPesananAdapter);

        DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference().child("orders");
        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                riwayatPesananList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Order order = snapshot.getValue(Order.class);
                    riwayatPesananList.add(order);
                }
                riwayatPesananAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RiwayatPesanan.this, "Gagal mengambil data pesanan", Toast.LENGTH_SHORT).show();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(RiwayatPesanan.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}
