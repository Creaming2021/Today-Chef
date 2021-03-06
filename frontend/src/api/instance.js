import axios from "axios";

// axios.defaults.baseURL = 'http://k4b204.p.ssafy.io/';
// axios.defaults.baseURL = 'api/';
// axios.defaults.headers.post['Content-Type'] = 'application/json';
// axios.defaults.responseType = 'json';
// axios.defaults.validateStatus = (status) => status >= 200 && status < 300;
axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';

const baseURL = 'https://k4b204.p.ssafy.io/api/';
// const baseURL = 'http://localhost:9999/api/';
const baseURLImage = 'https://k4b204.p.ssafy.io/api/images/';
const baseURLChat = 'https://k4b204.p.ssafy.io/chat/';
// const baseURLChat = 'http://localhost:5000/chat/';

export const basic = axios.create({
  baseURL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const image = axios.create({
  baseURL: baseURLImage,
  headers: {
    'Content-Type': 'multipart/form-data',
  },
});

export const chat = axios.create({
  baseURL: baseURLChat,
  headers: {
    'Content-Type': 'application/json',
  },
});