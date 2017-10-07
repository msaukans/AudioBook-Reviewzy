package gq.codester.maris.audiobookreviewzy;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener {

    private static final String REGISTER_URL = "http://codester.gq/book/addBook.php";

    FloatingActionButton fab;
    TextView tvUsername1,tvUsername2;
    EditText edSearch, ed_bname, ed_bauthor, ed_bDesc, ed_relD, ed_relM, ed_relY, ed_genre;
    String username,username2, bName, bAuthor, bDesc, relD, relM, relY, bGenre,rel;
    ImageView profilePic;

    ListView book_list;

    int[] pic = {
            R.mipmap.ic_add,
            R.mipmap.ic_add,
            R.mipmap.ic_add,
            R.mipmap.ic_add,
            R.mipmap.ic_add,
            R.mipmap.ic_add,
            R.mipmap.ic_add
    };

    String[] name = {
            "rent",
            "phone bill",
            "electricity",
            "car",
            "Magazine subscr",
            "Internet",
            "water"
    } ;

    String[] author = {
            "500",
            "20",
            "100",
            "200",
            "10",
            "40",
            "50"
    } ;

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


        //User details in navigation menu on top part of drawer
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


        //Displaying the book list
        final CustomList adapter = new CustomList(MainActivity.this, pic,name, author);
        book_list = (ListView)findViewById(R.id.lv);
        book_list.setAdapter(adapter);


        book_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {//to edit item in first adapter

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = book_list.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "clicked on: " + value, Toast.LENGTH_SHORT).show();

            }
        });

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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            startActivity(new Intent(this, Profile.class));
        } else if (id == R.id.nav_library) {
            //startActivity(new Intent(this, Library.class));
        } else if (id == R.id.nav_addBook) {
            createBook();
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, Settings.class));
        }else if (id == R.id.log_out) {
            // login.clear() users login from the sharedpreference
            //pass.clear()
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

    public void createBook(){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setTitle("Create Book");
        dialog.setContentView(R.layout.book_popup);

        ed_bname = (EditText) dialog.findViewById(R.id.ed_bname);
        ed_bauthor = (EditText) dialog.findViewById(R.id.ed_bauthor);
        ed_bDesc = (EditText) dialog.findViewById(R.id.ed_bDesc);
        ed_relD = (EditText) dialog.findViewById(R.id.ed_relD);
        ed_relM = (EditText) dialog.findViewById(R.id.ed_relM);
        ed_relY = (EditText) dialog.findViewById(R.id.ed_relY);
        ed_genre = (EditText) dialog.findViewById(R.id.ed_genre);

        Button btn = (Button) dialog.findViewById(R.id.btn_getBookDet);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //what happens when clicked done
                bName = ed_bname.getText().toString();
                bAuthor = ed_bauthor.getText().toString();
                bDesc = ed_bDesc.getText().toString();
                relD = ed_relD.getText().toString();
                relM = ed_relM.getText().toString();
                relY = ed_relY.getText().toString();
                bGenre = ed_genre.getText().toString();

                rel = relY+"/"+relM+"/"+relD;

                //pass this to the background service which will get the search results
                //and send you over to the search results activity

                if(TextUtils.isEmpty(bName)) {
                    Toast.makeText(MainActivity.this, "Please enter the details", Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(relD)) {
                    Toast.makeText(MainActivity.this, "Please enter the day that the book was released", Toast.LENGTH_LONG).show();
                    return;
                }

                if(!relD.matches("[0-9]+")){
                    Toast.makeText(MainActivity.this, "The day of the release can only be numbers", Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(relM)) {
                    Toast.makeText(MainActivity.this, "Please enter the details", Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(relY) ) {
                    Toast.makeText(MainActivity.this, "Please enter the details", Toast.LENGTH_LONG).show();
                    return;
                }

                register(bName,bAuthor,bDesc,rel);
                dialog.dismiss();

            }

        });
        dialog.show();

    }//end createBook()

    private void register(String name, String authName, String descr, String pubDate) {
        String urlSuffix = "?name="+name+"&authName="+authName+"&descr="+descr+"&pubDate="+pubDate;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);



                loading.dismiss();
                Toast.makeText(getApplicationContext(),s ,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(REGISTER_URL+s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;

                    result = bufferedReader.readLine();

                    return result;

                }catch(Exception e){
                    return null;
                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);
    }
}
