package es.uca.gofestapppp.ui.dates;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import es.uca.gofestapppp.R;

public class DatesFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;
    private static final ArrayList<DateEvent> dates  = new ArrayList<>( Arrays.asList(
                    new DateEvent(29, 5, 2020, "Último día de venta"),
                    new DateEvent(25, 6, 2020, "Primer día del evento"),
                    new DateEvent(26,6,2020, "Segundo día del evento"),
                    new DateEvent(27,6,2020, "Tercer día del evento")));

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dates, container, false);

        recyclerView = root.findViewById(R.id.dates_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity()){};
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DatesAdapter(dates);
        recyclerView.setAdapter(adapter);

        return root;
    }
}
