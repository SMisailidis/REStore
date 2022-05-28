package Code;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args)   {
		
		double data[][] = {
				{34.2 , 35, 2, 75},
				{18.2, 70, 8, 100},
				{25.0, 37, 4, 80}
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
