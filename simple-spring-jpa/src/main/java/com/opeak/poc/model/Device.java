package com.opeak.poc.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Device {

	@Id
	private String mdi = UUID.randomUUID().toString();

	private String name;

	public String getMdi() {
		return mdi;
	}

	public String getName() {
		return name;
	}

	public void setMdi(String mdi) {
		this.mdi = mdi;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mdi == null) ? 0 : mdi.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Device other = (Device) obj;
		if (mdi == null) {
			if (other.mdi != null)
				return false;
		} else if (!mdi.equals(other.mdi))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
