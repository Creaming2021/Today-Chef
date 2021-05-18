<template>
    <div class="page page--room">
        <section class="section section--room section--mmt p-0">
            <div class="section__content u-max-height p-0">
                <div class="chat">
                    <div class="chat__content mlzero">
                        <div class="chat__header" v-if="room">
                            <ion-icon name="return-left" @click="leaveRoom" class="icon"></ion-icon>
                            <span class="section__title"> {{ room.name }} </span>
                            <div class="chat__actions">
                                <div class="chat-admin-button" v-if="this.$store.state.chat.authUser._id === getUserData._id">
                                    <a v-bind:href="this.teacherLink" target="_blank" class="primary-btn class-btn">클래스 입장</a>
                                </div>
                                <ion-icon v-if="this.$store.state.chat.authUser._id === getUserData._id" 
                                    ios="ios-trash" md="md-trash"
                                    @click.stop="handleDelete"
                                    :name="room.name"
                                    class="icon">
                                </ion-icon>
                                <ion-icon v-if="this.$store.state.chat.authUser._id === getUserData._id" 
                                    name="ios-link" 
                                    @click="sendStreamingLink" 
                                    class="icon">
                                </ion-icon>
                                <ion-icon name="md-menu" @click="toggleUserList" class="icon"></ion-icon>
                            </div>
                        </div>
                        <MessageList :messages="messages" />
                        <ChatInput />
                    </div>
                    <Sidebar name="userlist" ref="userList">
                            <template slot="header">
                                <div class="userlist__actions">
                                    <div>
                                        <ion-icon name="people" class="icon"></ion-icon>
                                    </div>
                                    <span class="section__title">{{filteredUsers.length}}</span>
                                    <div class="toggle-icon" @click="toggleUserList">
                                        <ion-icon name="close-circle" class="icon"></ion-icon>
                                    </div>
                                </div>
                            </template>
                            <template slot="body">
                                <input
                                    type="text"
                                    class="rooms__search-input"
                                    placeholder="유저 닉네임 검색"
                                    v-model.trim="searchInput"
                                />
                                <ul class="chat__userlist" v-if="this.getCurrentRoom && filteredUsers">
                                    <transition-group name="slideDown">
                                        <li
                                            class="chat__user"
                                            v-for="user in filteredUsers"
                                            :key="user.lookup._id"
                                        >
                                            <div class="chat__user-item">
                                                <div class="chat__user-image">
                                                    <img
                                                        :src="user.lookup.image"
                                                        class="chat__user-avatar"
                                                        alt
                                                    />
                                                </div>

                                                <div class="chat__user-details">
                                                    <span>{{ user.lookup.handle }}</span>
                                                </div>
                                            </div>
                                        </li>
                                    </transition-group>
                                </ul>
                            </template>
                            <template slot="footer">
                                <button
                                    @click="leaveRoom"
                                    class="btn btn--clear btn--danger center"
                                >Leave Room</button>
                            </template>
                    </Sidebar>
                </div>
            </div>
            <Modal name="editRoom" ref="editRoom" v-if="this.getCurrentRoom">
                <template slot="header">
                    <h2 class="text-upper">Edit Room: {{ this.getCurrentRoom.name }}</h2>
                </template>
                <template slot="body">
                    <form @submit="handleEditRoom" slot="body" class="form form--nbs pt-3">
                        <div class="form__input-group">
                            <ion-icon name="pricetags" class="form__icon"></ion-icon>
                            <input
                                type="text"
                                name="roomName"
                                class="form__control"
                                placeholder="Enter New Room Name"
                                pattern=".{3,20}"
                                required
                                v-model.trim="newRoomName"
                            />
                            <label for="roomName" class="form__label">New Room name</label>
                        </div>
                        <Error :errors="errors" />
                        <button type="submit" class="btn btn--clear btn--info">Update Room Name</button>
                    </form>
                </template>
            </Modal>
            <Modal name="roomDetails" ref="roomDetails" v-if="this.getCurrentRoom && messages">
                <template slot="header">
                    <h2 class="lead text-upper">Room Details: {{ this.getCurrentRoom.name }}</h2>
                </template>
                <template slot="body">
                    <div class="infobox">
                        <div class="infobox__item">
                            <ion-icon name="planet" class="icon icon-lg"></ion-icon>
                        </div>
                        <div class="infobox__item">
                            <span class="infobox__item--left">Online Users</span>
                            <span
                                class="infobox__item--right"
                            >{{ this.getCurrentRoom.users.length }}</span>
                        </div>
                        <div class="infobox__item">
                            <span class="infobox__item--left">Messages</span>
                            <span class="infobox__item--right">{{ messages.length }}</span>
                        </div>
                        <div class="infobox__item">
                            <span class="infobox__item--left">Room Admin</span>
                            <span
                                class="infobox__item--right"
                            >{{ this.getCurrentRoom.user ? this.getCurrentRoom.user.handle : 'Unknown User' }}</span>
                        </div>
                        <div class="infobox__item">
                            <span class="infobox__item--left">Created</span>
                            <span
                                class="infobox__item--right"
                            >{{ moment(this.getCurrentRoom.created_at).fromNow() }}</span>
                        </div>
                    </div>
                </template>
            </Modal>
        </section>
    </div>
