<template>
  <!-- Header Section Begin -->
  <header class="header">
    <div class="sign-container">
      <div v-if="user.signStatus === 'signIn'" @click="onClickSignOut">
        {{user.nickname}}님 안녕하세요! SIGN OUT</div>
      <div v-else @click="onOpenSign">SIGN IN</div>
    </div>
    <div class="container">
      <div class="row">
          <div class="col-lg-3 col-md-3">
              <div 
                class="header__logo"
                @click="onClickNav('Home')">
                  <img src="@/assets/img/logo/logo_col.png"/>
                  <!-- <a href="./index.html"><img src="@/assets/CREAMING-logo-white.png" alt=""></a> -->
              </div>
          </div>
          <div class="col-lg-6 col-md-6">
              <nav class="header__menu mobile-menu">
                  <ul>
                      <li class="active" @click="onClickNav('Home')">홈</li>
                      <li @click="onClickNav('Notice')">공지사항</li>
                      <li @click="onClickNav('Course', 'all')">강의</li>
                      <li @click="onClickNav('Creator', 'info')">강사</li>
                      <li v-if="this.user.memberId != ''" @click="onClickNav('Profile', 'info')">마이프로필</li>
                      <li @click="onClickNav('Profile', 'info')">마이프로필 테스트용</li>
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
          <input class="medium" v-model="signUpForm.nickname" placeholder="닉네임을 입력하세요."/>
          <button class="small" @click="onClickCheckNickname" @change="initialNicknameState">중복 체크</button><br/>
          <input class="big" v-model="signUpForm.phone" placeholder="010-1234-5678"/><br/>
          <input class="big" v-model="signUpForm.email" placeholder="이메일을 입력하세요."/><br/>
          <button class="big-btn" @click="onClickSignUp">회원 가입</button>
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
import { mapState } from 'vuex';

export default {
  data(){
    return {
      openSignModal: false,
      signUpForm : {
        nickname: '',
        phone: '',
        email: '',
      },
    }
  },
  computed : {
    ...mapState({
      user: state => state.user,
    }),
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
    onClickNav(pathName, paramsType) {
      if(pathName === "Profile" || pathName === "Creator"){
        this.$router.push({
          name: pathName,
          params: {
            type : paramsType,
          }
        })
      } else if(pathName === 'Course') {
        this.$router.push({
          name: pathName,
          params: {
            item: 'course',
            category : paramsType,
          }
        })
      } else {
        this.$router.push({
          name: pathName
        })
      }
    },
    onOpenSign() {
      this.openSignModal = true;
    },
    onCloseSign() {
      this.openSignModal = false;
    },
    onClickKakaoSignIn() {
      // this.onCloseSign();
      this.$store.dispatch('GET_KAKAO_INFO');
    },
    onClickSignUp() {
      if(!this.user.emailState){
        alert("이메일 중복체크를 해주세요.");
      } else {
        this.$store.dispatch('SIGN_UP', this.signUpForm);
      }
    },
    onClickSignOut() {
      this.$store.dispatch('SIGN_OUT');
    },
    onClickCheckNickname(){
      this.$store.dispatch('CHECK_EMAIL', this.signUpForm.nickname);
    },
    initialNicknameState(){
      this.$store.dispatch('SET_EMAIL_STATE', false);
    },
  }
}
</script>

<style>
.header .sign-container {
  color: white;
  text-align: right;
  font-size: 1rem;
  /* background-color: #f3f2ee; */
  background-color: #b30b0b;
  padding: 10px 50px 10px 0px;
}

.header .sign-container > div{
  cursor: pointer;
  /* color: black; */
  color: white;
  font-weight: bold;
}

.sign-up-container{
  text-align: center;
  padding: 30px 0px 30px 0px;
}

.sign-up-container input,
.sign-up-container button {
  margin: 10px 0px 10px 0px;
  height: 50px;
  border-radius: 10px;
  border: 1px solid #f3f2ee;
  padding-left: 10px;
}

.sign-up-container .small{
  width: 30%;
}

.sign-up-container .medium{
  margin: 10px 3% 10px 0px;
  width: 47%;
}

.sign-up-container .big{
  width: 80%;
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
