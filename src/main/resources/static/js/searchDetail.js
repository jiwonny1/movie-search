// 전역변수 
var page = 1; // 현재 페이지, 끝까지 불러왔으면 -1로 변경


$(()=>{
	eventOn();
})

// 필요한 이벤트 활성화
function eventOn() {
	$(document).on("click", ".loadBtn", function(e) {
		e.preventDefault();
		loadMore();
	})
}


// 로직 실행 함수들------------------------------------------------------
function loadMore() {
	$(".loadBtn").css("display", "none");
	if(page == -1) return;
	
	let path = location.pathname; // /search/detail/{}
	let param = location.search;
	
	$.ajax({
		url: path + param + "&page=" + ++page,
		type: "POST",
		resultType: "HTML",
		success: function(data) {
			$(".cardContainer").append(data);
			
			if($(".cardContainer").find(".card").length % 30 != 0){
				page = -1;
				return;
			}
			
			$(".loadBtn").css("display", "block");
		}
	})
	
	
}