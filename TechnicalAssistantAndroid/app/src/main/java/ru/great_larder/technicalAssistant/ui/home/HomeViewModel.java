package ru.great_larder.technicalAssistant.ui.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import ru.great_larder.technicalAssistant.services.lang.Language;
import ru.great_larder.technicalAssistant.services.lang.impl.LanguageImpl;

public class HomeViewModel extends ViewModel {
    
    private final MutableLiveData<String> mText;
    Language language = (Language) new LanguageImpl();
    
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(language.REGISTRY("Русский"));
    }
    
    public LiveData<String> getText() {
        return mText;
    }
    
}