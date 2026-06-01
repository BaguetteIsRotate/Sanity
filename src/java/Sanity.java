package src.java;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
public class Sanity{
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        System.out.println(getFileDist(s, "Quintessential Meesh", 99));
    }
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
            ohNo();
        }
        return files;
    }
    public static ArrayList<File> getFileDist(String folderPath, String searchBy, int limit){
        ArrayList<File> files = new ArrayList<>();
        File folder = new File(folderPath);
        TreeMap<Integer, ArrayList<File>> map = new TreeMap<>();
        if(folder.exists() && folder.isDirectory()){
            File[] pathNames = folder.listFiles();
            for (File file:pathNames){
                System.out.println(Arrays.toString(pathNames));
                if(!(file.isDirectory())){
                    int j= getFileDistHelper(searchBy, searchBy.length(), file.getName(), file.getName().length());
                    ArrayList<File> thebank = map.getOrDefault(j,new ArrayList<File>());
                    if(j<=limit){
                        thebank.add(file);
                        map.put(j, thebank);
                    }
                }
            }
        }else{
            ohNo();
            return new ArrayList<File>();
        }
        System.out.println(map);
        for(int j : map.keySet()){
            ArrayList<File> n = map.get(j);
            files.addAll(n);
        }
        return files;
    }
    public static int getFileDistHelper(String str1, int len1, String str2, int len2){
        if (len1 == 0) {
            return len2;
        }
        if (len2 == 0) {
            return len1;
        }
        if (str1.charAt(str1.length()-1) == str2.charAt(str2.length()-1)) {
            return getFileDistHelper(str1, str1.length()-1, str2, str2.length()-1);
        }
        int insertionCost = getFileDistHelper(str1, len1, str2, len2-1);
        int deletionCost = getFileDistHelper(str1, len1-1, str2, len2);
        int substitutionCost = getFileDistHelper(str1, len1-1, str2, len2-1);
        return 1 + Math.min(Math.min(insertionCost, deletionCost), substitutionCost);
    }
    public static void ohNo(){
        JFrame error = new JFrame("Sanity: Error 💔");
        error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        error.setLayout(new FlowLayout());
        JLabel text = new JLabel("User entered a nonexistent path. Please enter a valid path.");
        error.add(text);
        error.setSize(500,200);
        error.setLocationRelativeTo(null);
        error.setVisible(true);
    }
}