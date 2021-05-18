<template>
  <div>
    <template slot="header">
      <div class="text-upper modal-title">Create Room</div>
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
              placeholder="1자 이상 7자 이하"
              pattern=".{1,7}"
              v-model.trim="room_name"
          />
          <label for="room_name" class="form__label">채팅방 이름</label>
        </div>
        <div class="form__input-group">
          <ion-icon name="key" class="form__icon"></ion-icon>
          <input
            type="password"
            name="password"
            class="form__control"
            placeholder="5자 이상 10자 이하"
            pattern=".{5,10}"
            v-model.trim="password"
          />
          <label for="password" class="form__label">비밀번호 (선택)</label>
        </div>
        <button type="submit" class="primary-btn">생성 완료</button>
      </form>
    </template>
  </div>
</template>

<script>
import { chat } from '@/api/instance.js';

export default {
  methods: {
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
              this.$swal.fire({
                icon: 'error',
                text: value,
              });
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
  },
}
</script>

<style>

</style>