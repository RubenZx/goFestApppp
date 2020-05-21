package es.uca.gofestapppp.ui.assistants;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import es.uca.gofestapppp.MainActivity;
import es.uca.gofestapppp.R;
import es.uca.gofestapppp.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AdapterData extends RecyclerView.Adapter<AdapterData.ViewHolderData> implements View.OnClickListener {

    ArrayList<User> listData;
    private Button.OnClickListener listener;

    public AdapterData(ArrayList<User> listData) {
        this.listData = listData;
    }


    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {
        holder.assignData(listData.get(position).getNombre(),listData.get(position).getId());
        holder.btnVer.setOnClickListener(this);
        holder.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @Override
    public void onClick(View v) {

    }

    public class ViewHolderData extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView username;
        Button btnVer;
        Context context;
        String id;
        User user;
        OkHttpClient client = new OkHttpClient();

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            username = (TextView) itemView.findViewById(R.id.username);
            btnVer = (Button) itemView.findViewById(R.id.ver);
        }

        public void assignData(String n, String i) {
            id = i;
            username.setText(n);
        }

        public void setOnClickListener() {
            btnVer.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString("user", id);
            Navigation.findNavController(v).navigate(R.id.action_nav_assistants_to_nav_user, bundle);
        }
    }
}
