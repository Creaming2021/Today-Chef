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
                                                    <li>내 정보 수정</li>
                                                    <li>내 쿠폰</li>
                                                    <li>내가 수강한 강의</li>
                                                    <li>내가 진행한 강의</li>
                                                    <li>결제 내역</li>
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

                <!-- 내 정보 수정 Section Start -->
                <div v-show="currentAtiveFilter==0" class="col-lg-9">
                  <div class="row">
                    <Personal :personalData="personalData"/>
                  </div>
                </div>
                <!-- 내 정보 수정 Section Start -->

                <!-- 내 쿠폰 Section Start -->
                <div v-show="currentAtiveFilter==1" class="col-lg-9">
                  <div class="row">
                    <nav class="coupon-nav header__menu mobile-menu">
                        <ul>
                          <li @click="onClickCouponFilter('possible')">사용 가능 쿠폰</li>
                          <li @click="onClickCouponFilter('done')">사용 완료 쿠폰</li>
                          <li @click="onClickCouponFilter('impossible')">사용 만료 쿠폰</li>
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
                <div v-show="currentAtiveFilter==2" class="col-lg-9">
                  <div class="row">
                    <div class="col-lg-4 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals" v-for="(course,idx) in enrolledCourseList" :key="idx">
                      <CourseItem :course="course" :idx="idx" :type="'otherClass'" />
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

                <!-- 내가 진행한 강의 Section Start -->
                <div v-show="currentAtiveFilter==3" class="col-lg-9">
                  <div class="row">
                    <div class="col-lg-4 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals" v-for="(course,idx) in myCourseList" :key="idx">
                      <CourseItem :course="course" :idx="idx" :type="'myClass'" />
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

                <!-- 결제 내역 Section Start -->
                <div v-show="currentAtiveFilter==4" class="col-lg-9">
                  <div class="row">
                    <div class="col-lg-12">
                        <div class="shopping__cart__table">
                          <table>
                            <thead>
                                <tr>
                                  <th>강의</th>
                                  <th>결제일</th>
                                  <th>가격</th>
                                </tr>
                            </thead>
                            <tbody v-for="(payment,idx) in myPaymentList" :key="idx">
                              <PaymentItem :payment="payment" :idx="idx" />
                            </tbody>
                          </table>
                        </div>
                    </div>
                  </div>
                </div>
                <!-- 결제 내역 Section End -->
            </div>
        </div>
    </section>
  </div>
</template>


<script>
import CourseItem from '@/components/common/CourseItem.vue';
import CouponItem from '@/components/common/CouponItem.vue';
import PaymentItem from '@/components/common/PaymentItem.vue';
import Personal from '@/components/mypage/Personal.vue';

