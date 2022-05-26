package Code;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;

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
		
		animationLabel = new JLabel();
		animationLabel.setIcon(new ImageIcon("C:\\Users\\User\\eclipse-workspace\\RESManager\\src\\Photos\\wind_turbine.gif"));
		
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

		//Adding GUI Items to the General Panel.
		apiLabel.setBounds(420,5,50,25);
		generalPanel.add(apiLabel);
		apiPanel.setBounds(5,35,900,25);
		generalPanel.add(apiPanel);
		sensorsLabel.setBounds(405,90,80,25);
		generalPanel.add(sensorsLabel);
		sensorsPanel.setBounds(200,120,500,25);
		generalPanel.add(sensorsPanel);
		animationLabel.setBounds(360,200,179,299);
		generalPanel.add(animationLabel);

		
		Timer timer = new Timer ();
		TimerTask hourlyTask = new TimerTask () {
		    @Override
		    public void run () {
		    	WeatherAPI weather = new WeatherAPI(); 
		    	temperatureField.setText(String.format("%.1f", weather.getTemperature()) + "C");
				humidityField.setText(Long.toString(weather.getHumidity())+"%");
				if(weather.getWindspeedDouble() != null)
					beaufortField.setText(String.format("%.1f", weather.getWindspeedDouble()));
				if(weather.getWindspeedLong() != null)
					beaufortField.setText(String.format("%.1f", weather.getWindspeedLong()));
				weatherDescField.setText(weather.getWeatherDescription());
				weatherCondField.setText(weather.getWeatherCondition());
		    }
		};
		// schedule the task to run starting now and then every hour...
		timer.schedule (hourlyTask, 0l, 1000*60*2);
		
		
		this.setContentPane(generalPanel);
		this.setVisible(true);
		this.setTitle("Data Page");
		this.setSize(930, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
