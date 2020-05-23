package es.uca.gofestapppp.ui.assistants;

import android.app.DatePickerDialog;
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
import java.util.Date;

import es.uca.gofestapppp.HttpRequest;
import es.uca.gofestapppp.R;
import es.uca.gofestapppp.User;

public class UserFragmentPost extends Fragment {

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    HttpRequest request = new HttpRequest();
    User userpost;
    DatePickerDialog.OnDateSetListener mDate;
    String msg;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userpost, container, false);
        final EditText username = (EditText)view.findViewById(R.id.edituser);
        final EditText dni = (EditText)view.findViewById(R.id.editdni);
        final EditText phone = (EditText)view.findViewById(R.id.editphone);
        final TextView birth = (TextView) view.findViewById(R.id.editbirth);
        final TextView inscription = (TextView)view.findViewById(R.id.inscription);
        inscription.setText(sf.format(new Date()));
        birth.setText(sf.format(new Date()));
        Button addUser = (Button)view.findViewById(R.id.post);
        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), mDate, year, month, day);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDate = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = year+"-"+(month+1)+"-"+dayOfMonth;
                birth.setText(date);
            }
        };

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().isEmpty() || dni.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || birth.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        userpost = new User(username.getText().toString(), dni.getText().toString(), phone.getText().toString(), sf.parse(birth.getText().toString()), sf.parse(inscription.getText().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        msg = request.post(userpost);
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                    if(msg.equals("Asistente añadido con éxito.")){
                        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(v).navigate(R.id.action_nav_userpost_to_nav_assistants);
                    }
                    else Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
