package com.example.vietis.designsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
    private SwipeRefreshLayout pullToRefresh;
    private RecyclerView recyclerView;
    SimpleRecyclerAdapter adapter;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isUserFirstTime = Boolean.valueOf(Utils.readSharedSetting(MainActivity.this, PREF_USER_FIRST_TIME, "true"));
        final Intent introIntent = new Intent(MainActivity.this, PagerActivity.class);
        introIntent.putExtra(PREF_USER_FIRST_TIME, isUserFirstTime);

        if (isUserFirstTime) {
            startActivity(introIntent);
        }

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar_elevated);
      /*  if (toolbar == null){

        }*/

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Design Sample");
        pullToRefresh = (SwipeRefreshLayout) findViewById(R.id.pull_to_refresh);
        recyclerView = (RecyclerView) findViewById(R.id.main_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        if (adapter == null) {
            adapter = new SimpleRecyclerAdapter(MainActivity.this);
            recyclerView.setAdapter(adapter);
        }

        adapter.SetOnItemClickListener(new SimpleRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "Item " + position, Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        intent = new Intent(MainActivity.this, FabHideActivity.class);
                        break;
                }

                if (intent != null) {
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (adapter == null) {
                    adapter = new SimpleRecyclerAdapter(MainActivity.this);
                    recyclerView.setAdapter(adapter);
                }
                stopRefresh();
            }
        });
    }

    public void stopRefresh() {
        pullToRefresh.setRefreshing(false);
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
        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
