<template>
    <div class="chat-input">
        <div class="chat__input">   
            <input
                class="chat__input-control"
                placeholder="Type a message here..."
                v-model.trim="content"
                v-on:keyup="triggerMessageSend"
                data-gramm_editor="false"
            />
            <button class="btn btn-send-chat-message" @click="sendMessage">
                <ion-icon name="md-paper-plane" class="send-icon"></ion-icon>
            </button>    
            
        </div>
    </div>
</template>


<script>
import { mapGetters } from 'vuex';

export default {
    name: 'ChatInput',
    data: function() {
        return {
            content: ''
        };
    },
    computed: {
        ...mapGetters(['getUserData', 'getCurrentRoom', 'getSocket'])
    },
    methods: {
        sendUserTyping() {
            this.getSocket.emit('userTyping', {
                room: this.getCurrentRoom,
                user: this.getUserData
            });
        },
        sendUserNotTyping() {
            this.getSocket.emit('removeUserTyping', {
                room: this.getCurrentRoom,
                user: this.getUserData
            });
        },
        triggerMessageSend(e) {
            e.preventDefault();
            if (e.keyCode === 13 && !e.shiftKey) {
                this.sendMessage();
            } else {
                if (this.content !== '') {
                    this.sendUserTyping();
                } else {
                    this.sendUserNotTyping();
                }
            }
        },
        sendMessage() {
            this.getSocket.emit('newMessage', {
                room: this.getCurrentRoom,
                user: this.getUserData,
                content: this.content
            });
            this.content = '';
            this.sendUserNotTyping();
        }
    },
    mounted() {}
};
</script>


<style lang="scss" scoped>

.btn-send-chat-message {
    color: white;
    background-color: white;
    border-radius: 100%;

    position: absolute;
    top: 5px;
    right: -5px;
    z-index: 1;
    width: 35px;
    height: 35px;
    padding: 0px;
    // color: #4f5b66;
}

.btn-send-chat-message:hover{
    background-color: #e53637;
    opacity: 1;
}

.btn-send-chat-message:hover .send-icon{
    font-weight: bold;
    color: #fff;
}

.send-icon {
    font-size: 1.1rem;
    color: #e53637;

}
</style>
