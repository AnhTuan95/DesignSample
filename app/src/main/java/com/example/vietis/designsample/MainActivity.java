package com.example.vietis.designsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.vietis.designsample.adapter.SimpleRecyclerAdapter;
import com.example.vietis.designsample.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String PREF_USER_FIRST_TIME = "user_first_time";
    boolean isUserFirstTime;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    SimpleRecyclerAdapter adapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isUserFirstTime = Boolean.valueOf(Utils.readSharedSetting(MainActivity.this, PREF_USER_FIRST_TIME, "true"));
        Intent introIntent = new Intent(MainActivity.this, PagerActivity.class);
        introIntent.putExtra(PREF_USER_FIRST_TIME, isUserFirstTime);

        if (isUserFirstTime){
            startActivity(introIntent);
        }

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar_elevated);
      /*  if (toolbar == null){

        }*/

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Design Sample");
        recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if (adapter == null){
            adapter = new SimpleRecyclerAdapter(this);
            recyclerView.setAdapter(adapter);
        }

        adapter.SetOnItemClickListener(new SimpleRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                switch (position){
                    /*case 0:
                        intent = new Intent(MainActivity.this, FabHideActivity.class);
                        break;*/
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Inflater the menu, this adds items to the action bar if it is present. */
        getMenuInflater().inflate(R.menu.menu_home_navigator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
