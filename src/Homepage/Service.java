package Homepage;

public class Service
{
    private String title;


    private int    cost;
    private String location;
    private String description;
    private int    unique_id;
    private String author_email;


    public Service(String title,int cost,String location,String description,int unique_id,String author_email)
    {
        this.title        = title;
        this.cost         = cost;
        this.location     = location;
        this.description  = description;
        this.unique_id    = unique_id;
        this.author_email = author_email;
    }
    public Service(String title,int cost,String location,String description,String author_email)
    {
        this.title        = title;
        this.cost         = cost;
        this.location     = location;
        this.description  = description;
        this.author_email = author_email;
    }

    public void setTitle(String title)              {this.title=title;}
    public void setCost(int cost)                   {this.cost=cost;}
    public void setLocation(String location)        {this.location=location;}
    public void setDescription(String description)  {this.description=description;}
    public void setUnique_id(int unique_id)         {this.unique_id=unique_id;}
    public void setAuthor_email(String author_email){this.author_email=author_email;}

    public String getTitle()       {return title;}
    public int getCost()           {return cost;}
    public String getLocation()    {return location;}
    public String getDescription() {return description;}
    public int getUnique_id()      {return unique_id;}
    public String getAuthor_email(){return author_email;}
}