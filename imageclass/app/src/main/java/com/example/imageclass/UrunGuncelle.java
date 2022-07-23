package com.example.imageclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class UrunGuncelle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_guncelle);
    }
    public void guncelle(View view){
        Toast.makeText(UrunGuncelle.this, "Ürün Güncellendi", Toast.LENGTH_SHORT).show();
    }
}