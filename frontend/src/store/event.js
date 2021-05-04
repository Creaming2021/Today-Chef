import * as event from '@/api/event.js';

export default {
  state: {
    eventList: [],
    event: {},
  },
  mutations: {
    SET_EVENT_LIST(state, payload){
      state.eventList = payload;
    },
    SET_EVENT(state, payload){
      state.eventDetail = payload;
    },
  },
  actions: {
    GET_EVENT_LIST({ commit }) {
      event.getEventList()
        .then(({ data }) => {
          if(data){
            commit('SET_EVENT_LIST', data);
          } else {
            console.log(data);
          }
        })
        .catch(e => { console.log(e); });
    },
    POST_EVENT({ dispatch }, request) {
      event.postEvent(request)
        .then(({ data }) => {
          if(data){
            dispatch('GET_EVENT', data);
          } else {
            console.log(data);
          }
        })
        .catch(e => { console.log(e); });
    },
    GET_EVENT({ commit }, request) {
      event.getEvent(request)
        .then(({ data }) => {
          if(data){
            commit('SET_EVENT', data);
          } else {
            console.log(data);
          }
        })
        .catch(e => { console.log(e); });
    },
    PUT_EVENT({ dispatch }, request) {
      event.putEvent(request)
        .then(({ data }) => {
          if(data){
            dispatch('GET_EVENT', data);
          } else {
            console.log(data);
          }
        })
        .catch(e => { console.log(e); });
    },
    DELETE_EVENT({ dispatch }, request) {
      event.deleteEvent(request)
        .then(({ data }) => {
          if(data){
            dispatch('', data);
          } else {
            console.log(data);
          }
        })
        .catch(e => { console.log(e); });
    },
    POST_EVENT_IMAGE({ commit }, request) {
      event.postEventImage(request)
        .then(({ data }) => {
          if(data){
            commit('SET_EVENT', 
              {
                ...event,
                image: data,
              });
          } else {
            console.log(data);
          }
        })
        .catch(e => { console.log(e); });
    },
  },
};