<template>
    <div class="chat__c-messagelist">
        <ul class="chat__messages" ref="messages" v-if="messages">
            <transition-group name="slideDown">
                <li class="chat__message" v-for="message in messages" :key="message._id">
                    <div
                        class="chat__message-item u-flex-right"
                        v-if="!message.admin && message.user && message.user._id === user._id"
                    >
                        <div class="chat__message-body">
                            <div class="chat__message-content chat__message-content--right">
                                <span v-html="message.content"></span>
                            </div>
                            <div class="chat__message-details">
                                <span>{{ message.user.handle }}</span>
                                <span>{{ moment(message.created_at).fromNow() }}</span>
                            </div>
                        </div>
                        <img
                            :src="message.user.image"
                            class="chat__user-avatar"
                            alt
                        >
                    </div>
                    <div class="chat__message-item u-flex-center" v-else-if="message.admin">
                        <div class="chat__message-body">
                            <div class="chat__message-alert">
                                <span> {{ message.content }} </span>
                            </div>
                        </div>
                    </div>

                    <div class="chat__message-item" v-else-if="!message.user">
                        <img src="@/assets/img/icons8-account-64.png" class="chat__user-avatar" alt>

                        <div class="chat__message-body">
                            <div class="chat__message-content chat__message-content--left">
                                <span>{{ message.content }}</span>
                            </div>
                            <div class="chat__message-details">
                                <span>Unknown User</span>
                                <span>{{ moment(message.created_at).fromNow() }}</span>
                            </div>
                        </div>
                    </div>

                    <!-- Message belongs to another user -->
                    <!-- <div class="chat__message-item" v-else>
                        <img :src="message.user.social.image" class="chat__user-avatar" alt>
                        <div class="chat__message-body">
                            <div class="chat__message-content chat__message-content--left">
                                <span>{{ message.content }}</span>
                            </div>
                            <div class="chat__message-details">
                                <span>{{ message.user.handle }}</span>
                                <span>{{ moment(message.created_at).fromNow() }}</span>
                            </div>
                        </div>
                    </div> -->
                </li>
            </transition-group>
        </ul>
    </div>
</template>


<script>
import { mapGetters } from 'vuex';

export default {
    name: 'MessageList',
    props: ['messages'],
    computed: {
        ...mapGetters(['getUserData'])
    },
    data: function() {
        return {
            user: null
        };
    },
    created() {
        this.user = this.getUserData;
    },
    methods: {
        scrollMessages() {
            var container = this.$refs.messages;
            container.scrollTop = container.scrollHeight;
        }
    },
    mounted() {
        this.scrollMessages();
    },
    updated() {
        this.scrollMessages();
    }
};
</script>


<style lang="scss" scoped>
</style>
