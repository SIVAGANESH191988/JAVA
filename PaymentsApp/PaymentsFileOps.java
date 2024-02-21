

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PaymentsFileOps {
	public final String usersFilePath = "G:\\8577 team force\\Payments_CLI_USers.csv";
	
	public void writeUserToFile(User u) throws IOException {
		
		File userFile = new File(usersFilePath);
		FileWriter fw = new FileWriter(userFile,true);
		fw.write(u.userToFileRecord());
		
		fw.flush();
		String a="";
		List<String> los=new ArrayList<String>();
		FileReader f2=new FileReader(userFile);
		BufferedReader b=new BufferedReader(f2);
		while((a=b.readLine())!=null)
		{
			los.add(a);
		}
		for(String i:los)
		{
			System.out.println(i);
		}
		fw.close();
	}

}