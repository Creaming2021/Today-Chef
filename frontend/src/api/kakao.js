export const getKakaoLogIn = async () => {
  return new Promise((resolve) => {
    window.Kakao.Auth.login({
      scope : 'profile, account_email',
      success: async res => {
        resolve( await getKakaoInfo(res));
      },
    });
  });
};

const getKakaoInfo = authObj => {
  return new Promise((resolve) => {
    window.Kakao.API.request({
      url:'/v2/user/me',
      success : res => {
        resolve({
          id: res.id,
          account: res.kakao_account,
          auth: authObj,
        });
      },
      fail : () => {
        this.$router.push({
          name: "Error"
        });
      }
    });
  });
}
