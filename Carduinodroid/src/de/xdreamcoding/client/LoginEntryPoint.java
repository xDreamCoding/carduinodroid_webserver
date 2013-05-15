package de.xdreamcoding.client;

import com.google.gwt.core.client.EntryPoint;
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

import de.xdreamcoding.shared.User;

public class LoginEntryPoint implements EntryPoint {

	private final LoginAsync LoginService = Login.Util.getInstance();
	private Button loginButton, connectButton;
	private TextBox loginField, ipField;
	private PasswordTextBox passwordField;
	private Label errorLabel;

	private static User user;

	@Override
	public void onModuleLoad() {

		loginButton = new Button("Login");
		loginButton.addStyleName("submit");
		loginField = new TextBox();
		loginField.addStyleName("text");
		passwordField = new PasswordTextBox();
		passwordField.addStyleName("text");
		connectButton = new Button("Login");
		connectButton.addStyleName("submit");
		ipField = new TextBox();
		ipField.addStyleName("text");
		errorLabel = new Label();

		RootPanel.get("loginFieldContainer").add(loginField);
		RootPanel.get("passwordFieldContainer").add(passwordField);
		RootPanel.get("sendButtonContainer").add(loginButton);
		RootPanel.get("ipFieldContainer").add(ipField);
		RootPanel.get("connectButtonContainer").add(connectButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);
		

		loginButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				sendLogin();
			}	
		});
		KeyUpHandler keyup = new KeyUpHandler() {
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendLogin();
				}
			}
		};
		passwordField.addKeyUpHandler(keyup);
		loginField.addKeyUpHandler(keyup);

		connectButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				sendIP();
			}	
		});
		ipField.addKeyUpHandler(new KeyUpHandler() {
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendIP();
				}
			}
		});
		
		
	}

	void sendLogin() {
		errorLabel.setText("");
		LoginService.loginUser(loginField.getText(), passwordField.getText(),
				new AsyncCallback<User>() {
					public void onFailure(Throwable caught) {
						errorLabel.setText(caught.getMessage());
					}

					@Override
					public void onSuccess(User result) {
						if (result != null) {
							user = result;
							errorLabel.setText("Hello " + result.getName()
									+ " you sucessfully logged in!");
						} else {
							errorLabel.setText("Login invalid!");
						}
					}
				});
	}

	void sendIP() {
		errorLabel.setText("");
		LoginService.connectIP(ipField.getText(),
				new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				errorLabel.setText(caught.getMessage());

			}

			public void onSuccess(String result) {
				errorLabel.setText(result);
			}
		});
	}
}
