import Axios from 'axios';

const instance = Axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 1000,
    headers: {
        'Content-Type': 'application/json'
    }
});

export default instance;
