package timelinecreator;


import javax.swing.*;
import java.awt.event.*;

public class colorChooser extends JFrame implements ActionListener
{
    JPanel panel = new JPanel();
    
    JColorChooser chooser = new JColorChooser();
    
    JButton select = new JButton("Select");
    JButton cancel = new JButton("Cancel");
    
    newProjectWindow Args;
    int Args2;
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == cancel)
        {
            this.dispose();
        }
        else if(e.getSource() == select)
        {
            switch(Args2)
            {
                case 1: Args.bgColour = chooser.getColor();this.dispose();break;
                case 2: Args.lnColour = chooser.getColor();this.dispose();break;
            }
        }
    }
    
    public colorChooser(newProjectWindow args,int args2)
    {
        super("Choose a Colour");
        setSize(465,420);
        setLocation(400,200);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        
        Args = args;
        Args2 = args2;
        
        panel.setLayout(null);
        add(panel);
        
        chooser.setBounds(5,0,450,350);
        panel.add(chooser);
        
        select.addActionListener(this);
        select.setBounds(150,355,75,25);
        panel.add(select);
        
        cancel.addActionListener(this);
        cancel.setBounds(265,355,75,25);
        panel.add(cancel);
    }
}
