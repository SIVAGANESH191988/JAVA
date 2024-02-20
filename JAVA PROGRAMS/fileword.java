import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.*;

import javax.swing.text.AbstractDocument.BranchElement;

public class fileword {

	public static void main(String[] args) throws IOException {
		File f1 = new File("C:\\Users\\DT049\\Downloads\\dataset.csv");
		FileReader f2 = new FileReader(f1);
		BufferedReader f3 = new BufferedReader(f2);
		String a =" ";
//		FileInputStream f3 = new FileInputStream(f1);
//		FileOutputStream f4 = new FileOutputStream("C:\\Users\\DT049\\Downloads\\2121.txt");
		while((a = f3.readLine())!= null) {
			String[] arr = null;
			arr=a.split(",");
			for (String i: arr) {
			System.out.println(i+"-");
			}
		}

	}

}
