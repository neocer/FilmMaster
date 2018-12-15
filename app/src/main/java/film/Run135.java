package film;

public class Run135 implements RunfromLink
{
    public FilmText Run(String text)
    {


        String tmp =  mytool.Stringcut.GetMidString(text,"<div class=\"vodh\">","<div class=\"vodinfobox\">");
        String title = mytool.Stringcut.GetMidString(tmp,"<h2>","</h2>");
        String qxd = mytool.Stringcut.GetMidString(tmp,"<span>","</span>");

        String jieshao = mytool.Stringcut.GetMidString(text,"<strong>剧情介绍：</strong></div><div class=\"vodplayinfo\">","</div></div><div");
        String pic = mytool.Stringcut.GetMidString(text,"<img class=\"lazy\" src=\"","\" alt=");
        String playtext = mytool.Stringcut.GetMidString(text,"<h3>来源：135zy</h3>","</ul>");
        String playname[] = mytool.Stringcut.GetMidString2(playtext,"checked=\"\" />","</li>");
        String playlink[] = mytool.Stringcut.GetMidString2(playtext,"value=\"","\" checked");
        return  new FilmText(title,qxd,jieshao,pic,playname,playlink);
    }

}
