package film;

import android.util.Log;

public class SearchMax implements Searchfilm
{
    public static String Link = "http://yongjiuzy.net";
    @Override
    public Searchresult[] Searchbykey(String key)
    {
        Searchresult[] x = new Searchresult[0];
        String text = mytool.MyRequest.sendPost("http://yongjiuzy.net/index.php?m=vod-search","wd="+key);
        String ReturnString[] = mytool.Stringcut.GetMidString2(text,"<tr class=","</tr>");
        Log.v("ceshi:",text);
        for(int i = 0 ; i < ReturnString.length ; i ++)
        {
            String Temp_name,Temp_link,Temp_date;
            Temp_name = mytool.Stringcut.GetMidString(ReturnString[i]," target=\"_blank\">","<font color=");
            Temp_link = mytool.Stringcut.GetMidString(ReturnString[i],"<a href=\"","\" target");
            Temp_date = mytool.Stringcut.GetMidString(ReturnString[i],"<font color=\"red\">","</font>");

            Searchresult[] tmp = new Searchresult[x.length+1];
            for(int j = 0 ;j < x.length ;j++)
            {
                tmp[j] = x[j];
            }
            tmp[x.length] = new Searchresult(Temp_name, Link + Temp_link,Temp_date);
            x=tmp;

        }
        return x;
    }
}
