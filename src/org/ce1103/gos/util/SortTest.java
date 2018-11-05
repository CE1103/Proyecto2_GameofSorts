package org.ce1103.gos.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.junit.jupiter.api.Test;

class SortTest {

	@Test
	void testClientSort0() throws IOException, JAXBException {
		String result = Client.clientSort(0, "[13,6,123,9,54,7,1]");
		assertEquals("[1, 6, 7, 9, 13, 54, 123]", result);
	}
	@Test
	void testClientSort1() throws IOException, JAXBException {
		String result = Client.clientSort(1, "[13,6,123,9,54,7,1]");
		assertEquals("[1, 6, 7, 9, 13, 54, 123]", result);
	}
	@Test
	void testClientSort2() throws IOException, JAXBException {
		String result = Client.clientSort(2, "[13,6,123,9,54,7,1]");
		assertEquals("[1, 6, 7, 9, 13, 54, 123]", result);
	}
	@Test
	void testClientSort3() throws IOException, JAXBException {
		String result = Client.clientSort(0, "[]");
		assertEquals("[]", result);
	}
	@Test
	void testClientSort4() throws IOException, JAXBException {
		String result = Client.clientSort(1, "[]");
		assertEquals("[]", result);
	}
	@Test
	void testClientSort5() throws IOException, JAXBException {
		String result = Client.clientSort(2, "[]");
		assertEquals("[]", result);
	}
	@Test
	void testClientSort6() throws IOException, JAXBException {
		String result = Client.clientSort(1, "");
		assertEquals("", result);
	}

}
