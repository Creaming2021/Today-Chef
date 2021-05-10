<template>
  <div>
    <!-- <div id="preloder">
        <div class="loader"></div>
    </div> -->
    <Event/>
    <div class="home-sub-title">밀키트 TOP 6</div>
    <TopList
      :item="'product'"
      :tabList="categoryTab" 
      :itemList="productTopList"
      @changeTab="changeList"/>
    <!-- <Sale/> -->
    <div class="home-sub-title">강의 TOP 6</div>
    <TopList 
      :item="'course'"
      :tabList="categoryTab" 
      :itemList="courseTopList"
      @changeTab="changeList"/>
    <!-- <Recommend
      :list="home.randomListState"/> -->
  </div>
</template>

<script>
import { mapState } from "vuex";
import Event from '@/components/home/Event.vue';
import TopList from '@/components/home/TopList.vue';
// import Sale from '@/components/home/Sale.vue';
// import Recommend from '@/components/home/Recommend.vue';

export default {
  name: "Home",
  components: {
    Event,
    TopList,
    // Sale,
    // Recommend
  },
  created() {
    this.getTopList();
  },
  computed: {
    ...mapState({
      courseTopList: state => state.course.topList,
      productTopList: state => state.product.topList,
    }),
  },
  methods: {
    changeList({ tab, item }){
      if(item === 'course'){
        this.$store.dispatch('GET_COURSE_TOP_LIST', {
          count: 6,
          category: tab,
        });
      } else if(item === 'product'){
        this.$store.dispatch('GET_PRODUCT_TOP_LIST', {
          count: 6,
          category: tab,
        });
      }
    },
    getTopList(){
      this.$store.dispatch('GET_COURSE_TOP_LIST', {
        count: 6,
        category: 'KOREA',
      });
      this.$store.dispatch('GET_PRODUCT_TOP_LIST', {
        count: 6,
        category: 'KOREA',
      });;
    },
  },
  data() {
    return {
      categoryTab: [
        { name: "한식", value: 'KOREA' }, 
        { name: "중식", value: 'CHINA' }, 
        { name: "일식", value: 'JAPAN' }, 
        { name: "양식", value: 'WESTERN' }, 
        { name: "디저트", value: 'DESSERT' }, 
        { name: "음료", value: 'DRINK' }, 
        { name: "세계음식", value: 'WORLD' }, 
        { name: "기타", value: 'ETC' }, 
      ],
    }
  }
};
</script>

<style>
.home-sub-title{
  width: 100%;
  text-align: center;
  size: 1.25rem;
  font-weight: bold;
  margin-top: 30px;
}
</style>
