package client;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.tomcat.util.http.fileupload.IOUtils;

public class URLClient {

	public static void main(String[] args) throws Exception {

		URL url = new URL("http://127.0.0.1:8080/chat");

		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

//		urlConnection.setReadTimeout((int) TimeUnit.SECONDS.toMillis(60));
//		urlConnection.setConnectTimeout((int) TimeUnit.SECONDS.toMillis(60));
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);

//		urlConnection.connect();

		OutputStream outputStream = urlConnection.getOutputStream();

		outputStream.write("消息测试-1".getBytes("UTF-8"));
		outputStream.flush();
		outputStream.write("消息测试-2".getBytes("UTF-8"));
		outputStream.write("消息测试-3".getBytes("UTF-8"));
		outputStream.flush();

		InputStream inputStream = urlConnection.getInputStream();

		ByteArrayOutputStream bufferStream = new ByteArrayOutputStream(64);

		IOUtils.copyLarge(inputStream, bufferStream);
		
		System.out.println(bufferStream.toString("UTF-8"));

		outputStream.close();
		inputStream.close();
		
		urlConnection.disconnect();
		

	}
}
