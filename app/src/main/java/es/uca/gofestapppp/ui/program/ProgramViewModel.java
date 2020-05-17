package es.uca.gofestapppp.ui.program;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProgramViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProgramViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is program fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}