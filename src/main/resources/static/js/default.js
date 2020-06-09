// 쿠키 받기 함수
function getCookie(strname)
{
    var nameOfCookie = strname + "=";
    var x = 0;
    while ( x <= document.cookie.length )
    {
        var y = (x+nameOfCookie.length);
        if ( document.cookie.substring( x, y ) == nameOfCookie )
        {
            if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
            endOfCookie = document.cookie.length;
            return unescape( document.cookie.substring( y, endOfCookie ) );
        }
        x = document.cookie.indexOf( " ", x ) + 1;
        if ( x == 0 )
        break;
    }
    return "";
}

// 쿠키 설정 함수
function setCookie(name, value, expire)
{
    var tDate = new Date();
    tDate.setDate(tDate.getDate() + expire);
    document.cookie = name + "=" + escape(value) + ";path=/;expires="+tDate.toGMTString()+";"
}

//한글입력검사
function checkkorean(strvalue)
{
    var i = 0;
    var str = /[가-히]/;
    while(i < strvalue.length)
    {
        if(!str.test(strvalue.charAt(i)))
            return true;
        i++;
    }
    return false;
}

//아이디검사
function checkid(strvalue)
{
    var str = /[A-Za-z0-9_]/;
    var i = 0
    while(i < strvalue.length)
    {
        if(!str.test(strvalue.charAt(i)))
        {
            return true;
            break;
        }
        i++;
    }
    if(strvalue.length < 6)
        return true;
    return false;
}

//영문이름검사
function checkenglish(strvalue)
{
    var str = /[A-Za-z]/;
    var i = 0
    while(i < strvalue.length)
    {
        if(!str.test(strvalue.charAt(i)))
        {
            return true;
            break;
        }
        i++;
    }
    return false;
}

// 비밀번호 검사 (영문대소문자, 숫자 혼합)
function checkpwd(strvalue)
{
    var alphacnt = 0;
    var digitcnt = 0;
    var i = 0;
    var str = /[A-Za-z]/;
    var str1 = /[0-9]/;
    while(i < strvalue.length)
    {
        if(str.test(strvalue.charAt(i)))
            alphacnt++;
        if(str1.test(strvalue.charAt(i)))
            digitcnt++;
        i++;
    }
    if(alphacnt > 0 && digitcnt > 0 && (alphacnt + digitcnt == strvalue.length))
        return false;
    else
        return true;
    if(strvalue.length < 6)
        return true;
    return false;
}

// 비밀번호 검사 (대문자, 소문자, 숫자, 특수문자 조합3가지 & 9자리 이상)
function checkpwd2(strvalue)
{
    var alphauppercnt = 0;
    var alphalowercnt = 0;
    var digitcnt = 0;
    var specialcnt = 0;
    var i = 0;
    var str1 = /[A-Z]/;
    var str2 = /[a-z]/;
    var str3 = /[0-9]/;
    var str4 = /[^A-Za-z0-9]/;
    while(i < strvalue.length)
    {
        if(str1.test(strvalue.charAt(i)) && alphauppercnt == 0)
            alphauppercnt++;
        if(str2.test(strvalue.charAt(i)) && alphalowercnt == 0)
            alphalowercnt++;
        if(str3.test(strvalue.charAt(i)) && digitcnt == 0)
            digitcnt++;
        if(str4.test(strvalue.charAt(i)) && specialcnt == 0)
            specialcnt++;
        i++;
    }
    if((alphauppercnt + alphalowercnt + digitcnt + specialcnt) >= 3 && strvalue.length >= 9)
        return false;
    return true;
}

