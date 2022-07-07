package serve;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;



public class ClientThread {
	public void Client(String i, ArrayList<String> list) {
		try {
			//实例化对象用于读取配置文件内容
			ClientThread clientThread = new ClientThread();
			
			//1.创建客户端Socket，指定服务器地址和端口
			//如果不是本机链接，则将localhost改为链接服务器的IP
			//从配置文件中获取IP和端口
			String ip = clientThread.readProperties("IP");
			String port = clientThread.readProperties("PORT");
			
            Socket socket=new Socket(ip, Integer.parseInt(port));
            //2.获取输出流，向服务器端发送信息
            OutputStream os=socket.getOutputStream();//字节输出流
            InputStream is=socket.getInputStream();//字节输入流

            //创建输出对象流，将对象输出
            ObjectOutputStream oos = new ObjectOutputStream(os);
            ObjectInputStream ois = new ObjectInputStream(is);
            
            oos.writeObject(i);
        	oos.writeObject(list);
        	ArrayList<Object> object =  (ArrayList<Object>) ois.readObject();
        	System.out.println(object);
        	
        	socket.close();
		} catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public String readProperties(String key) throws IOException {
		
		String filepath = "serve/socket.properties";
		InputStream in = ClientThread.class.getClassLoader().getResourceAsStream(filepath);
		Properties properties = new Properties();
		properties.load(in);
		String answer = properties.getProperty(key);
		in.close();
		in = null;

		return answer;
	}
}
