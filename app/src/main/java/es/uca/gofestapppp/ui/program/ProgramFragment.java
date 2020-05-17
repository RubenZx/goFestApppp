package es.uca.gofestapppp.ui.program;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import es.uca.gofestapppp.R;

public class ProgramFragment extends Fragment {

    private ProgramViewModel programViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        programViewModel =
                ViewModelProviders.of(this).get(ProgramViewModel.class);
        View root = inflater.inflate(R.layout.fragment_program, container, false);
        final TextView textView = root.findViewById(R.id.text_program);
        programViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
