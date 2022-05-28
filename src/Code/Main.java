package Code;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args)   {
		
		double data[][] = {
				{34.2 , 35, 4.6, 75},
				{40 , 35, 23.9, 75},
				{30 , 35, 23.9, 60},
				{22 , 40 , 30.1 , 10},
				{18.2, 70, 36.8, 100},
				{25.0, 37, 13.0, 80},
				{25, 50, 43.9, 80},
				{51.0, 80, 1.6, 100}
		};	
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("sensors.ser"));
			out.writeObject(data);
			out.close();
		}catch(IOException ecx) {
			ecx.printStackTrace();
		}
		
		new DataPage();
	}

}
