<template>
  <div>
    <section class="shop spad disableselect" >
        <div class="container">
            <div class="row">
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
                                                    <li><div>내 쿠폰</div></li>
                                                    <li><div>내가 수강한 강의</div></li>
                                                    <li><div>내가 진행한 강의</div></li>
                                                    <li><div>결제 내역</div></li>
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

                <!-- 내 쿠폰 Section Start -->
                <div v-if="currentAtiveFilter==0" class="col-lg-9">
                  <div class="row">
                    <nav class="header__menu mobile-menu">
                        <ul>
                          <li class="active" @click="onClickNav('Home')">사용 가능 쿠폰</li>
                          <li @click="onClickNav('Notice')">사용 완료 쿠폰</li>
                          <li @click="onClickNav('Course')">사용 만료 쿠폰</li>
                        </ul>
                    </nav>
                  </div>
                  <div class="row">
                    <div class="col-lg-4 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals" v-for="(coupon,idx) in CouponList" :key="idx">
                      <CouponItem :coupon="coupon" :idx="idx" />
                    </div>
                  </div>
                </div>
                <!-- 내 쿠폰 Section End -->

                <!-- 내가 수강한 강의 Section Start -->
                <div v-if="currentAtiveFilter==1" class="col-lg-9">
                  <div class="row">
                    <div class="col-lg-4 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals" v-for="(course,idx) in CourseFilterList" :key="idx">
                      <CourseItem :course="course" :idx="idx" />
                    </div>
                  </div>
                  <div class="row">
                      <div class="col-lg-12">
                          <div class="product__pagination">
                              <a class="active" href="#">1</a>
                              <a href="#">2</a>
                          </div>
                      </div>
                  </div>
                </div>
                <!-- 내가 수강한 강의 Section End -->
            </div>
        </div>
    </section>
  </div>
</template>


<script>
import CourseItem from '@/components/CourseItem.vue';
import CouponItem from '@/components/CouponItem.vue';

export default {
  components: {
    CourseItem,
    CouponItem,
  },
  mounted() {
    this.initActiveFilter();
    this.setClickEventOfFilter();
    },
  methods : {
    initActiveFilter() {
      this.currentAtiveFilter = this.$router.query ? this.$router.query.active : 0;
      const mypageFilterArray = document.querySelector('#mypage-filter').querySelectorAll('li');
      mypageFilterArray[this.currentAtiveFilter].querySelector('div').classList.toggle('active');
    },
    setClickEventOfFilter() {
      const mypageFilterArray = document.querySelector('#mypage-filter').querySelectorAll('li');
      mypageFilterArray.forEach((li,idx) => {
        li.querySelector('div').addEventListener('click',() => {
          mypageFilterArray[this.currentAtiveFilter].querySelector('div').classList.toggle('active');
          this.currentAtiveFilter = idx;
          mypageFilterArray[this.currentAtiveFilter].querySelector('div').classList.toggle('active');
        })
      });
    },
  },
  data() {
    return {
      currentAtiveFilter : '',
      CourseFilterList: [
        {
          id: 1,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '한식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 2,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '한식',
          date: '2021.04.17',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '323',
          rate: '3.5',
        },
        {
          id: 3,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '양식',
          date: '2021.04.18',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '123',
          rate: '3.5',
        },
      ],
      CouponList : [
        {
          id: 3,
          thumbnail: 'https://bonmall.co.kr/Res/U/M/BONMALL/img/event/coupon01_01.png',
          category: '양식',
          date: '2021.04.18',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 3,
          thumbnail: 'https://bonmall.co.kr/Res/U/M/BONMALL/img/event/coupon01_01.png',
          category: '양식',
          date: '2021.04.18',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 3,
          thumbnail: 'https://bonmall.co.kr/Res/U/M/BONMALL/img/event/coupon01_01.png',
          category: '양식',
          date: '2021.04.18',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 3,
          thumbnail: 'https://bonmall.co.kr/Res/U/M/BONMALL/img/event/coupon01_01.png',
          category: '양식',
          date: '2021.04.18',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 3,
          thumbnail: 'https://bonmall.co.kr/Res/U/M/BONMALL/img/event/coupon01_01.png',
          category: '양식',
          date: '2021.04.18',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 3,
          thumbnail: 'https://bonmall.co.kr/Res/U/M/BONMALL/img/event/coupon01_01.png',
          category: '양식',
          date: '2021.04.18',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 3,
          thumbnail: 'https://bonmall.co.kr/Res/U/M/BONMALL/img/event/coupon01_01.png',
          category: '양식',
          date: '2021.04.18',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '123',
          rate: '3.5',
        },
      ]
    }
  },

}
</script>

<style scoped>
.disableselect {
  -webkit-touch-callout: none; /* iOS Safari */
  -webkit-user-select: none;   /* Chrome/Safari/Opera */
  -khtml-user-select: none;    /* Konqueror */
  -moz-user-select: none;      /* Firefox */
  -ms-user-select: none;       /* Internet Explorer/Edge*/
   user-select: none;          /* Non-prefixed version, currently 
                                  not supported by any browser */
}
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
</style>

