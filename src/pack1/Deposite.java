
package pack1 ;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.util.*;

public class Deposite extends JFrame implements ActionListener{
    String fileName = "account_balance_data.txt"; 
    JTextField t1,t2;
    JButton b1,b2,b3;
    JLabel l1,l2,l3;
    String pin;
    String account;
    Deposite(String account){
        this.account=account;
        System.out.println(this.account);
        setTitle("Deposit");
        
        this.pin = pin;
        l1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));
        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.BOLD, 22));
        
        b1 = new JButton("DEPOSIT");
        b2 = new JButton("BACK");
        
        setLayout(null);
        
        l1.setBounds(220,80,500,35);
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        add(l1);
        
        
        t1.setBounds(310,125,320,45);
        add(t1);
        
        b1.setBounds(390,185,150,35);
        add(b1);
        
        b2.setBounds(390,230,150,35);
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
                    JOptionPane.showMessageDialog(null, "Please enter the Amount to you want to Deposit");
                }else{
                    int balance=addBalance(this.account,amount);
                    
                    JOptionPane.showMessageDialog(null, "Rs. "+amount+" Deposited Successfully");
                    setVisible(false);
                    new Transactions(this.account).setVisible(true);
                }
            }else if(ae.getSource()==b2){
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
            
    }
    public Integer addBalance(String targetAccountNo,String amount) throws FileNotFoundException, IOException {
        
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
                        double total=Double.valueOf(balance)+Double.valueOf(amount);
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
        new Deposite("");
    }
}