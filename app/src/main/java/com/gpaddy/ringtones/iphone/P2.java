package com.gpaddy.ringtones.iphone;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.List;

public class P2 extends AppCompatActivity {

    ViewPager pager;
    PagerTabStrip tabStrip;
    LinearLayout assistive;
    ImageView iconwallpaper;

    TextView buttonExit;
    TextView buttonRateApp;
    TextView buttonView;
    TextView nameApp;
    ImageView iconApp;
    TextView logo;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onResume() {
        super.onResume();

        View action_bar = findViewById(R.id.layout_action_bar);
        RelativeLayout layout_ads = (RelativeLayout) action_bar.findViewById(R.id.layout_ads_banner);
        adView = new AdView(this, "1515999665384488_1517419418575846", AdSize.BANNER_320_50);
        layout_ads.addView(adView);
        adView.loadAd();

        if(Build.VERSION.SDK_INT >=23 && isAskPermission)
        {
            boolean hasPermission1 = Settings.System.canWrite(this);

            if (!hasPermission1)
            {
                finish();
            }


        }
        isAskPermission = true;
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    private AdView adView;
    private boolean isAskPermission =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p2);

        if(Build.VERSION.SDK_INT >=23)
        {
            boolean hasPermission1 = Settings.System.canWrite(this);

            if (!hasPermission1)
            {

                Toast.makeText(getApplicationContext(), "Please accept iRingtone !",Toast.LENGTH_LONG).show();
                Intent grantIntent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.fromParts("package", getPackageName(), null));
                startActivity(grantIntent);
            }
        }

        View action_bar = findViewById(R.id.layout_action_bar);
        RelativeLayout layout_ads = (RelativeLayout) action_bar.findViewById(R.id.layout_ads_banner);

        adView = new AdView(this, "1515999665384488_1517419418575846", AdSize.BANNER_320_50);
        layout_ads.addView(adView);
        adView.loadAd();

        // load gooogle ads
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9182085626379132/4721928002");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                finish();
            }
        });

        requestNewInterstitial();

        if(Build.VERSION.SDK_INT >= 23)
        {
            boolean hasPermission1 = Settings.System.canWrite(this);

            if (false)
            {
               /* ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_SETTINGS},
                        REQUEST_WRITE_SETTINGS);*/
                Toast.makeText(getApplicationContext(), "Please accept iRingtone !", Toast.LENGTH_LONG).show();
                Intent grantIntent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS, Uri.fromParts("package", getPackageName(), null));
                startActivity(grantIntent);
            }
            else
            {
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View view) {
                        final Dialog dialog = new Dialog(P2.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.p25share);
                        dialog.getWindow().setBackgroundDrawableResource(R.drawable.themesetting);
                        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

                        TextView textfacebook = (TextView) dialog.findViewById(R.id.textfacebook);
                        TextView texttwitter = (TextView) dialog.findViewById(R.id.texttwitter);
                        TextView texthomepage = (TextView) dialog.findViewById(R.id.texthomepage);

                        textfacebook.setText("Share on Facebook");
                        textfacebook.startAnimation(AnimationUtils.loadAnimation(P2.this, R.anim.slide_in_left));

                        texttwitter.setText("Share on Twitter");
                        texttwitter.startAnimation(AnimationUtils.loadAnimation(P2.this, R.anim.slide_in_left));

                        texthomepage.setText("Home Page");
                        texthomepage.startAnimation(AnimationUtils.loadAnimation(P2.this, R.anim.slide_in_left));

                        // more ringtone
                        TextView textMoreRingtones = (TextView) dialog.findViewById(R.id.text_more_ringtones);
                        textMoreRingtones.setText(getString(R.string.more_ringtones));
                        textMoreRingtones.startAnimation(AnimationUtils.loadAnimation(P2.this, R.anim.slide_in_left));
                        ImageView moreRingtones = (ImageView) dialog.findViewById(R.id.more_ringtones);
                        moreRingtones.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent moreIntent = new Intent(Intent.ACTION_VIEW,
                                        Uri.parse("https://play.google.com/store/apps/details?id=com.zizi.ringtone.wallpaper"));
                                startActivity(moreIntent);
                            }
                        });
                        //==========================================

                        ImageView facebook = (ImageView) dialog.findViewById(R.id.facebook);
                        facebook.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_SEND);
                                intent.setType("text/plain");
                                intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + getPackageName());
                                boolean facebookAppFound = false;
                                List<ResolveInfo> matches = P2.this.getApplication().getPackageManager().queryIntentActivities(intent, 0);
                                for (ResolveInfo info : matches) {
                                    if (info.activityInfo.packageName.toLowerCase().startsWith(
                                            "com.facebook.katana")) {
                                        intent.setPackage(info.activityInfo.packageName);
                                        facebookAppFound = true;
                                        Intent chooser = Intent.createChooser(intent, "Open with");
                                        P2.this.startActivity(chooser);
                                        dialog.dismiss();
                                        break;
                                    }
                                }
                                if (!facebookAppFound) {
                                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(P2.this);
                                    builder.setMessage("Application not found")
                                            .setCancelable(false);
                                    android.app.AlertDialog alert = builder.create();
                                    alert.show();
                                }
                            }
                        });

                        ImageView twitter = (ImageView) dialog.findViewById(R.id.twitter);
                        twitter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String string = "https://play.google.com/store/apps/details?id=" + getPackageName();
                                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                                shareIntent.setType("text/plain");
                                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, string);
                                PackageManager pm = P2.this.getPackageManager();
                                List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
                                boolean shared = false;
                                for (final ResolveInfo app : activityList) {
                                    if (app.activityInfo.name.equals("com.twitter.android.composer.ComposerActivity")) {
                                        final ActivityInfo activity = app.activityInfo;
                                        final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
                                        shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                                        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                                        shareIntent.setComponent(name);
                                        startActivity(shareIntent);
                                        shared = true;
                                        dialog.dismiss();
                                        break;
                                    }
                                }
                                if (!shared) {
                                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(P2.this);
                                    builder.setMessage("Application not found").setCancelable(true);
                                    android.app.AlertDialog alert = builder.create();
                                    alert.show();
                                }
                            }
                        });

                        ImageView homepage = (ImageView) dialog.findViewById(R.id.homepage);
                        homepage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent moreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Antivirus+Free+-+GPaddy+Mobile+Security"));
                                startActivity(moreIntent);
                                dialog.dismiss();
                            }
                        });

                        dialog.show();
                    }
                });

                S3PagerAdapter mapager = new S3PagerAdapter(getSupportFragmentManager());
                pager = (ViewPager) findViewById(R.id.pager);
                pager.setAdapter(mapager);

                pager.setCurrentItem(0);

                tabStrip = (PagerTabStrip) findViewById(R.id.tab_strip);
                tabStrip.setTextColor(Color.WHITE);
            }
        }
        else {


            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View view) {
                    final Dialog dialog = new Dialog(P2.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.p25share);
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.themesetting);
                    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

                    TextView textfacebook = (TextView) dialog.findViewById(R.id.textfacebook);
                    TextView texttwitter = (TextView) dialog.findViewById(R.id.texttwitter);
                    TextView texthomepage = (TextView) dialog.findViewById(R.id.texthomepage);

                    textfacebook.setText("Share on Facebook");
                    textfacebook.startAnimation(AnimationUtils.loadAnimation(P2.this, R.anim.slide_in_left));

                    texttwitter.setText("Share on Twitter");
                    texttwitter.startAnimation(AnimationUtils.loadAnimation(P2.this, R.anim.slide_in_left));

                    texthomepage.setText("Home Page");
                    texthomepage.startAnimation(AnimationUtils.loadAnimation(P2.this, R.anim.slide_in_left));

                    // more ringtone
                    TextView textMoreRingtones = (TextView) dialog.findViewById(R.id.text_more_ringtones);
                    textMoreRingtones.setText(getString(R.string.more_ringtones));
                    textMoreRingtones.startAnimation(AnimationUtils.loadAnimation(P2.this, R.anim.slide_in_left));
                    ImageView moreRingtones = (ImageView) dialog.findViewById(R.id.more_ringtones);
                    moreRingtones.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent moreIntent = new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://play.google.com/store/apps/details?id=com.zizi.ringtone.wallpaper"));
                            startActivity(moreIntent);
                        }
                    });
                    //==========================================

                    ImageView facebook = (ImageView) dialog.findViewById(R.id.facebook);
                    facebook.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("text/plain");
                            intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + getPackageName());
                            boolean facebookAppFound = false;
                            List<ResolveInfo> matches = P2.this.getApplication().getPackageManager().queryIntentActivities(intent, 0);
                            for (ResolveInfo info : matches) {
                                if (info.activityInfo.packageName.toLowerCase().startsWith(
                                        "com.facebook.katana")) {
                                    intent.setPackage(info.activityInfo.packageName);
                                    facebookAppFound = true;
                                    Intent chooser = Intent.createChooser(intent, "Open with");
                                    P2.this.startActivity(chooser);
                                    dialog.dismiss();
                                    break;
                                }
                            }
                            if (!facebookAppFound) {
                                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(P2.this);
                                builder.setMessage("Application not found")
                                        .setCancelable(false);
                                android.app.AlertDialog alert = builder.create();
                                alert.show();
                            }
                        }
                    });

                    ImageView twitter = (ImageView) dialog.findViewById(R.id.twitter);
                    twitter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String string = "https://play.google.com/store/apps/details?id=" + getPackageName();
                            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                            shareIntent.setType("text/plain");
                            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, string);
                            PackageManager pm = P2.this.getPackageManager();
                            List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
                            boolean shared = false;
                            for (final ResolveInfo app : activityList) {
                                if (app.activityInfo.name.equals("com.twitter.android.composer.ComposerActivity")) {
                                    final ActivityInfo activity = app.activityInfo;
                                    final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
                                    shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                                    shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                            | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                                    shareIntent.setComponent(name);
                                    startActivity(shareIntent);
                                    shared = true;
                                    dialog.dismiss();
                                    break;
                                }
                            }
                            if (!shared) {
                                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(P2.this);
                                builder.setMessage("Application not found").setCancelable(true);
                                android.app.AlertDialog alert = builder.create();
                                alert.show();
                            }
                        }
                    });

                    ImageView homepage = (ImageView) dialog.findViewById(R.id.homepage);
                    homepage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent moreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.gpaddy.ringtones.iphone&hl=en"));
                            startActivity(moreIntent);
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            });

            S3PagerAdapter mapager = new S3PagerAdapter(getSupportFragmentManager());
            pager = (ViewPager) findViewById(R.id.pager);
            pager.setAdapter(mapager);

            pager.setCurrentItem(0);

            tabStrip = (PagerTabStrip) findViewById(R.id.tab_strip);
            tabStrip.setTextColor(Color.WHITE);
        }

        assistive = (LinearLayout) findViewById(R.id.assistive_touch_promotion);
        assistive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moreIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.zizi.ringtone.wallpaper"));
                startActivity(moreIntent);
            }
        });

        iconwallpaper = (ImageView) findViewById(R.id.iconwallpaper);
        int resources[] = new int[2];
        resources[0] = R.anim.zoom_out;
        resources[1] = R.anim.zoom_int;
        int count = 0;
        runAnimation(iconwallpaper, resources, count);

    }

    @Override
    public void onBackPressed() {
        iconwallpaper = (ImageView) findViewById(R.id.iconwallpaper);
        int resources[] = new int[2];
        resources[0] = R.anim.zoom_out;
        resources[1] = R.anim.zoom_int;
        int count = 0;
        runAnimation(iconwallpaper, resources, count);
        iconwallpaper.clearAnimation();
        AlertDialog.Builder builder = new AlertDialog.Builder(P2.this);
        builder.setCancelable(true);
        final AlertDialog dialog = builder.create();

        //(LAYOUT)
        LayoutInflater inflater = getLayoutInflater();
        @SuppressLint("InflateParams") View dialogLayout = inflater.inflate(R.layout.p28dialog, null);
        buttonExit = (TextView) dialogLayout.findViewById(R.id.textExit);
        buttonRateApp = (TextView) dialogLayout.findViewById(R.id.textRateApp);
        buttonView = (TextView) dialogLayout.findViewById(R.id.textView);
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (S2SetAdapterRingtone.mediaPlayer != null && S2SetAdapterRingtone.mediaPlayer.isPlaying())
                        S2SetAdapterRingtone.mediaPlayer.stop();
                    if (S2SetAdapterAlert.mediaPlayer != null && S2SetAdapterAlert.mediaPlayer.isPlaying())
                        S2SetAdapterAlert.mediaPlayer.stop();
                    if (S2SetAdapterFavorite.mediaPlayer != null && S2SetAdapterFavorite.mediaPlayer.isPlaying())
                        S2SetAdapterFavorite.mediaPlayer.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(mInterstitialAd != null && mInterstitialAd.isLoaded())
                {
                    mInterstitialAd.show();
                }
                else
                {
                    P2.super.onBackPressed();
                }
            }
        });
        buttonRateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.gpaddy.ringtones.iphone&hl=en"));
                startActivity(moreIntent);
                dialog.dismiss();
                P2.super.onBackPressed();
            }
        });
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.gpaddy.free.antivirus"));
                startActivity(moreIntent);
                dialog.dismiss();
                P2.super.onBackPressed();
            }
        });
        nameApp = (TextView) dialogLayout.findViewById(R.id.nameApp);
        iconApp = (ImageView) dialogLayout.findViewById(R.id.iconApp);
        logo = (TextView) dialogLayout.findViewById(R.id.logo);
        nameApp.setText(getString(R.string.nameApp));

        iconApp.setBackgroundResource(R.drawable.logo_antivirus);
        runAnimation(iconApp, resources, count);

        logo.setText(getString(R.string.logo));

        //(DIALOG)
        dialog.setView(dialogLayout);
        dialog.show();
    }

    public void runAnimation (final ImageView iconApp, final int resources[], final int count) {
        Animation zoom;
        if (count % 2 == 0) {
            zoom = AnimationUtils.loadAnimation(P2.this, resources[0]);
            iconApp.startAnimation(zoom);
            zoom.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }
                @Override
                public void onAnimationEnd(Animation animation) {
                    int a = count;
                    a++;
                    runAnimation(iconApp, resources, a);
                }
                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        } else {
            zoom = AnimationUtils.loadAnimation(P2.this, resources[1]);
            iconApp.startAnimation(zoom);
            zoom.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }
                @Override
                public void onAnimationEnd(Animation animation) {
                    int a = count;
                    a++;
                    runAnimation(iconApp, resources, a);
                }
                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        }
    }
}