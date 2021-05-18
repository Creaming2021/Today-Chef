export default {
    state: {
        socket: null
    },
    getters: {
        getSocket: state => state.socket,
    },
    mutations: {
        ASSIGN_SOCKET: (state, payload) => {
            state.socket = payload;
        },
    },
    actions: {
        assignSocket: (context, payload) => {
            context.commit('ASSIGN_SOCKET', payload);
        },
    }
};
