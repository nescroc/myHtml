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
	//������Ǯ. �����带 �����ϸ� �����ϰ� newCachedThreadPool�� �����߽��ϴ�
	private ExecutorService threadPool;
	//��������
	private ServerSocket serverSocket;
	//��Ʈ
	private final int port = 5000;
	//TextArea
	public static TextArea ta;
	//���� ������ ����� ��� Arraylist
	public static ArrayList<ClientThread> clientList = new ArrayList<ClientThread>();
	//���� while ���ǹ��� ���� boolean
	private boolean serverRun = false;
	
	//���� ������ �����Եɽ� �۵��ϴ� �κ�
	private void startServer(int port) {
		try {
			serverSocket = new ServerSocket(port);
			threadPool = Executors.newCachedThreadPool();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Ŭ���̾�Ʈ���� ���� ��û���� ��⸦ �� ���ʺ�
		Runnable rn = new Runnable() {
			@Override
			public void run() {
				while (!serverRun) {
					try {
						//�������� accept�� ���� ������ clientThread�� ��� ���� 
						Socket socket = serverSocket.accept();	
						//���� Ŭ���̾�Ʈ �ؽ�Ʈâ�� �����ߴٴ� ����� ������
						ta.appendText(socket.getInetAddress().toString()+" : ����\n");
						
						ClientThread client = new ClientThread(socket);
						//������Ͽ� ����
						clientList.add(client);
						
						threadPool.submit(client);
					} catch (Exception e) {
						e.printStackTrace();
						serverRun = true;
					}
				}
			}
		};
		//���ʺ� ����
		threadPool.submit(rn);
	}
	
	//���̾ƿ����
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		ta = new TextArea();
		ta.setEditable(false);
		root.setCenter(ta);
		
		Button button = new Button();
		button.setPrefWidth(400);
		button.setPrefHeight(30);
		button.setText("�����ϱ�");
		root.setBottom(button);
		
		Scene scene = new Scene(root, 400, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("���� Ŭ���̾�Ʈ");
		primaryStage.show();
		
		button.setOnAction(e -> {
			if(button.getText().equals("�����ϱ�")) {
				serverRun=false;
				startServer(port);
				button.setText("�����ϱ�");
				ta.appendText("������ �����մϴ�..\n");
			}
			else {
				button.setText("�����ϱ�");
				serverClose();
				Platform.runLater(()->{
					ta.appendText("���� ����");				
				});
			}
		});
	}
	//���� ���� ��ư �����κ�
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
