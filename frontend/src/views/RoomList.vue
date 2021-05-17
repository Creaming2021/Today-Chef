<template>
    <div class="roomList">
        <div class="body-header">
            <img src="@/assets/img/banner/banner-chat.png"/>
        </div>
        <section class="section section--mmt section-room-list">
            <div class="section__content">
                <Error :errorMessage="errorMessage" />
                <div class="rooms" v-if="rooms">
                    <div class="rooms__header">
                        <div class="rooms__details">
                            <div class="rooms__details-item">
                                <ion-icon name="ios-chatbubbles" class="icon"></ion-icon>
                                <span 
                                    class="badge badge--info"
                                >{{ rooms.length }}</span>
                            </div>
                            <div class="rooms__details-item">
                                <ion-icon name="md-lock" class="icon"></ion-icon>
                                <span
                                    class="badge badge--danger"
                                >{{ getPrivateRooms.length }}</span>
                            </div>
                            <div class="rooms__details-item">
                                <ion-icon name="md-unlock" class="icon"></ion-icon>
                                <span
                                    class="badge badge--success"
                                >{{ getPublicRooms.length }}</span>
                            </div>
                            <div class="rooms__actions">
                                <a @click="openModal" class="primary-btn create-btn">Create Room</a>
                                <!-- <ion-icon name="md-refresh-circle" @click="fetchRoomData" class="icon"></ion-icon> -->
                            </div>
                        </div>
                        <input
                            type="text"
                            class="rooms__search-input"
                            placeholder="채팅방 이름 검색"
                            v-model.trim="searchInput"
                        />
                    </div>
                    <!-- RoomList Header End -->
                    <transition name="slideDown">
                        <div class="row">
                            <div class="col-lg-12"> 
                                <!-- room list -->
                                <ul class="rooms__list">
                                    <template name="slideUp" v-if="filteredRooms.length > 0">
                                        <li
                                            v-for="room in filteredRooms"
                                            :key="room._id"
                                            class="rooms__list-item chat-room col-lg-3 col-md-4 col-xs-6"
                                        >
                                                <a
                                                    :href="`room/${room._id}`"
                                                    class="rooms__list-item-link room-link"
                                                    @click.prevent="handleRoomClick(room)"
                                                >
                                                    <div class="rooms__item-container">
                                                        <div class="rooms__item-details">
                                                            <div v-if="room.access"
                                                                :class="{ public: room.access, private: !room.access}"
                                                            >
                                                                <ion-icon name="md-unlock" class="icon"></ion-icon>
                                                            </div>
                                                            <div v-else
                                                                :class="{ public: room.access, private: !room.access}"
                                                            >
                                                                <ion-icon name="md-lock" class="icon" style="color: black"></ion-icon>
                                                            </div>
                                                            <strong>{{ room.name.substring(0,7) }}</strong>
                                                            <div class="rooms__item-detail">
                                                                <ion-icon name="md-people" class="icon"></ion-icon>
                                                                <span>{{ room.users.length }}</span>
                                                            </div>
                                                            <div class="rooms__item-detail">
                                                                <ion-icon name="md-trophy" class="icon"></ion-icon>
                                                                <span>{{ room.user ? room.user.handle : 'Unknown User' }}</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </a>
                                        </li>
                                    </template>
                                    <span v-else>No Rooms</span>
                                </ul>
                            </div>
                        </div>
                    </transition>
                    <!-- Private Room Enter Modal  : modal.scss -->
                    <Modal name="private-room" ref="privateRoom">
                        <template slot="header">
                            <h2
                                class="text-upper"
                            >Enter {{ this.privateRoomName || 'Private Room' }}</h2>
                        </template>
                        <template slot="body">
                            <form
                                @submit="handlePrivateRoomCheck"
                                slot="body"
                                class="form form--nbs pt-3"
                            >
                                <div class="form__input-group">
                                    <ion-icon name="key" class="form__icon"></ion-icon>
                                    <input
                                        type="password"
                                        name="password"
                                        class="form__control"
                                        placeholder="Enter Password"
                                        v-model.trim="privateRoomPassword"
                                    />
                                    <label for="password" class="form__label">Password</label>
                                </div>
                                <Error :errors="errors" />
                                <button type="submit" class="btn btn--clear btn--info">Enter Room</button>
                            </form>
                        </template>
                    </Modal>
                    <!-- Create Room Modal -->
                    <Modal name="create-room" ref="createRoom">
                        <template slot="header">
                            <h2 class="text-upper">Create Room</h2>
                        </template>
                        <template slot="body">
                            <form
                                @submit="handleCreateRoom"
                                slot="body"
                                class="form form--nbs pt-3"
                            >
                                <div class="form__input-group">
                                    <ion-icon name="list-box" class="form__icon"></ion-icon>
                                    <input
                                        type="text"
                                        name="room_name"
                                        class="form__control"
                                        placeholder="Enter Room Name"
                                        v-model.trim="room_name"
                                    />
                                    <label for="room_name" class="form__label">Room Name</label>
                                </div>
                                <div class="form__input-group">
                                    <ion-icon name="key" class="form__icon"></ion-icon>
                                    <input
                                        type="password"
                                        name="password"
                                        class="form__control"
                                        placeholder="Enter Password"
                                        pattern=".{5,10}"
                                        title="Password must be between 5 and 15 characters"
                                        v-model.trim="password"
                                    />
                                    <label for="password" class="form__label">Password (Optional)</label>
                                </div>
                                <Error :errors="errors" />
                                <button type="submit" class="primary-btn">Create Room</button>
                            </form>
                        </template>
                    </Modal>
                </div>
            </div>
        </section>
    </div>
