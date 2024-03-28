import java.util.*;
public class ArrayRotation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Scanner sc =new Scanner(System.in);
int n=sc.nextInt();
int x=sc.nextInt();
int temp=0;
int arr[]=new int [n];

for(int i=0;i<n;i++)
{
	arr[i]=sc.nextInt();
}
for(int k=0;k<x;k++)
{
	if(k!=0)
	{
temp =arr[k-1];
	}
	else
	{
		temp=arr[0];
	}
for(int i=1;i<n;i++)
{
	

	arr[i-1]=arr[i];
	
	
}
arr[n-1]=temp;
}


for(int i=0;i<n;i++)
{
	System.out.println(arr[i]);
}
	}
}

