package es.uca.gofestapppp.ui.dates;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import es.uca.gofestapppp.MainActivity;
import es.uca.gofestapppp.R;

public class DatesAdapter  extends RecyclerView.Adapter<DatesAdapter.MyViewHolder> {
    private static final int NOTIF_ID = 1;
    private static Context context;
    private ArrayList<DateEvent> dates;

    public DatesAdapter(ArrayList<DateEvent> dateEvents) {
        this.dates = dateEvents;
    }

    @NonNull
    @Override
    public DatesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date,
                parent, false);
        context = parent.getContext();
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final DatesAdapter.MyViewHolder holder, final int position) {
        holder.date.setText(dates.get(position).toString());
        holder.event.setText(dates.get(position).getEvent());

        holder.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateEvent d = dates.get(position);
                if(d.validateDate()) {
                    Snackbar.make(holder.itemView,"Faltan " + d.getDifDays() + " días hasta el "+ d.getEvent().toLowerCase(), Snackbar.LENGTH_LONG)
                            .setAction("Cerrar", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {}
                            })
                            .show();
                } else {
                    Toast.makeText(context,"Plazo finalizado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder notification = new NotificationCompat.Builder(context, "default")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(holder.event.getText().toString())
                        .setContentText(holder.date.getText().toString())
                        .setSubText("Pulsa para abrir la localización")
                        .setTicker("Abrir localización")
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setAutoCancel (true)
                        .setVibrate(new long[]{0, 250,250,250});

                Intent intent = new Intent(context, MainActivity.class);

                intent.putExtra("changeFragment", "location");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                PendingIntent contIntent = PendingIntent.getActivity(context,
                        0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                notification.setContentIntent(contIntent);

                NotificationManager mNotificationManager =
                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                NotificationChannel channel = new NotificationChannel("default",
                        "Default channel",
                        NotificationManager.IMPORTANCE_DEFAULT);

                assert mNotificationManager != null;
                mNotificationManager.createNotificationChannel(channel);
                mNotificationManager.notify(NOTIF_ID, notification.build());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView date, event;
        private ImageButton button;

        MyViewHolder(View v) {
            super(v);
            date = v.findViewById(R.id.date_text);
            event = v.findViewById(R.id.event_text);
            button = v.findViewById(R.id.date_button);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {}

    }


}
