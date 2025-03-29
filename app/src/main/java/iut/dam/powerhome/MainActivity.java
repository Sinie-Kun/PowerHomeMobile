package iut.dam.powerhome;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        drawerLayout = findViewById(R.id.drawer_layout);
//        navView = findViewById(R.id.nav_view);
//
//        // Ajouter l’icône du menu (≡)
//        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setHomeButtonEnabled(true); // (optionnel mais souvent utile)
//        }
//
//        // Afficher le fragment par défaut au lancement
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment_container, new HabitatsFragment())
//                    .commit();
//            navView.setCheckedItem(R.id.nav_habitats);
//        }
//
//        // Navigation entre les fragments
//        navView.setNavigationItemSelectedListener(item -> {
//            int id = item.getItemId();
//            if (id == R.id.nav_habitats){
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, new HabitatsFragment())
//                        .commit();
//            }
//            else if (id == R.id.nav_myhabitat){
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, new MyHabitatFragment())
//                        .commit();
//            }
//            else if (id == R.id.nav_mynotifications){
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, new MyNotificationsFragment())
//                        .commit();
//            }
//            else if (id == R.id.nav_mypreferences){
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.fragment_container, new MyPreferencesFragment())
//                        .commit();
//            }
//
//            drawerLayout.closeDrawer(GravityCompat.START);
//            return true;
//        });
//    }
//
//    // Synchroniser l'icône du menu avec l’ouverture du drawer
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
//    }
//
//
//
//        String urlString = "http:// [IP] /powerhome_server/getHabitats.php";
//    }
//    /*public void getRemoteHabitats() {
//        String urlString = "http://[server]/powerhome_server/getHabitats.php";
//        Ion.with(this)
//                .load(urlString)
//                .asString()
//                .setCallback(new FutureCallback<String>() {
//                    @Override
//                    public void onCompleted(Exception e, String result) {
//                        pDialog.dismiss();
//                        if(result == null)
//                            Log.d(TAG, "No response from the server!!!");
//                        else {
//                        }
//                    }
//                    // Traitement de result
//                }
//    });*/
