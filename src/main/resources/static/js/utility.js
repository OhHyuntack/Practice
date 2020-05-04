/**
 * @name         : postToURL
 * @description  : 해당 url 로 post방식으로 submit
 * @param        : obj
 * @author       :
 */
function postToURL(path, params) {
  var form = document.createElement("form");
  form.setAttribute("method", "post");
  form.setAttribute("action", path);

  //Spring Security의 token값 설정
  var csrfField = document.createElement("input");
  csrfField.setAttribute("type", "hidden");
  csrfField.setAttribute("name",
      $("meta[name='_csrf_parameter']").attr("content"));
  csrfField.setAttribute("value", $("meta[name='_csrf']").attr("content"));
  form.appendChild(csrfField);

  // parameter 세팅
  for (var key in params) {
    var hiddenField = document.createElement("input");
    hiddenField.setAttribute("type", "hidden");
    hiddenField.setAttribute("name", key);
    hiddenField.setAttribute("value", params[key]);
    form.appendChild(hiddenField);
  }

  document.body.appendChild(form);
  form.submit();
  //form.remove(); // 필요없을 경우 삭제, 반복적으로 사용할 경우 그대로 둠
  return false;
}

/* ---------------------------- 공통 ---------------------------- */
/**
 * @name         : ajax
 * @description  : ajax 통신 (csrf 토큰 포함)
 * @param        : url, paramMap, fn_success, fn_error, fn_complete
 * @author       :
 */
function ajax(url, paramMap, fn_success, fn_error, fn_complete, isAsyncN) {

  $.ajax({
    url: url,
    type: 'POST',
    dataType: "json",          // ajax 통신으로 받는 타입
    contentType: "application/json; charset=utf-8",  // ajax 통신으로 보내는 타입
    data: JSON.stringify(paramMap),
    async: !isAsyncN,
    beforeSend: function (xhr) {
      xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"),
          $("meta[name='_csrf']").attr("content"));
      $("#loading").show();
    },
    success: function (result) {
      fn_success(result);
    },
    error: function (jxhr, textStatus, err) {
      if (fn_error) {
        fn_error(jxhr, textStatus, err)
      }
      // console.log(error);
    },
    complete: function (xhr, status) {
      if (fn_complete) {
        fn_complete(xhr, status);
      }
      $("#loading").hide();
    }
  })
}

/**
 * @name         : validate
 * @description  : validate 공통
 * @date         :
 * @author       :
 */
