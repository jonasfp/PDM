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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

import br.edu.ifpe.recife.tads.cheguei_102.R;
import br.edu.ifpe.recife.tads.cheguei_102.model.Encomenda;
import br.edu.ifpe.recife.tads.cheguei_102.model.Usuario;
import br.edu.ifpe.recife.tads.cheguei_102.util.Util;

public class cadastrarEncomendasActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1;
    private ImageView imageViewCadastrar;
    private ImageView imageViewTirarFoto;
    private EditText editTextCadastrar;
    private EditText editTextCriarNome;
    private TextView textViewCadastrar;
    private Uri mSelectedUri;
    String numeroApartamento;
    Usuario usuario = new Usuario();

    //Array de teste
    private static final String[] Apartamentos = {"101A", "102A", "103A",
            "104A", "201A", "202A", "203A", "204A", "301A", "302A", "303A", "304A",
            "401A", "402A", "403A", "404A", "501A", "502A", "503A", "504A"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar_encomendas);
        editTextCadastrar = (EditText) findViewById(R.id.edit_PesquisarApartamento);
        imageViewCadastrar = (ImageView) findViewById(R.id.imgCadastrarLocalizar);
        imageViewTirarFoto = (ImageView) findViewById(R.id.imgTirarFoto);
        textViewCadastrar = (TextView) findViewById(R.id.txtCadastrarLocalizar);
        editTextCriarNome = (EditText) findViewById(R.id.edit_criarnome);

        imageViewCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroApartamento = editTextCadastrar.getText().toString();
                Log.i("Teste", numeroApartamento);
                for (String apt : Apartamentos) {
                    if (apt.equals(numeroApartamento)) {
                        textViewCadastrar.setText("Apartamento " + numeroApartamento + " localizado!");
                        break;
                    }
                }
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String filename = UUID.randomUUID().toString();
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == CAMERA_REQUEST) && (resultCode == Activity.RESULT_OK)) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] imageData = stream.toByteArray();
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
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
//                    String url = taskSnapshot.getDownloadUrl().toString();
                    imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Log.i("Teste", uri.toString());
                            String apartamento = numeroApartamento;
                            String dataAtual = Util.getDataAtual();
                            String horaAtual = Util.getHoraAtual();
                            String profileUrl = uri.toString();
                            String uid = FirebaseAuth.getInstance().getUid();
                            Log.i("Jonas", uid);
                            Encomenda encomenda1 = new Encomenda(uid, dataAtual, horaAtual, profileUrl, apartamento);
                            FirebaseFirestore.getInstance().collection("Encomendas")
                                    .add(encomenda1)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                        }
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