//비밀번호 검사 (소문자, 숫자 조합2가지 & 10자리 이상)
function checkpwd3(strvalue)
{
    var alphauppercnt = 0;
    var alphalowercnt = 0;
    var digitcnt = 0;
    var specialcnt = 0;
    var i = 0;
    var str1 = /[A-Z]/;
    var str2 = /[a-z]/;
    var str3 = /[0-9]/;
    var str4 = /[^A-Za-z0-9]/;
    while(i < strvalue.length)
    {
        if(str1.test(strvalue.charAt(i)) && alphauppercnt == 0)
            alphauppercnt++;
        if(str2.test(strvalue.charAt(i)) && alphalowercnt == 0)
            alphalowercnt++;
        if(str3.test(strvalue.charAt(i)) && digitcnt == 0)
            digitcnt++;
        if(str4.test(strvalue.charAt(i)) && specialcnt == 0)
            specialcnt++;
        i++;
    }
    
    if(strvalue.length < 10 || alphalowercnt < 1 || digitcnt < 1 || alphauppercnt > 0 || specialcnt > 0){
    	return true;
    } else {
    	return false;
    }
}

//주민등록번호검사
function checkresidentno(strvalue)
{
    var str = /\d{6}[1-4]\d{6}/;
    if(str.test(strvalue))
    {
        var check = new Array(13);
        var key = new Array(2,3,4,5,6,7,8,9,2,3,4,5);
        var sum = 0;
        for(var i=0; i<13; i++)
            check[i] = parseInt(strvalue.charAt(i),10);
        for(var i=0; i<12; i++)
            sum += check[i] * key[i];
        var rs = (11-(sum%11)) % 10
        if(rs != check[12])
            return true;
        return false;
    }
    return true;
}

//공백입력검사
function checkspace(strvalue)
{
    var str = /[ \n\r]/;
    var i = 0;
    var count = 0;
    while(i < strvalue.length)
    {
        if(str.test(strvalue.charAt(i)))
            count++;
        i++;
    }
    if(count == strvalue.length)
        return true;
    else
        return false;
}

//숫자입력검사
function checkdigit(strvalue)
{
    var str = /[-0-9]/;
    for(var i=0; i<strvalue.length; i++)
    {
        if(!str.test(strvalue.charAt(i)))
            return true;
    }
    return false;
}

//숫자입력및길이검사
function checkdigitsize(strvalue, size)
{
    var str = /[-0-9]/;
    for(var i=0; i<strvalue.length; i++)
    {
        if(!str.test(strvalue.charAt(i)))
            return true;
    }
    if(strvalue.length < size)
        return true;
    return false;
}

//메일주소검사
function checkmail(strvalue)
{
    var str = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
    if(!str.test(strvalue))
        return true;
    return false;
}

//전화번호검사
function checkphoneddd(strvalue)
{
    var str = /02|031|032|033|041|042|043|051|052|053|054|055|061|062|063|064|070/;
    if(str.test(strvalue))
        return false;
    else
        return true;
}

//핸드폰검사
function checkmobileddd(strvalue)
{
    var str = /010|011|012|015|016|017|018|019/;
    if(str.test(strvalue))
        return false;
    else
        return true;
}

//URL 검사
function checkurl(strvalue)
{
    var str = /http:\/\/([\w-]+\.)+[\w-]+(\/[\w- ./?%&=]*)?/;
    if(str.test(strvalue))
        return false;
    else
        return true;
}

//이미지 파일 검사
function checkimage(strvalue)
{
    var str = /gif|jpg|jpeg|png/;
    if(strvalue.lastIndexOf(".") > -1)
    {
        strvalue = strvalue.substr(strvalue.lastIndexOf(".") + 1,strvalue.length - strvalue.lastIndexOf(".") - 1).toLowerCase();
        if(str.test(strvalue))
            return false;
        else
            return true;
    }
    else
        return true;
}

//통화자리수표시 함수
function checkcurrency(intvalue)
{
    var strvalue = intvalue.toString();
    var commalocation = strvalue.length % 3;
    if(commalocation > 0)
    {
        var returnvalue = strvalue.substring(0,commalocation);
        if(strvalue.length > 3)
            returnvalue += ",";
    }
    else
        returnvalue = "";
    for(var i=commalocation; i < strvalue.length; i+=3)
    {
        returnvalue += strvalue.substring(i, i+3);
        if(i < strvalue.length - 3)
            returnvalue += ",";
    }
    return returnvalue;
}

