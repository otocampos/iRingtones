package com.gpaddy.ringtones.iphone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class P1 extends AppCompatActivity {

    private static final int REQUEST_WRITE_STORAGE = 112;
    private static final int REQUEST_WRITE_SETTINGS = 113;



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_WRITE_STORAGE: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Thread tRingtone = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            iRingtone();
                        }
                    });
                    tRingtone.start();

                    Thread tAlert = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            iAlert();
                        }
                    });
                    tAlert.start();

                    Thread tFavorite = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            File favorite = new File(Environment.getExternalStorageDirectory().getPath() + "/i Favorite/");
                            if (!favorite.exists()) {
                                favorite.mkdir();
                            }
                        }
                    });
                    tFavorite.start();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(P1.this, P2.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 3000);


                } else
                {
                    Toast.makeText(this, "The app was not allowed to write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }

                break;
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p1);
        if(Build.VERSION.SDK_INT >= 23)
        {
            boolean hasPermission = (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
            if (!hasPermission)
            {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_WRITE_STORAGE);
            }
            else {
                Thread tRingtone = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        iRingtone();
                    }
                });
                tRingtone.start();

                Thread tAlert = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        iAlert();
                    }
                });
                tAlert.start();

                Thread tFavorite = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        File favorite = new File(Environment.getExternalStorageDirectory().getPath() + "/i Favorite/");
                        if (!favorite.exists()) {
                            favorite.mkdir();
                        }
                    }
                });
                tFavorite.start();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(P1.this, P2.class);
                        startActivity(intent);
                        finish();
                    }
                }, 3000);
            }
        }
        else {
            Thread tRingtone = new Thread(new Runnable() {
                @Override
                public void run() {
                    iRingtone();
                }
            });
            tRingtone.start();

            Thread tAlert = new Thread(new Runnable() {
                @Override
                public void run() {
                    iAlert();
                }
            });
            tAlert.start();

            Thread tFavorite = new Thread(new Runnable() {
                @Override
                public void run() {
                    File favorite = new File(Environment.getExternalStorageDirectory().getPath() + "/i Favorite/");
                    if (!favorite.exists()) {
                        favorite.mkdir();
                    }
                }
            });
            tFavorite.start();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(P1.this, P2.class);
                    startActivity(intent);
                    finish();
                }
            }, 3000);
        }

    }

    public void iRingtone () {
        int count = 78;

        String sRingtone[] = new String[count];
        sRingtone[0] = "Alarm";
        sRingtone[1] = "Apex";
        sRingtone[2] = "Ascending";
        sRingtone[3] = "Bark";
        sRingtone[4] = "Beacon";
        sRingtone[5] = "Bell Tower";
        sRingtone[6] = "Blues";
        sRingtone[7] = "Boing";
        sRingtone[8] = "Bulletin";
        sRingtone[9] = "By The Seaside";
        sRingtone[10] = "Chimes";
        sRingtone[11] = "Circuit";
        sRingtone[12] = "Constellation";
        sRingtone[13] = "Cosmic";
        sRingtone[14] = "Crystals";
        sRingtone[15] = "Doorbell";
        sRingtone[16] = "Duck";
        sRingtone[17] = "Harp";
        sRingtone[18] = "Hillside";
        sRingtone[19] = "Illuminate";
        sRingtone[20] = "Marimba";
        sRingtone[21] = "Marimba remix";
        sRingtone[22] = "Marimba remix2";
        sRingtone[23] = "Marimba remix3";
        sRingtone[24] = "Motorcycle";
        sRingtone[25] = "Night Owl";
        sRingtone[26] = "Old Car Horn";
        sRingtone[27] = "Old Phone";
        sRingtone[28] = "Opening";
        sRingtone[29] = "Opening remix";
        sRingtone[30] = "Opening remix2";
        sRingtone[31] = "Opening remix3";
        sRingtone[32] = "Opening remix4";
        sRingtone[33] = "Piano Riff";
        sRingtone[34] = "Pinball";
        sRingtone[35] = "Playtime";
        sRingtone[36] = "Presto";
        sRingtone[37] = "Radar";
        sRingtone[38] = "Radiate";
        sRingtone[39] = "Ripples";
        sRingtone[40] = "Robot";
        sRingtone[41] = "Sci-fi";
        sRingtone[42] = "Sencha";
        sRingtone[43] = "Signal";
        sRingtone[44] = "Silk";
        sRingtone[45] = "Silk remix";
        sRingtone[46] = "Silk remix2";
        sRingtone[47] = "Slow Rise";
        sRingtone[48] = "Sonar";
        sRingtone[49] = "Stargaze";
        sRingtone[50] = "Strum";
        sRingtone[51] = "Summit";
        sRingtone[52] = "Timba";
        sRingtone[53] = "Time Passing";
        sRingtone[54] = "Trill";
        sRingtone[55] = "Twinkle";
        sRingtone[56] = "Uplift";
        sRingtone[57] = "Waves";
        sRingtone[58] = "Xylophone";
        sRingtone[59] = "Ringtone 1";
        sRingtone[60] = "Ringtone 2";
        sRingtone[61] = "Ringtone 3";
        sRingtone[62] = "Ringtone 4";
        sRingtone[63] = "Ringtone 5";
        sRingtone[64] = "Ringtone 6";
        sRingtone[65] = "Ringtone 7";
        sRingtone[66] = "Ringtone 8";
        sRingtone[67] = "Ringtone 9";
        sRingtone[68] = "Ringtone 10";
        sRingtone[69] = "Ringtone 11";
        sRingtone[70] = "Ringtone 12";
        sRingtone[71] = "Ringtone 13";
        sRingtone[72] = "Ringtone 14";
        sRingtone[73] = "Ringtone 15";
        sRingtone[74] = "Ringtone 16";
        sRingtone[75] = "Ringtone 17";
        sRingtone[76] = "Ringtone 18";
        sRingtone[77] = "Ringtone 19";

        File ringtone = new File(Environment.getExternalStorageDirectory().getPath() + "/i Ringtone/");
        if (!ringtone.exists()) {
            boolean isSucess = ringtone.mkdirs();
            for (int i = 0 ; i < count ; i++) {
                try {
                    copyRingtone(sRingtone[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void copyRingtone (String name) throws IOException {
        InputStream inputStream  = P1.this.getAssets().open(name + ".mp3");
        OutputStream outputStream = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + "/i Ringtone/" + name + ".mp3");

        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public void iAlert () {
        int count = 12;

        String sAlert[] = new String[count];
        sAlert[0] = "Aurora";
        sAlert[1] = "Bamboo";
        sAlert[2] = "Chord";
        sAlert[3] = "Circles";
        sAlert[4] = "Complete";
        sAlert[5] = "Digital";
        sAlert[6] = "Hello";
        sAlert[7] = "Keys";
        sAlert[8] = "Note";
        sAlert[9] = "Popcorn";
        sAlert[10] = "Pulse";
        sAlert[11] = "Synth";

        File alert = new File(Environment.getExternalStorageDirectory().getPath() + "/i Alert/");
        if (!alert.exists()) {
            alert.mkdir();
            for (int i = 0; i < count; i++) {
                try {
                    copyNotification(sAlert[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void copyNotification (String name) throws IOException {
        InputStream inputStream  = P1.this.getAssets().open(name + ".mp3");
        OutputStream outputStream = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + "/i Alert/" + name + ".mp3");

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
