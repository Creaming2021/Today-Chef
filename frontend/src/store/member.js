// import * as member from '@/api/member.js';

export default {
  state: {
    memberInfo: {},
    couponList: [],
  },
  mutations: {
    SET_MEMBER_INFO(state, payload){
      state.memberInfo = payload;
    },
    SET_COUPON_LIST(state, payload){
      state.couponList = payload;
    }
  },
  actions: {
    
  }
};