</template>

<script>
import Modal from '@/components/chat/layout/Modal';
import { mapActions, mapGetters } from 'vuex';
import Error from '@/components/chat/error/Error.vue';
import { chat } from '@/api/instance.js';

export default {
    name: 'RoomList',
    props: ['message'],
    components: {
        Modal: Modal,
        Error
    },
    data: function() {
        return {
            rooms: [],
            room_name: null,
            privateRoomName: null,
            password: null,
            privateRoomPassword: null,
            searchInput: '',
            errorMessage: this.message,
            errors: []
        };
    },
    computed: {
        ...mapGetters(['getUserData', 'getRoomData', 'getSocket']),
        getPrivateRooms() {
            return this.rooms.filter(room => room.access === false);
        },
        getPublicRooms() {
            return this.rooms.filter(room => room.access === true);
        },
        filteredRooms() {
            if (this.searchInput.toLowerCase() === 'my_rooms') {
                return this.rooms.filter(room => room.user._id === this.getUserData._id);
            } else {
                return this.rooms
                    .slice()
                    .sort(this.sortAlphabetical)
                    .filter(room =>
                        room.name.toLowerCase().includes(this.searchInput.toLowerCase())
                    );
            }
        }
    },
    methods: {
        ...mapActions(['updateRoomData', 'addRoom', 'deleteRoom', 'saveCurrentRoom']),
        sortAlphabetical(a, b) {
            let roomA = a.name.toUpperCase();
            let roomB = b.name.toUpperCase();
            if (roomA < roomB) {
                return -1;
            }
            if (roomA > roomB) {
                return 1;
            }
            return 0;
        },
        checkLingeringUser(data) {
            for (const room of data) {
                if (room.users.some(room => room._id === this.getUserData._id)) {
                    return true;
                }
            }
            return false;
        },
        fetchRoomData() {
            chat
                .get('/room')
                .then(res => {
                    if (this.checkLingeringUser(res.data)) {
                        return chat.put(`/room/remove/users/all`, {
                            user_id: this.getUserData._id
                        });
                    } else {
                        this.$store.dispatch('updateRoomData', res.data);
                        this.rooms = res.data;
                    }
                })
                .then(res => {
                    if (res && res.data) {
                        this.rooms = res.data;
                    }
                })
                .catch(err => {
                    console.log(err);
                });
        },
        openModal() {
            this.$refs.createRoom.open();
        },
        enterRoom(room) {
            this.$router.push({ name: 'Room', params: { id: room._id } });
        },
        handleCreateRoom(e) {

            e.preventDefault();

            chat
                .post('/room', {
                    room_name: this.room_name,
                    user: this.$store.state.chat.authUser,
                    password: this.password
                })
                .then(res => {
                    if (res.data.errors) {
                        for (const error of res.data.errors) {
                            const [value] = Object.values(error);
                            this.errors.push(value);
                        }
                    } else {
                        this.$store.dispatch('addRoom', res.data);
                        this.room_name = null;
                        this.password = null;
                        this.$refs.createRoom.close();
                        this.getSocket.emit('roomAdded', res.data);
                    }
                })
                .catch(err => {
                    console.log(err);
                });

            setTimeout(() => {
                this.errors = [];
            }, 1500);
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
                })
                .catch(err => console.log(err));
        },
        handleRoomClick(room) {
            if (
                room.access ||
                this.getUserData._id === room.user._id ||
                room.accessIds.includes(this.getUserData._id)
            ) {
                this.enterRoom(room);
            } else {
                this.privateRoomName = room.name;
                this.$refs.privateRoom.setData('room', room);
                this.$refs.privateRoom.open();
            }
        },
        handlePrivateRoomCheck(e) {
            e.preventDefault();
            chat
                .post('/room/verify', {
                    room_name: this.$refs.privateRoom.modalData.room.name,
                    password: this.privateRoomPassword,
                    user: this.$store.state.chat.authUser,
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
                        this.privateRoomPassword = null;
                    } else {
                        if (res.data.success) {
                            this.enterRoom(this.$refs.privateRoom.modalData.room);
                        }
                    }

                    setTimeout(() => {
                        this.errors = [];
                    }, 1500);
                })
                .catch(err => console.log(err));
        }
    },
    created() {

        if(this.$store.state.user.memberId === ''){
            this.$router.push({
                name: "Error",
            })
        }

        this.getSocket.on('roomAdded', data => {
            this.rooms.unshift(JSON.parse(data));
        });

        this.getSocket.on('roomListUpdated', data => {
            this.rooms = this.rooms.filter(room => room._id !== JSON.parse(data).room._id);
        });

        this.getSocket.on('updateRooms', data => {
            this.rooms = JSON.parse(data).room;
        });

        this.getSocket.on('roomNameUpdated', data => {
            let updateIndex = 0;
            this.rooms.forEach((room, index) => {
                if (room._id === JSON.parse(data).room._id) {
                    updateIndex = index;
                }
            });
            this.rooms.splice(updateIndex, 1, JSON.parse(data).room);
        });
    },
    mounted() {
        this.fetchRoomData();
        if (this.errorMessage) {
            setTimeout(() => {
                this.errorMessage = '';
            }, 1500);
        }
    }
};
</script>

<style lang="scss">
@import '@/assets/scss/views/rooms.scss';
@import '@/assets/scss/components/form.scss';
@import '@/assets/scss/base/base.scss';

.chat-room {
    display: inline-block;
}

.room-link:hover {
    color: black;
    text-decoration: none;
}

.section-room-list {
    margin-top: -50px;
}

.create-btn {
    position: absolute;
    width: 60%;
    top: 4px;
}

</style>
