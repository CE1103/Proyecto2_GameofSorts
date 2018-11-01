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
		con.setRequestMethod("GET");
		int status = con.getResponseCode();
		InputStreamReader reader = new InputStreamReader(con.getInputStream());
		char[] buffer = new char[1024];
		int leidos = 0;
		StringBuilder builder = new StringBuilder();
		while ((leidos = reader.read(buffer)) > 0) {
			builder.append(new String(buffer, 0, leidos));
//			String leido = new String(buffer, 0, leidos);
			System.out.println(status);
		}
		JAXBContext jaxbContext = JAXBContext.newInstance(SortedList.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader1 = new StringReader("xml string here");
		SortedList sortedList = (SortedList) unmarshaller.unmarshal(reader1);
		System.out.println(sortedList.listToSort);
		return sortedList.listToSort;
	}

}
