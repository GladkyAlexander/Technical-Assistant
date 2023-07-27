package ru.great_larder.technical_assistant_android.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import ru.great_larder.technical_assistant_android.GlobalLinkUser;
import ru.great_larder.technical_assistant_android.R;
import ru.great_larder.technical_assistant_android.databinding.FragmentHomeBinding;
import ru.great_larder.technical_assistant_android.domain.user.User;
import ru.great_larder.technical_assistant_android.ui.registration.RegistrationFragment;

public class HomeFragment extends Fragment {
    
    private FragmentHomeBinding binding;
    private FragmentContainerView fragmentContainerViewHome;
    
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
            new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);
        
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        
        fragmentContainerViewHome = binding.fragmentContainerViewHome;
        loadFragment(GlobalLinkUser.getUser());
        
       /* final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);*/
        return root;
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void loadFragment(User user){
        if(user == null){
            RegistrationFragment registrationFragment = new RegistrationFragment();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.replace(fragmentContainerViewHome.getId(), registrationFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}