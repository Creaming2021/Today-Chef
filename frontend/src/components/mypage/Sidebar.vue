<template>
  <!-- 마이페이지 사이드바 Section Start -->
  <div class="col-lg-3">
    <div class="shop__sidebar">
      <div class="shop__sidebar__accordion">
        <div class="accordion" id="accordionExample">
          <div class="card">
            <div class="card-heading">
              <div>내 정보</div>
            </div>
            <div id="filter" class="collapse show" data-parent="#accordionExample">
              <div class="card-body">
                <div class="shop__sidebar__price">
                  <ul id="mypage-filter">
                    <li :class="type==='info' && 'active'" @click="changeFilter('info')">내 정보 수정</li>
                    <li :class="type==='coupon' && 'active'" @click="changeFilter('coupon')">내 쿠폰</li>
                    <li :class="type==='student' && 'active'" @click="changeFilter('student')">내가 수강한 강의</li>
                    <li :class="type==='teacher' && 'active'" @click="changeFilter('teacher')">내가 진행한 강의</li>
                    <li :class="type==='payment' && 'active'" @click="changeFilter('payment')">결제 내역</li>
                  </ul>
                </div>
              </div>
            </div>
          </div>  
        </div>
      </div>
    </div>
  </div>
  <!-- 마이페이지 사이드바 Section End -->
</template>


<script>
export default {
  props: {
    type: String,
  },
  methods: {
    changeFilter(type){
      this.$router.push({
        name: 'Profile',
        params: {
          type: type,
        }
      })
    }
  }
}
</script>

<style>
.shop__sidebar__accordion .card-heading div {
  color: #111111;
  font-size: 18px;
  font-weight: 700;
  text-transform: uppercase;
  display: block;
  cursor : default;
}

.shop__sidebar__accordion .card-heading div:after {
	content: "";
	font-family: "FontAwesome";
	font-size: 24px;
	font-weight: 700;
	color: #111111;
	position: absolute;
	right: 0;
	top: 2px;
	line-height: 20px;
}

.shop__sidebar__accordion .active { 
	color: #111111;
	font-weight: 700;
}

.shop__sidebar__price ul li div{
	color: #b7b7b7;
	font-size: 15px;
	line-height: 32px;
	-webkit-transition: all, 0.3s;
	-o-transition: all, 0.3s;
	transition: all, 0.3s;
	cursor: pointer;
}

.shop__sidebar__price ul li div:hover{
  color: #111111;
  text-decoration: underline;
}

.active {
  color: #111111;
  font-weight: 700;
}

#mypage-filter li {
  cursor: pointer;
}

#mypage-filter li:hover {
  text-decoration: underline;
}
</style>
