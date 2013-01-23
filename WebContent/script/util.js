function checkNull(src, target, message) {
	if(src.value=="" || src.value==null) {
		src.setAttribute("class", "error_box");
		target.innerHTML = message;
		return true;
	} else {
		src.setAttribute("class", "");
		target.innerHTML = "";
		return false;
	}		
}
	
function isEqual(src1, src2, target, message) {
	if(src1.value!=src2.value) {
		src2.setAttribute("class", "error_box");
		target.innerHTML = message;
		return false;
	} else {
		src2.setAttribute("class", "");
		target.innerHTML = "";
		return true;
	} 
}