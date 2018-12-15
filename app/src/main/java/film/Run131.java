package film;

public class Run131 implements RunfromLink
{

    public FilmText Run(String text)
    {
        String title = mytool.Stringcut.GetMidString(text,"<!--片名开始-->","<!--片名结束-->");
        String qxd = mytool.Stringcut.GetMidString(text,"<!--备注开始-->","<!--备注结束-->");
        String jieshao = mytool.Stringcut.GetMidString(text,"txt=\"","\">");
        String pic = mytool.Stringcut.GetMidString(text,"<img class=\"lazy\" src=\"","\" alt=");
        String playtext = mytool.Stringcut.GetMidString(text,"<h3>来源：131zy</h3>","</ul>");
        String playname[] = mytool.Stringcut.GetMidString2(playtext,"checked=\"\" />","</li>");
        String playlink[] = mytool.Stringcut.GetMidString2(playtext,"value=\"","\" checked");
        return  new FilmText(title,qxd,jieshao,pic,playname,playlink);
    }
}
