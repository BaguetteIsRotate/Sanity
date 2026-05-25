package src.java;
import java.util.*;
import java.io.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class SanityMain{
    public static void main(String[] args){
        JFrame baguette = new JFrame("Sanity");
        baguette.setLayout(new FlowLayout());
        baguette.add(new JLabel("Enter file path"));
        baguette.setSize(700,300);
        baguette.setLocationRelativeTo(null);
        JTextField text = new JTextField(30);
        baguette.add(text);
        JTextArea label = new JTextArea();
        label.setEditable(false);
        baguette.add(label);
        text.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ArrayList<File> result = Sanity.getFileNames(text.getText());
                String outputplease = "Files in folder:"+"\n";
                for(File file: result){
                    outputplease+=file.getAbsolutePath();
                    outputplease+="\n";
                }
                label.setText(outputplease);
                text.setText("");
            }
        });
        baguette.setVisible(true);
    }
}
//"/Users/lordkat/Desktop/untitled folder 4"