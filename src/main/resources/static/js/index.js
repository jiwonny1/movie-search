
$(()=>{
	eventOn();
})

// 필요한 이벤트 활성화
function eventOn() {
	// 최근 검색어 삭제 버튼 클릭
	$(".deleteRecentSearch").on("click", function() {
		deleteRecentSearch(this);
	});
	
	// 최근 검색어 클릭
	$(".recentKeyword").on("click", function() {
		appendKeyword(this);
	})
	
	// 검색 버튼 클릭
	$("button.search").on("click", function() {
		search();
	})
	
	// 검색창에서 엔터로 submit 방지
	$(".searchInput").on("keydown", function(e) {
		if(e.keyCode==13) {
			e.preventDefault();
		}
	})
	
	// 검색창에서 엔터 입력으로 검색
	$(".searchInput").on("keyup", function(e) {
		if(e.keyCode==13) {
			e.preventDefault();
			search();
		}
	})
}


// 로직 실행 함수들------------------------------------------------------

// 최근 검색어 삭제
function deleteRecentSearch(clickedObject){
	let searchWord = $(clickedObject).prev().text();
	$.ajax({
		url: "/search/deleteRecentSearch",
		type: "POST",
		data: {searchWord: searchWord},
		success: function() {
			$(clickedObject).parent().remove();
		}
	})
}

// 검색어 창에 키워드 추가
function appendKeyword(clickedObject) {
	let keyword = $(clickedObject).text();
	let $input = $("input.searchInput");
	let beforeKeywords = $input.val().trim();
	let afterKeywords;
	
	if(beforeKeywords.length == 0)
		afterKeywords = beforeKeywords + keyword;
	else if(beforeKeywords.charAt(beforeKeywords.length - 1) == ",")
		afterKeywords = beforeKeywords + " " + keyword;
	else 
		afterKeywords = beforeKeywords + ", " + keyword;
	
	$input.val(afterKeywords);
}

// 검색
function search() {
	let rawInput = $("input.searchInput").val().split(",");
	
	for(raw of rawInput) {
		let keyword = raw.trim();
		console.log("--" + keyword + "--");
		if(keyword.length == 0) continue;
		if($("form input[value='" + keyword + "']").length == 0)
			$("form").append($('<input/>', {type: 'hidden', name: 'keywords', value: keyword }));
	}
	
	if($("form").find("input[type='hidden']").length == 0) {
		alert("검색어를 입력해 주세요.");
		return;
	}
	
	$("input.searchInput").attr("disabled", true);
	
	$("form").submit();
	
	$("input.searchInput").attr("disabled", false);
	$("form input[type='hidden']").remove();
}