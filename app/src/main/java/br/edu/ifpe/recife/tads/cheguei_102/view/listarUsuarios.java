package br.edu.ifpe.recife.tads.cheguei_102.view;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import br.edu.ifpe.recife.tads.cheguei_102.R;
import br.edu.ifpe.recife.tads.cheguei_102.model.Usuario;
import br.edu.ifpe.recife.tads.cheguei_102.util.UsuariosAdapter;

public class listarUsuarios extends AppCompatActivity {

private ArrayList<Usuario> mSnapshots = new ArrayList<Usuario>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuarios_list);

        FirebaseFirestore.getInstance().collection("Usuários")
                .whereEqualTo("perfil", "morador")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Usuario usuario = document.toObject(Usuario.class);
                                mSnapshots.add(usuario);
                                ListView listView = (ListView)findViewById(R.id.list_view_usuarios);
                                listView.setAdapter(new UsuariosAdapter(listarUsuarios.this,
                                        R.layout.usuarios_adapter2,mSnapshots)
                                );
                            }
                        } else {
                            Log.d("TAG", "Erro ", task.getException());
                        }
                    }
                });

//        FirebaseFirestore.getInstance().collection("Usuários")
//                .whereEqualTo("perfil", "funcionario")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (DocumentSnapshot document : task.getResult()) {
//                                Usuario usuario = document.toObject(Usuario.class);
//                                mSnapshots.add(usuario);
//                                ListView listView = (ListView)findViewById(R.id.list_view_usuarios);
//                                listView.setAdapter(new UsuariosAdapter(listarUsuarios.this,
//                                        R.layout.usuarios_adapter2,mSnapshots)
//                                );
//                            }
//                        } else {
//                            Log.d("TAG", "Erro: ", task.getException());
//                        }
//                    }
//                });


    }
}