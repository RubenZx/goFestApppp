package es.uca.gofestapppp.ui.assistants;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.uca.gofestapppp.R;

public class AssistantsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    ArrayList<String> listData;
    RecyclerView recycler;

    public AssistantsViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("This is assistants fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}