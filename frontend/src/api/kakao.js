export const getKakaoLogIn = () => (
  window.Kakao.Auth.login({
    scope : 'profile, account_email',
    success: async res => {
      console.log("겟 카카오 로그인", getKakaoInfo(res));
      return await getKakaoInfo(res);
    },
  })
);

const getKakaoInfo = async authObj => {
  await window.Kakao.API.request({
    url:'/v2/user/me',
    success : res => {
      console.log("겟 카카오 인포", {
        id: res.id,
        acount: res.kakao_account,
        auth: authObj,
      });
      return {
      id: res.id,
      acount: res.kakao_account,
      auth: authObj,
    }
  },
    fail : () => {
      this.$router.push({
        name: "Error"
      });
    }
  })
}
