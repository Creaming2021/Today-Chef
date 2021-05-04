import product from '@/store/productActions/product.js';
import productQna from '@/store/productActions/productQna.js';
import productReview from '@/store/productActions/productReview.js';

export default {
  state: {
    productList: [],
    product: {},
    qnaList: [],
    qna: {},
    reviewList: [],
    review: {},
  },
  mutations: {
    SET_PRODUCT_LIST(state, payload){
      state.productList = payload;
    },
    SET_PRODUCT(state, payload){
      state.product = payload;
    },
    SET_PRODUCT_QNA_LIST(state, payload){
      state.qnaList = payload;
    },
    SET_PRODUCT_QNA(state, payload){
      state.qna = payload;
    },
    SET_REVIEW_LIST(state, payload){
      state.reviewList = payload;
    },
    SET_REVIEW(state, payload){
      state.review = payload;
    }
  },
  actions: {
    ...product,
    ...productQna,
    ...productReview,
  }
}