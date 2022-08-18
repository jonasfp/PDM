package br.edu.ifpe.recife.tads.cheguei_102.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import br.edu.ifpe.recife.tads.cheguei_102.R;
import br.edu.ifpe.recife.tads.cheguei_102.model.Encomenda;

public class EncomendaCadastroActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1;
    private ImageView imageViewBuscar;
    private ImageView imageViewTirarFoto;
    private EditText editTextCadastrar;
    private TextView textViewCadastrar;
    String numeroApartamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar_encomendas);
        editTextCadastrar = findViewById(R.id.edit_PesquisarApartamento);
        imageViewBuscar = findViewById(R.id.imgCadastrarLocalizar);
        imageViewTirarFoto = findViewById(R.id.imgTirarFoto);
        textViewCadastrar = findViewById(R.id.txtResultadoBusca);

        imageViewTirarFoto.setVisibility(View.GONE);

        imageViewBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroApartamento = editTextCadastrar.getText().toString();
                FirebaseFirestore.getInstance().collection("Usuários")
                        .whereEqualTo("perfil", "condomino")
                        .whereEqualTo("apartamento", numeroApartamento)
                        .limit(1)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (!task.getResult().isEmpty()) {
                                        textViewCadastrar.setText("Apartamento " + numeroApartamento + " localizado!\nClique na câmera para tirar uma foto.");
                                        imageViewTirarFoto.setVisibility(View.VISIBLE);
                                    } else {
                                        textViewCadastrar.setText("Apartamento " + numeroApartamento + " não localizado!\nVerifique se o número está correto.");
                                    }
                                } else {
                                    Log.i("TAG", "onComplete: ");
                                }
                            }
                        });
            }
        });

        imageViewTirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tirarFoto();
            }
        });
    }

    private void tirarFoto() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String filename = UUID.randomUUID().toString();

        if ((requestCode == CAMERA_REQUEST) && (resultCode == Activity.RESULT_OK)) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] imageData = stream.toByteArray();

            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            StorageReference imageRef = storageRef.child("/images/" + filename);
            UploadTask uploadTask = imageRef.putBytes(imageData);

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    String ex = e.getLocalizedMessage();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Log.i("Teste", uri.toString());
                            String apartamento = numeroApartamento;
                            Date dataAtual = Calendar.getInstance(TimeZone.getTimeZone("America/Recife")).getTime();
                            Date horaAtual = Calendar.getInstance(TimeZone.getTimeZone("America/Recife")).getTime();
                            String imagemUrl = uri.toString();
                            String idPorteiro = FirebaseAuth.getInstance().getUid();
                            Log.i("Jonas", idPorteiro);
                            String encomendaId = UUID.randomUUID().toString();
                            Encomenda encomenda1 = new Encomenda(encomendaId, idPorteiro, dataAtual, horaAtual, imagemUrl, apartamento);
                            FirebaseFirestore.getInstance().collection("Encomendas")
                                    .document(encomendaId)
                                    .set(encomenda1)
                                    .addOnSuccessListener(command -> {
                                        Toast.makeText(EncomendaCadastroActivity.this, "Imagem salva com sucesso!",
                                                Toast.LENGTH_SHORT).show();
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.i("Teste", e.getMessage());
                                        }
                                    });
                        }
                    });
                }
            });
        }
    }
}

