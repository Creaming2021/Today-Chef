import axios from "axios";

axios.defaults.baseURL = 'http://k4b204.p.ssafy.io/';
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

export const image = axios.create({
  baseURL: 'api/',
  headers: {
    'Content-Type': 'multipart/form-data',
  }
});