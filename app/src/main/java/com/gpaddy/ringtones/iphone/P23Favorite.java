package com.gpaddy.ringtones.iphone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class P23Favorite extends Fragment {

    S2SetAdapterFavorite adapter;
    ArrayList<String> arrayString;
    ArrayList<S1ObjectMusic> objectArray;
    S1ObjectMusic objectMusic[];
    private ListView listView;
    private  IntentFilter intentFilter;
    private BroadcastReceiver receiver ;

    //S4DataBase database;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.p26pager, container, false);
        receiver = new LoadMusicBroadCast();
        intentFilter = new IntentFilter("addMusic");
        getContext().registerReceiver(receiver,intentFilter);
        listView = (ListView) view.findViewById(R.id.listView);
        //database = new S4DataBase(getContext());

        setList();

        return view;
    }

    public void setList(){
        arrayString = new ArrayList<>();
        objectArray = new ArrayList<>();

//        Cursor cursor = database.getAll();
//
//
//        while (cursor.moveToNext()) {
//            arrayString.add(cursor.getString(0));
//        }


        File file = new File(Environment.getExternalStorageDirectory()+"/i Favorite/");
        File[] files=file.listFiles();
        for(int i=0;i<files.length;i++){
            String name = files[i].getName().replace(".mp3","");
            arrayString.add(name);

        }

        int lengh = arrayString.size();
        objectMusic = new S1ObjectMusic[lengh];
        for (int i = 0 ; i < lengh ; i++) {
            objectMusic[i] = new S1ObjectMusic(arrayString.get(i));
            objectArray.add(objectMusic[i]);
        }

        adapter = new S2SetAdapterFavorite(getActivity() , R.layout.p22row, objectArray);
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getContext().unregisterReceiver(receiver);
    }

    class LoadMusicBroadCast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
           setList();
        }
    }


}

