package com.example.kev.kevmusic;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BackActivity extends AppCompatActivity {
    ProgressDialog PD;
    ListView musicList;
    private static String url;
    Context context;
    ArrayList<String> itemsList;
    ArrayList<SingleItem> singleItems;
    MyAdapter adapterd ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        PD = new ProgressDialog(this);
        PD.setMessage("Loading ...");
        PD.setCancelable(true);

        musicList = (ListView) findViewById(R.id.musicList);

        itemsList = new ArrayList<String>();
        singleItems = new ArrayList<SingleItem>();

        Intent thisIntent = getIntent();
        url = thisIntent.getStringExtra("url");


        musicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingleItem Item = singleItems.get(position);
                String videoUrl = Item.getUrlImage();
                String artist = Item.getArtist();
                String track = Item.getDesc();
                //Toast.makeText(MainActivity.this,"Artist: "+artistd,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(BackActivity.this, Display.class);
                i.putExtra("videoID",videoUrl);
                i.putExtra("track",track);
                i.putExtra("artist",artist);
                startActivity(i);
            }
        });

        adapterd = new MyAdapter(getApplicationContext(), R.layout.record_layout, singleItems);
        musicList.setAdapter(adapterd);
        makeJsonArrayRequest();
    }
    private void makeJsonArrayRequest(){
        PD.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            singleItems.clear();
                            int N = response.length();
                            for(int i=0; i < response.length(); i++){
                                JSONObject jresponse = response.getJSONObject(i);
                                //System.out.println("TRACK NAME: " + jresponse.getString("artist"));
                                singleItems.add(new SingleItem(
                                        jresponse.getString("tractName"),
                                        jresponse.getString("artist"),
                                        jresponse.getString("videoID")
                                ));
                            }
                        }catch (JSONException e){
                            //Log.d("KELVIN ERROR", "ERROR OCCURED");
                        }
                        PD.dismiss();
                        adapterd.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        queue.add(jsonRequest);
    }
    private class MyAdapter extends ArrayAdapter {

        private Context context;
        private View single_view;
        private ArrayList<SingleItem> singleItems;
        private LayoutInflater layoutInflater;


        public MyAdapter(Context context, int resource, ArrayList<SingleItem> singleItems) {
            super(context, resource, singleItems);
            this.context = context;
            this.singleItems = singleItems;
            layoutInflater = LayoutInflater.from(this.context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View row = convertView;
            ViewHolder holder = null;
            if (row == null) {
                row = layoutInflater.inflate(R.layout.record_layout, null);
                holder = new ViewHolder();
                holder.artistView = (TextView) row.findViewById(R.id.artistlabel);
                holder.descView = (TextView) row.findViewById(R.id.desclabel);
                holder.imageView = (ImageView) row.findViewById(R.id.imageView);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            final SingleItem singleItem = singleItems.get(position);
            holder.artistView.setText(singleItem.getArtist());
            holder.descView.setText("by "+singleItem.getDesc());

            String videoID = singleItem.getUrlImage();
            String url = "http://img.youtube.com/vi/"+videoID+"/1.jpg";
            Picasso.with(context).load(url).into(holder.imageView);

            row.setTag(holder);
            return row;
        }
    }

    public class ViewHolder {
        TextView artistView;
        TextView descView;
        ImageView imageView;
    }

    public class SingleItem {

        private String artist;
        private String urlImage;
        private String desc;

        public SingleItem() {
        }

        public SingleItem(String artist, String desc, String urlImage) {
            this.artist = artist;
            this.urlImage = urlImage;
            this.desc = desc;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getUrlImage() {
            return urlImage;
        }

        public void setUrlImage(String urlImage) {
            this.urlImage = urlImage;
        }
    }
}
