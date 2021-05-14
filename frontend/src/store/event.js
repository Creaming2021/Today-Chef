import * as event from '@/api/event.js';

export default {
  state: {
    eventList: [],
    event: {},
    imageList: [],
  },
  mutations: {
    SET_EVENT_LIST(state, payload){
      state.eventList = payload;
    },
    SET_EVENT(state, payload){
      state.event = payload;
    },
    SET_EVENT_IMAGE_LIST(state, payload){
      state.imageList = payload;
    },
  },
  actions: {
    GET_EVENT_LIST({ commit }) {
      event.getEventList()
        .then(({ data }) => {
          commit('SET_EVENT_LIST', data);
        })
        .catch(e => { console.log(e); });
    },
    POST_EVENT({ dispatch }, request) {
      event.postEvent(request)
        .then(({ data }) => {
          dispatch('GET_EVENT', data);
        })
        .catch(e => { console.log(e); });
    },
    GET_EVENT({ commit }, request) {
      event.getEvent(request)
        .then(({ data }) => {
          commit('SET_EVENT', data);
        })
        .catch(e => { console.log(e); });
    },
    PUT_EVENT({ dispatch }, request) {
      event.putEvent(request)
        .then(({ data }) => {
          dispatch('GET_EVENT', data);
        })
        .catch(e => { console.log(e); });
    },
    DELETE_EVENT({ dispatch }, request) {
      event.deleteEvent(request)
        .then(({ data }) => {
            dispatch('', data);
        })
        .catch(e => { console.log(e); });
    },
    POST_EVENT_IMAGE({ commit }, request) {
      event.postEventImage(request)
        .then(({ data }) => {
          commit('SET_EVENT', 
            {
              ...event,
              image: data,
            });
        })
        .catch(e => { console.log(e); });
    },
    GET_EVENT_IMAGE_LIST({ commit }) {
      event.getEventImageList()
        .then(({ data }) => {
          commit('SET_EVENT_IMAGE_LIST', data);
        })
        .catch(e => { console.log(e); });
    },
  },
};