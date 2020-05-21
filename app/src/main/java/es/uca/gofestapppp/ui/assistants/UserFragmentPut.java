package es.uca.gofestapppp.ui.assistants;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import es.uca.gofestapppp.HttpRequest;
import es.uca.gofestapppp.R;
import es.uca.gofestapppp.User;

public class UserFragmentPut extends Fragment {

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd");
    User user, userput;
    HttpRequest request = new HttpRequest();
    DatePickerDialog.OnDateSetListener mDate;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userput, container, false);
        Bundle data = getArguments();
        user = request.getUser(data.getString("user"));
        final EditText username = (EditText)view.findViewById(R.id.edituser);
        final EditText dni = (EditText)view.findViewById(R.id.editdni);
        final EditText phone = (EditText)view.findViewById(R.id.editphone);
        final TextView birth = (TextView) view.findViewById(R.id.editbirth);
        Button put = (Button)view.findViewById(R.id.put);
        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Theme_DeviceDefault, mDate, year, month, day);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDate = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = year+"-"+month+"-"+dayOfMonth;
                birth.setText(date);
            }
        };

        put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    userput = new User(user.getId(), username.getText().toString(), dni.getText().toString(), phone.getText().toString(), sf.parse(birth.getText().toString()), user.getInscripcion());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    request.put(userput);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getContext(), "Actualizado", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(v).navigate(R.id.action_nav_userput_to_nav_assistants);
            }
        });

        username.setText(user.getNombre());
        dni.setText(user.getDni());
        phone.setText(user.getTelefono());
        birth.setText(sf.format(user.getNacimiento()));
        return view;
    }
}
