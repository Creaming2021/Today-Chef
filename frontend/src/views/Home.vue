<template>
  <div>
    <!-- <div id="preloder">
        <div class="loader"></div>
    </div> -->
    <Event/>
    <h4>밀키트</h4>
    <TopList
      :item="'product'"
      :tabList="categoryTab" 
      :itemList="filteredProductTopList"
      @changeTab="changeList"/>
    <!-- <Sale/> -->
    <h4>실시간 강의</h4>
    <TopList 
      :item="'course'"
      :tabList="categoryTab" 
      :itemList="filteredCourseTopList"
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
        this.filteredCourseTopList = this.courseTopList[tab.toLowerCase()];
      } else if(item === 'product'){
        this.filteredProductTopList = this.productTopList[tab.toLowerCase()];
      }
    },
    getTopList(){
      this.$store.dispatch('GET_COURSE_TOP_LIST', 6);
      this.$store.dispatch('GET_PRODUCT_TOP_LIST', 6);
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
      filteredProductTopList: [],
      filteredCourseTopList: [],
    }
  }
};
</script>
