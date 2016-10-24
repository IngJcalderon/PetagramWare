package mx.unam.mascotamenus;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

import mx.unam.mascotamenus.adapter.PageAdapter;
import mx.unam.mascotamenus.pojo.Mascota;
import mx.unam.mascotamenus.vista.fragment.Favoritos;
import mx.unam.mascotamenus.vista.fragment.RecyclerViewFragmentDetalle;
import mx.unam.mascotamenus.vista.fragment.RecyclerViewFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager vpPager;
    private int[] tabIcons = {
            R.drawable.ic_home,
            R.drawable.ic_favorito
    };

    Mascota mascota;
    ArrayList<Mascota> listMacotas;
    private RecyclerView rvMascotas;
    private TextView tvfavorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //
        tabLayout =(TabLayout) findViewById(R.id.tabLayout);
        vpPager =(ViewPager) findViewById(R.id.vpPager);
        setUpViewPager();
        //setupTabIcons();

        if (toolbar==null){
            setSupportActionBar(toolbar);
        }

        vpPager.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /*Log.i("***","***");
                Toast.makeText(getActivity(), "XXXX", Toast.LENGTH_SHORT).show();*/
                Snackbar.make(v, "Diste Like", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //


        tvfavorito=(TextView)  findViewById(R.id.tvfavorito);

        tvfavorito.setOnClickListener(new AdapterView.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              Intent intent=new Intent(MainActivity.this,Favoritos.class);
                                              startActivity(intent);
                                          }
                                      }
        );

    }
/*
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id){
            case R.id.mContacto:
                intent=new Intent(this,Activity_Contacto.class);
                startActivity(intent);
                break;
            case R.id.mAcerca:
                intent=new Intent(this,Activity_Acercade.class);
                startActivity(intent);
                break;
            case R.id.mConfCuenta:
                intent=new Intent(this,configurar_cuenta.class);
                startActivity(intent);
                break;
            case R.id.mRecibirNotificaciones:
                intent=new Intent(this,configurar_cuenta.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    private  ArrayList<Fragment> agregarFraments(){
        ArrayList<Fragment> fragments=new ArrayList<>();

        fragments.add(new RecyclerViewFragment());
        fragments.add(new RecyclerViewFragmentDetalle());

        return fragments;
    }

    private  void setUpViewPager(){
        vpPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFraments()));
        tabLayout.setupWithViewPager(vpPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(0).setText("Lista");

        tabLayout.getTabAt(1).setIcon(R.drawable.ic_favorito);
        tabLayout.getTabAt(1).setText("Mascota");

    }

/*
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new RecyclerViewFragment(), "ONE");
        adapter.addFrag(new RecyclerViewFragmentDetalle(), "TWO");
        viewPager.setAdapter(adapter);
    }
    */
    /*
    private  void setupTabIcons(){
        //vpPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFraments()));
        //tabLayout.setupWithViewPager(vpPager);

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("ONE");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_home, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabOne1 = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne1.setText("ONE2");
        tabOne1.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_favorito, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabOne1);


    }

    */
/*

    private void setUpViewPager(ViewPager vpPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new RecyclerViewFragment(), "ONE");
        adapter.addFrag(new RecyclerViewFragmentDetalle(), "TWO");
        vpPager.setAdapter(adapter);
    }
*/
/*
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
*/
}
