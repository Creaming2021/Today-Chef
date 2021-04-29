<template>
  <div>
    <section class="shop spad disableselect" >
        <div class="container">
            <div class="row">
                <Sidebar/>

                <div 
                  v-show="currentAtiveFilter === 'info'" 
                  class="col-lg-9">
                    <div class="row">
                      <Personal :personalData="personalData"/>
                    </div>
                </div>

                <MyCoupon 
                  v-if="currentAtiveFilter === 'coupon'"
                  class="col-lg-9"
                  :couponList="couponList"/>

                <MyCourseList
                  v-else-if="currentAtiveFilter === 'student'"
                  class="col-lg-9"
                  :type="'otherClass'"
                  :myCourseList="myCourseList"/>

                <MyCourseList
                  v-else-if="currentAtiveFilter === 'teacher'"
                  class="col-lg-9"
                  :type="'myClass'"
                  :myCourseList="myCourseList"/>

                <MyPayment 
                  v-else-if="currentAtiveFilter === 'payment'" 
                  class="col-lg-9"
                  :myPaymentList="myPaymentList"/>
            </div>
        </div>
    </section>
  </div>
</template>


<script>
import Sidebar from '@/components/mypage/Sidebar.vue';
import Personal from '@/components/mypage/Personal.vue';
import MyCoupon from '@/components/mypage/MyCoupon.vue';
import MyCourseList from '@/components/mypage/MyCourseList.vue';
import MyPayment from '@/components/mypage/MyPayment.vue';

export default {
  components: {
    Sidebar,
    Personal,
    MyCoupon,
    MyCourseList,
    MyPayment,
  },
  created() {
    this.checkQuery();
  },
  methods : {
    checkQuery() {
      this.currentAtiveFilter = this.$route.params.type ? this.$route.params.type : 'info';
    },
  },
  data() {
    return {
      currentAtiveFilter : '',
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
      couponList : [
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
  watch: { 
    $route(to, from) { 
      if (to.path != from.path) { 
        this.checkQuery();
      } 
    } 
  }
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

.active {
	color: #111111;
	font-weight: 700;
}

#mypage-filter li:hover, .coupon-nav li:hover {
  text-decoration: underline;
}
</style>

