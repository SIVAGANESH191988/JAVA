import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class fileimagetxt {

	public static void main(String[] args) throws IOException {
		File f1 = new File("C:\\Users\\DT049\\Desktop\\01.jpg");
//		FileReader f2 = new FileReader(f1);
//		FileWriter f3 = new FileWriter(f1);
		int k=0;
		FileInputStream f3 = new FileInputStream(f1);
		FileOutputStream f4 = new FileOutputStream("C:\\Users\\DT049\\Downloads\\2121.txt");
		while(f3.available()>0) {
			f4.write(f3.read());
		}

	}

}
