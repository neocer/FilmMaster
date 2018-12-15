package com.filmmaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity
{
    ListView ListView_result;
    String[] Link;
    static film.Searchresult[] result;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ListView_result = findViewById(R.id.Listview1);
        ListView_result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FilmActivity.link =  Link[(int)id];
                FilmActivity.isLoad = true;
                Intent intent = new Intent();
                intent.setClass(SearchActivity.this, FilmActivity.class);
                startActivity(intent);
            //    Toast.makeText(SearchActivity.this, Link[(int)id], Toast.LENGTH_SHORT).show();
            }
        });


    }
    protected void onStart()
    {
        super.onStart();
        ListView_result.setAdapter(null);
        Link =null;
        Link = new String[result.length];

        List<String> listdata = new ArrayList<String>();
       for(int i = 0 ; i < result.length ; i ++)
       {
           listdata.add(result[i].Name);
           Link[i] = result[i].Link;
         //  Text = Text + "名称：" +result[i].Name + "\n链接:" + result[i].Link + "\n更新日期:" + result[i].Date + "\n\n";
       }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item,listdata);//listdata和str均可
        ListView_result.setAdapter(arrayAdapter);

    }


}
