package com.example.ashutoshdwivedi.carpoolstudents;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;

public class MainActivity extends AppCompatActivity {
    DrawerBuilder mDrawerBuilder;
    Drawer mDrawer;
    AccountHeader mAccountHeader;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        //setting profile
        final IProfile profile = new ProfileDrawerItem().withName("Test User").withEmail("test@gmail.com").withIcon("https://avatars3.githubusercontent.com/u/1476232?v=3&s=460").withIdentifier(100);


        // setting account header
        mAccountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.header)
                .withCompactStyle(true)
                .withSavedInstance(savedInstanceState)
                .addProfiles(
                        profile


                ).withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        // logic when header is clicked

                        return false;
                    }
                })
                .build();
        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                super.set(imageView, uri, placeholder);
                Glide.with(MainActivity.this).load(uri.toString()).crossFade().centerCrop()
                        .placeholder(R.drawable.user).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);

            }

            @Override
            public void cancel(ImageView imageView) {
                super.cancel(imageView);
                Glide.clear(imageView);
            }

            @Override
            public Drawable placeholder(Context ctx) {
                return super.placeholder(ctx);
            }
        });




        //Setting up drawer
        mDrawer= new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withAccountHeader(mAccountHeader)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.LEFT)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(0)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.get_ride).withIcon(R.drawable.cab).withIdentifier(1).withSelectable(true),
                        new PrimaryDrawerItem().withName(R.string.offer_ride).withIcon(R.drawable.discount).withIdentifier(2).withSelectable(true),
                        new PrimaryDrawerItem().withName(R.string.ride_history).withIcon(R.drawable.history).withIdentifier(3).withSelectable(true),
                        new PrimaryDrawerItem().withName(R.string.notification).withIcon(R.drawable.notification).withIdentifier(4).withSelectable(true)


                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if(drawerItem!=null)
                        {
                            if(drawerItem.getIdentifier()==1)
                            {
                                Toast.makeText(getApplicationContext(),"Clicked :"+position,Toast.LENGTH_SHORT).show();
                            }
                           else if(drawerItem.getIdentifier()==2)
                            {
                                Toast.makeText(getApplicationContext(),"Clicked :"+position,Toast.LENGTH_SHORT).show();
                            }
                            else if(drawerItem.getIdentifier()==3)
                            {
                                Toast.makeText(getApplicationContext(),"Clicked :"+position,Toast.LENGTH_SHORT).show();
                            }
                            else if(drawerItem.getIdentifier()==4)
                            {
                                Toast.makeText(getApplicationContext(),"Clicked :"+position,Toast.LENGTH_SHORT).show();
                            }
                        }


                        return false;
                    }
                })
                .withShowDrawerOnFirstLaunch(true)
                .build();


}
}