</template>


<script>
import MessageList from '@/components/chat/chat/MessageList.vue';
import ChatInput from '@/components/chat/chat/ChatInput.vue';
import Sidebar from '@/components/chat/layout/Sidebar.vue';
import Modal from '@/components/chat/layout/Modal.vue';
import Error from '@/components/chat/error/Error.vue';
import { mapActions, mapGetters } from 'vuex';
import { chat } from '@/api/instance.js';

export default {
    name: 'Room',
    components: {
        MessageList,
        ChatInput,
        Sidebar,
        Error,
        Modal
    },
    data: function() {
        return {
            room: [],
            users: [],
            usersTyping: [],
            messages: [],
            newRoomName: '',
            sidebarVisible: window.innerWidth < 768 ? false : true,
            searchInput: '',
            errors: [],
            roomLeft: false,
            teacherLink: '',
        };
    },
    computed: {
        ...mapGetters(['getUserData', 'getCurrentRoom', 'getSocket']),
        filteredUsers() {
            if (this.searchInput === '') {
                return this.users;
            }
            return  this.users
                .slice()
                .sort(this.sortAlphabetical)
                .filter(user => user.lookup.username.includes(this.searchInput));
        },
    },
    methods: {
        ...mapActions(['saveCurrentRoom']),
        sendStreamingLink() {
            this.getSocket.emit('newMessage', {
                room: this.getCurrentRoom,
                user: this.getUserData,
                content: '<a href="http://creaming.co.kr/' + this.getCurrentRoom._id + '/' + this.$store.state.chat.authUser.username + '/basic' +'" target="_blank">'
                        + '<img src="https://creaming-bucket-b204.s3.ap-northeast-2.amazonaws.com/logo2-13.png"/></a>',
            });
        },
        checkUserTabs(room) {
            if (
                room &&
                room.users.findIndex(user => user.lookup._id === this.getUserData._id) === -1
            ) {
                this.$router.push({ name: 'RoomList' });
            }
        },
        sortAlphabetical(a, b) {
            let userA = a.lookup.username.toUpperCase();
            let userB = b.lookup.username.toUpperCase();

            if (userA < userB) {
                return -1;
            }
            if (userA > userB) {
                return 1;
            }
            return 0;
        },
        leaveRoom(e, newPage) {
            if (e) {
                e.preventDefault();
            }
            chat
                .post('/room/remove/users', {
                    room_name: this.getCurrentRoom.name,
                    user: this.$store.state.chat.authUser,
                })
                .then(res => {
                    if (this.room.access || this.room.accessIds.includes(this.getUserData._id)) {
                        this.getSocket.emit('exitRoom', {
                            room: res.data,
                            user: null,
                            admin: true,
                            content: `${this.getUserData.handle} left ${this.getCurrentRoom.name}`
                        });
                    }
                    this.roomLeft = true;
                    if (!newPage) {
                        this.$router.push({ name: 'RoomList' });
                    }
                });
        },
        openEditRoom() {
            this.$refs.editRoom.open();
        },
        handleEditRoom(e) {
            e.preventDefault();
            chat
                .post('/room/update/name', {
                    room_name: this.getCurrentRoom.name,
                    new_room_name: this.newRoomName
                })
                .then(res => {
                    if (res.data.errors) {
                        for (const error of res.data.errors) {
                            const [key] = Object.keys(error);
                            const [value] = Object.values(error);
                            this.errors.push({
                                key,
                                value
                            });
                        }
                    } else {
                        this.$refs.editRoom.close();
                        this.getSocket.emit('roomUpdateEvent', {
                            oldRoomName: this.getCurrentRoom.name,
                            room: res.data
                        });
                        this.getSocket.emit('newMessage', {
                            room: this.getCurrentRoom,
                            user: this.getUserData,
                            admin: true,
                            content: `${this.getUserData.username} updated room ${
                                this.getCurrentRoom.name
                            } to ${this.newRoomName}`
                        });
                        this.newRoomName = '';
                    }

                    setTimeout(() => {
                        this.errors = [];
                    }, 1500);
                })
                .catch(err => console.log(err));
        },
        viewRoomDetails() {
            this.$refs.roomDetails.open();
        },
        toggleUserList() {
            this.$refs.userList.toggle();
            this.sidebarVisible = !this.sidebarVisible;
        },
        handleDelete(e) {
            e.preventDefault();
            chat
                .delete(`/room/${e.target.name}`)
                .then(res => {
                    this.$store.dispatch('deleteRoom', res.data);
                    this.getSocket.emit('roomDeleted', {
                        room: res.data,
                        user: this.getUserData,
                        admin: true,
                        content: `${res.data.user.username} deleted room ${res.data.name}`
                    });
                    this.$router.push({ name: 'RoomList' });
                })
                .catch(err => console.log(err));
        },
    },
    created() {

        if(this.$store.state.user.memberId === ''){
            this.$router.push({
                name: "Error",
            })
        }

        chat
            .get(`/room/${this.$route.params.id}`)
            .then(res => {
                this.room = res.data;
                this.users = res.data.users;
                this.$store.dispatch('saveCurrentRoom', res.data);
                
                if (!res.data.access) {
                    if (
                        !res.data.accessIds.includes(this.getUserData._id) &&
                        this.getUserData._id !== res.data.user._id
                    ) {
                        return this.$router.push({
                            name: 'RoomList',
                            params: { message: 'You do not have access to this room' }
                        });
                    }
                }

                this.teacherLink = "http://creaming.co.kr/"+this.getCurrentRoom._id+"/"+this.$store.state.chat.authUser.username+"/stt";

                this.getSocket.emit('userJoined', {
                    room: this.getCurrentRoom,
                    user: this.getUserData,
                    content: `${this.getUserData.handle} joined ${this.getCurrentRoom.name}`,
                    admin: true
                });

                this.getSocket.on('updateRoomData', data => {
                    data = JSON.parse(data);
                    if (data.messages) {
                        this.messages = data.messages;
                    }

                    if (data.room) {
                        this.room = data.room;
                        this.users = data.room.users;
                        this.$store.dispatch('saveCurrentRoom', data.room);
                    }
                });

                this.getSocket.on('reconnect', () => {
                    this.usersTyping = [];
                    this.getSocket.emit('reconnectUser', {
                        room: this.getCurrentRoom,
                        user: this.getUserData
                    });
                });

                this.getSocket.on('reconnected', () => {
                    console.warn('Reconnected');
                });

                this.getSocket.on('disconnect', () => {
                    console.warn('Disconnected');
                });

                this.getSocket.on('updateUserList', data => {
                    this.users = JSON.parse(data).users;
                });

                this.getSocket.on('receivedUserExit', room => {
                    this.checkUserTabs(room);
                });

                this.getSocket.on('receivedNewMessage', message => {
                    this.messages.push(JSON.parse(message));
                });

                this.getSocket.on('receivedUserTyping', data => {
                    this.usersTyping = JSON.parse(data).filter(
                        user => user !== this.getUserData.handle
                    );
                });

                this.getSocket.on('roomDeleted', () => {
                    this.$store.dispatch('saveCurrentRoom', null);
                    setTimeout(() => {
                        this.$router.push({ name: 'RoomList' });
                    }, 2000);
                });

                this.getSocket.on('roomUpdated', data => {
                    this.room = JSON.parse(data).room;
                    this.$store.dispatch('saveCurrentRoom', JSON.parse(data).room);
                });
            })
            .catch(err => {
                if (err.response.status === 404) {
                    this.$router.push({
                        name: 'RoomList',
                        params: { message: 'This room does not exist or has been deleted' }
                    });
                }
            });
    },
    beforeDestroy() {
        if (this.getCurrentRoom && !this.roomLeft) {
            this.leaveRoom(null, true);
        }
        this.getSocket.removeListener('userJoined');
    },
    mounted() {}
};
</script>


<style lang="scss">
@import '@/assets/scss/views/chat.scss';
@import '@/assets/scss/views/rooms.scss';
@import '@/assets/scss/components/infobox.scss';

.chat-admin-button {
    display: inline;
}
.class-btn:hover {
    text-decoration: none;
}
</style>
