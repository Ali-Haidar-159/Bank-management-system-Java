
package pack1;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;


public class Signup extends JFrame implements ActionListener{
    JLabel l1,l2,l3,l4;
    JTextField tf1,tf2,accountTf;
    JPasswordField pf2;
    JButton b1,b2,b3;
    String fileName = "account_data.txt";
  
    Signup() throws IOException{
        

        setTitle("Grameen Bank");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("logo/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l11 = new JLabel(i3);
        l11.setBounds(70, 10, 100, 100);
        add(l11);
        
        l1 = new JLabel("Grameen Bank");
        l1.setFont(new Font("Osward", Font.BOLD, 38));
        l1.setBounds(200,40,450,40);
        add(l1);
        
        l2 = new JLabel("Name:");
        l2.setFont(new Font("Raleway", Font.BOLD, 28));
        l2.setBounds(125,150,375,30);
        add(l2);
        
        tf1 = new JTextField(15);
        tf1.setBounds(300,150,230,30);
        tf1.setFont(new Font("Arial", Font.BOLD, 14));
        add(tf1);
        
        l3 = new JLabel("PIN:");
        l3.setFont(new Font("Raleway", Font.BOLD, 28));
        l3.setBounds(125,220,375,30);
        add(l3);
        
        tf2 = new JTextField(15);
        tf2.setFont(new Font("Arial", Font.BOLD, 14));
        tf2.setBounds(300,220,230,30);
        add(tf2);
        
        accountTf=new JTextField(15);
        accountTf.setFont(new Font("Arial", Font.BOLD, 14));
        accountTf.setBounds(300,390,230,30);
        add(accountTf);
                
        b1 = new JButton("SIGN UP");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        
        b2 = new JButton("CLEAR");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        b3 = new JButton("LOGIN");
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        
        b3.setFont(new Font("Arial", Font.BOLD, 14));
        b3.setBounds(300,340,230,30);
        add(b3);
        
        setLayout(null);
        
        b1.setFont(new Font("Arial", Font.BOLD, 14));
        b1.setBounds(300,300,100,30);
        add(b1);
        
        b2.setFont(new Font("Arial", Font.BOLD, 14));
        b2.setBounds(430,300,100,30);
        add(b2);
        
        
        b1.addActionListener((ActionListener) this);
        b2.addActionListener((ActionListener) this);
        b3.addActionListener((ActionListener) this);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(800,480);
        setLocation(550,200);
        setVisible(true);
        
    }
    
    
    public static void main(String[] args) throws IOException{
        new Signup();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try{        
            FileWriter fileWriter = new FileWriter(fileName,true);
            if(ae.getSource()==b1){
                String name  = tf1.getText();
                String pin  = tf2.getText() ;
                if(name.length()<3){
                    JOptionPane.showMessageDialog(null,"Name Length Invalid");
                    return;
                }
                System.out.println(pin.length());
                if(pin.length()!=4){
                    JOptionPane.showMessageDialog(null,"PIN Length Must be 4");
                    return;
                }
                String accountNo=generateAccountNo();
                fileWriter.write(accountNo+","+pin+"\n");
                fileWriter.flush();
                fileWriter.close();
                accountTf.setText(accountNo);
                
                if(1==1){
;
                }else{
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            }else if(ae.getSource()==b2){
                tf1.setText("");
                tf2.setText("");
            }
            else if(ae.getSource()==b3){
                setVisible(false);
                new Login();
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public String generateAccountNo() {
        Random random = new Random();
        int min = 10000; 
        int max = 99999; 
        int randomNumber = random.nextInt((max - min) + 1) + min;
        //System.out.println("Random number: " + randomNumber);
        return String.valueOf(randomNumber);
    }
   
    
}