package Code;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DataPage extends JFrame {
	
	private JPanel generalPanel;
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
	
	public DataPage()
	{
		generalPanel = new JPanel();
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
		
		animationLabel = new JLabel();
		animationLabel.setIcon(new ImageIcon("C:\\Users\\User\\eclipse-workspace\\RESManager\\src\\Photos\\wind_turbine.gif"));
		
		generalPanel.add(temperatureLabel);
		generalPanel.add(temperatureField);
		generalPanel.add(humidityLabel);
		generalPanel.add(humidityField);
		generalPanel.add(beaufortLabel);
		generalPanel.add(beaufortField);
		generalPanel.add(weatherCondLabel);
		generalPanel.add(weatherCondField);
		generalPanel.add(weatherDescLabel);
		generalPanel.add(weatherDescField);
		generalPanel.add(animationLabel);
		
		WeatherAPI weather = new WeatherAPI();
		
		temperatureField.setText(String.format("%.1f", weather.getTemperature()) + "C");
		humidityField.setText(Long.toString(weather.getHumidity()));
		beaufortField.setText(Long.toString(weather.getWindspeed())); //it was Double.
		weatherDescField.setText(weather.getWeatherDescription());
		weatherCondField.setText(weather.getWeatherCondition());
		
		this.setContentPane(generalPanel);
		this.setVisible(true);
		this.setTitle("Login");
		this.setSize(360, 450);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
