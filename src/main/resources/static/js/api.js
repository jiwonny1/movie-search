const options = {
    method: 'GET',
    headers: {
        accept: 'application/json',
        Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2ZTA4ZjM1ZmU0ZjllMTE3NzU0NmQxZTZiZGVjNTM1NiIsInN1YiI6IjY1ODMxYjc2ZmJlMzZmNGFhZDdmMWFjYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.f66fVzWsAYnQqpJz8D456kGelRjF_6zUwhdpvZ4Y-ao'
    }
};
let data = "";
fetch('https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=ko-KR&page=1&sort_by=popularity.desc&with_cast=timothee', options)
    .then(response => response.json())
    .then(response => {
        makeList(response);
        console.log(response);
    })
    .catch(err => console.error(err));

const listBox = document.getElementById("movie-list");

function makeList(data) {
    data.results.forEach((movie, index) => {
        const li = document.createElement("li");
        li.textContent = `${index}, ${movie.title}`;
        listBox.appendChild(li);
    });
}

/**
 * {
 *     "page": 1,
 *     "results": [
 *         {
 *             "adult": false,
 *             "backdrop_path": "/bmlkLCjrIWnnZzdAQ4uNPG9JFdj.jpg",
 *             "genre_ids": [
 *                 35,
 *                 10751,
 *                 14
 *             ],
 *             "id": 787699,
 *             "original_language": "en",
 *             "original_title": "Wonka",
 *             "overview": "초콜릿 제조가이자 발명가인 윌리 웡카는 세계 최고의 초콜릿 가게를 열기 위해 ‘달콤한 백화점’을 찾지만, 이를 방해하는 초콜릿 연합의 위협을 받게 되는데…",
 *             "popularity": 1081.396,
 *             "poster_path": "/5Afx73NwXPdgHd3Kt1tU7ig4Vox.jpg",
 *             "release_date": "2023-12-06",
 *             "title": "웡카",
 *             "video": false,
 *             "vote_average": 7.3,
 *             "vote_count": 386
 *         },
 *         {
 *             "adult": false,
 *             "backdrop_path": "/1X7vow16X7CnCoexXh4H4F2yDJv.jpg",
 *             "genre_ids": [
 *                 80,
 *                 18,
 *                 36
 *             ],
 *             "id": 466420,
 *             "original_language": "en",
 *             "original_title": "Killers of the Flower Moon",
 *             "overview": "1920년대 오클라호마주 오세이지 부족 땅에서 석유가 발견된 후, 부족 사람들이 한 명씩 죽어 나가자 FBI가 미스터리를 풀기 위해 나선다.",
 *             "popularity": 879.598,
 *             "poster_path": "/6jByCqeQXEu3RR5qvlTCBamJQED.jpg",
 *             "release_date": "2023-10-18",
 *             "title": "플라워 킬링 문",
 *             "video": false,
 *             "vote_average": 7.6,
 *             "vote_count": 1511
 *         },
 *         {
 *             "adult": false,
 *             "backdrop_path": "/jXJxMcVoEuXzym3vFnjqDW4ifo6.jpg",
 *             "genre_ids": [
 *                 28,
 *                 12,
 *                 14
 *             ],
 *             "id": 572802,
 *             "original_language": "en",
 *             "original_title": "Aquaman and the Lost Kingdom",
 *             "overview": "아틀란티스 왕국을 이끌 왕의 자리에 오른 아쿠아맨. 그 앞에 블랙 만타가 세상을 뒤흔들 강력한 지배 아이템 블랙 트라이던트를 손에 넣게 된다. 그동안 겪지 못 했던 최악의 위협 속 아쿠아맨은 블랙 만타와 손을 잡았던 이복 동생 옴 없이는 절대적 힘이 부족한 상황. 바다를 지배할 슈퍼 히어로가 세상의 판도를 바꾼다!",
 *             "popularity": 964.868,
 *             "poster_path": "/eDps1ZhI8IOlbEC7nFg6eTk4jnb.jpg",
 *             "release_date": "2023-12-20",
 *             "title": "아쿠아맨과 로스트 킹덤",
 *             "video": false,
 *             "vote_average": 7.7,
 *             "vote_count": 33
 *         },
 *         {
 *             "adult": false,
 *             "backdrop_path": "/8GnWDLn2AhnmkQ7hlQ9NJUYobSS.jpg",
 *             "genre_ids": [
 *                 18,
 *                 878,
 *                 28
 *             ],
 *             "id": 695721,
 *             "original_language": "en",
 *             "original_title": "The Hunger Games: The Ballad of Songbirds & Snakes",
 *             "overview": "반란의 불씨를 잠재우기 위해 시작된 잔인한 서바이벌 헝거게임. 헝거게임 10회를 맞아 ‘멘토제’가 도입되고 ‘스노우’는 12구역의 소녀 ‘루시 그레이’의 멘토가 된다. 그는 몰락한 가문의 영광을 되찾기 위해 ‘루시 그레이’를 헝거게임에서 우승 시키려 수단과 방법을 가리지 않는데…",
 *             "popularity": 810.099,
 *             "poster_path": "/6kkFs0dgTbpjmqetzR4CU1HfHMd.jpg",
 *             "release_date": "2023-11-15",
 *             "title": "헝거게임: 노래하는 새와 뱀의 발라드",
 *             "video": false,
 *             "vote_average": 7.2,
 *             "vote_count": 810
 *         },
 *         {
 *             "adult": false,
 *             "backdrop_path": "/k1KrbaCMACQiq7EA0Yhw3bdzMv7.jpg",
 *             "genre_ids": [
 *                 16,
 *                 10751,
 *                 10402,
 *                 14,
 *                 35
 *             ],
 *             "id": 901362,
 *             "original_language": "en",
 *             "original_title": "Trolls Band Together",
 *             "overview": "전 세계 모든 트롤을 열광케 했던 최고의 아이돌 그룹 ‘브로존’. 역대급 무대 실수와 형제 간의 불화로 결국 해체한 뒤, 모두에게 잊혀 간다. 그러던 어느 날, ‘브로존’의 황금막내 ‘브랜치’는 메인보컬 ‘플로이드’가 슈퍼스타 ‘벨벳’과 ‘비니어’에게 잡혀 재능을 빼앗기고 있다는 소식을 듣는다. 그를 구하기 위해서는 흩어져 있는 ‘브로존’을 재결합하고 완벽한 화음을 되찾아야 하는데… 12월, 가장 짜릿한 컴백 무대의 시작!",
 *             "popularity": 782.173,
 *             "poster_path": "/7M2pc9OboapgtoBbkU49Aim7O5B.jpg",
 *             "release_date": "2023-10-12",
 *             "title": "트롤 밴드 투게더",
 *             "video": false,
 *             "vote_average": 7.2,
 *             "vote_count": 389
 *         },
 *         {
 *             "adult": false,
 *             "backdrop_path": "/zIYROrkHJPYB3VTiW1L9QVgaQO.jpg",
 *             "genre_ids": [
 *                 28,
 *                 35
 *             ],
 *             "id": 897087,
 *             "original_language": "en",
 *             "original_title": "Freelance",
 *             "overview": "",
 *             "popularity": 730.353,
 *             "poster_path": "/7Bd4EUOqQDKZXA6Od5gkfzRNb0.jpg",
 *             "release_date": "2023-01-05",
 *             "title": "프리랜스",
 *             "video": false,
 *             "vote_average": 6.4,
 *             "vote_count": 337
 *         },
 *         {
 *             "adult": false,
 *             "backdrop_path": "/tLsc8SCFO0rMVgVyNm9XtfnyX84.jpg",
 *             "genre_ids": [
 *                 18,
 *                 9648,
 *                 53,
 *                 878
 *             ],
 *             "id": 726209,
 *             "original_language": "en",
 *             "original_title": "Leave the World Behind",
 *             "overview": "호화로운 임대 주택으로 휴가를 떠난 가족. 그런데 사이버 공격으로 기기가 고장 나고, 두 명의 낯선 사람이 불쑥 찾아오면서 불길한 일이 전개되기 시작한다.",
 *             "popularity": 695.078,
 *             "poster_path": "/29rhl1xopxA7JlGVVsf1UHfYPvN.jpg",
 *             "release_date": "2023-11-22",
 *             "title": "리브 더 월드 비하인드",
 *             "video": false,
 *             "vote_average": 6.6,
 *             "vote_count": 944
 *         },
 *         {
 *             "adult": false,
 *             "backdrop_path": "/15Fe18IglCCP1jJoj5F529on0IA.jpg",
 *             "genre_ids": [
 *                 28,
 *                 35
 *             ],
 *             "id": 1029575,
 *             "original_language": "en",
 *             "original_title": "The Family Plan",
 *             "overview": "헌신적인 남편이자 사랑이 넘치는 아버지, 그리고 자동차 영업왕인 댄 모건. 전직 킬러라는 과거가 그의 발목을 붙잡으려 하자, 댄은 아무것도 모르는 가족들을 데리고 기상천외한 로드 트립을 떠난다.",
 *             "popularity": 630.244,
 *             "poster_path": "/oMTuRG8koeKqH2tVonaWhyp7OBw.jpg",
 *             "release_date": "2023-12-14",
 *             "title": "'패밀리 플랜' - The Family Plan",
 *             "video": false,
 *             "vote_average": 7.6,
 *             "vote_count": 232
 *         },
 *         {
 *             "adult": false,
 *             "backdrop_path": "/fm6KqXpk3M2HVveHwCrBSSBaO0V.jpg",
 *             "genre_ids": [
 *                 18,
 *                 36
 *             ],
 *             "id": 872585,
 *             "original_language": "en",
 *             "original_title": "Oppenheimer",
 *             "overview": "세상을 구하기 위해 세상을 파괴할 지도 모르는 선택을 해야 하는 천재 과학자의 핵개발 프로젝트.",
 *             "popularity": 582.685,
 *             "poster_path": "/jpD6z9fgNe7OqsHoDeAWQWoULde.jpg",
 *             "release_date": "2023-07-19",
 *             "title": "오펜하이머",
 *             "video": false,
 *             "vote_average": 8.1,
 *             "vote_count": 5503
 *         },
 *         {
 *             "adult": false,
 *             "backdrop_path": "/9PqD3wSIjntyJDBzMNuxuKHwpUD.jpg",
 *             "genre_ids": [
 *                 16,
 *                 35,
 *                 10751
 *             ],
 *             "id": 1075794,
 *             "original_language": "en",
 *             "original_title": "Leo",
 *             "overview": "초등학교 마지막 해를 보내는 아이들. 이 모습을 교실에서 키우는 반려동물의 눈을 통해 바라본다. 성장 스토리를 담은 뮤지컬 코미디로 애덤 샌들러가 반려동물인 도마뱀의 목소리 연기를 선보인다.",
 *             "popularity": 617.925,
 *             "poster_path": "/pD6sL4vntUOXHmuvJPPZAgvyfd9.jpg",
 *             "release_date": "2023-11-17",
 *             "title": "레오",
 *             "video": false,
 *             "vote_average": 7.6,
 *             "vote_count": 609
 *         },
 *         {
 *             "adult": false,
 *             "backdrop_path": "/4XM8DUTQb3lhLemJC51Jx4a2EuA.jpg",
 *             "genre_ids": [
 *                 28,
 *                 80,
 *                 53
 *             ],
 *             "id": 385687,
 *             "original_language": "en",
 *             "original_title": "Fast X",
 *             "overview": "돔과 그의 패밀리 앞에 나타난 운명의 적 단테. 과거의 그림자는 돔의 모든 것을 파괴하기 위해 달려온다. 단테에 의해 산산히 흩어진 패밀리들은 모두 목숨을 걸고 맞서야 하는 함정에 빠지고 마는데...",
 *             "popularity": 508.326,
 *             "poster_path": "/wXNihLltMCGR7XepN39syIlCt5X.jpg",
 *             "release_date": "2023-05-17",
 *             "title": "분노의 질주: 라이드 오어 다이",
 *             "video": false,
 *             "vote_average": 7.2,
 *             "vote_count": 4497
 *         },
 *         {
 *             "adult": false,
 *             "backdrop_path": "/aNG1BSIULxbHtUmBiar0i3fR1S4.jpg",
 *             "genre_ids": [
 *                 16,
 *                 12,
 *                 35,
 *                 10751
 *             ],
 *             "id": 520758,
 *             "original_language": "en",
 *             "original_title": "Chicken Run: Dawn of the Nugget",
 *             "overview": "겁 없는 닭들이 한데 모였다. 전에 없던 불길한 위협이 닥쳐왔기 때문. 그 정체는, 뭔가 수상한 음식을 만들고 있는 인근 농장. 이곳에서 위험에 처한 동족을 구해야 한다.",
 *             "popularity": 751.068,
 *             "poster_path": "/tpUhFQWn5gV7GnagWOOMHToJOsX.jpg",
 *             "release_date": "2023-12-08",
 *             "title": "치킨 런: 너겟의 탄생",
 *             "video": false,
 *             "vote_average": 7.6,
 *             "vote_count": 156
 *         },
 *         {
 *             "adult": false,
 *             "backdrop_path": "/t5zCBSB5xMDKcDqe91qahCOUYVV.jpg",
 *             "genre_ids": [
 *                 27,
 *                 9648
 *             ],
 *             "id": 507089,
 *             "original_language": "en",
 *             "original_title": "Five Nights at Freddy's",
 *             "overview": "80년대에 아이들이 실종되고 폐업한지 오래된 프레디의 피자가게 그곳의 야간 경비 알바를 하게 된 ‘마이크'는 캄캄한 어둠만이 존재하는 줄 알았던 피자가게에서 살아 움직이는 피자가게 마스코트 '프레디와 친구들’을 목격한다. 어딘가 기괴하고 섬뜩한 프레디와 친구들이 실체를 드러내기 시작하는데...",
 *             "popularity": 524.657,
 *             "poster_path": "/6rUZcazlkYILiNPKFgCGkzTn0BN.jpg",
 *             "release_date": "2023-10-25",
 *             "title": "프레디의 피자가게",
 *             "video": false,
 *             "vote_average": 7.8,
 *             "vote_count": 2893
 *         },
 *         {
 *             "adult": false,
 *             "backdrop_path": "/kjQBrc00fB2RjHZB3PGR4w9ibpz.jpg",
 *             "genre_ids": [
 *                 28,
 *                 12,
 *                 18
 *             ],
 *             "id": 670292,
 *             "original_language": "en",
 *             "original_title": "The Creator",
 *             "overview": "인류를 지키기 위해 만들어진 AI가 LA에 핵폭탄을 터뜨린 후, 인류와 AI 간의 피할 수 없는 전쟁이 시작된다. 전직 특수부대 요원 '조슈아'는 실종된 아내의 단서를 얻을지도 모른다는 생각에 전쟁을 끝내기 위한 인류의 작전에 합류한다. 인류를 위협할 강력한 무기와 이를 창조한 '창조자'를 찾아 나서고, 그 무기가 아이 모습의 AI 로봇 '알피'란 사실을 알게 되는데...",
 *             "popularity": 519.127,
 *             "poster_path": "/vFsSluuzqxR46Ils9ib52ItdE9u.jpg",
 *             "release_date": "2023-09-27",
 *             "title": "크리에이터",
 *             "video": false,
 *             "vote_average": 7.1,
 *             "vote_count": 1503
 *         },
 *         {
 *             "adult": false,
 *             "backdrop_path": "/AucuqDaUW4z1oT5XyL71qwRY2F6.jpg",
 *             "genre_ids": [
 *                 27,
 *                 53
 *             ],
 *             "id": 983507,
 *             "original_language": "no",
 *             "original_title": "Meg, deg & Frank",
 *             "overview": "",
 *             "popularity": 420.453,
 *             "poster_path": "/9DPG1gxLwV2oyFdHq3SnISsWbse.jpg",
 *             "release_date": "2022-06-03",
 *             "title": "Meg, deg & Frank",
 *             "video": false,
 *             "vote_average": 6.8,
 *             "vote_count": 111
 *         },
 *         {
 *             "adult": false,
 *             "backdrop_path": "/tB59fpPnQITgnfxPfMf2gxASTeC.jpg",
 *             "genre_ids": [
 *                 28,
 *                 53,
 *                 18
 *             ],
 *             "id": 656156,
 *             "original_language": "ko",
 *             "original_title": "보호자",
 *             "overview": "10년 만에 출소한 수혁은 자신에게 딸이 있다는 사실을 알게 되고 조직을 떠나 평범하게 살기로 결심한다. 수혁의 출소를 기다리던 보스 응국은 수혁에게 배신감을 느끼고 자신의 오른팔이자 조직의 2인자 성준에게 그를 감시하라 지시한다. 수혁에 대한 열등감으로 가득 찬 성준은 일명 세탁기라 불리는 2인조 살인청부업자 우진과 진아에게 수혁을 제거할 것을 의뢰하고 자신들의 방식대로 무자비하게 타겟을 처리하는 이들은 수혁을 죽이기 위해 접근하는데…",
 *             "popularity": 404.916,
 *             "poster_path": "/2XwxhT1acqFHBPaVDvriX7iuul3.jpg",
 *             "release_date": "2023-08-15",
 *             "title": "보호자",
 *             "video": false,
 *             "vote_average": 6.5,
 *             "vote_count": 31
 *         },
 *         {
 *             "adult": false,
 *             "backdrop_path": "/3d39m2S3WynknLVGpsOxhiSKbgQ.jpg",
 *             "genre_ids": [
 *                 53,
 *                 28
 *             ],
 *             "id": 1146225,
 *             "original_language": "en",
 *             "original_title": "Escaping Paradise",
 *             "overview": "결혼기념일을 맞아 세부로 여행을 떠난 플로이드와 제나. 우연히 만난 케인과 함께 여행하게 된다.  친절한 척 다가온 그가 사실은 엄청난 범죄자라는 사실을 알게 되고 케인의 범죄 사실을 경찰에 신고하는데, 신고를 알게 된 케인은 부부를 노리고, 아내 제나를 납치한다.  지역 경찰마저 모두 케인의 편인 최악의 상황에서 전직 특수 부대 출신인 플로이드는 납치된 제나를 구하고 무사히 섬을 탈출할 수 있을까?",
 *             "popularity": 461.494,
 *             "poster_path": "/9kcaTlZI0sEOwxEmVNWeofzPY7i.jpg",
 *             "release_date": "2023-07-08",
 *             "title": "범죄천국",
 *             "video": false,
 *             "vote_average": 4.7,
 *             "vote_count": 3
 *         },
 *         {
 *             "adult": false,
 *             "backdrop_path": "/rb3QQ2TQfJoCpCZlfXBlBbzYy1D.jpg",
 *             "genre_ids": [
 *                 53,
 *                 28
 *             ],
 *             "id": 844416,
 *             "original_language": "en",
 *             "original_title": "Rumble Through the Dark",
 *             "overview": "",
 *             "popularity": 350.088,
 *             "poster_path": "/19UbYIT9WEQS5qSD3BREDxVXk8g.jpg",
 *             "release_date": "2023-11-03",
 *             "title": "럼블 쓰루 더 다크",
 *             "video": false,
 *             "vote_average": 6.7,
 *             "vote_count": 26
 *         },
 *         {
 *             "adult": false,
 *             "backdrop_path": "/rMvPXy8PUjj1o8o1pzgQbdNCsvj.jpg",
 *             "genre_ids": [
 *                 28,
 *                 12,
 *                 53
 *             ],
 *             "id": 299054,
 *             "original_language": "en",
 *             "original_title": "Expend4bles",
 *             "overview": "테러 단체에게 핵미사일이 탈취되어 3차 세계대전이 발발할 위기의 상황에 ‘리’(제이슨 스타뎀)를 새로운 리더로 맞은 무적의 팀 익스펜더블이 작전에 투입되는데...",
 *             "popularity": 428.282,
 *             "poster_path": "/dWR8OTRCcYDbBmd8GKsyJT73kU2.jpg",
 *             "release_date": "2023-09-15",
 *             "title": "익스펜더블 4",
 *             "video": false,
 *             "vote_average": 6.4,
 *             "vote_count": 904
 *         },
 *         {
 *             "adult": false,
 *             "backdrop_path": "/jhpsTzbXEu5bkCPmBqxv7vUTjIT.jpg",
 *             "genre_ids": [
 *                 14,
 *                 12,
 *                 878,
 *                 28
 *             ],
 *             "id": 566810,
 *             "original_language": "en",
 *             "original_title": "Dragon Kingdom",
 *             "overview": "",
 *             "popularity": 379.385,
 *             "poster_path": "/o7StI2iR8yY1N67meSkNcXfojyD.jpg",
 *             "release_date": "2018-11-26",
 *             "title": "Dragon Kingdom",
 *             "video": false,
 *             "vote_average": 5.7,
 *             "vote_count": 41
 *         }
 *     ],
 *     "total_pages": 41581,
 *     "total_results": 831604
 * }
 */