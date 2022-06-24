package br.edu.ifpe.recife.tads.cheguei_102.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import br.edu.ifpe.recife.tads.cheguei_102.R;

public class persquisarActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private EditText mEditPesquisa;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persquisar);
        spinner = (Spinner) findViewById(R.id.spinerEncomendas);
        spinner.setOnItemSelectedListener(this);
        mEditPesquisa = (EditText) findViewById(R.id.edit_PesquisarApartamento);
        imageView = (ImageView) findViewById(R.id.imgEncomendasPesquisar);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.pesquisa_Encomenda, R.layout.spinner);
        adapter.setDropDownViewResource(R.layout.spinner);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        if (position == 1) {
            mEditPesquisa.setEnabled(true);
            mEditPesquisa.setHint("Digite o número do apartamento:");
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String data = mEditPesquisa.getText().toString();
                    if (data.isEmpty()){
                        Toast.makeText(persquisarActivity.this,"O número do apartamento deve ser informado",Toast.LENGTH_SHORT).show();
                    } else {
                    Intent intent = new Intent(persquisarActivity.this,listarEncomendas.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("apt",data);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    }
                }
            });

        } else if(position==2) {
            mEditPesquisa.setHint("Toque na imagem ao lado ->");
            mEditPesquisa.setEnabled(false);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent =new Intent(persquisarActivity.this,listarUsuarios.class);
                    startActivity(intent);
                }
            });

        }
        else{
            mEditPesquisa.setEnabled(false);
            mEditPesquisa.setHint("");
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}