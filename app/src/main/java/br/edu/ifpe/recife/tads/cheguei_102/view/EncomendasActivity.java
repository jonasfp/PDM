package br.edu.ifpe.recife.tads.cheguei_102.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.RadioButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import br.edu.ifpe.recife.tads.cheguei_102.R;
import br.edu.ifpe.recife.tads.cheguei_102.model.Encomenda;
import br.edu.ifpe.recife.tads.cheguei_102.util.EncomendasAdapter;

public class EncomendasActivity extends AppCompatActivity {

    private RadioButton radioButtonNaoEntregues;
    private RadioButton radioButtonEntregues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encomendas);

        radioButtonNaoEntregues = findViewById(R.id.radioButtonNaoEntregues);
        radioButtonEntregues = findViewById(R.id.radioButtonEntregues);

        Bundle bundle = getIntent().getExtras();
        String apartamento = bundle.getString("apartamento");
        boolean esconderBotao = bundle.getBoolean("esconderBotao");

        radioButtonNaoEntregues.setOnClickListener(v -> {
            FirebaseFirestore.getInstance().collection("Encomendas")
                    .whereEqualTo("apartamento", apartamento)
                    .whereEqualTo("entregue", false)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                ArrayList<Encomenda> encomendas = new ArrayList<>();
                                for (DocumentSnapshot document : task.getResult()) {
                                    encomendas.add(document.toObject(Encomenda.class));
                                }
                                ListView listView = (ListView) findViewById(R.id.list_view_encomendas);
                                listView.setAdapter(new EncomendasAdapter(EncomendasActivity.this,
                                        R.layout.encomendas_adapter, encomendas, esconderBotao)
                                );
                            } else {
                                Log.d("TAG", "Erro ", task.getException());
                            }
                        }
                    });
        });

        radioButtonEntregues.setOnClickListener(v -> {
            FirebaseFirestore.getInstance().collection("Encomendas")
                    .whereEqualTo("apartamento", apartamento)
                    .whereEqualTo("entregue", true)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                ArrayList<Encomenda> encomendas = new ArrayList<>();
                                for (DocumentSnapshot document : task.getResult()) {
                                    encomendas.add(document.toObject(Encomenda.class));
                                }
                                ListView listView = (ListView) findViewById(R.id.list_view_encomendas);
                                listView.setAdapter(new EncomendasAdapter(EncomendasActivity.this,
                                        R.layout.encomendas_adapter, encomendas, esconderBotao)
                                );
                            } else {
                                Log.d("TAG", "Erro ", task.getException());
                            }
                        }
                    });
        });

        radioButtonNaoEntregues.performClick();
    }
}
