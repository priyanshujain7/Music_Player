package com.example.ritikjain.mp3sirtrial;

import android.content.Intent;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {
//    TextView textView1,textView2,textView3;
    String var;
    File f;
    File[] file;
    String path;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
//    EditText folderName;
//    String folderName1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        textView1=(TextView)(findViewById(R.id.text1));
//        textView2=(TextView)(findViewById(R.id.text2));
//            textView3=(TextView)(findViewById(R.id.text3));
        //textView2.setText(path);
        //folderName=(EditText)(findViewById(R.id.editText));
        //folderName1=folderName.getText().toString();
        path= Environment.getExternalStorageDirectory().toString()+"/Songs/";
        /*textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(path);
                    //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    // mediaPlayer.setAudioStreamType(AudioManager.STREAM_MEDIA);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });*/

        f=new File(path);
        file=f.listFiles();
 //       textView3.setText(file.length+"");
        arrayList=new ArrayList<String>();
        for(int i=0;i<file.length;i++)
        {
         //   Log.d("Files","Filename:"+file[i].getName());
            String f1=file[i].getName();
            if(f1.contains(".mp3")||f1.contains(".MP3"))
            {
               var=file[i].getName();
            }
//            HashMap<String,String> hashMap=new HashMap<>();
  //          hashMap.put("name",var);
            arrayList.add(var);
        }
        listView=(ListView)(findViewById(R.id.listView));
        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList); //used only with ArrayList<String> not with AL<Hashmap>
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),PlayerWorking.class);
               // intent.putExtra("Song",l);
                String song=file[i].getName();
                intent.putExtra("Songname",path+song);
                Toast.makeText(getApplicationContext(),path+song+"",Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

//        textView1.setText(var);

    }
}
