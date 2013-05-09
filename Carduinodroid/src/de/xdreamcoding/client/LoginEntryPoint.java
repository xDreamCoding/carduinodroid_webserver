package de.xdreamcoding.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class LoginEntryPoint implements EntryPoint {
		
	private final LoginAsync LoginService = Login.Util.getInstance();

	@Override
	public void onModuleLoad() {
		
		Button sendButton = new Button("Login");
		sendButton.addStyleName("submit");
		TextBox loginField = new TextBox();
		loginField.addStyleName("text");
		PasswordTextBox passwordField = new PasswordTextBox();
		passwordField.addStyleName("text");
		Label errorLabel = new Label();
		
		RootPanel.get("loginFieldContainer").add(loginField);
		RootPanel.get("passwordFieldContainer").add(passwordField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);
	}

}
