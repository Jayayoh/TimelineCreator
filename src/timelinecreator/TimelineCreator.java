//package
package timelinecreator;

/*
 * Created by John Omeljaniuk for Mrs. Smelow and for Bonus marks ;)
 * October 10 2012
 */

//import libraries
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class TimelineCreator extends JFrame implements ActionListener
{
 
    //main components
    static JPanel mainPanel = new JPanel();
    
    //main title label
    ImageIcon main_title_icon = new ImageIcon("main_title.png");
    JLabel main_title = new JLabel(main_title_icon);
    
    //buttons
    JButton newProject = new JButton("New Project");
    JButton openProject = new JButton("Open Project");
    JButton viewProject = new JButton("View Project");         
    
    public TimelineCreator()
    {
        //setup the frame
        super("Time Line Creator");
        setVisible(true);
        setSize(500,500);
        setLocation(400,150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        //add an actionlister
        
        //add components
        mainPanel.setLayout(null);
        add(mainPanel);
        
        //title
        main_title.setBounds(50,0,400,200);
        mainPanel.add(main_title);
        
        //new project button
        newProject.addActionListener(this);
        newProject.setBounds(200,250,125,25);
        mainPanel.add(newProject);
        
        //open Project button
        openProject.setBounds(200,300,125,25);
        openProject.addActionListener(this);
        mainPanel.add(openProject);
        
        //view Project button
        viewProject.setBounds(200,350,125,25);
        viewProject.addActionListener(this);
        mainPanel.add(viewProject);
        
    }

    public static void main(String[] args) 
    {
        //create and show the window
        TimelineCreator window = new TimelineCreator();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
        if(e.getSource() == newProject)
        {
            newProjectWindow smallWindow = new newProjectWindow();
        }
        else if(e.getSource() == openProject)
        {
            //file
            File toOpen = null;
            
            //openWindow smallWindow = new openWindow();
            JFileChooser chooser = new JFileChooser();
            
            chooser.setCurrentDirectory(new File("Resources/Map Cache/"));
            
            int returnVal = chooser.showOpenDialog(chooser);
            
            String projectName = "";
            Color bgColor = null;
            Color lnColor = null;
            int events = 0;
            
            ProjectWorkSpace smallWindow = null;
            
            if(returnVal == JFileChooser.APPROVE_OPTION)
            {
                toOpen = chooser.getSelectedFile();
            }
            
            try
            {
                BufferedReader br = new BufferedReader(new FileReader(toOpen));
                
                int eventNum = 0;
                
                //read the title
                for(int i = 0; i < 9; ++i)
                {
                    if(i == 1)
                    {
                        projectName = br.readLine();
                    }
                    else if(i == 3)
                    {
                        String[] temp = br.readLine().split(" ");
                        bgColor = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
                    }
                    else if(i == 5)
                    {
                        String[] temp = br.readLine().split(" ");
                        lnColor = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
                    }
                    else if(i == 8)
                    {
                        eventNum = Integer.parseInt(br.readLine());
                        System.out.println(eventNum);
                    }
                    else
                    {
                        br.readLine();
                    }
                }
                
                smallWindow = new ProjectWorkSpace(projectName,bgColor,lnColor);
                
                smallWindow.setVisible(false);
                
                for(int i = 0; i < eventNum; i++)
                {
                    String evName = "";
                    String evDate = "";
                    String evDesc = "";
                    
                    for(int j = 0; j < 7; j++)
                    {
                        if( j == 2 )
                        {
                            evName = br.readLine();
                        }
                        else if( j == 4 )
                        {
                            evDate = br.readLine();
                        }
                        else if( j == 6 )
                        {
                            evDesc = br.readLine();
                        }
                        else
                        {
                            br.readLine();
                        }
                    }
                    
                    int year = Integer.parseInt(evDate.substring(0,evDate.indexOf("/")));

                    int month = Integer.parseInt(evDate.substring(evDate.indexOf("/") + 1,evDate.lastIndexOf("/")));

                    int day = Integer.parseInt(evDate.substring(evDate.lastIndexOf("/") + 1,evDate.length()));
                    
                    int x = (smallWindow.events.size()) * 500;
                
                    int y;

                    if(smallWindow.events.size() % 2 > 0)
                    {
                        y = -500;
                    }
                    else
                    {
                        y = 25;
                    }
                    
                    
                    smallWindow.eventList.addItem(evName);
                    
                    System.out.println("Year :" + year + " month: " + month + " daye: " + day);
                    
                    smallWindow.events.add(new Event(year,month,day,evName,evDesc,x,y));
                } 
                
                smallWindow.sort();
            
                smallWindow.setVisible(true);
            }
            catch(Exception ex)
            {
                
            }
        }
        else if(e.getSource() == viewProject)
        {
            //file
            File toOpen = null;
            
            //openWindow smallWindow = new openWindow();
            JFileChooser chooser = new JFileChooser();
            
            chooser.setCurrentDirectory(new File("Projects/"));
            
            int returnVal = chooser.showOpenDialog(chooser);
            
            String projectName = "";
            Color bgColor = null;
            Color lnColor = null;
            int events = 0;
            
            viewProject smallWindow = null;
            
            if(returnVal == JFileChooser.APPROVE_OPTION)
            {
                toOpen = chooser.getSelectedFile();
            }
            
            try
            {
                BufferedReader br = new BufferedReader(new FileReader(toOpen));
                
                int eventNum = 0;
                
                //read the title
                for(int i = 0; i < 9; ++i)
                {
                    if(i == 1)
                    {
                        projectName = br.readLine();
                    }
                    else if(i == 3)
                    {
                        String[] temp = br.readLine().split(" ");
                        bgColor = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
                    }
                    else if(i == 5)
                    {
                        String[] temp = br.readLine().split(" ");
                        lnColor = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
                    }
                    else if(i == 8)
                    {
                        eventNum = Integer.parseInt(br.readLine());
                        System.out.println(eventNum);
                    }
                    else
                    {
                        br.readLine();
                    }
                }
                
                smallWindow = new viewProject(projectName,bgColor,lnColor);
                
                smallWindow.setVisible(false);
                
                for(int i = 0; i < eventNum; i++)
                {
                    String evName = "";
                    String evDate = "";
                    String evDesc = "";
                    
                    for(int j = 0; j < 7; j++)
                    {
                        if( j == 2 )
                        {
                            evName = br.readLine();
                        }
                        else if( j == 4 )
                        {
                            evDate = br.readLine();
                        }
                        else if( j == 6 )
                        {
                            evDesc = br.readLine();
                        }
                        else
                        {
                            br.readLine();
                        }
                    }
                    
                    int year = Integer.parseInt(evDate.substring(0,evDate.indexOf("/")));

                    int month = Integer.parseInt(evDate.substring(evDate.indexOf("/") + 1,evDate.lastIndexOf("/")));

                    int day = Integer.parseInt(evDate.substring(evDate.lastIndexOf("/") + 1,evDate.length()));
                    
                    int x = (smallWindow.events.size()) * 500;
                
                    int y;

                    if(smallWindow.events.size() % 2 > 0)
                    {
                        y = -500;
                    }
                    else
                    {
                        y = 25;
                    }
                    
                    smallWindow.events.add(new Event(year,month,day,evName,evDesc,x,y));
                } 
                
                smallWindow.setVisible(true);
            }
            catch(Exception ex)
            {
                
            }
            
            smallWindow.sort();
            
            smallWindow.setVisible(true);
        }
        
    }
}
