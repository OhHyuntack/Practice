//쿠키값 가져오기
function getCookie(cookie_name) {
  var x, y;
  var val = document.cookie.split(';');

  for (var i = 0; i < val.length; i++) {
    x = val[i].substr(0, val[i].indexOf('='));
    y = val[i].substr(val[i].indexOf('=') + 1);
    x = x.replace(/^\s+|\s+$/g, ''); // 앞과 뒤의 공백 제거하기

    if (x == cookie_name) {
      return unescape(y); // unescape로 디코딩 후 값 리턴
    }
  }
}

//쿠키값 Set
function setCookie(cookieName, value, exdays){
  var exdate = new Date();
  exdate.setDate(exdate.getDate() + exdays);
  var cookieValue = escape(value) + ((exdays==null) ? "" : "; expires=" +
      exdate.toGMTString());
  document.cookie = cookieName + "=" + cookieValue;
}

//쿠키값 Delete
function deleteCookie(cookieName){
  var expireDate = new Date();
  expireDate.setDate(expireDate.getDate() - 1);
  document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}

// 상단메뉴 고정
var jbOffset = $(".navigation").offset();
$(window).scroll( function() {
  if ( $(document).scrollTop() > jbOffset.top ) {
    $(".navigation").addClass("fixed");
  }
  else {
    $(".navigation").removeClass("fixed");
  }
});

// 검색조건 닫기/펼치기
var condiwp = $(".fold-warp");
$(".btn-fold").click(function() {
  if(condiwp.css("height") == "36px")
  {
    condiwp.css("height", "auto");
  } else {
    condiwp.animate({height: "36px"}, 300);
  }
});

// 서브 좌측메뉴 닫기/펼치기
var snbwp = $(".snb .hidden");
var snbfd = $(".btn-snb-fold");
var width = $(".contents").width();
$(".btn-snb-fold").click(function() {
  if(snbwp.css("width") == "0px")
  {
    snbwp.animate({width: "200px"}, 300);
    snbfd.animate({left: "173px"}, 300);
    $(".contents").animate({width: width + 0}, 300);
  } else {
    snbwp.animate({width: "0px"}, 300);
    snbfd.animate({left: "3px"}, 300);
    $(".contents").animate({width: width + 200}, 300);
  }
});

// 지도영역 좌측메뉴 닫기/펼치기
var leftawp = $(".left-area .hidden");
var leftafd = $(".left-area-fold");
var leftatab = $(".left-area .nav > li > a");
$(".left-area-fold").click(function() {
  if(leftawp.css("width") == "70px")
  {
    leftawp.animate({width: "370px"}, 300);
    leftafd.animate({right: "0px"}, 300);
  } else {
    leftawp.animate({width: "70px"}, 300);
    leftafd.animate({right: "273px"}, 300);
  }
});
leftatab.click(function() {
  leftawp.animate({width: "370px"}, 300);
  leftafd.animate({right: "0px"}, 300);
});

// 자주묻는질문 토글
$(".faq-list .text-left a").click(function() {
  $(this).parents().parents().next(".faq-view").slideToggle(".faq-view");
  return false;
});

// 모달 중첩 처리
$(document).on({
  'show.bs.modal': function() {
    var zIndex = 1040 + (10 * $('.modal:visible').length);
    $(this).css('z-index', zIndex);
    setTimeout(function() {
      $('.modal-backdrop').not('.modal-stack').css('z-index', zIndex - 1).addClass('modal-stack');
    }, 0);
  },
  'hidden.bs.modal': function() {
    if ($('.modal:visible').length > 0) {
      // restore the modal-open class to the body element, so that scrolling works
      // properly after de-stacking a modal.
      setTimeout(function() {
        $(document.body).addClass('modal-open');
      }, 0);
    }
  }
}, '.modal');

// 툴팁
$(function () {
  $('[data-toggle="tooltip"]').tooltip(),
      $('[data-toggle="popover"]').popover()
})
