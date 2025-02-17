import http from 'k6/http';

export const options = {
    stages: [
        { duration: '10s', target: 1000 }, // 1000명의 사용자로 시작
        { duration: '10s', target: 5000 }, // 점진적으로 5000명까지 증가
        { duration: '10s', target: 10000 }, // 최종적으로 10000명 도달
    ],
};

export default function () {
    const url = 'http://localhost:8080/entry';

    const random = Math.floor(Math.random() * 10000000) + 1;
    const data = JSON.stringify({
        redirectUrl: 'testUrl',
        memberSeq: random
    });

    const header = {
        headers: {
            'Content-Type': 'application/json',
        },
    }

    http.post(url, data, header);
}
