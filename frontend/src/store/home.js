export default {
  state: {
    arrivalHomeState: [],
    currentArrivalHomeState: [],
    categoryHomeState: [],
    currentCategoryHomeState: [],
    randomListState: [],
  },
  mutations: {
    SET_ARRIVAL_HOME_STATE(state, payload) {
      state.arrivalHomeState = payload;
    },
    SET_CURRENT_ARRIVAL_HOME_STATE(state, payload) {
      state.currentArrivalHomeState = payload;
    },
    SET_CATEGORY_HOME_STATE(state, payload) {
      state.categoryHomeState = payload;
    },
    SET_CURRENT_CATEGORY_HOME_STATE(state, payload) {
      state.currentCategoryHomeState = payload;
    },
    SET_RANDOM_LIST_STATE(state, payload) {
      state.randomListState = payload;
    },
  },
  actions: {
    SET_ARRIVAL_HOME_STATE : function({ commit }, payload) {
      commit('SET_ARRIVAL_HOME_STATE', payload);
    },
    SET_CURRENT_ARRIVAL_HOME_STATE : function({ commit }, payload) {
      commit('SET_CURRENT_ARRIVAL_HOME_STATE', payload);
    },
    SET_CATEGORY_HOME_STATE : function({ commit }, payload) {
      commit('SET_CATEGORY_HOME_STATE', payload);
    },
    SET_CURRENT_CATEGORY_HOME_STATE : function({ commit }, payload) {
      commit('SET_CURRENT_CATEGORY_HOME_STATE', payload);
    },
    SET_RANDOM_LIST_STATE : function({ commit }, payload) {
      commit('SET_RANDOM_LIST_STATE', payload);
    },
  },
};
