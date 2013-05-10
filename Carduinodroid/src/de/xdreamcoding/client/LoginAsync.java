package de.xdreamcoding.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginAsync {

	void connectIP(String ip, AsyncCallback<String> callback);

	void loginUser(String name, String pwhash, AsyncCallback<String> callback);

}
