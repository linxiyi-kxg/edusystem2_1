package serve;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
	private String filepath;
	public ReadProperties(String filepath) {
		this.filepath = filepath;
	}
	
	public String readerProperties(String key) throws IOException {
		InputStream in = ReadProperties.class.getClassLoader().getResourceAsStream(filepath);
		Properties properties = new Properties();
		properties.load(in);
		String para = properties.getProperty(key);
		in.close();
		in = null;
		return para;
	}
}
