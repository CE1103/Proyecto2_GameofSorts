package org.ce1103.gos.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.WebServiceProvider;

public class Client {
	
	private void postList(String strList) throws IOException {
		String url = "http://desktop-6f6k4bq:9080/Proyecto2_GameofSorts_WebService/rest/sort/postList";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "text/plain");
		String urlParameters = strList;
		
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		
		int responseCode = con.getResponseCode();
		System.out.println("Response code: " + responseCode);
	}

	private String getSortedList(int sort) throws IOException, JAXBException {
		URL url = null;
		if (sort == 0) {
			url = new URL("http://desktop-6f6k4bq:9080/Proyecto2_GameofSorts_WebService/rest/sort/selectionSort");
		} else if (sort == 1) {
			url = new URL("http://desktop-6f6k4bq:9080/Proyecto2_GameofSorts_WebService/rest/sort/insertionSort");
		} else if (sort == 2) {
			url = new URL("http://desktop-6f6k4bq:9080/Proyecto2_GameofSorts_WebService/rest/sort/quickSort");
		}
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		int status = con.getResponseCode();
		InputStreamReader reader = new InputStreamReader(con.getInputStream());
		char[] buffer = new char[1024];
		int leidos = 0;
		StringBuilder builder = new StringBuilder();
		while ((leidos = reader.read(buffer)) > 0) {
			builder.append(new String(buffer, 0, leidos));
			System.out.println(status);
		}
		JAXBContext jaxbContext = JAXBContext.newInstance(SortedList.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

	    StringReader reader1 = new StringReader(builder.toString());
	    SortedList sortedList = (SortedList) jaxbUnmarshaller.unmarshal(reader1);

		return sortedList.listToSort;
	}
	
	public static String clientSort(int sort, String strList) throws IOException, JAXBException { //sorts (0=SelectionSort, 1=InsertionSort, 2=QuickSort)
		Client client = new Client();
		client.postList(strList);
		return client.getSortedList(sort);
	}
}