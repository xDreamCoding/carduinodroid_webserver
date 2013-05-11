package de.xdreamcoding.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.xdreamcoding.shared.User;

public interface LoginAsync {

	void connectIP(String ip, AsyncCallback<String> callback);

	void loginUser(String name, String password, AsyncCallback<User> callback);

}
