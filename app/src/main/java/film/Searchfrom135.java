package film;

import android.util.Log;

public class Searchfrom135 implements Searchfilm
{

    public static String Link = "https://www.135zy.vip";
    @Override
    public Searchresult[] Searchbykey(String key)
    {
        Searchresult[] x = new Searchresult[0];
        String text = mytool.MyRequest.sendPost("https://www.135zy.vip/index.php?m=vod-search","wd="+key+"&submit=search");
        String ReturnString[] = mytool.Stringcut.GetMidString2(text,"</span><span class=\"xing_vb4\">","</li>");
        Log.v("ceshi:",text);
        for(int i = 0 ; i < ReturnString.length ; i ++)
        {
            String Temp_name,Temp_link,Temp_date;
            Temp_name = mytool.Stringcut.GetMidString(ReturnString[i],"target=\"_blank\">","</a></span>");
            Temp_link = mytool.Stringcut.GetMidString(ReturnString[i],"<a href=\"","\" target");
            Temp_date = mytool.Stringcut.GetMidString(ReturnString[i],"<span class=\"xing_vb6\">","</span>");
            if(Temp_date.length() ==0 )
            {
                Temp_date =   mytool.Stringcut.GetMidString(ReturnString[i], "<span class=\"xing_vb7\">", "</span>");
            }
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
