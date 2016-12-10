package com.gpaddy.ringtones.iphone;

import android.app.Activity;
import android.app.Dialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class S2SetAdapterRingtone extends ArrayAdapter {

    ArrayList<S1ObjectMusic> arrayObjects = null;
    Activity context = null;

    //S4DataBase database;

    int layoutID;
    int flag = 0;
    int recentIndex = -1;

    BroadcastReceiver receiver = null;

    public static MediaPlayer mediaPlayer = new MediaPlayer();

    class MyViewHolder {
        TextView textViewRow;
        TextView textButtonPlay;

        MyViewHolder (View view) {
            textViewRow = (TextView) view.findViewById(R.id.textView);
            textButtonPlay = (TextView) view.findViewById(R.id.textButtonPlay);
        }
    }


    private InterstitialAd interstitialAd;

    private void loadInterstitialAd(Context context) {
        interstitialAd = new InterstitialAd(context, "1515999665384488_1517419635242491");
        interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {

            }

            @Override
            public void onAdClicked(Ad ad) {
                ad.destroy();
            }
        });
        interstitialAd.loadAd();
    }

    public S2SetAdapterRingtone(Activity context, int layoutID, ArrayList<S1ObjectMusic> arrayObjects) {
        super(context, layoutID, arrayObjects);
        this.context = context;
        this.layoutID   = layoutID;
        this.arrayObjects = arrayObjects;

        loadInterstitialAd(context);
    }

    public View getView (final int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layoutID , null);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }

        final String name = arrayObjects.get(position).getName();
        final boolean status = arrayObjects.get(position).isStatus();

        if(status) {
            holder.textButtonPlay.setBackgroundResource(R.drawable.imagesbuttonpause);
        } else {
            holder.textButtonPlay.setBackgroundResource(R.drawable.imagesbuttonplay);
        }

        holder.textViewRow.setText(name);

        final MyViewHolder finalHolder = holder;
        holder.textButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (S2SetAdapterAlert.mediaPlayer != null && S2SetAdapterAlert.mediaPlayer.isPlaying())
                    S2SetAdapterAlert.mediaPlayer.stop();
                if (S2SetAdapterFavorite.mediaPlayer != null && S2SetAdapterFavorite.mediaPlayer.isPlaying())
                    S2SetAdapterFavorite.mediaPlayer.stop();
                flag++;
                if (recentIndex == position) {
                    if (arrayObjects.get(recentIndex).isStatus()) {
                        finalHolder.textButtonPlay.setBackgroundResource(R.drawable.imagesbuttonplay);
                        mediaPlayer.stop();
                        arrayObjects.get(position).setStatus(false);
                    } else {
                        finalHolder.textButtonPlay.setBackgroundResource(R.drawable.imagesbuttonpause);
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                        }
                        mediaPlayer = MediaPlayer.create(context, Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/i Ringtone/" + name + ".mp3"));
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer mp) {
                                mediaPlayer.stop();
                                finalHolder.textButtonPlay.setBackgroundResource(R.drawable.imagesbuttonplay);
                                arrayObjects.get(position).setStatus(false);
                            }
                        });
                        arrayObjects.get(position).setStatus(true);
                    }
                } else {
                    if (flag != 1) {
                        arrayObjects.get(recentIndex).setStatus(false);
                    }
                    arrayObjects.get(position).setStatus(true);
                    notifyDataSetChanged();

                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        mediaPlayer = new MediaPlayer();
                    }
                    mediaPlayer = MediaPlayer.create(context, Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/i Ringtone/" + name + ".mp3"));
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.stop();
                            finalHolder.textButtonPlay.setBackgroundResource(R.drawable.imagesbuttonplay);
                            arrayObjects.get(position).setStatus(false);
                        }
                    });
                    recentIndex = position;
                }

                IntentFilter filter = new IntentFilter("android.intent.action.PHONE_STATE");

                receiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);

                        switch (telephonyManager.getCallState()) {
                            case TelephonyManager.CALL_STATE_RINGING :
                                try {
                                    if (S2SetAdapterRingtone.mediaPlayer != null && S2SetAdapterRingtone.mediaPlayer.isPlaying()) {
                                        S2SetAdapterRingtone.mediaPlayer.stop();
                                        finalHolder.textButtonPlay.setBackgroundResource(R.drawable.imagesbuttonplay);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                        }
                    }
                };

                context.registerReceiver(receiver, filter);
            }
        });

        TextView buttonSet = (TextView) convertView.findViewById(R.id.textButtonSettting);
        buttonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final File file = new File(Environment.getExternalStorageDirectory().getPath() + "/i Ringtone/", name + ".mp3");

                final ContentValues values = new ContentValues();
                values.put(MediaStore.MediaColumns.DATA, file.getAbsolutePath());
                values.put(MediaStore.MediaColumns.TITLE, name);
                values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");

                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.p24setting);
                dialog.getWindow().setBackgroundDrawableResource(R.drawable.themesetting);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

                TextView textfavorite = (TextView) dialog.findViewById(R.id.textfavorite);

                TextView textringtone = (TextView) dialog.findViewById(R.id.textringtone);
                TextView textnotification = (TextView) dialog.findViewById(R.id.textnotification);
                TextView textalarm = (TextView) dialog.findViewById(R.id.textalarm);

                textfavorite.setText("Add to favorite");
                textfavorite.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left));

                textringtone.setText("Set as Ringtones");
                textringtone.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left));

                textnotification.setText("Set Notification sound");
                textnotification.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left));

                textalarm.setText("Set Alarm sound");
                textalarm.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left));

                // more ringtone
                TextView textMoreRingtones = (TextView) dialog.findViewById(R.id.text_more_ringtones);
                textMoreRingtones.setText(context.getString(R.string.more_ringtones));
                textMoreRingtones.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide_in_left));
                ImageView moreRingtones = (ImageView) dialog.findViewById(R.id.more_ringtones);
                moreRingtones.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent moreIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=com.zizi.ringtone.wallpaper"));
                        context.startActivity(moreIntent);
                    }
                });
                //==========================================

                //database = new S4DataBase(context);
                ImageView favorite = (ImageView) dialog.findViewById(R.id.favorite);
                favorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        File ifavorite = new File(Environment.getExternalStorageDirectory().getPath() + "/i Favorite/" + name + ".mp3");
                        if (!ifavorite.exists()) {
                            //database.insertDataBase(name);
                            try {
                                copyFavorite(name);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(context.getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context.getApplicationContext(), "Already exists", Toast.LENGTH_SHORT).show();
                        }

                        dialog.dismiss();
                    }
                });

                ImageView ringtones = (ImageView) dialog.findViewById(R.id.ringtones);
                ringtones.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
                        Uri uri = MediaStore.Audio.Media.getContentUriForPath(file.getAbsolutePath());
                        context.getContentResolver().delete(uri, MediaStore.MediaColumns.DATA + "=\"" + file.getAbsolutePath() + "\"", null);
                        Uri newUri = context.getApplicationContext().getContentResolver().insert(uri, values);
                        RingtoneManager.setActualDefaultRingtoneUri(context.getApplicationContext(), RingtoneManager.TYPE_RINGTONE, newUri);
                        Toast.makeText(context.getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();

                        if (interstitialAd != null && interstitialAd.isAdLoaded()) {
                            interstitialAd.show();
                            loadInterstitialAd(context);
                        }


                        dialog.dismiss();
                    }
                });

                ImageView notification = (ImageView) dialog.findViewById(R.id.notification);
                notification.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
                        Uri uri = MediaStore.Audio.Media.getContentUriForPath(file.getAbsolutePath());
                        context.getContentResolver().delete(uri, MediaStore.MediaColumns.DATA + "=\"" + file.getAbsolutePath() + "\"", null);
                        Uri newUri = context.getApplicationContext().getContentResolver().insert(uri, values);
                        RingtoneManager.setActualDefaultRingtoneUri(context.getApplicationContext(), RingtoneManager.TYPE_NOTIFICATION, newUri);
                        Toast.makeText(context.getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

//                ImageView alarm = (ImageView) dialog.findViewById(R.id.alarm);
//                alarm.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        values.put(MediaStore.Audio.Media.IS_ALARM, true);
//                        Uri uri = MediaStore.Audio.Media.getContentUriForPath(file.getAbsolutePath());
//                        context.getContentResolver().delete(uri, MediaStore.MediaColumns.DATA + "=\"" + file.getAbsolutePath() + "\"", null);
//                        Uri newUri = context.getApplicationContext().getContentResolver().insert(uri, values);
//                        RingtoneManager.setActualDefaultRingtoneUri(context.getApplicationContext(), RingtoneManager.TYPE_ALARM, newUri);
//                        Toast.makeText(context.getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    }
//                });

                dialog.show();
            }
        });

        return convertView;
    }

    BroadcastReceiver receiverStop = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
        }
    };

    private void copyFavorite (String name) throws IOException {
        InputStream inputStream  = context.getAssets().open(name + ".mp3");
        OutputStream outputStream = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + "/i Favorite/" + name + ".mp3");

        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

}
