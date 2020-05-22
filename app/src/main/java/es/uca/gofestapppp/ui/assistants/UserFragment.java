package es.uca.gofestapppp.ui.assistants;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.text.CollationElementIterator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import es.uca.gofestapppp.HttpRequest;
import es.uca.gofestapppp.R;
import es.uca.gofestapppp.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserFragment extends Fragment {

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    User user;
    HttpRequest request = new HttpRequest();


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        TextView username = view.findViewById(R.id.user);
        TextView dni = view.findViewById(R.id.dni);
        TextView telefono = view.findViewById(R.id.telefono);
        TextView nacimiento = view.findViewById(R.id.nacimiento);
        TextView inscripcion = view.findViewById(R.id.inscripcion);
        final Button btnDelete = view.findViewById(R.id.delete);
        final Button btnPut = view.findViewById(R.id.put);
        Bundle data = getArguments();
        user = request.getUser(data.getString("user"));
        username.setText(user.getNombre());
        dni.setText(user.getDni());
        telefono.setText(user.getTelefono());
        nacimiento.setText(sf.format(user.getNacimiento()));
        inscripcion.setText(sf.format(user.getInscripcion()));
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    request.delete(user.getId());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Navigation.findNavController(v).navigate(R.id.action_nav_user_to_nav_assistants);
                Toast.makeText(getContext(), "eliminado", Toast.LENGTH_SHORT).show();
            }
        });
        btnPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("user", user.getId());
                Navigation.findNavController(v).navigate(R.id.action_nav_user_to_nav_userput, bundle);
            }
        });
        return view;
    }

}