//날짜 입력 검사 함수
function checkdate(value){
    var str = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/;
    if(str.test(value)){
        return false;
    }else{
        return true;
    }
}

//월 입력 검사 함수
function checkmonth(value){
    var str = /^(19|20)\d{2}-(0[1-9]|1[012])$/;
    if(str.test(value)){
        return false;
    }else{
        return true;
    }
}

//팝업창 닫기 함수
function sendclose()
{
    window.close(this);
}

//인수 있는 팝업창 닫기 함수
function sendpopupclose(strname,strdays)
{ 
    setCookie( strname, "done" , strdays); // 1=하룻동안 공지창 열지 않음
    window.close();
}

//뒤로 이동 함수
function sendback()
{
    window.history.back();
}

//취소 함수
function sendcancel(strmsg, strlocation)
{
    if(confirm(strmsg))
        window.location.href = strlocation;
}

// 엔터키를 받아 실행하는 함수
function sendkeypress(value)
{
    if(window.event.keyCode == 13)
    {
        var objbtn = document.getElementById(value);
        objbtn.click();
    }
}

//팝업창 오픈 함수 - 가운데 정렬
function sendopen(strurl, strid, intwidth, intheight, strresizable, strscrollbars, boolreplace)
{
    var top = window.screen.availHeight / 2 - intheight / 2;
    var left = window.screen.availWidth / 2 - intwidth / 2;
    var objwin = window.open(strurl,strid,"top=" + top + ",left=" + left + ",width=" + intwidth + ",height=" + intheight + ",location=no,menubar=no,resizable=" + strresizable + ",scrollbars=" + strscrollbars + ",status=no,titlebar=no,toolbar=no",boolreplace);
    
    return objwin;
}

//팝업창 오픈 함수 - 왼쪽 정렬
function sendpopupopen(strurl, strid, intleft, inttop, intwidth, intheight, strresizable, strscrollbars, boolreplace)
{
    var objwin = window.open(strurl,strid,"top=" + inttop + ",left=" + intleft + ",width=" + intwidth + ",height=" + intheight + ",location=no,menubar=no,resizable=" + strresizable + ",scrollbars=" + strscrollbars + ",status=no,titlebar=no,toolbar=no",boolreplace);
    
    return objwin;
}

function go_history_back(){
	history.back(-1);
}


/**
 * 팝업
 * @param url
 * @param name
 * @param option
 * @return
 */
function winPop(url, name, option){
	window.open(url,name,option);
}

/**
 * 우편번호검색
 */
   function goAddressSearch_pop(){
        winPop("/popup/search_address_pop.jsp","addr_pop","width=496,height=650,scrollbars=no")
}

/**
 * 기관검색
 */
function goOfficeSearch_pop(officename){
    winPop("/popup/search_office_pop.jsp?office_nm=" + officename ,"off_pop","width=500,height=700,scrollbars=yes")
}

function goOfficeSearch_pop(officename, officecd){
    winPop("/popup/search_office_pop.jsp?office_nm=" + officename +"&office_cd=" + officecd,"off_pop","width=500,height=700,scrollbars=yes")
}

function goschoolSearch_pop(_gubun ,_kind){
	winPop("/popup/search_school_pop.jsp?kind="+_kind+"&gubun="+_gubun,"off_pop","width=500,height=700,scrollbars=yes")
}

function goDuplicateCheckIdpop(_memberid){
    if(_memberid != ""){
        if(checkid(_memberid)){
            alert("아이디를 영문대소문자, 숫자, '_'로만 6자리 이상 입력하여 주십시오.");
            return;
        }
    }
    
    winPop("/popup/duplicate_check_id_pop.jsp?memberid="+_memberid,"off_pop","width=500,height=350,scrollbars=no")
}

