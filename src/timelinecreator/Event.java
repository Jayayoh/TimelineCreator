
package timelinecreator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Event 
{
    //variables
    
    //dimensions
    int x;
    int y;
    int width = 500;
    int height = 300;
    int year;
    int month;
    int day;
    String str_month;
    
    //words
    String Title;
    String Description;
    String lines[];
    
    //technical aspects
    Font font;
    Color color;
    
    
    public Event(int year,int month,int day,String title, String desc,int x, int y)
    {
        Title = title;
        Description = desc;
        this.year = year;
        this.month = month;
        this.day = day;
        this.x = x;
        this.y = y;
        
        //append the date onto the title
        String Month = "";
        
        switch(month)
        {
            case 1: Month = "January"; break;
            case 2: Month = "February"; break;
            case 3: Month = "March"; break;
            case 4: Month = "April"; break;
            case 5: Month = "May"; break;
            case 6: Month = "June"; break;
            case 7: Month = "July"; break;
            case 8: Month = "August"; break;
            case 9: Month = "Spetember"; break;
            case 10: Month = "October"; break;
            case 11: Month = "November"; break;
            case 12: Month = "December"; break;
            default: Month = "";
        }
        
        str_month = Month;
        
        //setup the description into manageable lines
        lines = Description.split(" ");
        
        for(int i = 0; i < Math.ceil(lines.length / 10.0); i++)
        {
            for(int j = 0; j < 10; j ++)
            {
                if((i * 10 + j) >= lines.length)
                {
                    break;
                }
                else
                {
                    if(j == 0)
                    {
                        lines[i * 10] += " ";
                        continue;
                    }
                    
                    lines[i * 10] += lines[i * 10 + j] + " ";
                    lines[i * 10 + j] = null;
                }
            }
        }
    }
}
