<template>
  <!-- Header Section Begin -->
  <header class="header">
    <div class="sign-container">
      <div v-if="user.signStatus === 'signIn'">
        {{user.nickname}}님 안녕하세요!
        <span class="sign" @click="onClickSignOut">SIGN OUT</span></div>
      <div class="sign" v-else @click="onOpenSign">SIGN IN</div>
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
        <div class="col-lg-9 col-md-9">
          <nav class="header__menu mobile-menu">
            <ul>
              <li :class="currentTab === 'Home' && 'active'" @click="onClickNav('Home')">홈</li>
              <li :class="currentTab === 'Notice' && 'active'" @click="onClickNav('Notice')">공지사항</li>
              <li :class="currentTab === 'Product' && 'active'" @click="onClickNav('Product', 'all')">밀키트</li>
              <li :class="currentTab === 'Course' && 'active'" @click="onClickNav('Course', 'all')">클래스</li>
              <template v-if="this.user.memberId != ''">
                <li 
                  :class="currentTab === 'RoomList' && 'active'"
                  @click="onClickNav('RoomList')">채팅</li>
                <li 
                  :class="currentTab === 'Cart' && 'active'"
                  @click="onClickNav('Cart')">장바구니</li>
                <li
                  :class="currentTab === 'MyCourse' && 'active'"  
                  @click="onClickNav('MyCourse', 'list')">MY CLASS</li>
                <li 
                  :class="currentTab === 'Profile' && 'active'"
                  @click="onClickNav('Profile', 'info')">MY PAGE</li>
              </template>
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
          <input 
            class="medium" 
            v-model="nickname" 
            @keyup="initialNicknameState"
            placeholder="닉네임을 입력하세요."/>
          <button class="small" @click="onClickCheckNickname">중복 체크</button><br/>
          <input class="big" @keyup="autoHypenPhone" v-model="signUpForm.phone" placeholder="010-1234-5678" maxlength="13"/><br/>
          <input 
            type="email"
            class="medium" 
            v-model="email" 
            @keyup="initialEmailState"
            placeholder="이메일을 입력하세요."/>
          <button class="small" @click="onClickCheckEmail">중복 체크</button><br/>
          <button class="big-btn" @click="onClickSignUp">회원 가입</button>
      </div>
      <div v-else>
        <img 
          class="kakao-signin-btn"
          src="@/assets/img/kakao/kakao_login_large_wide.png"
          @click="onClickKakaoSignIn"/>
      </div>
    </b-modal>
  </header>
  <!-- Header Section End -->
</template>

<script>
export default {
  data(){
    return {
      openSignModal: false,
      signUpForm : {
        nickname: '',
        phone: '',
        email: '',
      },
      currentTab: 'Home',
      email: '',
      nickname: '',
    }
  },
  computed : {
    user: function(){
      if(this.$store.state.user.signStatus === 'signIn'){
        this.onCloseSign();
      }
      return this.$store.state.user;
    }
  },
  methods: {
    onClickNav(pathName, paramsType) {
      this.currentTab = pathName;
      if(pathName === "Profile"){
        this.$router.push({
          name: pathName,
          params: {
            type : paramsType,
          }
        })
      } else if(pathName === 'Creator') {
        this.$router.push({
          name: pathName,
          params: {
            mode: 'create',
            type : paramsType,
          }
        })
      } else if(pathName === 'Product') {
        this.$router.push({
          name: 'ItemList',
          params: {
            item: 'product',
            category : paramsType,
          }
        })
      } else if(pathName === 'Course') {
        this.$router.push({
          name: 'ItemList',
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
      this.signUpForm = {
        nickname: '',
        phone: '',
        email: '',
      };
      this.openSignModal = true;
    },
    onCloseSign() {
      this.openSignModal = false;
    },
    onClickKakaoSignIn() {
      this.$store.dispatch('GET_KAKAO_INFO');
    },
    onClickSignUp() {
      if(!this.user.nicknameState){
        this.$swal.fire({
          icon: 'error',
          text: '닉네임 중복체크를 해주세요.',
        });
      } else if(!this.user.emailState){
        this.$swal.fire({
          icon: 'error',
          text: '이메일 중복체크를 해주세요.',
        });
      } else if (this.signUpForm.phone == '') {
        this.$swal.fire({
          icon: 'error',
          text: '전화번호를 입력해주세요.',
        });
      } else {
        this.signUpForm.nickname = this.nickname;
        this.signUpForm.email = this.email;
        this.$store.dispatch('SIGN_UP', this.signUpForm);
      }
    },
    onClickSignOut() {
      this.$store.dispatch('SIGN_OUT');
    },
    onClickCheckEmail(){
      const regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      if (this.email == '') {
        this.$swal.fire({
          icon: 'error',
          text: '이메일을 입력해주세요.',
        });
        this.initialEmailState();
        return;
      } else if (this.email.match(regExp) == null) {
        this.$swal.fire({
          icon: 'error',
          text: '올바른 이메일을 입력해주세요.',
        });
        this.initialEmailState();
        return;
      }
      this.$store.dispatch('CHECK_EMAIL', this.email);
    },
    onClickCheckNickname(){
      if (this.nickname == '') {
        this.$swal.fire({
          icon: 'error',
          text: '닉네임을 입력해주세요.',
        });
        this.initialNicknameState();
        return;
      }
      this.$store.dispatch('CHECK_NICKNAME', this.nickname);
    },
    initialEmailState(){
      this.$store.dispatch('SET_EMAIL_STATE', false);
    },
    initialNicknameState(){
      this.$store.dispatch('SET_NICKNAME_STATE', false);
    },
    autoHypenPhone(event) {
      let str = event.target.value.replace(/[^0-9]/g, '');
      let tmp = '';
      if( str.length < 4){
          return str;
      }else if(str.length < 7){
          tmp += str.substr(0, 3);
          tmp += '-';
          tmp += str.substr(3);
      }else if(str.length < 11){
          tmp += str.substr(0, 3);
          tmp += '-';
          tmp += str.substr(3, 3);
          tmp += '-';
          tmp += str.substr(6);
      }else{              
          tmp += str.substr(0, 3);
          tmp += '-';
          tmp += str.substr(3, 4);
          tmp += '-';
          tmp += str.substr(7);
      }
      this.signUpForm.phone = tmp;
    },
  },
  watch: {
    email() {
      this.$store.dispatch('SET_EMAIL_STATE', false);
    },
    nickname() {
      this.$store.dispatch('SET_NICKNAME_STATE', false);
    }
  }
}
</script>

<style>
.header .sign-container {
  color: white;
  text-align: right;
  font-size: 1rem;
  /* background-color: #f3f2ee; */
  background-color: #e53637;
  padding: 10px 50px 10px 0px;
}

.header .sign-container .sign{
  margin-left: 8px;
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

.sign-up-container .big,
.sign-up-container .big-btn{
  width: 80%;
}

.sign-up-container .big-btn{
  margin-top: 50px;
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

.kakao-signin-btn{
  cursor: pointer; 
}
</style>
