
public class Thread1 extends Thread {
 public void run()
 {
	 for(int i=2;i<=100;i++)
	 {
		 if(i%2==0)
		 {
			 System.out.println(i);
			 try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		 }
	 }
 }
}
