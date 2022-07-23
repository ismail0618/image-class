package com.example.imageclass;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.imageclass.databinding.ActivityFeedBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class FeedActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    ArrayList<Urun> urunArrayList;
    private ActivityFeedBinding binding;
    UrunAdapter urunAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityFeedBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        urunArrayList=new ArrayList<>();

        firebaseFirestore=FirebaseFirestore.getInstance();

        getData();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        urunAdapter=new UrunAdapter(urunArrayList);
        binding.recyclerView.setAdapter(urunAdapter);

    }
    public void guncelle(View view){
        Intent intent=new Intent(FeedActivity.this,UrunGuncelle.class);
        startActivity(intent);
    }
    private void getData(){
        firebaseFirestore.collection("Urun").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    Toast.makeText(FeedActivity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
                if(value!=null){
                    for(DocumentSnapshot snapshot:value.getDocuments()){
                        Map<String,Object> data=snapshot.getData();
                        String productName=(String)data.get("productName");
                        String price=(String)data.get("price");
                        String stockAmount=(String)data.get("stockAmount");

                        Urun urun=new Urun(productName,price,stockAmount);
                        urunArrayList.add(urun);
                    }
                    urunAdapter.notifyDataSetChanged();
                }
            }
        });
    }
    public void sil(final View view){
        firebaseFirestore.collection("Urun").document(String.valueOf(urunAdapter)).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(FeedActivity.this, "Silindi", Toast.LENGTH_SHORT).show();
                    urunAdapter.removeUrun(urunArrayList.remove(1));
                }else{
                    Toast.makeText(FeedActivity.this, "Failed.Check Log", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}