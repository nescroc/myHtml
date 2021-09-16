
var xhrObject;//XMLHttpRequest객체를 저장할 변수를 전역 변수로 선언
function createXHR(){
	//XMLHttpRequest객체를 생성하는 메소드
	if(window.ActiveXObject){
		//웹브라우저가 IE5.0, IE6.0인 경우
		xhrObject = new ActiveXObject("Microsoft.XMLHTTP");
		//XMLHttpRequest객체 생성
	}else if(window.XMLHttpRequest){
		//웹 브러우저가 IE7.0~,파이어폭스,사파리,오페라,크롬
		xhrObject = new XMLHttpRequest();
		//XMLHttpRequest객체 생성
	}
}

function startMethod(){//클라이언트로부터 이벤트가 발생 시 실행 메서드
	createXHR();//createXHR()메서드 호출
	xhrObject.onreadystatechange=resultProcess;
		//응답 결과를 처리 메서드인 resultProcess() 메서드 설정
	xhrObject.open("Get","ajaxEx02.xml","true")	;//서버의 요청 설정
	xhrObject.send(null);//서버로 요청을 보냄
}
function processXML(){//XML 문서 객체 구조 처리 메서드
	var xmlDoc = xhrObject.responseXML;
				//서버로부터의 응답을 XML문서 객체로 받음
	var subject = "";
	var trTag = "";
	var tdTag = "";
	var arr=new Array();
	var subjects = xmlDoc.getElementsByTagName("subject");
	//응답받은 XML문서 객체에서 subject엘리먼트를 모두 찾아서 배열로 리턴
	for(var i =0; i<subjects.length;i++){
		//Subject엘리먼트 개수만큼 반복
		trTag = document.createElement("tr");//tr엘리먼트 생성
		subject = subjects.item(i);
		//subject엘리먼트 배열로부터 subject엘리먼트를
		//하나 받아서 subject 변수에 저장
		var child = subject.childNodes;
		//subject엘리먼트의 자식 엘리먼트를 배열로 리턴
		for(var j = 0;j<child.length;j++){
			if(child.item(j).nodeType==1){
				arr.push(child.item(j).firstChild.nodeValue);
			}
		}		
		for(var j = 0;j<child.length;j++){
			//subject 엘리먼트의 자식 엘리먼트 개수만큼 반복			
			tdTag = document.createElement("td");
			//td 엘리먼트 생성
			var text = document.createTextNode(arr[j]);
			//텍스트 엘리먼트 text를 생성
			tdTag.appendChild(text);
			//td엘리먼트에 텍스트 엘리먼트 text를 자식 엘리먼트로 추가
			trTag.appendChild(tdTag);
			//tr엘리먼트에 td 엘리먼트를 자식 엘리먼트로 추가
		}
		document.getElementById("resultDisplay").appendChild(trTag);
		//id 속성의 값이 "resultDisplay"인 엘리먼트에 tr 엘리먼트를 자식 엘리먼트로 추가
	}
}
function resultProcess(){//응답 결과가 오면 자동으로 실행
	if(xhrObject.readyState==4){
		//요청객체의 상태가 모든 데이터를 받을 수 있는 상태로 완료된 경우
		if(xhrObject.status==200){
			//서버로부터 응답받는 HTTP상태가 정상인 경우 수행
			processXML();
			//XML 문서 객체 구조 처리 메소드 호출
		}
	}
	
}

var xhrObject;//XMLHttpRequest객체를 저장할 변수를 전역 변수로 선언
function createXHR(){
	//XMLHttpRequest객체를 생성하는 메소드
	if(window.ActiveXObject){
		//웹브라우저가 IE5.0, IE6.0인 경우
		xhrObject = new ActiveXObject("Microsoft.XMLHTTP");
		//XMLHttpRequest객체 생성
	}else if(window.XMLHttpRequest){
		//웹 브러우저가 IE7.0~,파이어폭스,사파리,오페라,크롬
		xhrObject = new XMLHttpRequest();
		//XMLHttpRequest객체 생성
	}
}

function startMethod(){//클라이언트로부터 이벤트가 발생 시 실행 메서드
	createXHR();//createXHR()메서드 호출
	xhrObject.onreadystatechange=resultProcess;
		//응답 결과를 처리 메서드인 resultProcess() 메서드 설정
	xhrObject.open("Get","ajaxEx02.xml","true")	;//서버의 요청 설정
	xhrObject.send(null);//서버로 요청을 보냄
}
function processXML(){//XML 문서 객체 구조 처리 메서드
	var xmlDoc = xhrObject.responseXML;
				//서버로부터의 응답을 XML문서 객체로 받음
	var subject = "";
	var trTag = "";
	var tdTag = "";
	var arr=new Array();
	var subjects = xmlDoc.getElementsByTagName("subject");
	//응답받은 XML문서 객체에서 subject엘리먼트를 모두 찾아서 배열로 리턴
	for(var i =0; i<subjects.length;i++){
		//Subject엘리먼트 개수만큼 반복
		trTag = document.createElement("tr");//tr엘리먼트 생성
		subject = subjects.item(i);
		//subject엘리먼트 배열로부터 subject엘리먼트를
		//하나 받아서 subject 변수에 저장
		var child = subject.childNodes;
		//subject엘리먼트의 자식 엘리먼트를 배열로 리턴
		for(var j = 0;j<child.length;j++){
			if(child.item(j).nodeType==1){
				arr.push(child.item(j).firstChild.nodeValue);
			}
		}		
		for(var j = 0;j<child.length;j++){
			//subject 엘리먼트의 자식 엘리먼트 개수만큼 반복			
			tdTag = document.createElement("td");
			//td 엘리먼트 생성
			var text = document.createTextNode(arr[j]);
			//텍스트 엘리먼트 text를 생성
			tdTag.appendChild(text);
			//td엘리먼트에 텍스트 엘리먼트 text를 자식 엘리먼트로 추가
			trTag.appendChild(tdTag);
			//tr엘리먼트에 td 엘리먼트를 자식 엘리먼트로 추가
		}
		document.getElementById("resultDisplay").appendChild(trTag);
		//id 속성의 값이 "resultDisplay"인 엘리먼트에 tr 엘리먼트를 자식 엘리먼트로 추가
		arr=new Array();
	}
}
function resultProcess(){//응답 결과가 오면 자동으로 실행
	if(xhrObject.readyState==4){
		//요청객체의 상태가 모든 데이터를 받을 수 있는 상태로 완료된 경우
		if(xhrObject.status==200){
			//서버로부터 응답받는 HTTP상태가 정상인 경우 수행
			processXML();
			//XML 문서 객체 구조 처리 메소드 호출
		}
	}
	
}