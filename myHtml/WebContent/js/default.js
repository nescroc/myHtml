/**
 * 
 */

function popWin(mypage, myname, w, h, scroll){
	var winl = (screen.width-w)/2;
	var wint = (screen.height-h)/2;
	winprops = 'height='+h+',width='+w+',top='+wint+',left='+winl+',scrollbars='+scroll+',resizeable'
	win = window.open(mypage,myname,winprops)	
}