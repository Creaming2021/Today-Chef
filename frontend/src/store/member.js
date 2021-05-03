// import * as member from '@/api/member.js';

export default {
  state: {
    memberInfo: null,
    couponList: null,

  },
  mutations: {
    setMemberInfo(state, payload){
      state.memberInfo = payload;
    },
    setCouponList(state, payload){
      state.couponList = payload;
    }
  },
  actions: {
    
  }
}