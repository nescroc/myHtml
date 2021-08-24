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
	
	//Server에서 받아온 socket을 생성자에 주입받음
	public ClientThread(Socket socket) {
		try {			
			//받은 소켓에서의 입출력 스트림을 이 쓰레드에 받음
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//클라이언트에서의 전송된 데이터를 받아서 다른 클라이언트에 전송하는 부분. 예외가 날시 반복문 탈출, 서버에서의 유저리스트에서 삭제
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
	//현재 서버에 접속한 유저들에게 전송을 처리하는 메서드. stream을 통해 병렬처리를 시도해봤습니다
	private void broadCast(String message) {
		//전달받은 내용을 서버 창에 띄우는 부분
		Platform.runLater(()->{
			Server.ta.appendText(message+"\n");
		});
		//서버리스트를 stream을 통해 병렬처리 하는부분
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
