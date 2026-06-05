package src.java;
import java.util.*;
import java.util.concurrent.Flow;
import java.io.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
public class SanityMain{
    public static void main(String[] args){
        JFrame baguette = new JFrame("Sanity");
        baguette.setLayout(new BorderLayout());
        baguette.setSize(700,300);
        baguette.setLocationRelativeTo(null);
        baguette.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel top= new JPanel(new FlowLayout());
        top.add(new JLabel("Enter file path:"));

        
        JTextField text = new JTextField(30);
        top.add(text);
        
        JButton button = new JButton("Browse");
        top.add(button);

        JButton button2 = new JButton("Search");
        top.add(button2);

        baguette.add(top,BorderLayout.PAGE_START);

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

        DefaultListModel<File> model = new DefaultListModel<>();
        JList<File> label = new JList<>(model); 
        JScrollPane pane = new JScrollPane(label,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setWheelScrollingEnabled(true);
        pane.setPreferredSize(new Dimension(600,200) );
        //label.setEditable(false);
        //label.setLineWrap(true);
        pane.setVisible(false);
        JPanel left= new JPanel(new FlowLayout());
        left.add(pane);
        baguette.add(left, BorderLayout.LINE_START);

        JPanel right= new JPanel(new FlowLayout());
        JTextArea label2 = new JTextArea();
        label2.setEditable(false);
        label2.setLineWrap(true);
        JScrollPane pane2 = new JScrollPane(label2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane2.setWheelScrollingEnabled(true);
        pane2.setPreferredSize(new Dimension(600,200) );
        right.add(pane2);
        JButton button3 = new JButton("Save");
        button3.setPreferredSize(new Dimension(100,30));
        baguette.add(button3, BorderLayout.SOUTH);
        baguette.add(right, BorderLayout.LINE_END);

        baguette.setVisible(true);

        text.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                model.clear();
                ArrayList<File> result = Sanity.getFileNames(text.getText());
                //String outputplease = "Files in folder:"+"\n";
                for(File file: result){
                    model.addElement(file);
                    //outputplease+="\n";
                }
                text.setText("");
                pane.setVisible(true);
                baguette.setSize(1268,310);
                baguette.revalidate();
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                text.postActionEvent();
            }
        });
    }
}