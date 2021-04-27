<template>
  <!-- Footer Section Begin -->
  <footer class="footer">
    <div class="container">
      <div class="row">
        <div class="col-lg-1 offset-lg-1 col-md-3 col-sm-6">
          <div class="footer__widget">
            <h6 @click="onClickTab('Home')">home</h6>
          </div>
        </div>
        <div class="col-lg-1 offset-lg-1 col-md-3 col-sm-6">
          <div class="footer__widget">
              <h6 @click="onClickTab('Notice')">notice</h6>
          </div>
        </div>
        <div class="col-lg-1 offset-lg-1 col-md-3 col-sm-6">
          <div class="footer__widget">
            <h6 @click="onClickTab('Course')">course</h6>
            <ul>
              <li @click="onClickTab('Course', 'korea')">한식</li>
              <li @click="onClickTab('Course', 'japan')">일식</li>
              <li @click="onClickTab('Course', 'usa')">양식</li>
              <li @click="onClickTab('Course', 'china')">중식</li>
              <li @click="onClickTab('Course', 'dessert')">디저트</li>
              <li @click="onClickTab('Course', 'drinks')">음료</li>
              <li @click="onClickTab('Course', 'dessert')">세계 음식</li>
              <li @click="onClickTab('Course', 'etc')">기타</li>
            </ul>
          </div>
        </div>
        <div class="col-lg-1 offset-lg-1 col-md-3 col-sm-6">
          <div class="footer__widget">
            <h6 @click="onClickTab('Creator')">creator</h6>
          </div>
        </div>
        <div class="col-lg-2 offset-lg-1 col-md-3 col-sm-6">
          <div class="footer__widget">
            <h6 @click="onClickTab('Profile')">profile</h6>
            <ul>
              <li @click="onClickTab('Profile', 'info')">정보 수정</li>
              <li @click="onClickTab('Profile', 'coupon')">내 쿠폰</li>
              <li @click="onClickTab('Profile', 'student')">수강한 강의</li>
              <li @click="onClickTab('Profile', 'teacher')">진행한 강의</li>
              <li @click="onClickTab('Profile', 'payment')">결제 내역</li>
            </ul>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12 text-center">
          <div class="footer__copyright__text">
            <img src="@/assets/img/logo/logo_row.png"/>
            <p>Copyright ©
              2021 Creaming
              All rights reserved
            </p>
          </div>
        </div>
      </div>
    </div>
  </footer>
  <!-- Footer Section End -->
</template>


<script>
export default {
  methods: {
    onClickTab(pathName, paramsType) {
      pathName === 'Profile'
      ? this.$router.push({
          name: pathName,
          params: {
            type: paramsType,
          }
        })
      : this.$router.push({
          name: pathName
        })
    },
  }
}
</script>

<style>
.footer .container h6{
  cursor: pointer;
}

.footer .container li{
  color: black;
  font-size: 15px;
  cursor: pointer;
}

.footer__copyright__text img{
  height: 200px;
  width: auto;
}
</style>
