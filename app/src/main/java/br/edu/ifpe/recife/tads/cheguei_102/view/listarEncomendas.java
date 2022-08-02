package br.edu.ifpe.recife.tads.cheguei_102.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import br.edu.ifpe.recife.tads.cheguei_102.R;
import br.edu.ifpe.recife.tads.cheguei_102.model.Encomenda;
import br.edu.ifpe.recife.tads.cheguei_102.util.EncomendasAdapter;

public class listarEncomendas extends AppCompatActivity {

    private ArrayList<Encomenda> mSnapshots = new ArrayList<Encomenda>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.encomendas_usuarios_list);
        Bundle bundle = getIntent().getExtras();
        String apt = bundle.getString("apt");

        FirebaseFirestore.getInstance().collection("Encomendas")
                .whereEqualTo("apartamento", apt)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Encomenda encomenda = document.toObject(Encomenda.class);
                                mSnapshots.add(encomenda);
                                ListView listView = (ListView) findViewById(R.id.list_view_encomendas);
                                listView.setAdapter(new EncomendasAdapter(listarEncomendas.this,
                                        R.layout.encomendas_usuarios_adapter2, mSnapshots)
                                );
                            }
                        } else {
                            Log.d("TAG", "Erro ", task.getException());
                        }
                    }
                });
    }
}
