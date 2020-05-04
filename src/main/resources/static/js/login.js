import 'jquery';
import 'bootstrap';
import '../css/bootstrap.min.css';
import '../css/icons.min.css';
import '../css/style.css';
import '../js/jquery.min';
import '../js/vendor.min';
import '../js/common'
import {ajax, getEmailVal, getTelVal, valichk} from './utility'

var login = {
  "isDuplcChk": false,
  "oldUserId": "",
  init: function () {
    let _this = this;

    $('#duplcChk').on('click', function () {
      _this.duplcChk()
    });
    $('#request').on('click', function () {
      _this.request();
    });
    $('#cancel').on('click', function () {
      _this.cancel();
    });

    // 중복체크 후 아이디가 변경되면 다시 체크
    $("[name=userId]").on("propertychange change keyup paste input",
        function () {
          var currentVal = $(this).val();
          if (currentVal == _this.oldUserId) {
            return;
          }

          _this.isDuplcChk = false;
          _this.oldUserId = currentVal;
        });

    // 이메일 선택 시 직접입력창 숨김
    $('#emailAddress_select').change(function () {
      if ($(this).val() == "self" || $(this).val() == "") { // 직접입력 시
        $('#emailAddress_2').val('');
        $("#emailAddress_2").attr("disabled", false);
        $('#emailAddress_2').css('display', 'inline-block');
      } else {  // 이메일 선택 시
        $('#emailAddress_2').val($(this).val());
        $("#emailAddress_2").attr("disabled", true);
        $('#emailAddress_2').css('display', 'none');
      }
      _this.emailConcat();
    });

    $('#emailAddress_1').blur(function () {
      _this.emailConcat();
    });

    $('#emailAddress_2').blur(function () {
      _this.emailConcat();
    });

    // 연락처 입력란에 숫자만 입력하도록 제한
    $('.nan').keypress(function (evt) {
      var theEvent = evt || window.event;

      // Handle paste
      if (theEvent.type === 'paste') {
        key = event.clipboardData.getData('text/plain');
      } else {
        // Handle key press
        var key = theEvent.keyCode || theEvent.which;
        key = String.fromCharCode(key);
      }
      var regex = /[0-9]|\./;
      if (!regex.test(key)) {
        theEvent.returnValue = false;
        if (theEvent.preventDefault) {
          theEvent.preventDefault();
        }
      }
    })
  },
  duplcChk: function () {
    let _this = this;
    let userId = $('[name=userId]').val();
    const regexId = /^(?=.*[a-z])[a-z0-9]{5,20}$/gi

    if (!regexId.test(userId)) {
      _this.isDuplcChk = false;
      alert('아이디는 영문 또는 영문과 숫자 조합 5자리 이상으로 만들어야 합니다.');
    } else {
      ajax('/signup/checkDuplicate', {userId: userId},
          function (result) {
            if (result) {
              _this.isDuplcChk = false;
              alert('존재하는 ID 입니다.');
            } else {
              _this.isDuplcChk = true;
              alert('사용가능한 ID 입니다.');
            }
          });
    }
  },
  request: function () {
    let _this = this;

    $('[name=officeTel]').val(getTelVal('officeTel'));
    $('[name=emailAddress]').val(getEmailVal('emailAddress'));

    if (!_this.isDuplcChk) {
      alert("ID 중복체크는 필수 입니다.");
      return;
    }

    if (!/^[a-zA-Z0-9~!@\#$%<>^&*\()\-=+_\’]{8,50}$/.test($("#userPw").val())) {
      alert("비밀번호는 영문/숫자 조합 8자 이상 으로 만들어야합니다.");
      return;
    }

    if ($('[name=userPw]').val() != $('[name=userPwConfirm]').val()) {
      alert("비밀번호가 다릅니다.");
      return;
    }

    if (!valichk($('#requestSignup'))) {
      return;
    }

    let param = $('#requestSignup').serializeObject();

    ajax('/signup/request', param, function (result) {
      if (result > 0) {
        alert("계정신청 되었습니다.");
        history.back();
      } else {
        alert("계정신청에 실패하였습니다.\n관리자에게 문의하세요. code:1");
      }
    }, function (request, status, error) {
      alert("계정신청에 실패하였습니다.\n관리자에게 문의하세요. code:2");
      // console.log(
      //     "code:" + request.status + "\n" + "message:" + request.responseText
      //     + "\n" + "error:" + error);
    });
  },
  cancel: function () {
    this.isDuplcChk = false;
    $('#requestSignup')[0].reset();
  },
  emailConcat: function () {
    $('#emailAddress').val(getEmailVal('emailAddress'));
  }
}
login.init();