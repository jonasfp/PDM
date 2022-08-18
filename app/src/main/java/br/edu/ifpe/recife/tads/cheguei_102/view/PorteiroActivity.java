package br.edu.ifpe.recife.tads.cheguei_102.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import br.edu.ifpe.recife.tads.cheguei_102.R;
import br.edu.ifpe.recife.tads.cheguei_102.model.Usuario;

public class PorteiroActivity extends AppCompatActivity {
    private TextView textCadastrar;
    private TextView textBuscar;
    private ImageView imageViewCadastrar;
    private ImageView imageViewBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.porteiro);
        textCadastrar = findViewById(R.id.textViewCadastrar);
        imageViewCadastrar = findViewById(R.id.produto);
        textBuscar = findViewById(R.id.txtBuscar);
        imageViewBuscar = findViewById(R.id.imgBuscar);

        textCadastrar.setOnClickListener(v -> abreEncomendaCadastroActivity());
        imageViewCadastrar.setOnClickListener(v -> abreEncomendaCadastroActivity());
        textBuscar.setOnClickListener(v -> abrePesquisaActivity());
        imageViewBuscar.setOnClickListener(v -> abrePesquisaActivity());
    }

    private void abrePesquisaActivity() {
        Intent intent = new Intent(PorteiroActivity.this, PesquisaActivity.class);
        startActivity(intent);
    }

    private void abreEncomendaCadastroActivity() {
        Intent intent = new Intent(PorteiroActivity.this, EncomendaCadastroActivity.class);
        startActivity(intent);
    }
}