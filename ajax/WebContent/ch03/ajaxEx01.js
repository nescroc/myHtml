var xhrObject; //XMLHttpRequest객체를 저장할 변수를 전역변수로 선언
function createXHR(){//XMLHttpRequest객체를 생성하는 메서드
	if(window.ActiveXObject){//웹브러우저가 IE5.0, IE6.0인 경우
		xhrObject = new ActiveXObject("Microsoft.XMLHTTP");
	}else if(window.XMLHttpRequest){
		//웹 브러우저가  IE7.0이후, 파이어폭스, 사파리,오페라 등인 경우
		xhrObject = new XMLHttpRequest();
		//XMLHttpRequest객체 생성
	}
}
function startMethod(){//클라이언트로부터 이벤트가 발생 시 실행할 메서드
	createXHR();//createXHR()메소드 호출
	xhrObject.onreadystatechange = resultProcess;
		//응답 결과를 처리 메서드인 resultProcess()메서드 설정
	xhrObject.open("GET","ajaxEx01.xml","true");
	//서버 요청 설정 - ajaxEx01.xml을 요청할 준비
	xhrObject.send(null);//get방식이라 null, 로 서버 요청을 보냄.	
}
function resultProcess(){//응답 결과가 오면 자동으로 실행
	if(xhrObject.readyState==4){
		//요청 객체의 상태가 모든 데이터를 받을 수 있는 상태로 완료된 경우
		if(xhrObject.status==200){
			//서버로부터 응답받는 Http상태가 정상인 경우 수행
			document.getElementById("displayArea").innerHTML =
				xhrObject.responseText;
			//문자열로 이루어진
		}
	}
}
