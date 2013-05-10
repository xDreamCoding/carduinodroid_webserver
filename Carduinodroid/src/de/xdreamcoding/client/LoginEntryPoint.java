package de.xdreamcoding.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class LoginEntryPoint implements EntryPoint {
		
	private final LoginAsync LoginService = Login.Util.getInstance();
	Button sendButton;
	TextBox loginField;
	PasswordTextBox passwordField;
	Label errorLabel;

	@Override
	public void onModuleLoad() {
		
		sendButton = new Button("Login");
		sendButton.addStyleName("submit");
		loginField = new TextBox();
		loginField.addStyleName("text");
		passwordField = new PasswordTextBox();
		passwordField.addStyleName("text");
		errorLabel = new Label();
		
		RootPanel.get("loginFieldContainer").add(loginField);
		RootPanel.get("passwordFieldContainer").add(passwordField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);
		
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendLoginToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendLoginToServer();
				}
			}
		}
		
			
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		passwordField.addKeyUpHandler(handler);
	}

	void sendLoginToServer() {
		LoginService.loginUser(loginField.getText(), passwordField.getText(),
			new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {
					errorLabel.setText(caught.getMessage());
				}

				public void onSuccess(String b) {
				}
			});
		}
}

