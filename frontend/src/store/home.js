export default {
  state: {
    arrivalHomeState: null,
    currentArrivalHomeState: null,
    categoryHomeState: null,
    currentCategoryHomeState: null,
    randomListState: null,
  },
  mutations: {
    setArrivalHomeState(state, payload) {
      state.arrivalHomeState = payload
    },
    setCurrentArrivalHomeState(state, payload) {
      state.currentArrivalHomeState = payload
    },
    setCategoryHomeState(state, payload) {
      state.categoryHomeState = payload
    },
    setCurrentCategotyHomeState(state, payload) {
      state.currentCategoryHomeState = payload
    },
    setRandomListState(state, payload) {
      state.randomListState = payload
    },
  },
  actions: {
    setArrivalHomeState : function(context, payload) {
      context.commit('setArrivalHomeState', payload)
    },
    setCurrentArrivalHomeState : function(context, payload) {
      context.commit('setCurrentArrivalHomeState', payload)
    },
    setCategoryHomeState : function(context, payload) {
      context.commit('setCategoryHomeState', payload)
    },
    setCurrentCategotyHomeState : function(context, payload) {
      context.commit('setCurrentCategotyHomeState', payload)
    },
    setRandomListState : function(context, payload) {
      context.commit('setRandomListState', payload)
    },
  },
};
