import axios from "axios";

// axios.defaults.baseURL = 'http://localhost:8080/api/';
// axios.defaults.baseURL = 'api/';
// axios.defaults.headers.post['Content-Type'] = 'application/json';
axios.defaults.responseType = 'json';
axios.defaults.validateStatus = (status) => status >= 200 && status < 300;
axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';

export const basic = axios.create({
  baseURL: 'api/',
  headers: {
    'Content-Type': 'application/json',
  }
});

export const kakao = axios.create({
  baseURL: 'https://kauth.kakao.com/',
  headers: {
    'Authorization': '11062caa28def457380d2b37765fae5e',
    'Content-type': 'application/x-www-form-urlencoded;charset=utf-8',
  }
})