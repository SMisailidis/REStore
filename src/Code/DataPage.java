package Code;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DataPage extends JFrame {
	
	private JPanel generalPanel;
	private JLabel apiLabel;
	private JPanel apiPanel;
	private JLabel temperatureLabel;
	private JTextField temperatureField;
	private JLabel humidityLabel;
	private JTextField humidityField;
	private JLabel beaufortLabel;
	private JTextField beaufortField;
	private JLabel weatherDescLabel;
	private JTextField weatherDescField;
	private JLabel weatherCondLabel;
	private JTextField weatherCondField;
	private JLabel animationLabel;
	
	private JLabel sensorsLabel;
	private JPanel sensorsPanel;
	private JLabel temperatureSLabel;
	private JTextField temperatureSField;
	private JLabel humiditySLabel;
	private JTextField humiditySField;
	private JLabel beaufortSLabel;
	private JTextField beaufortSField;
	private JLabel brightnessSLabel;
	private JTextField brightnessSField;
	
	private JLabel decisionLabel;
	private JPanel decisionPanel;
	private JLabel temperatureDLabel;
	private JTextField temperatureDField;
	private JLabel humidityDLabel;
	private JTextField humidityDField;
	private JLabel beaufortDLabel;
	private JTextField beaufortDField;
	
	
	double data[][] = null;
	double beaufortToKnots;
	double productivitySolar;
	double productivityWind;
	
	public DataPage()
	{
		generalPanel = new JPanel(null);
		apiPanel = new JPanel();
		apiLabel = new JLabel("API Data");
		temperatureLabel = new JLabel("Temperature:");
		temperatureField = new JTextField(7);
		temperatureField.setEditable(false);
		humidityLabel = new JLabel("Humidity:");
		humidityField = new JTextField(7);
		humidityField.setEditable(false);
		beaufortLabel = new JLabel("Beaufort:");
		beaufortField = new JTextField(7);
		beaufortField.setEditable(false);
		weatherDescLabel = new JLabel("Weather Description:");
		weatherDescField = new JTextField(10);
		weatherDescField.setEditable(false);
		weatherCondLabel = new JLabel("Weather Condition:");
		weatherCondField = new JTextField(7);
		weatherCondField.setEditable(false);
		
		sensorsLabel = new JLabel("Sensors Data");
		sensorsPanel = new JPanel();
		temperatureSLabel = new JLabel("Temperature:");
		temperatureSField = new JTextField(7);
		temperatureSField.setEditable(false);
		humiditySLabel = new JLabel("Humidity:");
		humiditySField = new JTextField(7);
		humiditySField.setEditable(false);
		beaufortSLabel = new JLabel("Beaufort:");
		beaufortSField = new JTextField(7);
		beaufortSField.setEditable(false);
		brightnessSLabel = new JLabel("Brightness:");
		brightnessSField = new JTextField(7);
		brightnessSField.setEditable(false);
		
		decisionLabel = new JLabel("Decision Data");
		decisionPanel = new JPanel();
		temperatureDLabel = new JLabel("Temperature:");
		temperatureDField = new JTextField(7);
		temperatureDField.setEditable(false);
		humidityDLabel = new JLabel("Humidity:");
		humidityDField = new JTextField(7);
		humidityDField.setEditable(false);
		beaufortDLabel = new JLabel("Beaufort:");
		beaufortDField = new JTextField(7);
		beaufortDField.setEditable(false);
		
		animationLabel = new JLabel();
		animationLabel.setIcon(new ImageIcon("C:\\Users\\User\\eclipse-workspace\\RESManager\\src\\Photos\\wind_turbine.gif"));
		//animationLabel.setIcon(new ImageIcon("C:\\Users\\Lenovo\\Desktop\\TSworkspace\\RESManager\\src\\Photos\\wind_turbine.gif"));
		
		//Adding GUI Items to the API Panel.
		apiPanel.add(temperatureLabel);
		apiPanel.add(temperatureField);
		apiPanel.add(humidityLabel);
		apiPanel.add(humidityField);
		apiPanel.add(beaufortLabel);
		apiPanel.add(beaufortField);
		apiPanel.add(weatherCondLabel);
		apiPanel.add(weatherCondField);
		apiPanel.add(weatherDescLabel);
		apiPanel.add(weatherDescField);
		
		//Adding GUI Items to the Sensors Panel.
		sensorsPanel.add(temperatureSLabel);
		sensorsPanel.add(temperatureSField);
		sensorsPanel.add(humiditySLabel);
		sensorsPanel.add(humiditySField);
		sensorsPanel.add(beaufortSLabel);
		sensorsPanel.add(beaufortSField);
		sensorsPanel.add(brightnessSLabel);
		sensorsPanel.add(brightnessSField);
		//Adding GUI Items to the Decision Panel.
		decisionPanel.add(temperatureDLabel);
		decisionPanel.add(temperatureDField);
		decisionPanel.add(humidityDLabel);
		decisionPanel.add(humidityDField);
		decisionPanel.add(beaufortDLabel);
		decisionPanel.add(beaufortDField);

		//Adding GUI Items to the General Panel.
		apiLabel.setBounds(420,5,50,25);
		generalPanel.add(apiLabel);
		apiPanel.setBounds(5,35,900,25);
		generalPanel.add(apiPanel);
		sensorsLabel.setBounds(405,90,80,25);
		generalPanel.add(sensorsLabel);
		sensorsPanel.setBounds(100,120,700,25);
		generalPanel.add(sensorsPanel);
		decisionLabel.setBounds(405,170,80,25);
		generalPanel.add(decisionLabel);
		decisionPanel.setBounds(200,200,500,25);
		generalPanel.add(decisionPanel);
		
		animationLabel.setBounds(360,280,179,299);
		generalPanel.add(animationLabel);

		
		
		//Reading Sensor Data.
		try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("sensors.ser"));
            
            data = (double[][]) in.readObject();
            in.close();
        }catch(IOException exc1) {
            exc1.printStackTrace();
        }catch(ClassNotFoundException exc2) {
            exc2.printStackTrace();
        }		
		
		//Weather API Timer.
		Timer timer = new Timer ();
		TimerTask hourlyTask = new TimerTask () {
		    @Override
		    public void run () {
		    	WeatherAPI weather = new WeatherAPI(); 
		    	temperatureField.setText(Double.toString(Math.round(weather.getTemperature())));
				humidityField.setText(Long.toString(weather.getHumidity()));
				beaufortField.setText(String.format("%.1g", weather.getBeaufort()));
				weatherDescField.setText(weather.getWeatherDescription());
				weatherCondField.setText(weather.getWeatherCondition());
		    }
		};
		// schedule the task to run starting now and then every 2 minutes...
		timer.schedule (hourlyTask, 01, 1000*60*2);
		
		
		//Sensor Timer
		Timer timerS = new Timer ();
		
		
		TimerTask timerTaskS = new TimerTask() {
			int i=-1;
			@Override
			public void run() {
				
		    	WeatherAPI weather = new WeatherAPI(); 
				
				if(i<data.length)
				{
					i++;
					temperatureSField.setText(Double.toString(data[i][0]));
					humiditySField.setText(Double.toString(data[i][1]));
					beaufortSField.setText(Integer.toString((int) Math.round(Math.cbrt(Math.pow(data[i][2] / 1.625, 2)))));
					brightnessSField.setText(Double.toString(data[i][3]));
					
				}
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
				
				/*-----------Fixing any deviations between API's Data and Sensors Data.-----------*/
							
				//Checking Deviation for temperature
				if(Math.abs(Double.parseDouble(temperatureField.getText()) - Double.parseDouble(temperatureSField.getText())) > 3) {
					temperatureDField.setText(temperatureSField.getText());
					
				}	
				else
					temperatureDField.setText(temperatureField.getText());
				
				//Checking Deviation for humidity.
				if(Math.abs(Double.parseDouble(humidityField.getText()) - Double.parseDouble(humiditySField.getText())) > 7)
					humidityDField.setText(humiditySField.getText());
				else
					humidityDField.setText(humidityField.getText());
					
				//Checking Deviation for beaufort.
				if(Math.abs(Double.parseDouble(beaufortField.getText()) - Double.parseDouble(beaufortSField.getText())) > 1) {
					beaufortDField.setText(beaufortSField.getText());
					beaufortToKnots = data[i][2];
				}	
				else {
					beaufortDField.setText(beaufortField.getText());
					if(weather.getWindspeedDouble() != null) {
						beaufortToKnots = weather.getWindspeedDouble();
					}
					else {
						beaufortToKnots = weather.getWindspeedLong();
					}
				}
					
				
				//Calculates the productivity of solar panel
				productivitySolar = 100 - Math.abs(25 - Double.parseDouble(temperatureDField.getText())) * 4;// 	
				
				//Calculates the productivity of wind
				
				if(weather.getWindspeedDouble() != null) {
					double mph = beaufortToKnots * 1.15077945; //knots to mph
					productivityWind = 100 - Math.abs(31 - mph) * 3.22580645161; 
				}
				else {
					double mph = beaufortToKnots * 1.15077945; //knots to mph
					productivityWind =  100 - Math.abs(31 - mph) * 3.22580645161; 
				}
				
				//PRODROME EDW ALLA3E TA GAMHSE TA THN MANA! :)
				if(data[i][3] > 70 && weather.getWeatherCondition().contains("Clear")) {
					System.out.println("Παραγωγικοτητα αιολικου: " + productivityWind);
					System.out.println("Παραγωγικοτητα φωτοβολταικου: " + productivitySolar);
					
					if((Double.parseDouble(temperatureDField.getText())) <23){
						
						if(productivitySolar >= productivityWind) {
							System.out.println("Λειτουργει το φωτοβολταικο με μειωμενη παραγωγικοτητα: "+ String.format("%.1f", productivitySolar) + "%" + " λογω χαμηλης θερμοκρασιας");
						}
						else {
							beaufortCheck();
						}
					}
					else if((Double.parseDouble(temperatureDField.getText())) <= 28) {
						if(productivitySolar >= productivityWind) {
							System.out.println("Λειτουργει αποδοτικα το φωτοβολταικο και η παραγωγικοτητα του ειναι: " + String.format("%.1f", productivitySolar) + "%");
						}
						else {
							beaufortCheck();
						}
					}
					else if(Double.parseDouble(temperatureDField.getText()) < 50) {
						if(productivitySolar >= productivityWind) {
							System.out.println("Λειτουργει το φωτοβολταικο με μειωμενη παραγωγικοτητα: "+ String.format("%.1f", productivitySolar) + "%" + " λογω χαμηλης θερμοκρασιας");
						}
						else {
							beaufortCheck();
						}
					}
					else {
						System.out.println("Τερματισμος φωτοβολταικου.");
						beaufortCheck();
					}
				}
				else {
					beaufortCheck();
				}	
			}	
		};
		// schedule the task to run starting now and then every 1 minute...
		timerS.schedule (timerTaskS, 01, 1000*60*1);
		
		
		
		//About Window.
		this.setContentPane(generalPanel);
		this.setVisible(true);
		this.setTitle("REStore");
		this.setSize(930, 700);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	

	public void beaufortCheck() {
		//PRODROME EDW ALLA3E TA GAMHSETA THN MANA! :)
		System.out.println("Παραγωγικοτητα αιολικου: " + productivityWind);
		System.out.println("Παραγωγικοτητα φωτοβολταικου: " + productivitySolar);
		if (Double.parseDouble(beaufortSField.getText()) < 6) {
			System.out.println("Λειτουργει η ανεμογεννητρια. Χαμηλη παραγωγικοτητα: " + String.format("%.1f", productivityWind) + "%");
		}
		else if (Double.parseDouble(beaufortSField.getText()) <= 9) {
			System.out.println("Λειτουργει αποδοτικά η ανεμογεννητρια και η παραγωγικοτητα της ειναι: " + String.format("%.1f", productivityWind) + "%");
		}
		else {
			System.out.println("Τερματισμος ανεμογεννητριας");
		}
	}
}