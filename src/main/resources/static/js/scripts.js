/*!
* Start Bootstrap - Shop Homepage v5.0.6 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project
let isIdChecked = false;

document.getElementById('search-icon').addEventListener('click', function(event) {
    event.preventDefault();
    var searchBox = document.getElementById('search-box');
    searchBox.style.display = searchBox.style.display === 'block' ? 'none' : 'block';
});

            
document.getElementById('search-input').addEventListener('keypress', function(event) {
    if (event.key === 'Enter') {
        event.preventDefault();
        // 검색 실행 로직 추가
        console.log('검색:', this.value); // 여기에 실제 검색 로직을 추가합니다.
    }
});

document.getElementById('logo').addEventListener('click', function() {
    window.location.href = '../shopping/main';
});

document.getElementById('checkId').addEventListener('click', function() {
    const mid = document.getElementById('mid').value;
    if(mid) {
        fetch('/member/checkId?mid=' + encodeURIComponent(mid))
            .then(response => response.json())
            .then(data => {
                if(data.isAvailable) {
                    alert('사용 가능한 아이디입니다.');
                    isIdChecked = true; // 중복 확인이 완료되었음을 표시합니다.
                } else {
                    alert('이미 사용 중인 아이디입니다.');
                    isIdChecked = false; // 사용자가 새로운 아이디를 입력해야 함을 표시합니다.
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('오류가 발생했습니다. 다시 시도해주세요.');
                isIdChecked = false; // 오류가 발생했으므로 중복 확인을 다시 해야 함을 표시합니다.
            });
    } else {
        alert('아이디를 입력해주세요.');
        isIdChecked = false; // 아이디 입력이 필요함을 표시합니다.
    }
});

document.querySelector('form').addEventListener('submit', function(event) {
    const mpw = document.getElementById('mpw').value;
    const mpwConfirm = document.getElementById('mpwConfirm').value;

    // 비밀번호 일치 검사
    if(mpw !== mpwConfirm) {
        alert('입력한 비밀번호가 일치하지 않습니다.');
        event.preventDefault(); // 폼 제출 막기
        return; // 더 이상의 코드 실행을 막기 위해
    }

    // 중복 확인 버튼 클릭 여부 검사
    if (!isIdChecked) {
        alert('아이디 중복 확인을 해주세요.');
        event.preventDefault();
    }
});

window.onbeforeunload = function() {
    navigator.sendBeacon('/logout');
};
