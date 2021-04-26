import * as kakao from '@/api/kakao.js';
import * as user from '@/api/user.js';

export default {
  state: {
    signStatus : null,
    userId : null,
    userNickname : null,
    authObj: null,
  },
  mutations: {
    setAuthObj(state, payload){
      state.authObj = payload;
    },
    setSignStatus(state, payload){
      state.signStatus = payload;
    },
    setSignIn(state, payload) {
      console.log(payload);

      state.signStatus = 'signIn';
      // state.userId = payload.id;
      state.userNickname = payload.profile.nickname;
    },
    setSignOut(state){
      state.signStatus = null;
      state.userId = null;
      state.userEmail = null;
    },
  },
  actions: {
    setSignIn : function(context, authObj) {
      user.checkUserDB(authObj.id)
        .then((dbResult) => {
          if(dbResult){
            sessionStorage.setItem('access_token', authObj.auth.access_token);
            sessionStorage.setItem('refresh_token', authObj.auth.refresh_token);
            context.commit('setSignIn', authObj.account);
          } else {
            context.commit('setSignStatus', 'signUp');
          }
        })
        .catch(e => {
          console.log(e);
        });
    },
    getKakaoInfo : function(context) {
      kakao.getKakaoLogIn()
        .then(authObj => {
          context.commit('setAuthObj', authObj);
          context.dispatch('setSignIn', authObj);
        });
    },
    setSignUp : function(context, userInfo) {
      user.signUp(userInfo)
        .then(() => {
          sessionStorage.setItem('access_token', this.authObj.auth.access_token);
          sessionStorage.setItem('refresh_token', this.authObj.auth.refresh_token);
          context.dispatch('setSignIn', this.authObj);
        });
    },
    setSignOut : function(context) {
      sessionStorage.clear();
      context.commit('setSignOut');
    },
  },
};
