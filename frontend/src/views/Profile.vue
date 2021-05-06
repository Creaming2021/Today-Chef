<template>
  <div>
    <section class="shop spad disableselect" >
        <div class="container">
            <div class="row">
                <Sidebar/>

                <div 
                  v-show="type === 'info'" 
                  class="col-lg-9">
                    <div class="row">
                      <Personal/>
                    </div>
                </div>

                <MyCoupon 
                  v-if="type === 'coupon'"
                  class="col-lg-9"/>

                <MyCourseList
                  v-else-if="type === 'student'"
                  class="col-lg-9"
                  :type="type"/>

                <MyCourseList
                  v-else-if="type === 'teacher'"
                  class="col-lg-9"
                  :type="type"/>

                <MyPayment 
                  v-else-if="type === 'payment'" 
                  class="col-lg-9"/>
            </div>
        </div>
    </section>
  </div>
</template>

<script>
import { mapState } from 'vuex';

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
  computed : {
    ...mapState({
      user: state => state.user,
    }),
  },
  created() {
    this.checkQuery();
    this.getMemberInfo();
  },
  data() {
    return {
      type : '',
    }
  },
  methods : {
    checkQuery() {
      this.type = this.$route.params.type ? this.$route.params.type : 'info';
    },
    getMemberInfo(){
      this.$store.dispatch('GET_MEMBER_INFO', this.user.memberId);
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

