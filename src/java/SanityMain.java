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
        baguette.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextField text = new JTextField(30);
        baguette.add(text);

        JButton button = new JButton("Browse");
        baguette.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = chooser.showOpenDialog(button);
                if(result == JFileChooser.APPROVE_OPTION){
                    File jeremy = chooser.getSelectedFile();
                    String namedUri = jeremy.toPath().toString();
                    text.setText(namedUri);
                }
            }
        });

        JTextArea label = new JTextArea();
        JScrollPane pane = new JScrollPane(label,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setWheelScrollingEnabled(true);
        pane.setPreferredSize(new Dimension(700,300) );
        label.setEditable(false);
        label.setLineWrap(true);
        pane.setVisible(false);
        baguette.add(pane);
        baguette.setVisible(true);

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
                pane.setVisible(true);
                baguette.revalidate();
            }
        });

    }
}
//"/Users/lordkat/Desktop/untitled folder 4"