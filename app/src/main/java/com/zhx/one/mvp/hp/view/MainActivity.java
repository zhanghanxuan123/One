package com.zhx.one.mvp.hp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.zhx.one.R;
import com.zhx.one.base.BaseActivity;
import com.zhx.one.base.BaseFragment;
import com.zhx.one.base.Main2Activity;
import com.zhx.one.bean.HPIdListEntity;
import com.zhx.one.bean.ReadingListEntity;
import com.zhx.one.mvp.movie.view.MovieFragment;
import com.zhx.one.mvp.music.view.MusicFragment;
import com.zhx.one.mvp.read.view.ReadFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int EXIT_APP_DELAY = 1000;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private FloatingActionButton mFab;

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private BaseFragment currentFragment;
    private int currentIndex;
    private SwitchCompat mThemeSwitch;
    private PopupWindow mPopupWindow;
    //private SearchViewHolder holder;
    private long lastTime = 0;
    HPIdListEntity mIdListEntity;
    private HPFragment mHPFragment;
    private ReadFragment mReadFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //mIdListEntity = (HPIdListEntity)getIntent().getSerializableExtra("HPIdList");
        //Log.i(TAG,"onCreate"+mIdListEntity.getData().get(0));
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }
        //selectFragment(R.id.nav_home);
        //currentFragment = HPFragment.newInstance(mIdListEntity);
        if (savedInstanceState == null) {
            //selectFragment(0);
            mHPFragment=HPFragment.newInstance();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.fm_content, mHPFragment).commit();
            currentFragment = mHPFragment;
            Log.i(TAG,"savedInstanceState == null");
        } else {
            //activity销毁后记住销毁前所在页面
            Log.i(TAG,"销毁后记住销毁前所在页面");
            //mIdListEntity = (HPIdListEntity) savedInstanceState.getSerializable("IdListEntit");
            currentIndex = savedInstanceState.getInt("currentIndex");
            switch (currentIndex) {
                case 0:
                    if (currentFragment == null) {
                        Log.i(TAG," HPFragment == null");
                        currentFragment = HPFragment.newInstance();

                    }
                    switchContent(null, currentFragment);
                    break;
                case 1:
                    if (currentFragment == null) {
                        Log.i(TAG," ReadFragment == null");
                        currentFragment = ReadFragment.newInstance();

                    }
                    switchContent(null, currentFragment);
                    break;
                case 2:
                    if (currentFragment == null) {
                        currentFragment = MusicFragment.newInstance();
                    }
                    switchContent(null, currentFragment);
                    break;
                case 3:
                    if (currentFragment == null) {
                        currentFragment = MovieFragment.newInstance();
                    }
                    switchContent(null, currentFragment);
                    break;
            }
        }
        //initNavView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void initEvents() {
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (currentFragment == null) {
                currentFragment = HPFragment.newInstance();
            }
            /*if (!(currentFragment instanceof HPFragment)) {
                switchContent(currentFragment, HPFragment.newInstance());

                return;
            }*/
            if ((System.currentTimeMillis() - lastTime) > EXIT_APP_DELAY) {
                Snackbar.make(mDrawerLayout, "再次返回退出", Snackbar.LENGTH_SHORT).show();
                lastTime = System.currentTimeMillis();
            } else {
                moveTaskToBack(true);
            }
        }
    }

    public void setToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            mToolbar = toolbar;
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            mDrawerLayout.addDrawerListener(toggle);
            toggle.syncState();
            mNavView.setNavigationItemSelectedListener(this);
        }
    }

    public void setFab(FloatingActionButton fab) {
        mFab = fab;
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    /**
     * switch fragment.
     *
     * @param from
     * @param to
     */

    public void switchContent(BaseFragment from, BaseFragment to) {
        if (currentFragment == null) {
            return;
        } else {
            currentFragment = to;
            //添加渐隐渐现的动画
            FragmentTransaction ft = fragmentManager.beginTransaction();
            //ft.setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragment_fade_out);
            ft.replace(R.id.fm_content, to).commit();
            //hideAllFragment(ft);

        }
        invalidateOptionsMenu();
    }

    private void selectFragment(int fragmentId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideAllFragment(transaction);
        switch (fragmentId) {
            case 0:
                if (mHPFragment == null) {
                    mHPFragment = HPFragment.newInstance();
                    // todo diff with transaction.replace() ?
                    Log.i("selectFragment","mHPFragment == null");
                    transaction.add(R.id.fm_content,mHPFragment);
                } else {
                    Log.i("selectFragment","mHPFragment != null");
                    transaction.show(mHPFragment);
                    //transaction.show(mHPFragment);
                }
                currentFragment = mHPFragment;
                break;

            case 1:
                if (mReadFragment == null) {
                    Log.i("selectFragment","mReadFragment == null");
                    mReadFragment = ReadFragment.newInstance();
                    transaction.add(R.id.fm_content, mReadFragment);
                } else {
                    Log.i("selectFragment","mReadFragment != null");
                    transaction.show(mReadFragment);
                }
                currentFragment = mReadFragment;
                break;
        }
        transaction.commit();
    }

    private void hideAllFragment(FragmentTransaction transaction) {
        if (mHPFragment != null) {
            transaction.hide(mHPFragment);
        }
        if (mReadFragment != null) {
            transaction.hide(mReadFragment);
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            //showSearchView();
            startActivity(new Intent(MainActivity.this, Main2Activity.class));
            return true;
        }
        currentFragment.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        int menuId = R.menu.main;
        /*if (currentFragment instanceof HomeFragment) {
            menuId = R.menu.main;
        } else if (currentFragment instanceof BookshelfFragment) {
            //menuId = R.menu.bookshelf_main;
        } else if (currentFragment instanceof EBookFragment) {
            //menuId = R.menu.ebook_main;
        }*/
        getMenuInflater().inflate(menuId, menu);
        currentFragment.onCreateOptionsMenu(menu, getMenuInflater());
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Log.d(TAG,"R.id.nav_home");
            //switchContent(currentFragment, HPFragment.newInstance());
            selectFragment(0);
            currentIndex = 0;
        } else if (id == R.id.nav_reading) {
            Log.d(TAG,"R.id.nav_reading");
            selectFragment(1);
            currentIndex = 1;
            //switchContent(currentFragment, ReadFragment.newInstance());
        } else if (id == R.id.nav_music) {
            Log.d(TAG,"R.id.nav_music");
            switchContent(currentFragment, MusicFragment.newInstance());
            currentIndex = 2;
        } else if (id == R.id.nav_movie) {
            Log.d(TAG,"R.id.nav_movie");
            switchContent(currentFragment, MovieFragment.newInstance());
            currentIndex = 3;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        /*ReadingListEntity read = new ReadingListEntity();
        read.setRes(123);
        outState.putSerializable("IdListEntity",mIdListEntity);*/
        outState.putInt("currentIndex",1);
        super.onSaveInstanceState(outState);
        Log.i(TAG,"onSaveInstanceState");
    }

}
