package br.edu.ifpe.recife.tads.cheguei_102.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import br.edu.ifpe.recife.tads.cheguei_102.R;

public class LoginActivity extends AppCompatActivity {
    private EditText editEmail;
    private EditText editSenha;
    private Button btnEntrar;
    private TextView textCriarConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editEmail = findViewById(R.id.edit_email);
        editSenha = findViewById(R.id.edit_senha);
        btnEntrar = findViewById(R.id.btn_entrar);
        textCriarConta = findViewById(R.id.txt_criarconta);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String password = editSenha.getText().toString();

                if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Nome, senha e email devem ser preenchidos", Toast.LENGTH_SHORT).show();
                    return;
                }
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(LoginActivity.this, "Login efetuado com sucesso!",
                                        Toast.LENGTH_SHORT).show();
                                String uid = task.getResult().getUser().getUid();

                                FirebaseFirestore.getInstance().collection("Usuários")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        if (document.getString("uid").equals(uid)) {
                                                            if (document.getString("perfil").equals("porteiro")) {
                                                                Intent intent = new Intent(LoginActivity.this, PorteiroActivity.class);
                                                                startActivity(intent);
                                                            } else if (document.getString("perfil").equals("condomino")) {
                                                                Intent intent1 = new Intent(LoginActivity.this, EncomendasActivity.class);
                                                                String nomeUsuario = document.getString("apartamento");
                                                                Bundle bundle = new Bundle();
                                                                bundle.putString("apartamento",nomeUsuario);
                                                                bundle.putBoolean("esconderBotao", true);
                                                                intent1.putExtras(bundle);
                                                                startActivity(intent1);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "Não foi possível fazer login!",
                                        Toast.LENGTH_SHORT).show();

                                Log.i("Teste", e.getMessage());
                            }
                        });
            }
        });

        textCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });
    }


}