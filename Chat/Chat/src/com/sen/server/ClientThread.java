package com.sen.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.stream.Stream;

import javafx.application.Platform;

public class ClientThread extends Thread {

	private BufferedWriter bw;
	private BufferedReader br;
	boolean whileRun = false;
	
	//Server���� �޾ƿ� socket�� �����ڿ� ���Թ���
	public ClientThread(Socket socket) {
		try {			
			//���� ���Ͽ����� ����� ��Ʈ���� �� �����忡 ����
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Ŭ���̾�Ʈ������ ���۵� �����͸� �޾Ƽ� �ٸ� Ŭ���̾�Ʈ�� �����ϴ� �κ�. ���ܰ� ���� �ݺ��� Ż��, ���������� ��������Ʈ���� ����
	@Override
	public void run() {
		whileRun = false;
		while (!whileRun) {
			try {
				String message = br.readLine();
				broadCast(message);
			} catch (Exception e) {
				e.printStackTrace();				
				whileRun = true;
				Server.clientList.remove(this);
			}
		}
	}
	//���� ������ ������ �����鿡�� ������ ó���ϴ� �޼���. stream�� ���� ����ó���� �õ��غý��ϴ�
	private void broadCast(String message) {
		//���޹��� ������ ���� â�� ���� �κ�
		Platform.runLater(()->{
			Server.ta.appendText(message+"\n");
		});
		//��������Ʈ�� stream�� ���� ����ó�� �ϴºκ�
		Stream<ClientThread> str = Server.clientList.parallelStream();
		str.forEach(ct -> {
			try {
				ct.bw.write(message + "\n");
				ct.bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
				whileRun = true;
				Server.clientList.remove(this);
			}
		});
		
	}
}
