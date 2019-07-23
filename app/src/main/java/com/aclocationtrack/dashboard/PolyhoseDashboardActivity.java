package com.aclocationtrack.dashboard;

import android.Manifest;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseActivity;
import com.aclocationtrack.dashboard.anouncement.AnouncementFragment;
import com.aclocationtrack.dashboard.customer.AddCustomerFragment;
import com.aclocationtrack.dashboard.customer.CustomerListFragment;
import com.aclocationtrack.dashboard.downloads.DownloadsFragment;
import com.aclocationtrack.dashboard.myattendance.AddAttendenceFragment;
import com.aclocationtrack.dashboard.myattendance.AttendenceListFragment;
import com.aclocationtrack.dashboard.polydashboard.PloyDashBoardFragment;
import com.aclocationtrack.dashboard.tasks.AddTaskFragment;
import com.aclocationtrack.dashboard.tasks.TaskListFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class PolyhoseDashboardActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int RC_LOCATION = 12;


    @BindView(R.id.container)
    FrameLayout frameLayout;

    private Fragment fragment = null;

    TextView mName;
    TextView mRole;
    TextView mHeaderRegion;
    private NavigationView navigationView;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poly_dashboard);

        setButterKnife();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_dashboard);
        addFragment(R.id.nav_dashboard);

        View header = navigationView.getHeaderView(0);

        mName = (TextView) header.findViewById(R.id.name);
        mRole = (TextView) header.findViewById(R.id.role);
        mHeaderRegion = (TextView) header.findViewById(R.id.heade_region);

        setTitle(getString(R.string.polydash));

        String n = dataSource.getName();
        String role = dataSource.getRoleType();

        mName.setText(n + "");
        mRole.setText(role + "");

//
//        if (getIntent() != null) {
//
//            int id = getIntent().getIntExtra("position", R.id.nav_my_order);
//
//            callFragment(navigationView, id);
//
//        }
        setCustomFontForNavigation();
    }


    private void setCustomFontForNavigation() {
        final Menu m = navigationView.getMenu();
        for (int i = 0; i < m.size(); i++) {
            MenuItem mi = m.getItem(i);

            SpannableString s1 = new SpannableString(mi.getTitle());
            s1.setSpan(new TypefaceSpan("font/Roboto-Regular.ttf"), 0, s1.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            mi.setTitle(s1);

            //for applying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    SpannableString s = new SpannableString(subMenuItem.getTitle());
                    s.setSpan(new TypefaceSpan("font/Roboto-Regular.ttf"), 0, s.length(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    subMenuItem.setTitle(s);
                }
            }

        }

//        final Menu navMenu = navigationView.getMenu();
//        navigationView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                ArrayList<View> menuItems = new ArrayList<>(); // save Views in this array
//                navigationView.getViewTreeObserver().removeOnGlobalLayoutListener(this); // remove the global layout listener
//                for (int i = 0; i < navMenu.size(); i++) {// loops over menu items  to get the text view from each menu item
//                    final MenuItem item = navMenu.getItem(i);
//
//                    navigationView.findViewsWithText(menuItems, item.getTitle(), View.FIND_VIEWS_WITH_TEXT);
//                }
//                for (final View menuItem : menuItems) {// loops over the saved views and sets the font
//                    ((TextView) menuItem).setTypeface(Typeface.createFromAsset(getAssets(), "font/Loto-Regular.ttf"), Typeface.NORMAL);
//                }
//            }
//        });

    }


    private void callFragment(NavigationView navigationView, int id) {
        navigationView.setCheckedItem(id);
        addFragment(id);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {

            getSupportFragmentManager().popBackStack();
            navigationView.setCheckedItem(R.id.nav_dashboard);
            setTitle(getString(R.string.polydash));
        } else {
            super.onBackPressed();
        }
    }

//    @AfterPermissionGranted(RC_LOCATION)
//    private void locationPermission() {
//        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
//        if (EasyPermissions.hasPermissions(this, perms)) {
//            // Already have permission, do the thing
//
//
//            if (isLocationServiceEnabled()) {
//
////                if (dataSource.getRoleType().toLowerCase().equalsIgnoreCase("customer")) {
////
////                    startActivity(new Intent(DealerDashboardActivity.this, AddOrderForCustomerActivity.class).putExtra("isfinish", false));
////                } else {
////                    startActivity(new Intent(DealerDashboardActivity.this, AddOrderForDealerActivity.class).putExtra("isfinish", false));
////                }
//            }
//            // ...
//        } else {
//            // Do not have permissions, request them now
//            EasyPermissions.requestPermissions(this, getString(R.string.location_rationale),
//                    RC_LOCATION, perms);
//        }
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        addFragment(id);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }


    //    @OnClick({R.id.dash_my_attendance, R.id.dash_customer, R.id.dash_add_task, R.id.dash_announcement, R.id.dash_download, R.id.dash_task_list})
    public void onDashboardClick(View view) {

        switch (view.getId()) {

            case R.id.dash_my_attendance:

                enableNavigation(R.id.nav_my_attendance);

                break;

            case R.id.dash_add_task:

                enableNavigation(R.id.nav_add_task);

                break;

            case R.id.dash_announcement:

                enableNavigation(R.id.nav_anouncement);

                break;


            case R.id.dash_customer:

                enableNavigation(R.id.nav_customer_list);

                break;
            case R.id.dash_download:

                enableNavigation(R.id.nav_downlods);

                break;

            case R.id.dash_task_list:

                enableNavigation(R.id.nav_task_list);

                break;


        }

    }

    public void enableNavigation(int id) {

        navigationView.setCheckedItem(id);
        addFragment(id);
    }


    public void addCustomerFragment() {


        getSupportFragmentManager().beginTransaction().add(R.id.container, AddCustomerFragment.getInstance(true)).addToBackStack(null).commit();

    }


    private void addFragment(int id) {


        switch (id) {


            case R.id.nav_dashboard:


                fragment = new PloyDashBoardFragment();
                setTitle(getString(R.string.polydash));

                break;

            case R.id.nav_my_attendance:
                fragment = new AddAttendenceFragment();
                setTitle(getString(R.string.my_attendance));
                break;
            case R.id.nav_attendance_list:
                fragment = new AttendenceListFragment();
                setTitle(getString(R.string.my_attendances));
                break;
            case R.id.nav_add_task:

                fragment = new AddTaskFragment();

                setTitle(getString(R.string.add_task));

                break;

            case R.id.nav_task_list:

                fragment = new TaskListFragment();

                setTitle(getString(R.string.task_list));

                break;
            case R.id.nav_add_customer:

                fragment = new AddCustomerFragment();

                setTitle(getString(R.string.add_customer));

                break;
            case R.id.nav_customer_list:

                fragment = new CustomerListFragment();

                setTitle(getString(R.string.customer));

                break;
            case R.id.nav_anouncement:

                fragment = new AnouncementFragment();

                setTitle(getString(R.string.anouncement));

                break;

            case R.id.nav_downlods:

                fragment = new DownloadsFragment();

                setTitle(getString(R.string.downloads));

                break;


            case R.id.nav_logout:

                logout();

                return;

            default:
                fragment = new PloyDashBoardFragment();
                setTitle(getString(R.string.polydash));

        }

        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_dashboard) {
            fragmentTransaction.replace(R.id.container, fragment);
        } else {
            getSupportFragmentManager().popBackStack();
            fragmentTransaction.replace(R.id.container, fragment).addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}
