import * as event from '@/api/event.js';

export default {
  state: {
    eventList: null,
    event: null,
  },
  mutations: {
    setEventList(state, payload){
      state.eventList = payload;
    },
    setEvent(state, payload){
      state.eventDetail = payload;
    },
  },
  actions: {
    getEventList: function(context, request) {
      event.getEventList(request)
        .then((result) => {
          if(result){
            context.commit('setEventList', result);
          } else {
            console.log(result);
          }
        })
        .catch(e => { console.log(e); });
    },
    getEvent: function(context, request) {
      event.getEvent(request)
        .then((result) => {
          if(result){
            context.commit('setEvent', result);
            context.dispatch('postEventImage', request);
          } else {
            console.log(result);
          }
        })
        .catch(e => { console.log(e); });
    },
    postEventImage: function(context, request) {
      event.postEventImage(request)
        .then((result) => {
          if(result){
            context.commit(
              'setEvent', 
              {
                ...event,
                image: result,
              });
          } else {
            console.log(result);
          }
        })
        .catch(e => { console.log(e); });
    },
  }
}