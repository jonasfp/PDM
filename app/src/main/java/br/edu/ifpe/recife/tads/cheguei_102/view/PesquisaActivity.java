package br.edu.ifpe.recife.tads.cheguei_102.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import br.edu.ifpe.recife.tads.cheguei_102.R;

public class PesquisaActivity extends AppCompatActivity {

    private EditText editPesquisa;
    private ImageView imageView;
    private RadioButton rbEncomendas;
    private RadioButton rbCondominos;
    private TextView txtResultadoBusca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pesquisa);
        editPesquisa = findViewById(R.id.edit_PesquisarApartamento);
        imageView = findViewById(R.id.imgEncomendasPesquisar);
        rbEncomendas = findViewById(R.id.radioButtonCondomino);
        rbCondominos = findViewById(R.id.radioButtonPorteiro);
        txtResultadoBusca = findViewById(R.id.txtResultadoBusca);

        editPesquisa.setText("101A");

        rbEncomendas.setOnClickListener(v -> {
            editPesquisa.setEnabled(true);
            editPesquisa.setHint("Digite o número do apartamento:");
            editPesquisa.setText("101A");
        });

        rbCondominos.setOnClickListener(v -> {
            editPesquisa.setEnabled(false);
            editPesquisa.setText("");
            editPesquisa.setHint("Toque na imagem ao lado ->");
        });

        imageView.setOnClickListener(v -> {
            if (rbEncomendas.isChecked()) {
                String apartamentoInformado = editPesquisa.getText().toString();
                if (apartamentoInformado.isEmpty()){
                    Toast.makeText(PesquisaActivity.this,"O número do apartamento deve ser informado",Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseFirestore.getInstance().collection("Encomendas")
                            .whereEqualTo("apartamento", apartamentoInformado)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        if (task.getResult().isEmpty()) {
                                            txtResultadoBusca.setText("Esse apartamento não está cadastrado.");
                                            return;
                                        }
                                        Intent intent = new Intent(PesquisaActivity.this, EncomendasActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("apartamento",apartamentoInformado);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    } else {
                                        Log.d("TAG", "Erro ", task.getException());
                                    }
                                }
                            });
                }
            } else {
                Intent intent =new Intent(PesquisaActivity.this, CondominosActivity.class);
                startActivity(intent);
            }
        });
    }

}