package src.java;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
public class Sanity{
    public static ArrayList<File> getFileNames(String folderPath){
        ArrayList<File> files = new ArrayList<>();
        File folder = new File(folderPath);
        if(folder.exists() && folder.isDirectory()){
            File[] pathNames = folder.listFiles();
            for (File file:pathNames){
                if(file.isDirectory()){
                    ArrayList<File> newFiles = getFileNames(file.getAbsolutePath());
                    files.addAll(newFiles);
                }else{
                    files.add(file);
                }
            }
        }else{
            JFrame error = new JFrame("Sanity: Error 💔");
            error.setLayout(new FlowLayout());
            JLabel text = new JLabel("User entered a nonexistent path. Please enter a valid path.");
            error.add(text);
            error.setSize(500,200);
            error.setLocationRelativeTo(null);
            error.setVisible(true);
        }
        return files;
    }
}