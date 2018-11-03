package org.ce1103.gos.util;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="SortedList")
@XmlAccessorType (XmlAccessType.FIELD)
public class SortedList {

	@XmlAttribute(name="listToSort")
	public String listToSort;

	public String getListToSort() {
		return listToSort;
	}

	public void setListToSort(String listToSort) {
		this.listToSort = listToSort;
	}

}