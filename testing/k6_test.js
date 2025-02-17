import http from 'k6/http';

export const options = {
    stages: [
        { duration: '3s', target: 1000 },
        { duration: '3s', target: 5000 },
    ],
};

export default function () {
    const url = 'http://localhost:8080/entry';

    const random = Math.floor(Math.random() * 10_000_000_000) + 1;
    const data = JSON.stringify({
        redirectUrl: 'testUrl',
        memberSeq: random
    });

    const header = {headers: {'Content-Type': 'application/json',},}

    http.post(url, data, header);
}
