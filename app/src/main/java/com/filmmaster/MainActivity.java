package com.filmmaster;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import film.Seachfrom131;
import film.SearchMax;
import film.Searchfilm;
import film.Searchfrom135;
import film.SearchfromOK;
import film.Searchresult;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
 
public class MainActivity extends AppCompatActivity
{
    private RadioGroup rg;
    private Button Button_Search;
    private EditText EditText_Search;

    ProgressDialog Messagebox ;

    Handler Myhandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            if(msg.what == 1)
            {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SearchActivity.class);
                MainActivity.this.startActivity(intent);

            }
            else if(msg.what == 0)
            {
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("提示")//设置对话框的标题
                        .setMessage("没有搜索到结果")//设置对话框的内容
                        //设置对话框的按
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               // Toast.makeText(MainActivity.this, "点击了确定的按钮", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        rg = findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new OnCheckedChangeListener(){

                public void onCheckedChanged(RadioGroup arg0, int checkedId) {
                      value.Data.method = checkedId;

                  //  Toast.makeText(getApplicationContext(),"Id:"+checkedId, Toast.LENGTH_SHORT).show();
                }

            });
        value.Data.method = R.id.radioButton0;
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Init();
        Messagebox = new ProgressDialog(this);


    }

    private void Init()
    {
        //改导航栏背景颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setNavigationBarColor(getResources().getColor(R.color.navigationBarColor));
        //Messagebox =  new ProgressDialog(this);
        EditText_Search = findViewById(R.id.editText);

    }

    public void Searchclicked(View view)
    {

        //hideKeyboard();   //收起键盘
        View v = getCurrentFocus();
        if(v!=null)
            hideKeyboard(v.getWindowToken());
        ((EditText)findViewById(R.id.editText)).clearFocus();
        EditText_Search = findViewById(R.id.editText);
        if(EditText_Search.getText().length()==0)
        {
            Toast.makeText(getApplicationContext(),"请输入要搜索的电影名称", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Messagebox.setMessage("正在搜索，请稍后...");
            Messagebox.show();
            Messagebox.setCancelable(false);
            new Thread(new Runable()).start();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture)
    {

    }



    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v!= null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                return false;
            }
            else
                return true;
        }
        return false;
    }
    private void hideKeyboard(IBinder token) {
        if (token != null)
        {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent me)
    {
        if (me.getAction() == MotionEvent.ACTION_DOWN)
        {  //把操作放在用户点击的时候
            View v = getCurrentFocus();      //得到当前页面的焦点,ps:有输入框的页面焦点一般会被输入框占据
            if (isShouldHideKeyboard(v, me))
            { //判断用户点击的是否是输入框以外的区域
                hideKeyboard(v.getWindowToken());   //收起键盘
            }
        }
        return super.dispatchTouchEvent(me);
    }

    //内部类
    class Runable implements Runnable
    {
        @Override
        public void run()
        {
            String Text = new String("");
            Searchfilm a;
            if(value.Data.method == R.id.radioButton0)
                a=new SearchMax();
            else if(value.Data.method == R.id.radioButton1)
                a=new Seachfrom131();
            else if(value.Data.method == R.id.radioButton2)
               a=new SearchfromOK();
            else
               a = new Searchfrom135();
            Searchresult result [] = a.Searchbykey(EditText_Search.getText().toString());
            for(int i = 0 ; i < result.length ; i ++)
            {
                Text = Text + "名称：" +result[i].Name + "\n链接:" + result[i].Link + "\n更新日期:" + result[i].Date + "\n\n";
            }

            Messagebox.dismiss();

            Message msg = Message.obtain();
            if(result.length <=0)
                msg.what=0;
            else
                msg.what = 1;
            Bundle data = new Bundle();
            data.putString("text",Text);
            msg.setData(data);
            SearchActivity.result = result;
            Myhandler.sendMessage(msg);

        }
    }
    public void About(View view)
    {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, AboutActivity.class);
        MainActivity.this.startActivity(intent);

    }

}
