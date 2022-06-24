package br.edu.ifpe.recife.tads.cheguei_102.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import br.edu.ifpe.recife.tads.cheguei_102.R;
import br.edu.ifpe.recife.tads.cheguei_102.model.Usuario;

public class encomendasActivity extends AppCompatActivity {
    private TextView textView;
    private TextView textView2;
    private ImageView imageView;
    private ImageView imageView2;
    private ArrayList<Usuario> mSnapshots = new ArrayList<Usuario>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encomendas);
        textView = (TextView) findViewById(R.id.textViewCadastrar);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(encomendasActivity.this, cadastrarEncomendasActivity.class);
                startActivity(intent);
            }
        });
        imageView = (ImageView) findViewById(R.id.produto);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(encomendasActivity.this, cadastrarEncomendasActivity.class);
                startActivity(intent);
            }
        });

        textView2 = (TextView) findViewById(R.id.txtBuscar);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(encomendasActivity.this, persquisarActivity.class);
                startActivity(intent);

            }
        });
        imageView2 = (ImageView) findViewById(R.id.imgBuscar);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(encomendasActivity.this, persquisarActivity.class);
                startActivity(intent);

            }
        });
    }
}