package com.example.android.popularmovies;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.example.android.popularmovies.data.TextViewAdapter;
import com.example.android.popularmovies.utilities.FetchMovieTask;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String order = "popular"; // default;
    ProgressDialog dialog;

    private TextView mErrorMessageDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadMovieData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.popular_order){
            order = getString(R.string.popular_order_api);
            loadMovieData();
            return true;
        }
        if (id==R.id.rated_order){
            order = getString(R.string.rated_order_api);
            loadMovieData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void loadMovieData() {
        if (isOnline()){
            dialog = ProgressDialog.show(MainActivity.this, "", "Loading...");
            new FetchMovieTask(this, new FetchMyDataTaskCompleteListener()).execute(order);
        }else{
            GridView gridview = (GridView) findViewById(R.id.movies_grid);
            gridview.setAdapter(new TextViewAdapter(this));
        }
    }

    public class FetchMyDataTaskCompleteListener implements AsyncAux{

        @Override
        public void onTaskComplete(List result) {
            dialog.dismiss();
        }
    }
}
