package es.uca.gofestapppp.ui.assistants;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AssistantsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AssistantsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is assistants fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}