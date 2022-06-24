package br.edu.ifpe.recife.tads.cheguei_102.util;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import br.edu.ifpe.recife.tads.cheguei_102.R;
import br.edu.ifpe.recife.tads.cheguei_102.model.Encomenda;

public class EncomendasAdapter extends ArrayAdapter<Encomenda> {

    private ArrayList<Encomenda> mSnapshots;
    private ImageView imageView;

    public EncomendasAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Encomenda> mSnapshots) {
        super(context, resource, mSnapshots);
        this.mSnapshots = mSnapshots;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View listItem = inflater.inflate(R.layout.encomendas_usuarios_adapter2, null, true);
        TextView data = (TextView) listItem.findViewById(R.id.textViewEncomendas2);
        TextView hora = (TextView) listItem.findViewById(R.id.textViewEncomendas3);
        ImageView img = (ImageView) listItem.findViewById(R.id.imageViewEncomendas);
        data.setText(mSnapshots.get(position).getDataDeEntrega());
        hora.setText(mSnapshots.get(position).getHoraDaEntrega());
        String teste = mSnapshots.get(position).getProfileUrl();
        return listItem;

//        FirebaseUIActivity firebaseUIActivity = new FirebaseUIActivity();
//        firebaseUIActivity.glideCarrega();
//      firebaseUIActivity.loadWithGlide(mSnapshots.get(position).getProfileUrl(),img);
//        return null;
    }


}
