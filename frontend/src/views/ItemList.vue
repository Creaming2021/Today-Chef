<template>
  <div>
    <!-- Shop Section Begin -->
    <section class="shop spad disableselect" >
      <div class="container">
        <div class="row">
          <ItemSearch
            :searchCourse="searchCourse"
            :searchKeyword="searchKeyword"
            :currentCategory="currentCategory"
            :currentFilter="currentFilter"
            :item="item"
            @setKeyword="setKeyword"
            @setCategory="setCategory"
            @setFilter="setFilter"/>
          <ItemList
            :itemList="itemList"
            :currentSortValue="currentSortValue"
            :currentSortId="currentSortId"
            :item="item"
            :category="currentCategory"
            @setSortValue="setSortValue"/>
        </div>
      </div>
    </section>
    <!-- Shop Section End -->
  </div>
</template>


<script>
import { mapState } from 'vuex';
import ItemSearch from '@/components/itemList/ItemSearch.vue';
import ItemList from '@/components/itemList/ItemList.vue';

export default {
  components: {
    ItemSearch,
    ItemList,
  },
  computed : {
    ...mapState({
      courseList: state => state.course.courseList,
      productList: state => state.product.productList,
    }),
  },
  created() {
    this.checkQuery();
  },
  data() {
    return {
      item: '',
      searchKeyword: '',
      currentCategory: 'all',
      currentFilter: 'all',
      currentSortId: 'review',
      currentSortValue: '리뷰 많은 순',
      itemList: [],
    }
  },
  methods : {
    searchCourse() {
      console.log('Search Value', this.searchKeyword);
      console.log('currentSortValue', this.currentSortValue);
      console.log('currentFilter', this.currentFilter);
      console.log('currentCategory', this.currentCategory);
    },
    checkQuery() {
      this.currentCategory = this.$route.params.category;
      this.item = this.$route.params.item;
      this.getItemList();
    },
    getItemList(){
      if(this.item === 'course'){
        this.$store.dispatch('GET_COURSE_LIST');
      } else if(this.item === 'product'){
        this.$store.dispatch('GET_PRODUCT_LIST');
      }
    },
    setCourseList() {
      this.itemList = this.courseList;
    },
    setProductList() {
      this.itemList = this.productList;
    },
    setKeyword(newKeyword) {
      this.searchKeyword = newKeyword;
    },
    setFilter(newFilter) {
      this.currentFilter = newFilter;
      this.searchCourse();
    },
    setCategory(newCategory) {
      this.currentCategory = newCategory;
      this.searchCourse();
    },
    setSortValue(newSort) {
      this.currentSortValue = newSort.value;
      this.currentSortId = newSort.id;
      this.searchCourse();
    },
  },
  watch: { 
    $route(to, from) { 
      if (to.path != from.path) { 
        this.checkQuery();
      } 
    },
    productList: function(){
      this.setProductList();
    },
    courseList: function(){
      this.setCourseList();
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
</style>
