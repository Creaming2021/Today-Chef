import Swal from 'sweetalert2';
import * as kakao from '@/api/kakao.js';
import * as user from '@/api/user.js';

export default {
  state: {
    signStatus : '',
    memberId : '',
    nickname : '',
    authObj: {},
    emailState: false,
  },
  mutations: {
    SET_AUTH_OBJ(state, payload){
      state.authObj = payload;
    },
    SET_SIGN_STATUS(state, payload){
      state.signStatus = payload;
    },
    SET_SIGN_IN(state, payload) {
      state.signStatus = 'signIn';
      state.memberId = payload.memberId;
      state.nickname = payload.nickname;
    },
    SET_SIGN_OUT(state){
      state.signStatus = '';
      state.memberId = '';
      state.nickname = '';
      state.authObj = {};
      state.emailState = false;
    },
    SET_EMAIL_STATE(state, payload){
      state.emailState = payload;
    },
    SET_NICKNAME(state, payload){
      state.nickname = payload;
    }
  },
  actions: {
    GET_KAKAO_INFO({ commit, dispatch }) {
      kakao.getKakaoLogIn()
        .then(authObj => {
          commit('SET_AUTH_OBJ', authObj);
          dispatch('SIGN_IN', authObj);
        });
    },
    SIGN_IN({ commit }, authObj) {
      user.signIn(authObj.id)
        .then(({ data }) => {
          sessionStorage.setItem('access_token', authObj.auth.access_token);
          sessionStorage.setItem('refresh_token', authObj.auth.refresh_token);
          commit('SET_SIGN_IN', data);
        })
        .catch(() => {
          commit('SET_SIGN_STATUS', 'signUp');
        });
    },
    SIGN_UP({ dispatch, state }, memberInfo) {
      user.signUp({
          kakaoId: state.authObj.id,
          nickname: memberInfo.nickname,
          phone: memberInfo.phone,
          email: memberInfo.email,
        })
        .then(({ data }) => {
          Swal.fire({
            icon: 'success',
            text: '성공적으로 회원가입을 완료했습니다.',
          });
          dispatch('SIGN_IN', state.authObj);
          dispatch('SET_COUPON', {
            memberId: data.memberId,
            couponId: 1
          });
        });
    },
    SIGN_OUT({ commit }) {
      sessionStorage.clear();
      commit('SET_SIGN_OUT');
    },
    CHECK_EMAIL({ commit }, request) {
      user.checkEmail(request)
        .then(({ data }) => {
          if(data) {
            Swal.fire({
              icon: 'success',
              text: '사용 가능한 이메일 입니다.',
            });
            commit('SET_EMAIL_STATE', true);
          } else {
            Swal.fire({
              icon: 'error',
              text: '이미 사용중인 이메일 입니다.',
            });
            commit('SET_EMAIL_STATE', false);
          }
        })
        .catch(e => { console.log(e); });
    },
    SET_EMAIL_STATE({ commit }, request) {
      commit('SET_EMAIL_STATE', request);
    }
  },
};
