package br.edu.ifpe.recife.tads.cheguei_102.util;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import br.edu.ifpe.recife.tads.cheguei_102.R;
import br.edu.ifpe.recife.tads.cheguei_102.model.Usuario;

public class UsuariosAdapter extends ArrayAdapter<Usuario> {

    private ArrayList<Usuario> mSnapshots;

    public UsuariosAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Usuario> mSnapshots) {
        super(context, resource, mSnapshots);
        this.mSnapshots = mSnapshots;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View listItem = inflater.inflate(R.layout.usuarios_adapter2, null, true);
        TextView nome = listItem.findViewById(R.id.textViewNomeUsuario);
        TextView email = listItem.findViewById(R.id.textViewEmail);
        nome.setText(mSnapshots.get(position).getNomeUsuario());
        email.setText(mSnapshots.get(position).getEmail());

        return listItem;
    }

}
