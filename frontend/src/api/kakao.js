import { kakao } from './instance.js';

// const BASE_URL = "https://kauth.kakao.com/oauth/authorize";
const AUTHORIZATION_CODE = 'authorization_code';
const REST_API_KEY = "d441239bc2e622db1a715417daa8e828";
const REDIRECT_URI = "http://localhost:8080/auth";
// const CODE = "code";
// const CLIENT_SECRET_CODE = 'UOj54MGZPFAkyJrK9hfp2zV3lsCgX0r8';
// const scope = '';

export const getKakaoAccessToken = () => {
  // `${BASE_URL}?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_code=${CODE}`
  const PARAMETER = {
    redirectUri: REDIRECT_URI
  };
  window.Kakao.Auth.authorize(PARAMETER);
};

export const getKakaoToken =  async (code) => {

  const data = {
    grant_type: AUTHORIZATION_CODE,
    client_id: REST_API_KEY,
    redirect_uri: REDIRECT_URI,
    code: code,
  };
  
  const queryString = Object.keys(data)
  .map(k => encodeURIComponent(k) + '=' + encodeURIComponent(data[k]))
  .join('&');


  await kakao.post(
    'oauth/token', 
    queryString
  ).then((res) => {
      console.log('카카오 토큰', res);
      return res;
    }
  ).catch((e) => {
    console.log(e);
  });
};

export const signOut = () => {
  alert("로그아웃");
};
