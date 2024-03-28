import java.util.*;
public class Ex {
public static void main(String Args[])
{
	Scanner sc= new Scanner(System.in);
String s=sc.nextLine();
	String s1[]=s.split(" ");
	int t=0;
int count=0;
	
	for(int i=0;i<s1.length;i++)
	{
		
		for(int j=i+1;j<s1.length;j++)
		{
			if(count==s1.length-1)
			{
				break;
			}
		if(s1[i].length()>s1[j].length())
		{
			
			t=s1[i].length();
			count++;
			
			
		}
		
		else
		{
			t=s1[j].length();
			
		}
		
	}
		
	}
	System.out.println(t);
}
}
