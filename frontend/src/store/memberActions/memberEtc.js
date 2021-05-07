import * as memberEtc from '@/api/memberEtc.js';

export default{
	GET_MEMBER_INFO({ commit }, request ) {
		memberEtc.getMemberInfo(request)
			.then(({ data }) => {
				commit('SET_MEMBER_INFO', data);
			})
			.catch(e => { console.log(e); });
	},
	PUT_MEMBER_INFO({ commit, dispatch }, request ) {
		memberEtc.putMemberInfo(request)
			.then(() => {
				dispatch('GET_MEMBER_INFO', request.memberId);
				commit('SET_NICKNAME', request.nickname);
			})
			.catch(e => { console.log(e); });
	},
	GET_CART_LIST({ commit }, request ) {
		memberEtc.getCartList(request)
			.then(({ data }) => {
				commit('SET_CART_LIST', data);
			})
			.catch(e => { console.log(e); });
	},
	POST_CART_LIST({ dispatch }, request ) {
		memberEtc.postCartList(request)
			.then(() => {
				dispatch('', request);
			})
			.catch(e => { console.log(e); });
	},
	DELETE_CART_LIST({ dispatch }, request ) {
		memberEtc.deleteCartList(request)
			.then(() => {
				dispatch('GET_CART_LIST', request);
			})
			.catch(e => { console.log(e); });
	},
	GET_COUPON_LIST({ commit }, request ) {
		memberEtc.getCouponList(request)
			.then(({ data }) => {
				commit('SET_COUPON_LIST', data);
			})
			.catch(e => { console.log(e); });
	},
	PUT_COUPON({ commit }, request ) {
		memberEtc.putCoupon(request)
			.then(({ data }) => {
				commit('', data);
			})
			.catch(e => { console.log(e); });
	},
	SET_COUPON({ commit }, request ) {
		memberEtc.setCoupon(request)
			.then(({ data }) => {
				commit('', data);
			})
			.catch(e => { console.log(e); });
	},
	POST_PROFILE_IMAGE({ commit, store }, request ) {
		memberEtc.postProfileImage(request)
			.then(({ data }) => {
				commit('SET_MEMBER_INFO', {
					...store.memberInfo,
					profileImage: data,
				});
			})
			.catch(e => { console.log(e); });
	},
}