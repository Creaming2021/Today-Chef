import axios from "axios";

// axios.defaults.baseURL = 'http://localhost:8080/api/';
axios.defaults.baseURL = 'api/';
// axios.defaults.headers.post['Content-Type'] = 'application/json';
axios.defaults.responseType = 'json';
axios.defaults.validateStatus = (status) => status >= 200 && status < 300;

export const basic = axios.create({
  headers: {
    'Content-Type': 'application/json',
  }
});