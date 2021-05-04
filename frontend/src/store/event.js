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
    GET_EVENT_LIST: function({ commit }, request) {
      event.getEventList(request)
        .then(({ data }) => {
          if(data){
            commit('SET_EVENT_LIST', data);
          } else {
            console.log(data);
          }
        })
        .catch(e => { console.log(e); });
    },
    GET_EVENT: function({ commit }, request) {
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
    POST_EVENT_IMAGE: function({ commit }, request) {
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