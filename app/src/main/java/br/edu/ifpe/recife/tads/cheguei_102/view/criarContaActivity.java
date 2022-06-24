package br.edu.ifpe.recife.tads.cheguei_102.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import br.edu.ifpe.recife.tads.cheguei_102.R;
import br.edu.ifpe.recife.tads.cheguei_102.model.Usuario;

public class criarContaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText mEditUsername;
    private EditText mEditEmail;
    private EditText mEditPassword;
    private Button mBtnEnter;
    private Spinner spinner;
    Usuario usuario = new Usuario();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_conta);
        mEditUsername = (EditText) findViewById(R.id.edit_criarnome);
        mEditEmail = (EditText) findViewById(R.id.edit_criaremail);
        mEditPassword = (EditText) findViewById(R.id.edit_criarsenha);
        spinner = (Spinner) findViewById(R.id.spinerUsuario);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.perfil, R.layout.spinner);
        adapter.setDropDownViewResource(R.layout.spinner);
        spinner.setAdapter(adapter);
        mBtnEnter = findViewById(R.id.btn_criarcadastro);
        mBtnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        if (position == 1) {
            mEditUsername.setHint("Apartamento");
            usuario.setPerfil("morador");

        } else {
            mEditUsername.setHint("Nome");
            usuario.setPerfil("funcionario");

        }

        Log.i("Teste", usuario.getPerfil());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }

    private void createUser() {
        String nome = mEditUsername.getText().toString();
        String email = mEditEmail.getText().toString();
        String senha = mEditPassword.getText().toString();
        String perfil = usuario.getPerfil();

        if (usuario.getPerfil() == null || nome == null || nome.isEmpty() || email == null || email.isEmpty() || senha == null || senha.isEmpty()) {

            Toast.makeText(this, "Erro! Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String msg;
                        if (task.isSuccessful()) {
                            msg = "CADASTRO REALIZADO COM SUCESSO!";
                            String uid = FirebaseAuth.getInstance().getUid();
                            Log.i("UID", uid);
                            Usuario usuario = new Usuario(uid, nome, email, perfil);
                            FirebaseFirestore.getInstance().collection("Usuários")
                                    .add(usuario)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                        }
                                    });

                        } else {
                            msg = "Erro no Cadastro";
                        }
                        Toast.makeText(criarContaActivity.this, msg,
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("Teste", e.getMessage());
                    }
                });
    }


}