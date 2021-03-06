import * as product from '@/api/product.js';

export default {
  GET_PRODUCT_TOP_LIST({ commit }, request){
    product.getTopList( request )
      .then(({ data }) => {
        commit('SET_PRODUCT_TOP_LIST', data);
      })
      .catch(e => { console.log(e); });
  },
	GET_PRODUCT_LIST({ commit }) {
		product.getProductList()
			.then(({ data }) => {
				if(data){
					commit('SET_PRODUCT_LIST', data);
				} else {
					console.log(data);
				}
			})
			.catch(e => { console.log(e); });
	},
	POST_PRODUCT({ dispatch }, request) {
		product.postProduct(request)
			.then(({ data }) => {
				if(data){
					dispatch('GET_PRODUCT', data);
				} else {
					console.log(data);
				}
			})
			.catch(e => { console.log(e); });
	},
	GET_PRODUCT({ commit }, request) {
		product.getProduct(request)
			.then(({ data }) => {
				if(data){
					commit('SET_PRODUCT', data);
				} else {
					console.log(data);
				}
			})
			.catch(e => { console.log(e); });
	},
	PUT_PRODUCT({ dispatch }, request) {
		product.putProduct(request)
			.then(({ data }) => {
				if(data){
					dispatch('GET_PRODUCT', data);
				} else {
					console.log(data);
				}
			})
			.catch(e => { console.log(e); });
	},
	DELETE_PRODUCT({ commit }, request) {
		product.deleteProduct(request)
			.then(({ data }) => {
				if(data){
					commit('', data);
				} else {
					console.log(data);
				}
			})
			.catch(e => { console.log(e); });
	},
}