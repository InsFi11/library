package beingjavaguys.domain;

import java.sql.Date;

/**
 * Created by InF on 10.06.2014.
 */
abstract public class IPagination implements Comparable{

   abstract public String getGenre();
   abstract public int getId();
   abstract public String getName();
   abstract public String getAuthor();
   abstract public Date getDate();
   abstract public String getPicturePass();
   abstract public String getAbout();
   abstract public String getPrice();
   public int getIsInCollection(){return 0;};
   public void setIsInCollection(int isInCollection){};


   public int compareTo(Object obj)
    {
        IPagination tmp = (IPagination)obj;

        if(this.getDate().getTime() > tmp.getDate().getTime())
        {
      /* текущее меньше полученного */
            return -1;
        }
        else   if(this.getDate().getTime() < tmp.getDate().getTime())
        {
      /* текущее больше полученного */
            return 1;
        }
    /* текущее равно полученному */
        return 0;
    }

}
