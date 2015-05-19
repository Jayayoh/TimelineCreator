
package timelinecreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class ProjectWorkSpace extends JFrame implements ActionListener,KeyListener
{
    
    viewPanel screen;
    
    JPanel addPanel = new JPanel();
    
        JLabel lbl_Title = new JLabel("Event Name: ");
        JTextField txt_title = new JTextField();
        
        JLabel lbl_date = new JLabel("Event Date (YYYY/MM/DD): ");
        JTextField txt_date = new JTextField();
        
        JLabel lbl_desc = new JLabel("Event Description: ");
        JTextArea txt_desc = new JTextArea();
        JScrollPane scrollBar;
        
        JButton btn_add = new JButton("Add Event");
        
        JLabel lbl_msg = new JLabel();
                
    
    JPanel editPanel = new JPanel();
    
        JComboBox eventList = new JComboBox();
        
        JLabel lbl_change_title = new JLabel("Change Title: ");
        JTextField txt_change_title = new JTextField();
        
        JLabel lbl_change_date = new JLabel("Change date (YYYY/MM/DD): ");
        JTextField txt_change_date = new JTextField();
        
        JLabel lbl_change_Desc = new JLabel("Change Description: ");
        JTextArea txt_change_desc = new JTextArea();
        JScrollPane change_scrollBar;
        
        JButton btn_change = new JButton("Apply Changes");
        JButton btn_delete = new JButton("Delete Event");
    
    JPanel filePanel = new JPanel();
    
        JLabel lbl_change_filename = new JLabel("Change Project Name: ");
        JTextField txt_change_filename = new JTextField();
        JButton btn_change_filename = new JButton("Apply");
        
        JLabel lbl_save_msg = new JLabel();
    
        JButton btn_save = new JButton("Save File");
    
    //variables
    Color bgColor;
    Color lnColor;
    
    int refx;
    int refy;
    
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    
    String projectTitle;
    
    ArrayList <Event> events = new ArrayList();

    public ProjectWorkSpace(String title, Color bgColor, Color lnColor) 
    {
        setVisible(true);
        setTitle("Project Workspace " + title);
        initComponents();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setLocation(400,200);
        //stop the timer  on exit
        addWindowListener(new WindowAdapter() { 
            public void windowClosing(WindowEvent e) { 
                update.stop();
                meClose();
            }});

        mainTab.addKeyListener(this);
        
        mainTab.setActionMap(null);
        
        screen = new viewPanel();
        
        projectTitle = title;
        
        refx = 25;
        refy = 150;
        
        //display
        mainTab.addTab(title, screen);
        //add event
        mainTab.addTab("Add Event", addPanel);
        addPanel.setLayout(null);
            //components
            lbl_Title.setSize(100,25);
            lbl_Title.setLocation(100,25);
            addPanel.add(lbl_Title);
        
            txt_title.setSize(100,25);
            txt_title.setLocation(260,25);
            addPanel.add(txt_title);
            
            lbl_date.setSize(200,25);
            lbl_date.setLocation(100,60);
            addPanel.add(lbl_date);
            
            txt_date.setSize(100,25);
            txt_date.setLocation(260,60);
            addPanel.add(txt_date);
            
            lbl_desc.setSize(100,25);
            lbl_desc.setLocation(100,95);
            addPanel.add(lbl_desc);
            
            txt_desc.setBorder(BorderFactory.createLineBorder(Color.black));
            txt_desc.setLineWrap(true);
            scrollBar = new JScrollPane(txt_desc);
            scrollBar.setVerticalScrollBar(new JScrollBar());
            scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scrollBar.setSize(210,210);
            scrollBar.setLocation(100,115);
            addPanel.add(scrollBar);
            
            btn_add.setSize(100,25);
            btn_add.setLocation(100,350);
            btn_add.addActionListener(this);
            addPanel.add(btn_add);

            lbl_msg.setSize(200,25);
            lbl_msg.setLocation(100,320);
            addPanel.add(lbl_msg);
        
        //edit event
        editPanel.setLayout(null);
        mainTab.addTab("Edit Event", editPanel);
        //components
                
            eventList.setLocation(25,15);
            eventList.setSize(250,25);
            eventList.addActionListener(this);
            editPanel.add(eventList);
            
            lbl_change_title.setLocation(25,75);
            lbl_change_title.setSize(125,25);
            editPanel.add(lbl_change_title);
            
            txt_change_title.setLocation(200,75);
            txt_change_title.setSize(100,25);
            editPanel.add(txt_change_title);
            
            lbl_change_date.setLocation(25,115);
            lbl_change_date.setSize(175,25);
            editPanel.add(lbl_change_date);
            
            txt_change_date.setLocation(200,115);
            txt_change_date.setSize(100,25);
            editPanel.add(txt_change_date);
            
            lbl_change_Desc.setLocation(25,155);
            lbl_change_Desc.setSize(125,25);
            editPanel.add(lbl_change_Desc);
            
            change_scrollBar = new JScrollPane(txt_change_desc);
            change_scrollBar.setVerticalScrollBar(new JScrollBar());
            change_scrollBar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            change_scrollBar.setSize(210,180);
            change_scrollBar.setLocation(25,180);
            txt_change_desc.setBorder(BorderFactory.createLineBorder(Color.black));
            txt_change_desc.setLineWrap(true);
            editPanel.add(change_scrollBar);
            
            btn_change.setLocation(275,200);
            btn_change.setSize(150,25);
            btn_change.addActionListener(this);
            editPanel.add(btn_change);
            
            btn_delete.setLocation(275,250);
            btn_delete.setSize(150,25);
            btn_delete.addActionListener(this);
            editPanel.add(btn_delete);
            
        
        //file
        mainTab.addTab("File", filePanel);
        
        filePanel.setLayout(null);
        
        //components
        
            lbl_change_filename.setLocation(15,25);
            lbl_change_filename.setSize(150,25);
            filePanel.add(lbl_change_filename);
            
            txt_change_filename.setLocation(165,25);
            txt_change_filename.setSize(200,25);
            filePanel.add(txt_change_filename);
            
            btn_change_filename.setLocation(375,25);
            btn_change_filename.setSize(75,25);
            btn_change_filename.addActionListener(this);
            filePanel.add(btn_change_filename);
            
            btn_save.setLocation(15,75);
            btn_save.setSize(100,25);
            btn_save.addActionListener(this);
            filePanel.add(btn_save);
            
            lbl_save_msg.setLocation(15,125);
            lbl_save_msg.setSize(250,25);
            filePanel.add(lbl_save_msg);
        
        this.bgColor = bgColor;
        this.lnColor = lnColor;
        
        update.start();
        
    }
    
    public void meClose()
    {
        this.dispose();
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
    
    Timer update = new Timer(10,new ActionListener() {public void actionPerformed(ActionEvent e){
        mainLoop();
    }});
     
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainTab = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainTab, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainTab, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane mainTab;
    // End of variables declaration//GEN-END:variables

    public void writeToFile()
    {
        BufferedWriter out;
        FileWriter ryt;
        
        try
        {
            ryt=new FileWriter("Projects/" + projectTitle + ".tlc");
            out=new BufferedWriter(ryt);

            //write the title
            out.write("Project Title: ");
            out.newLine();
            out.write(projectTitle);
            out.newLine();
            
            //write the background color
            out.write("Background color: ");
            out.newLine();
            out.write(bgColor.getRed() + " " + bgColor.getGreen() + " " + bgColor.getBlue());
            out.newLine();
            
            //write the line color
            out.write("Line Color: ");
            out.newLine();
            out.write(lnColor.getRed() + " " + lnColor.getGreen() + " " + lnColor.getBlue());
            out.newLine();
            out.newLine();
            
            //write the number of events
            out.write("Events: ");
            out.newLine();
            out.write(Integer.toString(events.size()));
            out.newLine();
            out.newLine();
            
            //loop through the events. write out the event information
            for(int i = 0; i < events.size(); i++)
            {
                //event title
                out.write("Event " + i + " Title: ");
                out.newLine();
                out.write(events.get(i).Title);
                out.newLine();
                
                //event date
                out.write("Event " + i + " Date: ");
                out.newLine();
                out.write(events.get(i).year + "/" + events.get(i).month + "/" + events.get(i).day);
                out.newLine();
                
                //event description
                out.write("Event " + i + " Description: ");
                out.newLine();
                out.write(events.get(i).Description);
                out.newLine();
                out.newLine();
            }
            
            out.write("File End");
            out.close();
            
            lbl_save_msg.setForeground(Color.green);
            lbl_save_msg.setText("Saved as " + projectTitle + ".tlc");
        }
        catch(Exception e)
        {
            lbl_save_msg.setForeground(Color.red);
            lbl_save_msg.setText("Error Processing Request");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == btn_add)
        {

            try
            {
                //set up the values to be passed

                int year = Integer.parseInt(txt_date.getText().substring(0,txt_date.getText().indexOf("/")));

                int month = Integer.parseInt(txt_date.getText().substring(txt_date.getText().indexOf("/") + 1,txt_date.getText().lastIndexOf("/")));

                int day = Integer.parseInt(txt_date.getText().substring(txt_date.getText().lastIndexOf("/") + 1,txt_date.getText().length()));

                String title = txt_title.getText(); 

                String description = txt_desc.getText();

                int x = (events.size()) * 500;
                
                int y;

                if(events.size() % 2 > 0)
                {
                    y = -500;
                }
                else
                {
                    y = 25;
                }

                if(events.size() > 0)
                {

                    for(int i = 0; i < events.size(); i++)
                    {
                        if(events.get(i).Title.equals(title))
                        {
                            lbl_msg.setForeground(Color.red);
                            lbl_msg.setText(title + " already exists.");
                            
                            System.out.println(i + " : " + events.size());
                            
                            System.out.println(events.get(i).Title + " : " + title);
                            
                            break;
                        }
                        
                        if(i == events.size() - 1)
                        {
                            events.add(new Event(year,month,day,title,description,x,y));

                            lbl_msg.setForeground(Color.green);
                            lbl_msg.setText("Event has been added!");

                            sort();
                            
                            eventList.addItem(title);
                            
                            mainTab.requestFocusInWindow();
                            
                            break;
                        }
                    }
                }
                else
                {
                    events.add(new Event(year,month,day,title,description,x,y));

                    lbl_msg.setForeground(Color.green);
                    lbl_msg.setText("Event has been added!");

                    sort();

                    eventList.addItem(title);
                    
                    mainTab.requestFocusInWindow();
                }
                
            }
            catch(Exception ex)
            {
                lbl_msg.setForeground(Color.red);
                lbl_msg.setText("There was an error ");
            }
        }
        else if(e.getSource() == eventList)
        {
            for(int i = 0; i < events.size(); i++)
            {
                if(events.get(i).Title.equals(eventList.getSelectedItem()))
                {
                    txt_change_title.setText(events.get(i).Title);
                    txt_change_date.setText(events.get(i).year + "/" + events.get(i).month + "/" + events.get(i).day);
                    txt_change_desc.setText(events.get(i).Description);
                }
            }
            
            mainTab.requestFocusInWindow();
        }
        else if(e.getSource() == btn_change)
        {
            int year = Integer.parseInt(txt_change_date.getText().substring(0,txt_change_date.getText().indexOf("/")));

            int month = Integer.parseInt(txt_change_date.getText().substring(txt_change_date.getText().indexOf("/") + 1,txt_change_date.getText().lastIndexOf("/")));

            int day = Integer.parseInt(txt_change_date.getText().substring(txt_change_date.getText().lastIndexOf("/") + 1,txt_change_date.getText().length()));

            String title = txt_change_title.getText(); 

            String description = txt_change_desc.getText();
                
            for(int i = 0; i < events.size(); i++)
            {
                if(events.get(i).Title.equals(eventList.getSelectedItem()))
                {
                    eventList.removeItem(eventList.getSelectedItem());
                    events.set(i, new Event(year,month,day,title,description,events.get(i).x,events.get(i).y));
                    eventList.addItem(events.get(i).Title);
                    break;
                }
            }
            
            sort();
            
            mainTab.requestFocusInWindow();
        }
        else if(e.getSource() == btn_delete)
        {
            for(int i = 0; i < events.size(); i++)
            {
                if(events.get(i).Title.equals(eventList.getSelectedItem()))
                {
                    eventList.removeItem(eventList.getSelectedItem());
                    events.remove(i);
                    break;
                }
            }
            
            sort();
            
            mainTab.requestFocusInWindow();
        }
        else if(e.getSource() == btn_change_filename)
        {
            this.projectTitle = txt_change_filename.getText();
            this.setTitle("Project Workspace " + projectTitle);
            
            mainTab.requestFocusInWindow();
        }
        else if(e.getSource() == btn_save)
        {
            writeToFile();
            
            mainTab.requestFocusInWindow();
        }
    }

    class viewPanel extends JPanel 
    {
        
        JLabel lbl_inst = new JLabel("Move using the arrow keys!");
        
        public viewPanel()
        {
            setVisible(true);
            setLayout(null);
        }
        
        @Override
        public void paintComponent(Graphics g)
        {
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
