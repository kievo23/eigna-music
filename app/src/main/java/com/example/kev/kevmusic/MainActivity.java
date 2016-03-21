package com.example.kev.kevmusic;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.millennialmedia.InlineAd;
import com.millennialmedia.MMException;
import com.millennialmedia.MMSDK;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String TAG = "Activity: ";
    TextView textBackground;
    Button billboard,christian,pop,hiphop,dance,country,electric,rock,rnb,workout,holiday;
    InlineAd inlineAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //TOOL BAR YA RIGHT
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FLOATER YA EMAIL
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //MMSDK.initialize(this);

        billboard       = (Button) findViewById(R.id.billboard);
        christian       = (Button) findViewById(R.id.christian);
        pop             = (Button) findViewById(R.id.pop);

        dance           = (Button) findViewById(R.id.dance);
        country         = (Button) findViewById(R.id.country);
        electric        = (Button) findViewById(R.id.electric);
        rock            = (Button) findViewById(R.id.rock);
        rnb             = (Button) findViewById(R.id.rnb);
        workout         = (Button) findViewById(R.id.workout);
        holiday         = (Button) findViewById(R.id.holiday);
        hiphop          = (Button) findViewById(R.id.hiphop);


        billboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BackActivity.class);
                i.putExtra("url", "http://api.academicsage.com/eigna/billboard.php");
                startActivity(i);
            }
        });
        christian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BackActivity.class);
                i.putExtra("url", "http://api.academicsage.com/eigna/billboard.php");
                startActivity(i);
            }
        });

        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BackActivity.class);
                i.putExtra("url", "http://api.academicsage.com/eigna/billboard.php");
                startActivity(i);
            }
        });
/*
        try {
            // NOTE: The ad container argument passed to the createInstance call should be the
            // view container that the ad content will be injected into.

            inlineAd = InlineAd.createInstance("222024", (ViewGroup) findViewById(R.id.mother));

            inlineAd.setListener(new InlineAd.InlineListener() {
                @Override
                public void onRequestSucceeded(InlineAd inlineAd) {
                    Log.i(TAG, "Inline Ad loaded.");
                }


                @Override
                public void onRequestFailed(InlineAd inlineAd, InlineAd.InlineErrorStatus errorStatus) {

                    Log.i(TAG, errorStatus.toString());
                }


                @Override
                public void onClicked(InlineAd inlineAd) {

                    Log.i(TAG, "Inline Ad clicked.");
                }


                @Override
                public void onResize(InlineAd inlineAd, int width, int height) {

                    Log.i(TAG, "Inline Ad starting resize.");
                }


                @Override
                public void onResized(InlineAd inlineAd, int width, int height, boolean toOriginalSize) {

                    Log.i(TAG, "Inline Ad resized.");
                }


                @Override
                public void onExpanded(InlineAd inlineAd) {

                    Log.i(TAG, "Inline Ad expanded.");
                }


                @Override
                public void onCollapsed(InlineAd inlineAd) {

                    Log.i(TAG, "Inline Ad collapsed.");
                }


                @Override
                public void onAdLeftApplication(InlineAd inlineAd) {

                    Log.i(TAG, "Inline Ad left application.");
                }
            });

        } catch (MMException e) {
            Log.e(TAG, "Error creating inline ad", e);
            // abort loading ad
        }*/
/*
        if (inlineAd != null) {
            // set a refresh rate of 30 seconds that will be applied after the first request
            inlineAd.setRefreshInterval(30000);

            // The InlineAdMetadata instance is used to pass additional metadata to the server to
            // improve ad selection
            final InlineAd.InlineAdMetadata inlineAdMetadata = new InlineAd.InlineAdMetadata().
                    setAdSize(InlineAd.AdSize.BANNER);

            inlineAd.request(inlineAdMetadata);
        }
*/
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

