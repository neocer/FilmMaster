package film;

public class RunMax implements RunfromLink
{
    public FilmText Run(String text)
    {


        String title = mytool.Stringcut.GetMidString(text,"<!--片名开始-->","<!--片名结束-->");
        String qxd = mytool.Stringcut.GetMidString(text,"<!--备注开始-->","<!--备注结束-->");
        String jieshaotxt = mytool.Stringcut.GetMidString(text,"<div class=\"videoDetail\">","</div>");
        String jieshao = "影片别名:"+ mytool.Stringcut.GetMidString(jieshaotxt,"<!--别名开始-->","<!--别名结束-->")
                + "\n影片备注:" + mytool.Stringcut.GetMidString(jieshaotxt,"<!--备注开始-->","<!--备注结束-->")
                + "\n影片主演:" + mytool.Stringcut.GetMidString(jieshaotxt,"<!--主演开始-->","<!--主演结束-->")
                + "\n影片导演:" + mytool.Stringcut.GetMidString(jieshaotxt,"<!--导演开始-->","<!--导演结束-->");;;
        String pic = mytool.Stringcut.GetMidString(text,"<img src=\"","\"/>");
        String playtext = mytool.Stringcut.GetMidString(text,"<!--前yjyun-->","<!--后yjyun-->");
        String playname[] = mytool.Stringcut.GetMidString2(playtext,"value=\"","\" checked=");
        String playlink[] = mytool.Stringcut.GetMidString2(playtext,"</span>","</li>");

        return  new FilmText(title,qxd,jieshao,pic,playname,playlink);
    }

}