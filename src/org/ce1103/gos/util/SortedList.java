package org.ce1103.gos.util;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SortedList {
	
	public String listToSort;

	public String getListToSort() {
		return listToSort;
	}

	@XmlElement
	public void setListToSort(String listToSort) {
		this.listToSort = listToSort;
	}

}