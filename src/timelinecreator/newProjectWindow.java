
package timelinecreator;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class newProjectWindow extends JFrame implements ActionListener
{
    //the main panel
    JPanel panel = new JPanel();
    
    //get name
    JLabel label_name = new JLabel("Project Name: ");
    JTextField txt_name = new JTextField();
    
    //get background colour
    JButton btn_bgColour = new JButton("Choose Background Colour");
    
    //get Line Colour
    JButton btn_lnColour = new JButton("Choose a Line Colour");
    
    //finish the project
    JButton btn_finish = new JButton("Finish");
    
    //cancel the screen
    JButton btn_cancel = new JButton("Cancel");
    
    //user variables
    Color bgColour = Color.white;
    Color lnColour = Color.black;
    
    //Action Listener
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == btn_bgColour)
        {
            colorChooser myColor = new colorChooser(this,1);
        }
        else if(e.getSource() == btn_lnColour)
        {
            colorChooser myColor = new colorChooser(this,2);
        }
        else if(e.getSource() == btn_cancel)
        {
            this.dispose();
        }
        else if(e.getSource() == btn_finish)
        {
            if(txt_name != null && bgColour != null && lnColour != null)
            {
                ProjectWorkSpace workSpace = new ProjectWorkSpace(txt_name.getText(), bgColour, lnColour);
                this.dispose();
            }
            else
            {
                txt_name.setText("Error");
            }
        }
    }
    
    public newProjectWindow()
    {
        super("New Project");
        setSize(440,145);
        setLocation(400,200);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //panel
        panel.setLayout(null);
        add(panel);
        
        //get name
        label_name.setBounds(10,10,100,25);
        panel.add(label_name);
        
        txt_name.setBounds(110,10,210,25);
        panel.add(txt_name); 
        
        //cancel the screen
        btn_cancel.addActionListener(this);
        btn_cancel.setBounds(135,80,75,25);
        panel.add(btn_cancel);
        
        //finish creating the project
        btn_finish.addActionListener(this);
        btn_finish.setBounds(220,80,75,25);
        panel.add(btn_finish);
        
        //get colour -- opens up a colour palette
        btn_bgColour.addActionListener(this);
        btn_bgColour.setBounds(10,45,200,25);
        panel.add(btn_bgColour);
        
        //get line colour -- opens up a colour palette
        btn_lnColour.addActionListener(this);
        btn_lnColour.setBounds(220,45,200,25);
        panel.add(btn_lnColour);
    }
}
