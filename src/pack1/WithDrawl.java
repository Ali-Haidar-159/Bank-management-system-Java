
package pack1;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;

public class WithDrawl extends JFrame implements ActionListener{
    String fileName = "account_balance_data.txt"; 
    JTextField t1,t2;
    JButton b1,b2,b3;
    JLabel l1,l2,l3,l4;
    String pin;
    String account;
    WithDrawl(String account){
        this.account=account;
        setTitle("Withdraw Money");
        
        
        l1 = new JLabel("MAXIMUM WITHDRAWAL IS RS.10,000");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));
        
        l2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("System", Font.BOLD, 16));
        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 25));
        
        b1 = new JButton("WITHDRAW");
        b2 = new JButton("BACK");
        
        setLayout(null);
        
        l1.setBounds(220,80,400,20);
        add(l1);
        
        l2.setBounds(220,110,400,20);
        add(l2);
        
        t1.setBounds(300,150,330,30);
        add(t1);
        
        b1.setBounds(390,190,150,35);
        add(b1);
        
        b2.setBounds(390,245,150,35);
        add(b2);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        setSize(960,500);
        setLocation(550,200);
        
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae){
        try{        
            String amount = t1.getText();
            Date date = new Date();
            if(ae.getSource()==b1){
                if(t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Withdraw");
                }else{
                    removeBalance(this.account,amount);
                    JOptionPane.showMessageDialog(null, "Rs. "+amount+" Debited Successfully");
                    
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                }
            }else if(ae.getSource()==b2){
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        }catch(Exception e){
                e.printStackTrace();
                System.out.println("error: "+e);
        }
            
    }
public Integer removeBalance(String targetAccountNo,String amount) throws FileNotFoundException, IOException {
        
         try{
             java.util.List<String> updatedLines = new ArrayList<>();
             BufferedReader br = new BufferedReader(new FileReader(fileName));
             boolean found = false;
             
             String line;
             while((line=br.readLine())!=null){
                 
                 String[] parts = line.split(",");
                if (parts.length == 2) {
                    String accountNo = parts[0];
                    String balance = parts[1];
                    
                    if (accountNo.equals(targetAccountNo)) {
                        found = true;
                        double total=Double.valueOf(balance)-Double.valueOf(amount);
                        updatedLines.add(accountNo+","+String.valueOf(total));
                    }
                    else{
                        updatedLines.add(line);
                    }
                }
             }
             
            FileWriter fileWriter = new FileWriter(fileName);

            for (String updatedLine : updatedLines) {
                fileWriter.write(updatedLine);
                fileWriter.write("\n");
            }

            fileWriter.close();

             
             
             
         }catch(Exception e){
             e.printStackTrace();
         }
return 0;
    }
    
    
    public static void main(String[] args){
        new WithDrawl("");
    }
}