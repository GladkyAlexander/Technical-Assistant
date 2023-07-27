package ru.great_larder.technical_assistant_android.ui.registration;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import ru.great_larder.technical_assistant_android.R;
import ru.great_larder.technical_assistant_android.SelectCountryAdapter;
import ru.great_larder.technical_assistant_android.domain.Lang;
import ru.great_larder.technical_assistant_android.services.language.Language;
import ru.great_larder.technical_assistant_android.services.language.LanguageImpl;

import java.util.ArrayList;
import java.util.List;

public class RegistrationFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    
    private RegistrationViewModel mViewModel;
    private EditText editTextFirstName, editTextLastName, textEmailAddress, editTextPhone, editTextTextCompanyAffiliation
        , editTextLoginRegister, editTextPasswordRegister, editTextDatabaseName, editTextTextDatabasePassword;
    private Button register;
    private Spinner spinner;
    Language language = new LanguageImpl();
    String[] countryNames={"Рсский","English"};
    int flags[] = {R.drawable.ru, R.drawable.en};
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_registration, container, false);
     
        Spinner spinner = root.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        SelectCountryAdapter adapter = new SelectCountryAdapter(requireActivity(), flags, countryNames);
        spinner.setAdapter(adapter);
        
        return root;
        
    }
    
    
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {
        System.out.println(countryNames[position] + "1111111111111111111111111111111111");
        Toast.makeText(requireActivity(), countryNames[position], Toast.LENGTH_LONG).show();
    }
    
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    
    }
}