//접수증출력
function onPrint1(lecture_id, seq, member_id){
	window.open("/printpage/receipt_certi.do?lecture_id=" + lecture_id + "&lecreceipt_num=" + seq + "&member_id=" + member_id,"pop_onPrint1","width=515,height=670,scrollbars=yes");
}

//영수증출력
function onPrint2(lecture_id, seq, member_id){
	window.open("/printpage/voucher_certi.do?lecture_id=" + lecture_id + "&lecreceipt_num=" + seq + "&member_id=" + member_id,"pop_onPrint2","width=515,height=680,scrollbars=yes");
}

//온라인감면신청서출력
function onPrint3(lecture_id, seq, member_id){
	window.open("/printpage/reductionReq_certi.do?lecture_id=" + lecture_id + "&lecreceipt_num=" + seq + "&member_id=" + member_id,"pop_onPrint3","width=800,height=680,scrollbars=yes");
}



/**
 * 수료증
 * @param id
 * @param seq
 */
function on_certificate_Print(lecture_id, seq){
	window.open("/printpage/certificate.jsp?lecture_id=" + lecture_id + "&lecreceipt_num=" + seq,"on_certificate_Print","width=745,height=800,scrollbars=yes");
}

/** =============================================
Comment: .김신규 <sg1075 @nospam@ korea.com>
          - http://kldp.net/snippet/detail.php?type=snippet&id=2
          - Version: 0.0.1
         . 입력값의 왼쪽, 오른쪽, 왼쪽과 오른쪽 둘다 공백제거
Return : string
Usage  : trimEx(aaa.value) or ltrimEx(aaa.value) or rtrimEx(aaa.value)
--------------------------------------------- **/
function ltrimEx(strSource)
{
	re = /^\s+/g;
	
	return strSource.replace(re, '');
}

function rtrimEx(strSource)
{
	re = /\s+$/g;
	
	return strSource.replace(re, '');
}

function trimEx(strSource)
{
	re = /^\s+|\s+$/g;
	
	return strSource.replace(re, '');
}



//숫자만입력
function onlyNumber(){

   if((event.keyCode<48)||(event.keyCode>57)){
	   event.returnValue=false; 
   }
      
}


function login_alert(){
	alert("로그인 후 사용이 가능합니다.");
}

//로그인 체크함수
function sendlogin(form1){
    if(form1.member_id.value == ""){
        alert("아이디를 입력하여 주십시오.");
        form1.member_id.focus();
        return false;
    }
    if(form1.member_pw.value == ""){
        alert("비밀번호를 입력하여 주십시오.");
        form1.member_pw.focus();
        return false;
    }
    
    return true;
}

// 실명인증 요청 함수
function openrealnameWindow(form1){
    var openrealnameWindow = window.open("", "openrealnameWindow", "width=430, height=560, resizable=1, scrollbars=no, status=0, titlebar=0, toolbar=0, left=300, top=200" );

    if(openrealnameWindow == null){ 
         alert(" ※ 윈도우 XP SP2 또는 인터넷 익스플로러 7 사용자일 경우에는 \n화면 상단에 있는 팝업 차단 알림줄을 클릭하여 팝업을 허용해 주시기 바랍니다. \n\n※ MSN,야후,구글 팝업 차단 툴바가 설치된 경우 팝업허용을 해주시기 바랍니다.");
    }

    form1.action = "/commonfile/realname/name_check_request.jsp";
    form1.target = "openrealnameWindow";
    form1.submit();
}

//사이렌 민간아이핀 인증 요청 함수
function openspinWindow(form1){
    var openrealnameWindow = window.open("", "openspinWindow", "width=430, height=560, resizable=1, scrollbars=no, status=0, titlebar=0, toolbar=0, left=300, top=200" );

    if(openrealnameWindow == null){ 
         alert(" ※ 윈도우 XP SP2 또는 인터넷 익스플로러 7 사용자일 경우에는 \n화면 상단에 있는 팝업 차단 알림줄을 클릭하여 팝업을 허용해 주시기 바랍니다. \n\n※ MSN,야후,구글 팝업 차단 툴바가 설치된 경우 팝업허용을 해주시기 바랍니다.");
    }

    //form1.action = "/i-PIN/ipin_input_seed.jsp";
    form1.action = "/i-PIN/ipin_request.jsp";
    form1.target = "openspinWindow";
    form1.submit(); 
}

