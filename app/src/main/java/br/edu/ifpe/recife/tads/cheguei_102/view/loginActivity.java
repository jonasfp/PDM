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

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import br.edu.ifpe.recife.tads.cheguei_102.R;
import br.edu.ifpe.recife.tads.cheguei_102.model.Encomenda;
import br.edu.ifpe.recife.tads.cheguei_102.model.Usuario;

public class loginActivity extends AppCompatActivity {
    private EditText mEditEmail;
    private EditText mEditPassword;
    private Button mBtnEnter;
    private TextView mTxtAccount;
    private List<Encomenda> encomendas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mEditEmail = findViewById(R.id.edit_email);
        mEditPassword = findViewById(R.id.edit_senha);
        mBtnEnter = findViewById(R.id.btn_entrar);
        mTxtAccount = findViewById(R.id.txt_criarconta_);
        mBtnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEditEmail.getText().toString();
                String password = mEditPassword.getText().toString();
//                String email = "terraca@gmail.com";
//                String password = "123456";
                Log.i("Teste", email);
                Log.i("Teste", password);

                if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
                    Toast.makeText(loginActivity.this, "Nome, senha e email devem ser preenchidos", Toast.LENGTH_SHORT).show();
                    return;
                }
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(loginActivity.this, "Login efetuado com sucesso!",
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
                                                            if (document.getString("perfil").equals("funcionario")) {
                                                                Intent intent = new Intent(loginActivity.this, encomendasActivity.class);
                                                                startActivity(intent);
                                                            } else if(document.getString("uid").equals(uid)) {
                                                                Intent intent1 = new Intent(loginActivity.this, listarEncomendas.class);
                                                                String nomeUsuario = document.getString("nomeUsuario");
                                                                Bundle bundle = new Bundle();
                                                                bundle.putString("apt",nomeUsuario);
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
                                Toast.makeText(loginActivity.this, "Não foi possível fazer login!",
                                        Toast.LENGTH_SHORT).show();

                                Log.i("Teste", e.getMessage());
                            }
                        });
            }
        });

        mTxtAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, criarContaActivity.class);
                startActivity(intent);
            }
        });
    }


}