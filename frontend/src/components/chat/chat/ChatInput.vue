<template>
    <div class="chat-input">
        <div class="chat__input">   
            <button class="btn btn-send-chat-message" @click="sendMessage">
                <ion-icon name="md-paper-plane" class="send-icon"></ion-icon>
            </button>    
            <input
                class="chat__input-control"
                placeholder="Type a message here..."
                v-model.trim="content"
                v-on:keyup="triggerMessageSend"
                data-gramm_editor="false"
            />
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

.chat-input {
    box-shadow: 0px 0px 20px 0.5px grey;
}

.btn-send-chat-message {
    position: inherit;
    color: white;
    background-color: #e53637;
    border-radius: 100%;

    position: absolute;
    // top: 10%;
    left: 85%;
    margin-right: px;
    // margin-top: 17px;
    z-index: 1;
    // color: #4f5b66;
}

.send-icon {
    font-size: 1.5em;
    color: #fff;
    margin: 0 auto;
}
</style>
