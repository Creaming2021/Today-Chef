<template>
  <div>
    <div
      slot="body"
      class="form form--nbs pt-3"
    >
      <div class="form__input-group">
        <ion-icon name="list-box" class="chat-create__icon"></ion-icon>
        <input
            type="text"
            name="room_name"
            class="form__control"
            placeholder="1자 이상 7자 이하"
            pattern=".{1,7}"
            v-model.trim="courseInfo.roomName"
        />
        <label for="room_name" class="form__label">채팅방 이름</label>
      </div>
      <div class="form__input-group">
        <ion-icon name="key" class="chat-create__icon"></ion-icon>
        <input
          type="password"
          name="password"
          class="form__control"
          placeholder="5자 이상 10자 이하"
          pattern=".{5,10}"
          v-model.trim="courseInfo.password"
        />
        <label for="password" class="form__label">비밀번호</label>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    handleCreateRoom: Function,
    courseInfo: Object,
  },
}
</script>

<style scoped>
.chat-create__icon{
  position: absolute;
  top: 40px;
  right: 40px;
  font-size: 1.5em;
  margin: 0 0.8em;
  z-index: 2;
}
</style>