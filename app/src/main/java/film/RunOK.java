package film;

public class RunOK implements RunfromLink
{

    public FilmText Run(String text)
    {
        String tmp =  mytool.Stringcut.GetMidString(text,"<div class=\"vodh\">","<div class=\"vodinfobox\">");
        String title = mytool.Stringcut.GetMidString(tmp,"<h2>","</h2>");
        String qxd = mytool.Stringcut.GetMidString(tmp,"<span>","</span>");
        String jieshao = mytool.Stringcut.GetMidString(text,"txt=\"","\">");
        String pic = mytool.Stringcut.GetMidString(text,"<img class=\"lazy\" src=\"","\" alt=");
        String playtext = mytool.Stringcut.GetMidString(text," <h3>播放类型：<span class=\"suf\">kuyun</span></h3>","</ul>");
        String playname[] = mytool.Stringcut.GetMidString2(playtext,"checked=\"\" />","</li>");
        String playlink[] = mytool.Stringcut.GetMidString2(playtext,"value=\"","\" checked");
        return  new FilmText(title,qxd,jieshao,pic,playname,playlink);
    }
}
