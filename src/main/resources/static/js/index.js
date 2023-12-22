
$(()=>{
	eventOn();
})

function eventOn() {
	// 최근 검색어 삭제 버튼 클릭
	$(document).on("click", ".deleteRecentSearch", function() {
		deleteRecentSearch(this);
	});
}

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