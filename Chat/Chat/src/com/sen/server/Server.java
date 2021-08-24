package com.sen.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Server extends Application {
	//쓰레드풀. 쓰레드를 관리하며 간단하게 newCachedThreadPool로 구현했습니다
	private ExecutorService threadPool;
	//서버소켓
	private ServerSocket serverSocket;
	//포트
	private final int port = 5000;
	//TextArea
	public static TextArea ta;
	//유저 쓰레드 목록을 담는 Arraylist
	public static ArrayList<ClientThread> clientList = new ArrayList<ClientThread>();
	//서버 while 조건문에 들어가는 boolean
	private boolean serverRun = false;
	
	//서버 시작을 누르게될시 작동하는 부분
	private void startServer(int port) {
		try {
			serverSocket = new ServerSocket(port);
			threadPool = Executors.newCachedThreadPool();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//클라이언트에서 연결 요청받을 대기를 할 러너블
		Runnable rn = new Runnable() {
			@Override
			public void run() {
				while (!serverRun) {
					try {
						//서버소켓 accept로 받은 소켓을 clientThread에 담아 실행 
						Socket socket = serverSocket.accept();	
						//서버 클라이언트 텍스트창에 접속했다는 기록을 남겨줌
						ta.appendText(socket.getInetAddress().toString()+" : 접속\n");
						
						ClientThread client = new ClientThread(socket);
						//유저목록에 담음
						clientList.add(client);
						
						threadPool.submit(client);
					} catch (Exception e) {
						e.printStackTrace();
						serverRun = true;
					}
				}
			}
		};
		//러너블 실행
		threadPool.submit(rn);
	}
	
	//레이아웃담당
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		ta = new TextArea();
		ta.setEditable(false);
		root.setCenter(ta);
		
		Button button = new Button();
		button.setPrefWidth(400);
		button.setPrefHeight(30);
		button.setText("시작하기");
		root.setBottom(button);
		
		Scene scene = new Scene(root, 400, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("서버 클라이언트");
		primaryStage.show();
		
		button.setOnAction(e -> {
			if(button.getText().equals("시작하기")) {
				serverRun=false;
				startServer(port);
				button.setText("종료하기");
				ta.appendText("서버를 시작합니다..\n");
			}
			else {
				button.setText("시작하기");
				serverClose();
				Platform.runLater(()->{
					ta.appendText("서버 종료");				
				});
			}
		});
	}
	//서버 종료 버튼 구현부분
	private void serverClose() {		
		serverRun = true;
		try {			
			serverSocket.close();			
		} catch (IOException e) {}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
