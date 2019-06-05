package com.example.aziz.earth;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>>{
    ArrayList<Earthquake> earthquakes = new ArrayList<>();
    EarthquakeAdapter mAdapter;
    ListView earthquakeListView;
    ImageView mEmptyState;
    ProgressBar mProgressBar;

    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=2&limit=220";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmptyState =  findViewById(R.id.empty_view);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        earthquakeListView = (ListView) findViewById(R.id.list);



        if(!isInternetConnection())
        {
//            Toast.makeText(this, "NO CONNECt", Toast.LENGTH_SHORT).show();
            mProgressBar.setVisibility(View.GONE);
            earthquakeListView.setEmptyView(mEmptyState);
        }
        else
        {
            LoaderManager loaderManager  = getLoaderManager();
            loaderManager.initLoader(110, null, this);
        }


        mAdapter = new EarthquakeAdapter(this, earthquakes);
        earthquakeListView.setAdapter(mAdapter);
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this, earthquakes.get(i).getUrl(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(earthquakes.get(i).getUrl()));

                if(intent.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(intent);
                }
            }
        });



    }
    public  boolean isInternetConnection()
    {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected())
        return true;
        return false;
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        mAdapter.clear();
        if(earthquakes == null) {
            return;
        }

        mAdapter.addAll(earthquakes);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        mAdapter.clear();
    }


//    public class EearthQuackAsynTask extends AsyncTask<String,Void,List<Earthquake>>{
//        @Override
//        protected List<Earthquake> doInBackground(String... strings) {
//            List<Earthquake> llst=QueryUtils.fetchEarthquakeData(strings[0]);
//            return llst;
//        }
//
//        @Override
//        protected void onPostExecute(List<Earthquake> earthquakes) {
////            mAdapter.clear();
//            mAdapter.addAll(earthquakes);
//
//        }
//    }

}