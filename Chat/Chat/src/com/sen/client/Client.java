package com.sen.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Client extends Application {
	
	//Ŭ���̾�Ʈ ����
	private Socket socket;
	//���
	private BufferedWriter bw;
	//�Է�
	private BufferedReader br;
	//�ؽ�Ʈ ���̾��
	private TextArea ta;
	//��ư
	private Button button;
	//���� ��ư�� �����ÿ� ������ �׼�
	private void startClient(String ip, int port) {
		try {
			socket = new Socket(ip, port);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			//������ ���� �Է¹޴� ������ �ϴ� �޼���
			receive();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//������ �Է��� ����� �����带 ������ �޼���
	private void receive() {
		Thread th = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						String message = br.readLine();
						Platform.runLater(() -> {
							ta.appendText(message + "\n");
						});
					} catch (Exception e) {
						Platform.runLater(()->{
							ta.appendText("������ ���� �Ǿ����ϴ�.\n");						
						});
						Platform.runLater(() -> {
							button.setText("����");
						});
						connectionClose();
						e.printStackTrace();					
						break;
					}
				}
			}
		};
		th.start();
	}
	
	//���� �κ�
	private void send(String message) {		
		try {	
			bw.write(message);		
			bw.flush();		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//fx �ʱ�ȭ
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(5));

		ta = new TextArea();
		ta.setEditable(false);
		root.setCenter(ta);

		HBox hbox = new HBox();
		button = new Button();
		button.setPrefWidth(70);
		button.setPrefHeight(30);
		button.setText("����");
		TextField tf = new TextField();
		tf.setPrefWidth(260);
		tf.setPrefHeight(30);
		Button sendbutton = new Button();
		sendbutton.setPrefWidth(70);
		sendbutton.setPrefHeight(30);
		sendbutton.setText("����");
		hbox.getChildren().addAll(button, tf, sendbutton);
		root.setBottom(hbox);

		Scene scene = new Scene(root, 400, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("����� Ŭ���̾�Ʈ");
		primaryStage.show();
		
		button.setOnAction(e -> {
			if (button.getText().equals("����")) {
				String ip = "";
				try {
					ip = InetAddress.getLocalHost().getHostAddress();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				startClient(ip, 5000);
				Platform.runLater(() -> {
					button.setText("����");
				});

			} else if(button.getText().equals("����")) {
				connectionClose();
				Platform.runLater(() -> {
					button.setText("����");
				});
			}
		});
		tf.setOnAction(e -> {
			send(socket.getInetAddress().toString() + " : " + tf.getText() + "\n");
			Platform.runLater(() -> {
				tf.setText("");
			});
		});
		sendbutton.setOnAction(e -> {
			send(socket.getInetAddress().toString() + " : " + tf.getText() + "\n");
			Platform.runLater(() -> {
				tf.setText("");
			});
		});
	}
	//���� �κ�
	private void connectionClose() {
		System.exit(0);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
