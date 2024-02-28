
public class ThreadExample
  {
public static void main(String[]args) throws InterruptedException
{
	

	Thread2 obj1=new Thread2();
	Thread t2=new Thread(obj1);
	t2.start();
	Thread1 obj=new Thread1();
	Thread t1=new Thread(obj);
	t1.start();

}
}