//싸이렌 휴대폰 본인확인 요청 함수
function openPCCReqWindow(form1){ 
    var PCC_window = window.open("", "PCCV3Window", "width=430, height=560, resizable=1, scrollbars=no, status=0, titlebar=0, toolbar=0, left=300, top=200" );

    if(PCC_window == null){ 
         alert(" ※ 윈도우 XP SP2 또는 인터넷 익스플로러 7 사용자일 경우에는 \n화면 상단에 있는 팝업 차단 알림줄을 클릭하여 팝업을 허용해 주시기 바랍니다. \n\n※ MSN,야후,구글 팝업 차단 툴바가 설치된 경우 팝업허용을 해주시기 바랍니다.");
    }

    form1.action = "/pcc/pcc_request.jsp";
    form1.target = "PCCV3Window";
    form1.submit();
}

// 싸이렌 본인확인 요청 함수
function openPCCWindow(){ 
    var PCC_window = window.open("", "PCCV3Window", "width=430, height=560, resizable=1, scrollbars=no, status=0, titlebar=0, toolbar=0, left=300, top=200" );

    if(PCC_window == null){ 
         alert(" ※ 윈도우 XP SP2 또는 인터넷 익스플로러 7 사용자일 경우에는 \n화면 상단에 있는 팝업 차단 알림줄을 클릭하여 팝업을 허용해 주시기 바랍니다. \n\n※ MSN,야후,구글 팝업 차단 툴바가 설치된 경우 팝업허용을 해주시기 바랍니다.");
    }

    document.reqPCCForm.action = "https://pcc.siren24.com/pcc_V3/jsp/pcc_V3_j10.jsp";
    document.reqPCCForm.target = "PCCV3Window";
    form1.submit();
}

//싸이렌 가상식별실명인증 요청 함수
function openCBAWindow(){ 
    var CBA_window = window.open('', 'CBA_window', 'width=410, height=450, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=300, top=200' );
    
    if(CBA_window == null){ 
        alert(" ※ 윈도우 XP SP2 또는 인터넷 익스플로러 7 사용자일 경우에는 \n화면 상단에 있는 팝업 차단 알림줄을 클릭하여 팝업을 허용해 주시기 바랍니다. \n\n※ MSN,야후,구글 팝업 차단 툴바가 설치된 경우 팝업허용을 해주시기 바랍니다.");
   }
    
    document.reqCBAForm.action = "https://name.siren24.com/vname/jsp/vname_j10.jsp";
    document.reqCBAForm.target = "CBA_window";
    form1.submit();
}

//아이핀 인증 요청 함수
function openipinWindow(form1){
    var openipinWindow = window.open("","openipinWindow","width=450,height=500,resizable=0,scrollbars=no,status=0,titlebar=0,toolbar=0");

    if(openipinWindow == null) {
        alert(" ※ 윈도우 XP SP2 또는 인터넷 익스플로러 7 사용자일 경우에는 \n화면 상단에 있는 팝업 차단 알림줄을 클릭하여 팝업을 허용해 주시기 바랍니다. \n\n※ MSN,야후,구글 팝업 차단 툴바가 설치된 경우 팝업허용을 해주시기 바랍니다.");
    }

    form1.action = "/G-PIN/Sample-AuthRequest.jsp";
    form1.target = "openipinWindow";
    form1.submit();
}

//파일다운로드 처리 함수
function filedownload(fdir, fname){
    var form1 = document.fileform;
    
    form1.fdir.value = fdir;
    form1.fname.value = fname;
    form1.action = "/commonfile/fdown.jsp";
    form1.method = "get";
    form1.submit();
}