export default {
  components: {
    CourseItem,
    CouponItem,
    PaymentItem,
    Personal
  },
  mounted() {
    this.initActiveFilter();
    this.setClickEventOfFilter(document.querySelector('#mypage-filter').querySelectorAll('li'),'currentAtiveFilter');
    this.setClickEventOfFilter(document.querySelector('.coupon-nav').querySelectorAll('li'),'couponAtiveFilter');
    },
  methods : {
    initActiveFilter() {
      this.currentAtiveFilter = this.$router.query ? this.$router.query.active : 0;
      this.couponAtiveFilter = 0;
      const mypageFilterArray = document.querySelector('#mypage-filter').querySelectorAll('li');
      const mypageCouponActiveArray = document.querySelector('.coupon-nav').querySelectorAll('li');
      mypageFilterArray[this.currentAtiveFilter].classList.toggle('active');
      mypageCouponActiveArray[this.couponAtiveFilter].classList.toggle('active');
    },
    setClickEventOfFilter(array,type) {
      array.forEach((element,idx) => {
        element.addEventListener('click',() => {
          array[this[type]].classList.toggle('active');
          this[type] = idx;
          array[this[type]].classList.toggle('active');
        })
      });
    },
    onClickCouponFilter(state) {
      if (state === 'done') {// 완료 쿠폰
        alert('사용완료쿠폰 정렬')
      }
      else if (state === 'possible') { //사용 가능 쿠폰
        alert('사용가능쿠폰 정렬')
      }
      else { //사용 만료 쿠폰
        alert('사용만료쿠폰 정렬')
      }
    },
    onClickMyClass() {
      console.log('course')
    }
  },
  data() {
    return {
      currentAtiveFilter : '',
      couponAtiveFilter : '',
      personalData : {
        name : "이병훈",
        img : "https://blog.kakaocdn.net/dn/cyOIpg/btqx7JTDRTq/1fs7MnKMK7nSbrM9QTIbE1/img.jpg",
        email : "er******@n*******.com",
        phone : "+82 10-4***-8***",
        address : "경기도 수원시 영통구 청명로 100 (영통동, 건영1차아파트) 000동 0000호",
      },
      enrolledCourseList: [
        {
          id: 1,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '한식',
          date: '2021.04.16',
          name: '어렵게 배워보는 갈비찜',
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
          name: '보통으로 배워보는 갈비찜',
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
        {
          id: 3,
          thumbnail: 'https://cdn.class101.net/images/70d3e20b-0c66-4321-be69-6b4f682017d6/375xauto.webp',
          category: '양식',
          date: '2021.04.18',
          name: '쉽게 까먹는 일본가정식',
          teacher: '다까무라',
          price: '50600',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 3,
          thumbnail: 'https://cdn.class101.net/images/25b73a82-9387-44b9-86bd-fe312461e139/375xauto.webp',
          category: '양식',
          date: '2021.02.12',
          name: '낭만 캠핑을 위한 비비큐',
          teacher: '미국소',
          price: '52600',
          likeCnt: '123',
          rate: '3.2',
        },
      ],
      myCourseList: [
        {
          id: 1,
          thumbnail: 'https://cdn.class101.net/images/8fd1878d-9cae-4d48-8dad-52a0fcff7127/2048xauto',
          category: '한식',
          date: '2021.04.16',
          name: '취미로 하는 수비드 고기 요리',
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
          thumbnail: 'https://cdn.class101.net/images/860b79ca-e228-404a-816a-573b69fae710/375xauto.webp',
          category: '양식',
          date: '2021.04.18',
          name: '낭만브레드의 김탁구',
          teacher: '낭만브레드',
          price: '50000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 3,
          thumbnail: 'https://cdn.class101.net/images/caa0d8ae-6d24-4f1c-b3f3-00961541e2be/375xauto.webp',
          category: '양식',
          date: '2021.04.18',
          name: '감각적인 디저트를 완성하다!',
          teacher: '미완성식탁',
          price: '56300',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 1,
          thumbnail: 'https://cdn.class101.net/images/8fd1878d-9cae-4d48-8dad-52a0fcff7127/2048xauto',
          category: '한식',
          date: '2021.04.16',
          name: '취미로 하는 수비드 고기 요리',
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
      ],
      myPaymentList : [
        {
          id: 1,
          thumbnail: 'https://cdn.class101.net/images/8fd1878d-9cae-4d48-8dad-52a0fcff7127/2048xauto',
          category: '양식',
          date: '2021.04.18',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '123',
          rate: '3.5',
          enrolledAddress : '대전 유성구 온천서로 2',
          deliveryCorp : '대한통운',
          deliveryNum : 12351,
          deliveryReq : '상온에 두면 안됩니다.'
        },
        {
          id: 1,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '한식',
          date: '2021.04.16',
          name: '어렵게 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '123',
          rate: '3.5',
          enrolledAddress : '대전 유성구 온천서로 2',
          deliveryCorp : '대한통운',
          deliveryNum : 12351,
          deliveryReq : '상온에 두면 안됩니다.'
        },
        {
          id: 2,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '한식',
          date: '2021.04.17',
          name: '보통으로 배워보는 갈비찜',
          teacher: '김한식',
          price: '50000',
          likeCnt: '323',
          rate: '3.5',
          enrolledAddress : '대전 유성구 온천서로 2',
          deliveryCorp : '대한통운',
          deliveryNum : 12351,
          deliveryReq : '상온에 두면 안됩니다.'
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
          enrolledAddress : '대전 유성구 온천서로 2',
          deliveryCorp : '대한통운',
          deliveryNum : 12351,
          deliveryReq : '상온에 두면 안됩니다.'
        },
        {
          id: 3,
          thumbnail: 'https://cdn.class101.net/images/70d3e20b-0c66-4321-be69-6b4f682017d6/375xauto.webp',
          category: '양식',
          date: '2021.04.18',
          name: '쉽게 까먹는 일본가정식',
          teacher: '다까무라',
          price: '50600',
          likeCnt: '123',
          rate: '3.5',
          enrolledAddress : '대전 유성구 온천서로 2',
          deliveryCorp : '대한통운',
          deliveryNum : 12351,
          deliveryReq : '상온에 두면 안됩니다.'
        },
        {
          id: 3,
          thumbnail: 'https://cdn.class101.net/images/25b73a82-9387-44b9-86bd-fe312461e139/375xauto.webp',
          category: '양식',
          date: '2021.02.12',
          name: '낭만 캠핑을 위한 비비큐',
          teacher: '미국소',
          price: '52600',
          likeCnt: '123',
          rate: '3.2',
          enrolledAddress : '대전 유성구 온천서로 2',
          deliveryCorp : '대한통운',
          deliveryNum : 12351,
          deliveryReq : '상온에 두면 안됩니다.'
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
.active {
	color: #111111;
	font-weight: 700;
}
#mypage-filter li {
  cursor: pointer;
}
#mypage-filter li:hover, .coupon-nav li:hover {
  text-decoration: underline;
}
</style>

