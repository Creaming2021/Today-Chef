<template>
  <div class="container">
    <div class="personal-card">
      <div class="personal-header">프로필 이미지 관리</div>
      <div class="personal-content">
        <div>
          <img 
            class="small-profile-image"
            :src="member.profileImage || 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBuHwFCvVVSVGc-l7HNr2xuMVs-ru25GcUUmjpIp-pBgx5ZAlyN510nbsXzyLn5X8RdmY&usqp=CAU'" alt="">
        </div>
        <h4>{{member.nickname}}</h4>
      </div>
      <div><button @click="onClickChange('img')">수정</button></div>
    </div>
    <div class="personal-card">
      <div class="personal-header">연락처 관리</div>
      <div class="personal-content">
        <h5><b-icon icon="telephone"/> {{member.phone}}</h5>
        <h5><b-icon icon="envelope"/> {{member.email}}</h5>
      </div>
      <div><button @click="onClickChange('phone&email')">수정</button></div>
    </div>
    <!-- <div class="personal-card">
      <div class="personal-header">배송지 관리</div>
      <div v-if="this.member.address">주소 : {{member.address.address}}</div>
      <div v-else>주소 : 기본 배송지를 입력해주세요.</div>
      <div><button @click="onClickChange('address')">수정</button></div>
    </div> -->
      
    <b-modal v-model="openModifyModal" size="md" centered hide-footer hide-header>
      <p class="sign-modal-btn" @click="onCloseModal">X</p>
      <div 
        v-if="type === 'img'"
        class="modify-container">
          <img 
            @click="selectImage"
            class="profile-image"
            :src="profileUrl !== '' ? profileUrl : (modifyForm.profileImage || 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBuHwFCvVVSVGc-l7HNr2xuMVs-ru25GcUUmjpIp-pBgx5ZAlyN510nbsXzyLn5X8RdmY&usqp=CAU')"/>
          <input 
            type="file"
            ref="imgFile"
            class='img-input-tag' 
            @change="changeImg" 
          />
          <input 
            class="small" 
            v-model="modifyForm.nickname" 
            @keyup="initialNicknameState"
            placeholder="닉네임을 입력하세요."/>
          <button @click="onClickCheckNickname">중복 체크</button><br/>
          <button class="big-btn" @click="onPutMemberInfo">수정 완료</button>
      </div>
      <div v-else-if="type === 'phone&email'" class="modify-container">
        <input class="big" v-model="modifyForm.phone" placeholder="010-1234-5678"/><br/>
        <button class="big-btn" @click="onPutMemberInfo">수정 완료</button>
      </div>
      <!-- <div v-else-if="type === 'address'" class="modify-container">
        기본 배송지 : <input class="big" v-model="modifyForm.address.address" placeholder="기본 배송지를 입력하세요."/><br/>
        <button class="big-btn" @click="onPutMemberInfo">수정 완료</button>
      </div> -->
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
      },
      profileUrl: '',
    }
  },
  computed : {
    ...mapState({
      member: state => state.member.memberInfo,
      nicknameState: state => state.user.nicknameState,
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
      this.profileUrl = '',
      this.openModifyModal = true;
    },
    onCloseModal() {
      this.openModifyModal = false;
    },
    onPutMemberInfo(){
      if(this.member.nickname !== this.modifyForm.nickname && !this.nicknameState){
        this.$swal.fire({
          icon: 'error',
          text: '닉네임 중복체크를 해주세요.',
        });
      }else{
        this.$store.dispatch('PUT_MEMBER_INFO', this.modifyForm);
        this.onCloseModal();
      }
    },
    changeImg(event) {
      this.profileUrl = URL.createObjectURL(event.target.files[0]);
      this.modifyForm.profileImage = event.target.files[0];
    },
    selectImage(){
      this.$refs.imgFile.click();
    },
    onClickCheckNickname(){
      this.$store.dispatch('CHECK_NICKNAME', this.modifyForm.nickname);
    },
    initialNicknameState(){
      if(this.member.nickname !== this.modifyForm.nickname){
        this.$store.dispatch('SET_NICKNAME_STATE', false);
      }
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
  position: relative;
}
.personal-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  margin-top: 20px;
}
.personal-header {
  font-size: 18px;
  font-weight: 700;
}
.personal-card button {
  position: absolute;
  color: #111111;
  font-size: 1rem;
  font-weight: 700;
  border: none;
  padding: 6px 25px;
  cursor: pointer;
  right: 10px;
  top: 10px;
  border-radius: 10px;
}
.personal-card button:hover {
  color: white;
  background-color: #e53637;
}
.personal-content .small-profile-image {
  width: 300px;
  height: 300px;
  border-radius: 100%;
  margin: 10px 0px 20px 0px;
  object-fit: cover;
}
.profile-image{
  display: block;
  margin: auto;
  width: 400px;
  height: 400px;
  object-fit: cover;
  cursor: pointer;
}
.img-input-tag{
  display: none;
}
.modify-container{
  text-align: center;
}
.modify-container button{
  margin: 10px auto 10px auto;
  font-weight: bold;
  border-radius: 10px;
  border: none;
  padding: 20px 50px 20px 50px;
}
.modify-container button:hover{
  background-color: #e53637;
  color: white;
}
.modify-container .big{
  margin: 20px 0px 20px 0px;
  border-radius: 10px;
  border: 1px solid #afafaf;
  width: 400px;
  height: 60px;
  padding-left: 30px;
}

.modify-container .small{
  margin: 20px 20px 20px 0px;
  border-radius: 10px;
  border: 1px solid #afafaf;
  width: 200px;
  height: 60px;
  padding-left: 30px;
}
</style>