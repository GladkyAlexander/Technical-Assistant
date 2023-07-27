package ru.great_larder.technical_assistant_android.ui.item;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ru.great_larder.technical_assistant_android.R;

public class ItemRowSpinnerFragment extends Fragment {
  
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_item_row_spinner, container, false);
        
        TextView text = root.findViewById(R.id.text_language);
        ImageView icon = root.findViewById(R.id.icon);
        
        return root;
    }
}