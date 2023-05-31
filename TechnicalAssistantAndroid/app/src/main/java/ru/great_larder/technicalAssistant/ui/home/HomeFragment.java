package ru.great_larder.technicalAssistant.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;
import ru.great_larder.technicalAssistant.databinding.FragmentHomeBinding;
import ru.great_larder.technicalAssistant.domain.User;
import ru.great_larder.technicalAssistant.services.DatabaseAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {
    
    private FragmentHomeBinding binding;
    private EditText loginEnter, passwordEnter, lastName, firstName, emailAddress, phone, position, companyAffiliation
        , language, loginRegister, passwordRegister, emailServer, portOnTheEmailServer, databaseName, databasPassword;
    private Button register, enter;
    private TableLayout layoutRegister, layoutEnter;
    
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
            new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);
        
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        
        final TextView textView = binding.textHome;
        final Button btnRegister = binding.btnRegister;
        final Button btnEnter = binding.btnEnter;
        final Button buttonEnter = binding.buttonEnter;
        
        linearLayoutTop = binding.linerLayoutTop;
        linearLayoutEnter = binding.linerLayoutEnter;
        linearLayoutRegistry = binding.linearLayoutRegistry;
        
        
        btnRegister.setOnClickListener(v -> register());
        btnEnter.setOnClickListener(d ->enter());
        buttonEnter.setOnClickListener(s ->in());
        
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    
    private void in() {
        final EditText login = binding.editTextTextPersonName;
        final EditText password = binding.editTextTextPassword;
        DatabaseAdapter adapter = new DatabaseAdapter(Objects.requireNonNull(getActivity()));
        adapter.open();
        User user = adapter.getUserByLoginAndPassword(login.getText().toString(), password.getText().toString());
        adapter.close();
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void register(){
        linearLayoutRegistry.setVisibility(View.VISIBLE);
        linearLayoutEnter.setVisibility(View.GONE);
        linearLayoutTop.setVisibility(View.GONE);
    }
    public void enter(){
        linearLayoutEnter.setVisibility(View.VISIBLE);
        linearLayoutRegistry.setVisibility(View.GONE);
        linearLayoutTop.setVisibility(View.GONE);
    }
}