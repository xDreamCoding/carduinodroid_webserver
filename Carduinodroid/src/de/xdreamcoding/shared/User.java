package de.xdreamcoding.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class User implements IsSerializable{
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
