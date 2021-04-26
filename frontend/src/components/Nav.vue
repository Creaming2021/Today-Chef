<template>
  <!-- Header Section Begin -->
  <header class="header">
    <div class="sign-container">
      <div @click="onOpenSign">SIGN IN</div>
      <div @click="onClickSignOut">SIGN OUT</div>
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
    <b-modal v-model="openSignModal" size="lg" centered hide-footer>
      <img 
        src="@/assets/img/kakao/kakao_login_large_wide.png"
        @click="kakaoSignIn"/>
    </b-modal>
  </header>
  <!-- Header Section End -->
</template>

<script>
import { getKakaoLogin, signOut } from '@/api/kakao.js';

export default {
  data(){
    return {
      openSignModal: false,
    }
  },
  methods : {
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
    kakaoSignIn(){
      getKakaoLogin();
    },
    onClickSignOut(){
      signOut();
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


</style>
