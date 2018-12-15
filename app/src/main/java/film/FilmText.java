package film;

public class FilmText
{
    public String name;
    public String Qxd;
    public String Js;
    public String pic;
    public String playname[];
    public String playlink[];
    public FilmText(String name,String Qxd,String Js,String pic,String playname[],String playlink[])
    {
        this.name = name ;
        this.Qxd = Qxd;
        this.Js = Js;
        this.pic = pic;
        this.playlink = playlink;
        this.playname = playname;
    }
}
