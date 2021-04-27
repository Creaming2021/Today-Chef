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
                      <h6 @click="onClickNav('Notice')">notice</h6>
                  </div>
              </div>
              <div class="col-lg-1 offset-lg-1 col-md-3 col-sm-6">
                  <div class="footer__widget">
                      <h6>course</h6>
                      <ul>
                          <li><a href="Course">일식</a></li>
                          <li><a href="Course">양식</a></li>
                          <li><a href="Course">한식</a></li>
                          <li><a href="Course">중식</a></li>
                          <li><a href="Course">디저트</a></li>
                          <li><a href="Course">음료</a></li>
                          <li><a href="Course">세계음식</a></li>
                          <li><a href="Course">기타</a></li>
                      </ul>

                  </div>
              </div>
              <div class="col-lg-1 offset-lg-1 col-md-3 col-sm-6">
                  <div class="footer__widget">
                      <h6 @click="onClickNav('Creator')">creator</h6>
                  </div>
              </div>
              <div class="col-lg-2 offset-lg-1 col-md-3 col-sm-6">
                  <div class="footer__widget">
                      <h6>profile</h6>
                      <ul>
                          <li><a href="Profile">정보 수정</a></li>
                          <li><a href="Profile">내 쿠폰</a></li>
                          <li><a href="Profile">수강한 강의</a></li>
                          <li><a href="Profile">진행한 강의</a></li>
                          <li><a href="Profile">결제 내역</a></li>
                      </ul>
                  </div>
              </div>
          </div>
          <div class="row">
              <div class="col-lg-12 text-center">
                  <div class="footer__copyright__text">
                      <div>CREAMING 로고</div>
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
    onClickTab(pathName) {
      this.$router.push({
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
</style>
