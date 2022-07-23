package com.example.imageclass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.imageclass.databinding.ActivityProductBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class product extends AppCompatActivity {

    private ActivityProductBinding binding;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProductBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        firebaseFirestore=FirebaseFirestore.getInstance();
    }
    public void urunEkle(View view){
        String name=binding.nameText.getText().toString();
        String stock=binding.stockText.getText().toString();
        String price=binding.priceText.getText().toString();

        HashMap<String,Object> urunData=new HashMap<>();
        urunData.put("productName",name);
        urunData.put("stockAmount",stock);
        urunData.put("price",price);

        firebaseFirestore.collection("Urun").add(urunData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

                Intent intent=new Intent(product.this,FeedActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(product.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}