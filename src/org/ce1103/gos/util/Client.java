package org.ce1103.gos.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Client {

	public static String getSortedList(int sort, String strList) throws IOException, JAXBException {
		URL url = null;
		if (sort == 0) {
			url = new URL("http://desktop-6f6k4bq:9080/Proyecto2_GameofSorts_WebService/rest/sort/selectionSort?value=" + strList);
		} else if (sort == 1) {
			url = new URL("http://desktop-6f6k4bq:9080/Proyecto2_GameofSorts_WebService/rest/sort/insertionSort?value=" + strList);
		} else if (sort == 2) {
			url = new URL("http://desktop-6f6k4bq:9080/Proyecto2_GameofSorts_WebService/rest/sort/quickSort?value=" + strList);
		}
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		System.out.println("1");
		con.setRequestMethod("GET");
		int status = con.getResponseCode();
		System.out.println("2");
		InputStreamReader reader = new InputStreamReader(con.getInputStream());
		char[] buffer = new char[1024];
		int leidos = 0;
		StringBuilder builder = new StringBuilder();
		System.out.println("3");
		while ((leidos = reader.read(buffer)) > 0) {
			builder.append(new String(buffer, 0, leidos));
//			String leido = new String(buffer, 0, leidos);
			System.out.println(status);
		}
		System.out.println("4");
		System.out.println(builder.toString());
		JAXBContext jaxbContext = JAXBContext.newInstance(SortedList.class);
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

	    StringReader reader1 = new StringReader(builder.toString());
	    SortedList sortedList = (SortedList) jaxbUnmarshaller.unmarshal(reader1);

		return sortedList.listToSort;
	}
	
	public static void main(String[] args) throws IOException, JAXBException {
		System.out.println(Client.getSortedList(0, "[1,2,14,3]"));
	}

}