function valichk(obj) {
  let flag = true;
  // input, textarea를 포함하고 있는 폼의 클래스의 디폴트
  let targetNm = $('.search-condition');

  if (obj != null) {
    targetNm = obj;
  }

  targetNm.find("input").each(function (index) {
    $(this).attr('class');
    //필수값 체크
    if ($(this).hasClass('reqed')) {
      if ($.trim($(this).val()) == "") {
        alert($(this).attr("title") + "은(는) 필수값입니다.");
        $(this).focus();
        flag = false;
        return false;
      }
    }
    //숫자형 체크
    if ($(this).hasClass('nan')) {
      var regNumber = /^[0-9]*$/;

      if (!regNumber.test($(this).val().replace(/-/gi, ''))) {
        alert($(this).attr("title") + "은(는) 숫자만 입력해주세요.");
        $(this).focus();
        flag = false;
        return false;
      }
    }
    //전화번호 체크
    if ($(this).hasClass('ntel')) {
      var regTel = /^\d{2,4}-\d{3,4}-\d{4}$/;

      if (!regTel.test($(this).val()) && $(this).val() != '') {
        alert($(this).attr("title") + "의 전화번호 형식이 맞지 않습니다.");
        $(this).focus();
        flag = false;
        return false;
      }
    }
    //휴대폰번호 체크
    if ($(this).hasClass('nphone')) {
      var regPhone = /^01([0|1|2|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;

      if (!regPhone.test($(this).val()) && $(this).val() != '') {
        alert($(this).attr("title") + "의 휴대폰번호 형식이 맞지 않습니다.");
        $(this).focus();
        flag = false;
        return false;
      }
    }
    //날짜형 체크
    if ($(this).hasClass('ndate')) {
      var dayRegExp = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/;
      if ($(this).val() != "" && !dayRegExp.test($(this).val())) {
        alert($(this).attr("title") + "의 날짜 형식이 맞지 않습니다.");
        $(this).focus();
        flag = false;
        return false;
      }
    }
    //날짜시간형 체크
    if ($(this).hasClass('ndatetime')) {
      // var daytimeRegExp = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1]) $/;
      var daytimeRegExp = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1]) (오[전후]) ([1-9]|1[012]):([0-5][0-9])$/;
      if ($(this).val() != "" && !daytimeRegExp.test($(this).val())) {
        alert($(this).attr("title") + "의 날짜 형식이 맞지 않습니다.");
        $(this).focus();
        flag = false;
        return false;
      }
    }
    //이메일형 체크
    if ($(this).hasClass('nemail')) {
      var emailRegExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
      if ($(this).val() != "" && !emailRegExp.test($(this).val())) {
        alert($(this).attr("title") + "의 이메일 형식이 맞지 않습니다.");
        $(this).focus();
        flag = false;
        return false;
      }
    }
    //숫자/문자열 길이 체크 (DB의 LENGTH)
    if ($(this).hasClass('nlength')) {
      // 한글 포함 byte수 변환
      var strByteLength = function (s, b, i, c) {
        for (b = i = 0; c = s.charCodeAt(i++);
            b += c >> 11 ? 3 : c >> 7 ? 2 : 1) {
          ;
        }
        return b
      };

      var maxLength = $(this).attr('maxlength');
      var valLength = strByteLength($(this).val());

      if (valLength > maxLength) {
        alert($(this).attr("title") + "의 숫자 또는 문자열의 제한길이를 초과하였습니다.");
        $(this).focus();
        flag = false;
        return false;
      }
    }
  });

  targetNm.find("textarea").each(function (index) {
    var clsnm = $(this).attr('class');

    //필수값 체크
    if ($(this).hasClass('reqed')) {
      if ($.trim($(this).val()) == "") {
        alert($(this).attr("title") + "은(는) 필수값입니다.");
        $(this).focus();
        flag = false;
        return false;
      }
    }
    //숫자/문자열 길이 체크 (DB의 LENGTH)
    if ($(this).hasClass('nlength')) {
      // 한글 포함 byte수 변환
      var strByteLength = function (s, b, i, c) {
        for (b = i = 0; c = s.charCodeAt(i++);
            b += c >> 11 ? 3 : c >> 7 ? 2 : 1) {
          ;
        }
        return b
      };

      var maxLength = $(this).attr('maxlength');
      var valLength = strByteLength($(this).val());

      if (valLength > maxLength) {
        alert($(this).attr("title") + "의 숫자 또는 문자열의 제한길이를 초과하였습니다.");
        $(this).focus();
        flag = false;
        return false;
      }
    }
  });
  return flag;
}

/**
 * @name : getTelVal
 * @description : 전화번호 입력창 입력값 추출
 * @param : id
 * @author :
 */
function getTelVal(id) {
  var t_sel = $(`#${id}_select`).val();
  var t_1 = $(`#${id}_1`).val();
  var t_2 = $(`#${id}_2`).val();
  var t = t_sel + '-' + t_1 + '-' + t_2;
  if ($.trim(t) == "--") {
    t = '';
  }
  // console.log(t);
  return t;
}

/**
 * @name : getEmailVal
 * @description : ax5select 전화번호 입력창 입력값 추출
 * @param : id
 * @author :
 */
function getEmailVal(id) {
  var e_1 = $('#' + id + '_1').val();
  var e_2 = $('#' + id + '_2').val();
  var e = e_1 + '@' + e_2;

  if ($.trim(e) == "@") {
    e = '';
  }
  return e;
}

// form QueryString Data ('ex)name=홍길동&id=1234567' 를 json 형태로 변경
jQuery.fn.serializeObject = function () {
  var obj = null;
  try {
    if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
      var arr = this.serializeArray();
      if (arr) {
        obj = {};
        jQuery.each(arr, function () {
          obj[this.name] = this.value;
        });
      }
    }
  } catch (e) {
    alert(e.message);
  } finally {
  }
  return obj;
};

export {ajax, postToURL, valichk, getTelVal, getEmailVal};