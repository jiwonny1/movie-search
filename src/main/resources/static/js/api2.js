const options = {
    method: 'GET',
    headers: {
        accept: 'application/json',
        Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ZTA4ZjM1ZmU0ZjllMTE3NzU0NmQxZTZiZGVjNTM1NiIsInN1YiI6IjY1ODMxYjc2ZmJlMzZmNGFhZDdmMWFjYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.f66fVzWsAYnQqpJz8D456kGelRjF_6zUwhdpvZ4Y-ao'
    }
};
const currentUrl = window.location.href;

// URL에서 contentId 추출 (예: http://localhost:8000/post/detail/385687)
const contentIdMatch = currentUrl.match(/\/post\/detail\/(\d+)/);

// contentId가 있는지 확인하고 값을 가져오기
const contentId = contentIdMatch ? contentIdMatch[1] : null;
if (contentId) {
    fetch(`https://api.themoviedb.org/3/movie/${contentId}?language=ko-KR`, options)
        .then(response => response.json())
        .then(response => {
            showDetail(response);
            console.log(response);
        })
        .catch(err => console.error(err));
}
function showDetail(movieData) {
    // 영화 포스터, 제목, 줄거리를 페이지에 적용
    const movieOverview = document.getElementById("movieOverview");

    // 포스터, 제목, 줄거리 데이터를 가져와서 적용
    const overview  = movieData.overview;

    movieOverview.textContent = overview;
    console.log(overview);
}

document.addEventListener("DOMContentLoaded", function () {
    const stars = document.querySelectorAll(".star-rating input[type='radio']");

    stars.forEach(function (star) {
        star.addEventListener("change", function () {
            clearAllStars();
            highlightStars(this);
        });
    });

    function clearAllStars() {
        stars.forEach(function (star) {
            star.nextElementSibling.classList.remove("selected");
        });
    }

    function highlightStars(selectedStar) {
        let currentStar = selectedStar;
        while (currentStar) {
            currentStar.nextElementSibling.classList.add("selected");
            currentStar = currentStar.previousElementSibling;
        }
    }
});
