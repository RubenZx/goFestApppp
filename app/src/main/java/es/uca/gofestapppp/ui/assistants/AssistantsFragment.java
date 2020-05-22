package es.uca.gofestapppp.ui.assistants;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
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

public class AssistantsFragment extends Fragment {

    private AssistantsViewModel assistantsViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<User> listData;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    HttpRequest request = new HttpRequest();


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assistants, container, false);
        listData = new ArrayList<>();
        listData = request.getAll();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdapterData adapter = new AdapterData(listData);
        recyclerView.setAdapter(adapter);
        FloatingActionButton addUser = (FloatingActionButton) view.findViewById(R.id.floatingAddUser);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_assistants_to_nav_userpost);
            }
        });
        return view;
    }
}

