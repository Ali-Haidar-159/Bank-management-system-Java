
package pack1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Transactions extends JFrame implements ActionListener{
    String account;
    JLabel l1;
    JButton b1,b2,b3,b4,b5,b6,b7;
    String pin;
    Transactions(String accountNo){
        this.account=accountNo;
        setTitle("Transaction");
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("logo/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 118, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l2 = new JLabel();
        l2.setBounds(0, 0, 96, 108);
        add(l2);
        
        l1 = new JLabel("Please Select Your Transaction");
        l1.setForeground(Color.black);
        l1.setFont(new Font("System", Font.BOLD, 16));
        
       
        b1 = new JButton("DEPOSIT");
        b2 = new JButton("CASH WITHDRAWL");
        b3 = new JButton("EXIT");
        
        setLayout(null);
        
        l1.setBounds(235,80,500,35);
        add(l1);
        
        b1.setBounds(170,130,150,35);
        add(b1);
        
        b2.setBounds(170,180,150,35);
        add(b2);
        
        b3.setBounds(170,225,150,35);
        add(b3);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        
        
        setSize(960,500);
        setLocation(550,200);
        
        setVisible(true);

    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b1){ 
            setVisible(false);
            new Deposite(account);
        }else if(ae.getSource()==b2){ 
            setVisible(false);
            new WithDrawl(account);
        }
        else if(ae.getSource()==b3){
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dispose();
        }
}
    
    public static void main(String[] args)
    {
        new Transactions("5454");
    }

}
