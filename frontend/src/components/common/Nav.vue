<template>
  <!-- Header Section Begin -->
  <header class="header">
    <div class="sign-container">
      <div v-if="user.signStatus === 'signIn'" @click="setSignOut">
        {{user.userNickname}}님 안녕하세요! SIGN OUT</div>
      <div v-else @click="onOpenSign">SIGN IN</div>
    </div>
    <div class="container">
      <div class="row">
          <div class="col-lg-3 col-md-3">
              <div class="header__logo">
                  로고자리
                  <!-- <a href="./index.html"><img src="@/assets/CREAMING-logo-white.png" alt=""></a> -->
              </div>
          </div>
          <div class="col-lg-6 col-md-6">
              <nav class="header__menu mobile-menu">
                  <ul>
                      <li class="active" @click="onClickNav('Home')">홈</li>
                      <li @click="onClickNav('Notice')">공지사항</li>
                      <li @click="onClickNav('Course')">강의</li>
                      <li @click="onClickNav('Creator')">강사</li>
                      <li @click="onClickNav('Profile')">마이프로필</li>
                  </ul>
              </nav>
          </div>
      </div>
      <div class="canvas__open"><i class="fa fa-bars"></i></div>
    </div>
    <b-modal v-model="openSignModal" size="md" centered hide-footer hide-header>
      <p class="sign-modal-btn" @click="onCloseSign">X</p>
      <div 
        v-if="user.signStatus === 'signUp'"
        class="sign-up-container">
          <input v-model="signUpForm.phoneNumber" placeholder="010-1234-5678"/><br/>
          <input v-model="signUpForm.email" placeholder="이메일을 입력하세요."/>
          <button @click="onClickSignUp">회원 가입</button>
      </div>
      <div v-else>
        <img 
          src="@/assets/img/kakao/kakao_login_large_wide.png"
          @click="onClickKakaoSignIn"/>
      </div>
    </b-modal>
  </header>
  <!-- Header Section End -->
</template>

<script>
import { mapActions, mapState } from 'vuex';

export default {
  data(){
    return {
      openSignModal: false,
      signUpForm : {
        phoneNumber: '',
        email: '',
      }
    }
  },
  computed : {
    ...mapState(['user']),
  },
  watch: {
    user : {
      handler: function () {
        if(this.user.signStatus == 'signIn'){
          this.onCloseSign();
        }
      },
      deep: true,
    }
  },
  methods : {
    ...mapActions(['getKakaoInfo','setSignOut', 'setSignUp']),
    onClickNav(pathName) {
      this.$router.push({
        name: pathName
      })
    },
    onOpenSign() {
      this.openSignModal = true;
    },
    onCloseSign() {
      this.openSignModal = false;
    },
    onClickKakaoSignIn() {
      // this.onCloseSign();
      this.getKakaoInfo();
    },
    onClickSignUp() {
      this.setSignUp(this.signUpForm);
    }   
  }
}
</script>

<style>
.header .sign-container {
  color: white;
  text-align: right;
  font-size: 1rem;
  background-color: black;
  padding: 10px 50px 10px 0px;
}

.header .sign-container > div{
  cursor: pointer;
}

.sign-up-container{
  text-align: center;
  padding-top: 50px;
}

.sign-up-container input,
.sign-up-container button{
  margin: 10px 0px 10px 0px;
  width: 80%;
  height: 50px;
  border-radius: 10px;
  border: 1px solid #f3f2ee;
  padding-left: 10px;
}

.sign-up-container button{
  margin: 50px 0px 50px 0px;
  background-color: #f3f2ee;
  border: none;
  font-weight: bold;
}

.sign-up-container button:hover{
  background-color: #e53637;
  font-weight: bold;
  color: white;
}

.sign-modal-btn{
  text-align: right;
  cursor: pointer;
}

</style>
