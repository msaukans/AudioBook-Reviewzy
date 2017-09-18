package gq.codester.maris.audiobookreviewzy;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener {

    FloatingActionButton fab;
    TextView tvUsername1,tvUsername2;
    EditText edSearch;
    String username,username2;
    ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

        profilePic = (ImageView) headerView.findViewById(R.id.user_pic);
        profilePic.setImageResource(R.mipmap.ic_add);

        username = "marsis";
        tvUsername1 = (TextView) headerView.findViewById(R.id.nav_username);
        tvUsername1.setText(username);

        username2 = "marsis@maris.uk";
        tvUsername2 = (TextView) headerView.findViewById(R.id.nav_username2);
        tvUsername2.setText(username2);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "option 1 pressed", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, Settings.class));
            return true;
        }
        else if (id == R.id.search) {
            Toast.makeText(this, "option 2 pressed", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_settings2) {
            Toast.makeText(this, "option 3 pressed", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            startActivity(new Intent(this, Profile.class));
        } else if (id == R.id.nav_library) {
            //startActivity(new Intent(this, Library.class));
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, Settings.class));
        }else if (id == R.id.log_out) {
            // login.clear() users login from the sharedpreference
            startActivity(new Intent(this, LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClick(View v){
        if(fab == v){
            {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setTitle("Search Box");
                dialog.setContentView(R.layout.search_popup);

                edSearch = (EditText) dialog.findViewById(R.id.search_in);

                Button btn = (Button) dialog.findViewById(R.id.btn_search);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //what happens when clicked done
                        String search = edSearch.getText().toString();
                        //pass this to the background service which will get the search results
                        //and send you over to the search results activity
                        Toast.makeText(MainActivity.this,  "Search for: " + search, Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }

        }


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
