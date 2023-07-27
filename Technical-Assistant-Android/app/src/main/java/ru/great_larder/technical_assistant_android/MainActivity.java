package ru.great_larder.technical_assistant_android;

import android.os.Bundle;
import android.view.Menu;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import ru.great_larder.technical_assistant_android.database.DatabaseAdapterSQLite;
import ru.great_larder.technical_assistant_android.databinding.ActivityMainBinding;
import ru.great_larder.technical_assistant_android.domain.user.User;
import ru.great_larder.technical_assistant_android.services.DataUser;
import ru.great_larder.technical_assistant_android.services.HandlerUserListener;
import ru.great_larder.technical_assistant_android.services.ObserverUser;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ObserverUser {
    
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    HandlerUserListener handlerUserListener = new HandlerUserListener();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        handlerUserListener.registerObserverUser(this);
        GlobalLinkUser.setHandlerUserListener(handlerUserListener);
        
        List<User> users;
        DatabaseAdapterSQLite adapterSQLite = new DatabaseAdapterSQLite(this);
        adapterSQLite.open();
        users = adapterSQLite.getUsers();
        adapterSQLite.close();
        
        if(users.size() == 1){
            GlobalLinkUser.setUser(users.get(0));
        } else {
            GlobalLinkUser.setUser(null);
        }
        
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show());
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
            R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
            .setOpenableLayout(drawer)
            .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
            || super.onSupportNavigateUp();
    }
    
    @Override
    public void updateUser(DataUser dataUser) {
    
    }
}