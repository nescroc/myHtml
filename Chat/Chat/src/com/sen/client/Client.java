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
	
	//클라이언트 소켓
	private Socket socket;
	//출력
	private BufferedWriter bw;
	//입력
	private BufferedReader br;
	//텍스트 에이어리아
	private TextArea ta;
	//벗튼
	private Button button;
	//시작 버튼을 누를시에 동작할 액션
	private void startClient(String ip, int port) {
		try {
			socket = new Socket(ip, port);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			//서버로 부터 입력받는 역할을 하는 메서드
			receive();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//서버로 입력을 담당할 쓰레드를 포함한 메서드
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
							ta.appendText("연결이 종료 되었습니다.\n");						
						});
						Platform.runLater(() -> {
							button.setText("시작");
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
	
	//전송 부분
	private void send(String message) {		
		try {	
			bw.write(message);		
			bw.flush();		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//fx 초기화
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
		button.setText("시작");
		TextField tf = new TextField();
		tf.setPrefWidth(260);
		tf.setPrefHeight(30);
		Button sendbutton = new Button();
		sendbutton.setPrefWidth(70);
		sendbutton.setPrefHeight(30);
		sendbutton.setText("전송");
		hbox.getChildren().addAll(button, tf, sendbutton);
		root.setBottom(hbox);

		Scene scene = new Scene(root, 400, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("사용자 클라이언트");
		primaryStage.show();
		
		button.setOnAction(e -> {
			if (button.getText().equals("시작")) {
				String ip = "";
				try {
					ip = InetAddress.getLocalHost().getHostAddress();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				startClient(ip, 5000);
				Platform.runLater(() -> {
					button.setText("종료");
				});

			} else if(button.getText().equals("종료")) {
				connectionClose();
				Platform.runLater(() -> {
					button.setText("시작");
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
	//종료 부분
	private void connectionClose() {
		System.exit(0);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
