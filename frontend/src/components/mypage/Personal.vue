<template>
  <div class="container">
    <div class="personal-card">
      <div class="personal-header">프로필 이미지 관리</div>
      <div class="personal-content">
        <div><img :src="member.profileImage" alt=""></div>
        <div>닉네임 : {{member.nickname}}</div>
      </div>
      <div><button @click="onClickChange('img')">수정</button></div>
    </div>
    <div class="personal-card">
      <div class="personal-header">연락처 관리</div>
      <div>휴대전화 : {{member.phone}}</div>
      <div>연락처 이메일 : {{member.email}}</div>
      <div><button @click="onClickChange('phone&email')">수정</button></div>
    </div>
    <div class="personal-card">
      <div class="personal-header">배송지 관리</div>
      <div v-if="this.member.address">주소 : {{member.address.address}}</div>
      <div v-else>주소 : 기본 배송지를 입력해주세요.</div>
      <div><button @click="onClickChange('address')">수정</button></div>
    </div>
      
    <b-modal v-model="openModifyModal" size="md" centered hide-footer hide-header>
      <p class="sign-modal-btn" @click="onCloseModal">X</p>
      <div 
        v-if="type === 'img'"
        class="sign-up-container">
          <img :src="modifyForm.profileImage"/>
          닉네임 : <input class="big" v-model="modifyForm.nickname" placeholder="닉네임을 입력하세요."/><br/>
          <button class="big-btn" @click="onPutMemberInfo">수정 완료</button>
      </div>
      <div v-else-if="type === 'phone&email'" >
        연락처 : <input class="big" v-model="modifyForm.phone" placeholder="010-1234-5678"/><br/>
        <button class="big-btn" @click="onPutMemberInfo">수정 완료</button>
      </div>
      <div v-else-if="type === 'address'" >
        기본 배송지 : <input v-model="modifyForm.address.address" placeholder="기본 배송지를 입력하세요."/><br/>
        <button class="big-btn" @click="onPutMemberInfo">수정 완료</button>
      </div>
    </b-modal>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  data(){
    return {
      openModifyModal: false,
      type: '',
      modifyForm: {
        memberId: '',
        address: {
          address: '',
        },
        nickname: '',
        phone: '',
        profileImage: '',
      }
    }
  },
  computed : {
    ...mapState({
      member: state => state.member.memberInfo,
    }),
  },
  methods : {
    onClickChange(type) {
      this.type = type;
      this.setModifyForm();
      this.onOpenModal();
    },
    setModifyForm(){
      this.modifyForm = {
        memberId: this.member.memberId,
        address: {
          address: this.member.address && this.member.address.address || '',
        },
        nickname: this.member.nickname,
        phone: this.member.phone,
        profileImage: this.member.profileImage || '',
      }
    },
    onOpenModal() {
      this.openModifyModal = true;
    },
    onCloseModal() {
      this.openModifyModal = false;
    },
    onPutMemberInfo(){
      this.$store.dispatch('PUT_MEMBER_INFO', this.modifyForm);
      this.onCloseModal();
    },
  },
}
</script>

<style scoped>
.container {
  width: 80%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.personal-card {
  display: flex;
  flex-direction: column;
  padding: 20px 30px 30px 20px;
  width : 100%;
  border: 2px solid #e5e5e5;
  margin-bottom : 15px;
}
.personal-content {
  display: flex;
  align-items: center;
}
.personal-header {
  font-size: 18px;
  font-weight: 700;
}
.personal-card button {
  color: #111111;
  font-size: 15px;
  font-weight: 700;
  text-transform: uppercase;
  display: inline-block;
  border: 1px solid #e5e5e5;
  padding: 6px 25px;
  margin-top: 30px;
  cursor: pointer;
}
.personal-content img {
  width: 100px;
  height: 100px;
  border-radius: 100%;
  margin: 10px 50px 0px 0px;
}
</style>