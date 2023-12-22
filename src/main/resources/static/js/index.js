
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
}

// 로직 실행 함수들------------------------------------------------------

// 최근 검색어 삭제
function deleteRecentSearch(clickedObject){
	let searchWord = $(clickedObject).prev().text();
	$.ajax({
		url: "/search/deleteRecentSearch",
		type: "POST",
		data: {searchWord : searchWord},
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

	if(beforeKeywords.length == 0 || beforeKeywords.charAt(beforeKeywords.length - 1) == ",")
		afterKeywords = beforeKeywords + " " + keyword;
	else 
		afterKeywords = beforeKeywords + ", " + keyword;
	
	$input.val(afterKeywords);
}