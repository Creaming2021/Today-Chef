import * as kakao from '@/api/kakao.js';
import * as user from '@/api/user.js';

export default {
  state: {
    signStatus : '',
    userId : '',
    userNickname : '',
    authObj: {},
  },
  mutations: {
    SET_AUTH_OBJ(state, payload){
      state.authObj = payload;
    },
    SET_SIGN_STATUS(state, payload){
      state.signStatus = payload;
    },
    SET_SIGN_IN(state, payload) {
      console.log(payload);

      state.signStatus = 'signIn';
      // state.userId = payload.id;
      state.userNickname = payload.profile.nickname;
    },
    SET_SIGN_OUT(state){
      state.signStatus = null;
      state.userId = null;
      state.userEmail = null;
    },
  },
  actions: {
    GET_KAKAO_INFO({ commit, dispatch }) {
      kakao.getKakaoLogIn()
        .then(authObj => {
          commit('SET_AUTH_OBJ', authObj);
          dispatch('SET_SIGN_IN', authObj);
        });
    },
    SIGN_IN({ commit }, authObj) {
      user.signIn(authObj.id)
        .then(({ data }) => {
          if(data){
            sessionStorage.setItem('access_token', authObj.auth.access_token);
            sessionStorage.setItem('refresh_token', authObj.auth.refresh_token);
            commit('SET_SIGN_IN', data);
          } else {
            commit('SET_SIGN_STATUS', 'signUp');
          }
        })
        .catch(e => {
          console.log(e);
        });
    },
    SIGN_UP({ dispatch, state }, userInfo) {
      user.signUp(userInfo)
        .then(() => {
          sessionStorage.setItem('access_token', state.authObj.auth.access_token);
          sessionStorage.setItem('refresh_token', state.authObj.auth.refresh_token);
          dispatch('SET_SIGN_IN', state.authObj);
        });
    },
    SIGN_OUT({ commit }) {
      sessionStorage.clear();
      commit('SET_SIGN_OUT');
    },
  },
};
