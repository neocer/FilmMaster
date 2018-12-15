package film;

public class Searchresult
{
    public String Name;
    public String Link;
    public String Date;
    public Searchresult(){};
    public Searchresult(String Name,String Link,String Date)
    {
        this.Date = Date;
        this.Name = Name;
        this.Link = Link;

    }
}