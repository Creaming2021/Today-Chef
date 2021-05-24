import memberEtc from '@/store/memberActions/memberEtc.js';
import memberItem from '@/store/memberActions/memberItem.js';

export default {
  state: {
    memberInfo: {
      address: {
        address: "",
        name: "",
        phoneNumber: "",
      },
      email: "",
      memberId: 0,
      nickname: "",
      phone: "",
      profileImage: ""
    },
    cartList: [],
    couponList: [],
    courseList: [],
    productList: [],
    paymentList: [],
    payment: {},
  },
  mutations: {
    SET_MEMBER_INFO(state, payload){
      state.memberInfo = payload;
    },
    SET_CART_LIST(state, payload){
      state.cartList = payload;
      console.log(payload);
    },
    SET_COUPON_LIST(state, payload){
      state.couponList = payload;
    },
    SET_COURSE_LIST(state, payload){
      state.courseList = payload;
    },
    SET_PRODUCT_LIST(state, payload){
      state.productList = payload;
    },
    SET_PAYMENT_LIST(state, payload){
      state.paymentList = payload;
    },
    SET_PAYMENT(state, payload){
      state.payment = payload;
    },
  },
  actions: {
    ...memberEtc,
    ...memberItem,
  }
};