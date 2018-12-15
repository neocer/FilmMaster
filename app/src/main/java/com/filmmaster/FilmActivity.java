package com.filmmaster;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import film.FilmText;
import film.Run131;
import film.Run135;
import film.RunMax;
import film.RunOK;
import film.RunfromLink;
import film.Seachfrom131;
import film.Searchfrom135;
import film.SearchfromOK;
public class FilmActivity extends AppCompatActivity
{
    public static String link;
    private String pic_link;

    TextView TextView_title;
    TextView TextView_qxd;
    TextView TextView_jieshao;
    ImageView  ImageView_pic;
    ListView ListView_xuanji;
    String playlink[];
    ProgressDialog Messagebox ;
    static boolean isLoad;
    Handler Myhandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            if(msg.what == 2)
            {
                String text = msg.getData().getString("text");

//                String title = mytool.Stringcut.GetMidString(text,"<!--片名开始-->","<!--片名结束-->");
//                String qxd = mytool.Stringcut.GetMidString(text,"<!--备注开始-->","<!--备注结束-->");
//                String jieshao = mytool.Stringcut.GetMidString(text,"txt=\"","\">");
//                String pic = mytool.Stringcut.GetMidString(text,"<img class=\"lazy\" src=\"","\" alt=");
//                String playtext = mytool.Stringcut.GetMidString(text,"<h3>来源：131zy</h3>","</ul>");
//                String playname[] = mytool.Stringcut.GetMidString2(playtext,"checked=\"\" />","</li>");
//                playlink = mytool.Stringcut.GetMidString2(playtext,"value=\"","\" checked");

                RunfromLink RunFilm = new Run135();
                if(value.Data.method == R.id.radioButton0)
                    RunFilm=new RunMax();
                else if(value.Data.method == R.id.radioButton1)
                    RunFilm=new Run131();
                else if(value.Data.method == R.id.radioButton2)
                    RunFilm=new RunOK();
                else
                    RunFilm = new Run135();
                FilmText Result =  RunFilm.Run(text);

                TextView_title.setText(Result.name);
                TextView_qxd.setText(Result.Qxd);
                TextView_jieshao.setText(Result.Js);
                pic_link= Result.pic;
                playlink = Result.playlink;
                List<String> listdata = new ArrayList<String>();
                for(int i = 0 ; i < Result.playname.length ; i ++)
                {
                    listdata.add(Result.playname[i].replace("$","\n"));
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(FilmActivity.this, R.layout.list_item2,listdata);
                ListView_xuanji.setAdapter(arrayAdapter);
                new Runablepic().run();
                //Toast.makeText(FilmActivity.this, pic, Toast.LENGTH_SHORT).show();
            }
            else
                if(msg.what ==3)
                {
                    Bitmap bmp = (Bitmap)msg.obj;
                    ImageView_pic.setImageBitmap(bmp);
                    Messagebox.dismiss();
                }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        TextView_jieshao = findViewById(R.id.Jieshao);
        TextView_title = findViewById(R.id.Title);
        TextView_qxd = findViewById(R.id.textView1);
        ImageView_pic = findViewById(R.id.imageView1);
        ListView_xuanji = findViewById(R.id.Listview1);
        Messagebox = new ProgressDialog(this);
        ListView_xuanji.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri = Uri.parse(playlink[(int)id]);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                //    Toast.makeText(SearchActivity.this, Link[(int)id], Toast.LENGTH_SHORT).show();
            }
        });

    }
    protected void onStart()
    {
        super.onStart();
        if(isLoad==true)
        {
            playlink = null;
            ListView_xuanji.setAdapter(null);
            Messagebox.setMessage("正在加载，请稍后...");
            Messagebox.show();
            Messagebox.setCancelable(false);
            isLoad=false;
        }


        new Thread(new Runable()).start();
    }
    //内部类
    class Runable implements Runnable
    {
        @Override
        public void run()
        {
            String text = mytool.MyRequest.sendGet(FilmActivity.link,"");

            Message msg = Message.obtain();
            msg.what = 2;
            Bundle data = new Bundle();
            data.putString("text",text);
            msg.setData(data);
            Myhandler.sendMessage(msg);
        }
    }

    class Runablepic implements Runnable
    {
        @Override
        public void run()
        {
            Bitmap bmp = getURLimage(pic_link);
            Message msg = Message.obtain();
            msg.what = 3;
            msg.obj = bmp;
            Myhandler.sendMessage(msg);

        }
    }
    public Bitmap getURLimage(String url) {
        Bitmap bmp = null;
        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }


}
