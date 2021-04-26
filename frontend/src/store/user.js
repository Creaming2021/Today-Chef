import * as kakao from '@/api/kakao.js';
import * as user from '@/api/user.js';

export default {
  state: {
    signStatus : null,
    userId : null,
    userEmail : null,
    userNickname : null,
  },
  mutations: {
    setSignStatus(state, payload){
      state = payload;
    },
    setSignIn(state, payload) {
      state.signStatus = 'logIn';
      state.userId = payload.id;
      state.userEmail = payload.email;
      state.userNickname = payload.nickname;
    },
    setSignOut(state){
      state.signStatus = false;
      state.userId = null;
      state.userEmail = null;
    },
  },
  actions: {
    setSignIn : async function(context) {
      const authObj = await kakao.getKakaoLogIn();
      console.log("셋 사인 인", authObj);

      user.checkUser(authObj.id)
      .then((dbResult) => {
        if(dbResult){
          sessionStorage.setItem('access_token', authObj.auth.access_token);
          sessionStorage.setItem('refresh_token', authObj.auth.refresh_token);
          context.commit('setSignIn',authObj.account);
        } else {
          context.commit('setSignState', 'signUp');
        }
      })
      .catch(e => {console.log(e)});
    },
    setSignOut : function(context) {
      sessionStorage.clear();
      context.commit('setSignOut');
    },
  },
};
