package br.edu.ifpe.recife.tads.cheguei_102.util;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Comparator;

import br.edu.ifpe.recife.tads.cheguei_102.R;
import br.edu.ifpe.recife.tads.cheguei_102.model.Usuario;

public class CondominosAdapter extends ArrayAdapter<Usuario> {

    private ArrayList<Usuario> usuarios;

    public CondominosAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Usuario> usuarios) {
        super(context, resource, usuarios);
        usuarios.sort(Comparator.comparing(Usuario::getApartamento));
        this.usuarios = usuarios;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View listItem = inflater.inflate(R.layout.condominos_adapter, null, true);
        TextView nome = listItem.findViewById(R.id.textViewNomeUsuario);
        TextView email = listItem.findViewById(R.id.textViewEmail);
        nome.setText(usuarios.get(position).getApartamento());
        email.setText(usuarios.get(position).getEmail());

        return listItem;
    }

}
