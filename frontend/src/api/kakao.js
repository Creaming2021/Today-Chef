// import { kakao } from './instance.js';

// const BASE_URL = "https://kauth.kakao.com/oauth/authorize";
// const AUTHORIZATION_CODE = 'authorization_code';
// const REST_API_KEY = "d441239bc2e622db1a715417daa8e828";
// const REDIRECT_URI = "http://localhost:8080/auth";
// const CODE = "code";
// const CLIENT_SECRET_CODE = 'UOj54MGZPFAkyJrK9hfp2zV3lsCgX0r8';
// const scope = '';

export const getKakaoLogin = () => {
  window.Kakao.Auth.login({
    scope : 'profile, account_email',
    success: res => getKakaoInfo(res),
  });
};

const getKakaoInfo = authObj => {
  console.log(authObj);
  window.Kakao.API.request({
    url:'/v2/user/me',
    success : res => {
      const kakao_account = res.kakao_account;
      // const userInfo = {
      //   nickname : kakao_account.profile.nickname,
      //   email : kakao_account.email,
      //   password : '',
      //   account_type : 2,
      // }
      console.log(kakao_account);
    },
    fail : error => {
      this.$router.push("/errorPage");
      console.log(error);
    }
  })
}

export const signOut = () => {
  alert("로그아웃");
};
