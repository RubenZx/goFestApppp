package es.uca.gofestapppp.ui.assistants;

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

public class AssistantsFragment extends Fragment {

    private AssistantsViewModel assistantsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        assistantsViewModel =
                ViewModelProviders.of(this).get(AssistantsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_assistants, container, false);
        final TextView textView = root.findViewById(R.id.text_assistants);
        assistantsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
