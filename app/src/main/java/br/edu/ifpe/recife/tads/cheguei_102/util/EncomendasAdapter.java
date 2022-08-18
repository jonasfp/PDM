package br.edu.ifpe.recife.tads.cheguei_102.util;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import br.edu.ifpe.recife.tads.cheguei_102.R;
import br.edu.ifpe.recife.tads.cheguei_102.model.Encomenda;
import br.edu.ifpe.recife.tads.cheguei_102.view.EncomendasActivity;

public class EncomendasAdapter extends ArrayAdapter<Encomenda> {

    private final boolean esconderBotao;
    private ArrayList<Encomenda> encomendas;

    public EncomendasAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Encomenda> encomendas, boolean esconderBotao) {
        super(context, resource, encomendas);
        encomendas.sort((p1, p2) -> p2.getDataDeEntrega().compareTo(p1.getDataDeEntrega()));
        this.encomendas = encomendas;
        this.esconderBotao = esconderBotao;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Encomenda encomenda = encomendas.get(position);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View listItem = inflater.inflate(R.layout.encomendas_adapter, null, true);

        TextView textDataHoraEntrega = listItem.findViewById(R.id.text_entrega_data_hora);
        ImageView imageView = listItem.findViewById(R.id.imagem_encomenda);
        TextView pendente = listItem.findViewById(R.id.text_pendente);
        Button btnMarcarComoEntregue = listItem.findViewById(R.id.btn_marcar_como_entregue);
        btnMarcarComoEntregue.setVisibility(encomenda.isEntregue() ? View.GONE : View.VISIBLE);
        if (esconderBotao) btnMarcarComoEntregue.setVisibility(View.GONE);

        btnMarcarComoEntregue.setOnClickListener(v -> {
            encomenda.setEntregue(true);
            pendente.setText(encomenda.isEntregue() ? "Sim" : "Não");
            btnMarcarComoEntregue.setVisibility(encomenda.isEntregue() ? View.GONE : View.VISIBLE);

            FirebaseFirestore.getInstance().collection("Encomendas")
                    .document(encomenda.getUid())
                    .set(encomenda)
                    .addOnCompleteListener(command -> {
                        Toast.makeText(getContext(), "Entrega registrada.", Toast.LENGTH_SHORT).show();
                    });
        });

        pendente.setText(encomenda.isEntregue() ? "Sim" : "Não");

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        textDataHoraEntrega.setText(formatador.format(encomendas.get(position).getDataDeEntrega()));

        Picasso.get().load(encomenda.getImagemUrl()).into(imageView);

        return listItem;
    }

}
