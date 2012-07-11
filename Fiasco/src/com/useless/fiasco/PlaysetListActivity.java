package com.useless.fiasco;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class PlaysetListActivity extends FragmentActivity
        implements PlaysetListFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playset_list);

        if (findViewById(R.id.playset_detail_container) != null) {
            mTwoPane = true;
            ((PlaysetListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.playset_list))
                    .setActivateOnItemClick(true);
        }
    }

    public void onItemSelected(String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(PlaysetDetailFragment.ARG_ITEM_ID, id);
            PlaysetDetailFragment fragment = new PlaysetDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.playset_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, PlaysetDetailActivity.class);
            detailIntent.putExtra(PlaysetDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
