package csci152.test;

import csci152.adt.HashTableMap;
import csci152.adt.Map;
import csci152.adt.Queue;
import csci152.adt.SortedQueue;
import csci152.impl.LLQHashTableMap;
import csci152.impl.LinkedListQueue;
import csci152.impl.LinkedListSortedQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Serik Zhilibayev Aikorkem Zhumabek
 */
public class Assignment4 {

    
    public static void traverse(File dirSource, Map<String, Queue<OccurenceRecord>> mp, SortedQueue<String> sq) throws IOException{
        File[] contents = dirSource.listFiles();
        for (File file : contents){
            if (file.isDirectory()){
                traverse(file, mp, sq);
            }else{
                tokenize(file, mp, sq);
            }
        }
    }
    
    public static void tokenize(File f, Map<String, Queue<OccurenceRecord>> mp, SortedQueue<String> sq) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(f));
        
        int ch;
        String token = "";
        int colIdx = 0;
        int row = 0;
        int i = 0;
        
        do{
            ch = reader.read();
            if (isAvailable(ch)){
                token += (char)ch;
            }else if (ch == '\n'){   
                colIdx = 0;
                i = -1;
                row++;
            } else if (token.length() > 0){
                
                token = toLowerCase(token);
                
                sq.insert(token);
                Queue<OccurenceRecord> q = mp.getValue(token);
                OccurenceRecord ocr = new OccurenceRecord(f.getName(), row, colIdx);
                if (q == null){
                    mp.define(token, new LinkedListQueue());
                    mp.getValue(token).enqueue(ocr);
                }else{
                    q.enqueue(ocr);
                }
                token = "";
                colIdx = i + 1;
            }
            i++;
        }while (ch != -1);
       
        reader.close();
        
    }
    
    private static boolean isAvailable(int ch) {
        return ((64 < ch && ch < 91)||(96 < ch && ch < 123)||ch == 39||ch == 45);
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        File f = new File("inputfiles");
        File rf = new File("getwordinfo.txt");
        Scanner sc = new Scanner(rf);
        if (f.exists()){
            System.out.println("Exists");
        }
        
        HashTableMap<String, Queue<OccurenceRecord>> mp = new LLQHashTableMap(1000);
        SortedQueue<String> sq = new LinkedListSortedQueue(); 
        
        int wc = 0;
        traverse(f, mp, sq);
        wc = count(mp, sq);
        System.out.println(sq);
        
        System.out.println("Load Factor: " + mp.getLoadFactor());
        System.out.println("Bucket Size Standard Deviation: " + mp.getBucketSizeStandardDev() + "\n");
        
        String st = "";
        Queue<OccurenceRecord> ocr = null;
        while (sc.hasNext()){
            st = toLowerCase(sc.next());
            ocr = mp.getValue(st);
            if (ocr == null){
                System.out.println("Word: "+ st +" -> Not found");
            }else{ 
                System.out.println("Word: " + st);
                System.out.println("Word Occurency: " + ocr.toString());
                System.out.println("Number Of Occurences: "+ ocr.getSize() + "   Usage Frequency: " + (double)(ocr.getSize())/wc*100.0+"%\n");
            }
        }
       
    }

    private static String toLowerCase(String token) {
        String res = "";
        for (int i = 0; i < token.length(); i++){
            int k = token.charAt(i);
            if (64<k && k<91){
                res += (char)(k+32);
            }else{
                res += (char)k;
            }
        }
        return res;
    }

    private static int count(HashTableMap<String, Queue<OccurenceRecord>> mp, SortedQueue<String> sq) throws Exception {
        int wc = 0;
        Queue<String> q = new LinkedListQueue();
        while (sq.getSize() > 0){
            String k = sq.dequeue();
            q.enqueue(k);
            wc += mp.getValue(k).getSize();
        }
        while (q.getSize()>0){
            sq.insert(q.dequeue());
        }
        return wc;
    }
}
class OccurenceRecord{
    
    String filename;
    int lineNumber;
    int column;
    
    public OccurenceRecord(String st, int num1, int num2){
        filename = st;
        lineNumber = num1;
        column = num2;
    }
    public String toString(){
        return filename + "---" + lineNumber + "---" + column; 
    } 
}