package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.trananh3010.model.User;

public class TestFile {
	public static void main(String[] args) {
		try {
			File f = new File("datas/user.txt");
		    FileWriter fw = new FileWriter(f);
		    
		    User user = new User("001", "Tuấn", "Trần", "tuananh", "asdasdasdas", "asdasdasdas");
		    
		    
		    fw.write(user.toJson());
		    
		    fw.close(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			File f = new File("datas/user.txt");
		    FileReader fr = new FileReader(f);
		    BufferedReader br = new BufferedReader(fr);
		     String line;
		     while ((line = br.readLine()) != null){
		       System.out.println(line);
		     }
		     fr.close();
		     br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
