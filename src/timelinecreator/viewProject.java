
package timelinecreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class viewProject extends JFrame implements KeyListener
{
    Toolkit tool = Toolkit.getDefaultToolkit();
    
    //variables
    Color bgColor;
    Color lnColor;
    
    int refx;
    int refy;
    
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    
    boolean sorted = false;
    
    String projectTitle;
    
    ArrayList <Event> events = new ArrayList();
    
    panel screen = new panel();
    
    public viewProject(String name,Color bgColor,Color lnColor)
    {
        setSize(tool.getScreenSize().width - 100,tool.getScreenSize().height - 100);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setLocation(50,50);
        
        //stop the timer  on exit
        addWindowListener(new WindowAdapter() { 
            public void windowClosing(WindowEvent e) { 
                update.stop();
                close();
            }});
        
        projectTitle = name;
        this.bgColor = bgColor;
        this.lnColor = lnColor;
        
        setTitle("Viewing " + projectTitle);
        
        refx = tool.getScreenSize().width / 2;
        refy = tool.getScreenSize().height / 2;
        
        add(screen);
        
        this.addKeyListener(this);
        
        update.start();
    }
    
    public void sort()
    {
        for(int i = 0; i < events.size(); i++)
        {
            for(int j = 0; j < events.size(); j ++)
            {
                
                if(j == i)
                {
                    continue;
                }
                
                Event ev1 = events.get(i);
                Event ev2 = events.get(j);
                    
                if(events.get(i).year < events.get(j).year)
                {
                    //set the array placement of the new events
                    events.set(i, ev2);
                    events.set(j, ev1);
                }
                else if(events.get(i).year == events.get(j).year)
                {
                    if(events.get(i).month < events.get(j).month)
                    {
                        //set the array placement of the new events
                        events.set(i, ev2);
                        events.set(j, ev1);
                    }
                    else if(events.get(i).month == events.get(j).month)
                    {
                        if(events.get(i).day < events.get(j).day)
                        {
                            //set the array placement of the new events
                            events.set(i, ev2);
                            events.set(j, ev1);
                        }
                    }
                }
            }
        }
        
        for(int i = 0; i < events.size(); i++)
        {
            events.get(i).x = i * 250;
            
            if(i % 2 > 0)
            {
                events.get(i).y = -((events.get(i).lines.length / 10 * 25) + 40);
            }
            else
            {
                events.get(i).y = 25;
            }
        }
    }
    
    public void close()
    {
        this.dispose();
    }
    
    @Override
    public void keyTyped(KeyEvent e) 
    {
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
         if(e.getKeyCode() == 37)
         {
             right = true;
         }
         else if(e.getKeyCode() == 39)
         {
             left = true;
         }
         
         if(e.getKeyCode() == 38)
         {
             up = true;
         }
         else if(e.getKeyCode() == 40)
         {
             down = true;
         }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        if(e.getKeyCode() == 37)
         {
             right = false;
         }
         else if(e.getKeyCode() == 39)
         {
             left = false;
         }
         
         if(e.getKeyCode() == 38)
         {
             up = false;
         }
         else if(e.getKeyCode() == 40)
         {
             down = false;
         }
    }
    
     public void mainLoop()
    {
        
        if(up == true)
        {
            refy += 10;
        }
        
        if(down == true)
        {
            refy -= 10;
        }
        
        if(right == true)
        {
            refx += 10;
        }
        
        if(left == true)
        {
            refx -= 10;
        }
        
        repaint();
    }
     
    Timer update = new Timer(10,new ActionListener() {public void actionPerformed(ActionEvent e){
        mainLoop();
        
        if(sorted == false)
        {
            sort();
            sorted = true;
        }
        
    }});
    
    class panel extends JPanel
    {
        public panel()
        {
            setVisible(true);
        }
        
        public void paintComponent(Graphics g)
        {
            //set the font -- larger
            g.setFont(new Font(null, 0 , 18));
            
            g.setColor(bgColor);
            g.fillRect(0,0,this.getWidth(),this.getHeight());
            
            g.setColor(lnColor);
            g.drawString("Move using the arrow keys!", 10,20);
            
            for(int i = 0; i < events.size(); i++)
            {
                g.drawString(events.get(i).Title + " (" + events.get(i).str_month + " " + events.get(i).day + ", " + events.get(i).year + ")",refx + events.get(i).x,(refy + events.get(i).y));
                
                int mod = 0;
                
                for(int j = 0; j < events.get(i).lines.length; j++)
                {
                    if(events.get(i).lines[j] != null)
                    {
                        mod += 25;
                        g.drawString(events.get(i).lines[j],refx + events.get(i).x,(refy + events.get(i).y + mod));
                    }
                }
               
            }
                          
            if(events.size() >= 1)
            {
               g.drawLine(refx,refy,refx + events.get(events.size() - 1).x,refy);
            }
        }
    }
}
