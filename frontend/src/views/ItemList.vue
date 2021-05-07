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
            :itemList="filteredItemList"
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
      filteredItemList: [],
      itemList: [],
    }
  },
  methods : {
    searchCourse() {
      console.log('Search Value', this.searchKeyword);
      console.log('currentFilter', this.currentFilter);
      console.log('currentCategory', this.currentCategory);

      // sortCourseList: [
      //   { id: 'review', value: '리뷰 많은 순'},
      //   { id: 'rank', value: '별점 높은 순'},
      //   { id: 'arrival', value: '최신순'}
      // ],
      // sortProductList: [
      //   { id: 'review', value: '리뷰 많은 순'},
      //   { id: 'rank', value: '별점 높은 순'},
      //   { id: 'price', value: '가격 낮은 순'},
      // ],
      this.filteredItemList = this.itemList.filter( item => {
        if(!item.name.includes(this.searchKeyword)) // 키워드 검색
          return false;

        if(this.currentCategory != 'all' 
          && item.category.toLowerCase() !== this.currentCategory){ // 카테고리 검색
          return false;
        }
        
        if(this.item === 'course' && this.currentFilter != 'all'){ // 필터 검색
          let date = new Date();
          let today = '' + date.getFullYear() 
                      + ((date.getMonth()+1) < 10 ? '0' + (date.getMonth()+1) : (date.getMonth()+1))
                      + (date.getDate() < 10 ? '0' + date.getDate() : date.getDate());

          if(this.currentFilter === 'done' && today < item.date){
            return false;
          } else if(this.currentFilter === 'before' && today > item.date){
            return false;
          }
        }
        return true;
      });
      
      this.filteredItemList.sort((itemA, itemB) => {
        if(this.currentSortId === 'review'){
          return itemA.reviewCnt - itemB.reviewCnt;
        } else if(this.currentSortId === 'rank') {
          return itemB.rating - itemA.rating;
        } else if(this.currentSortId === 'arrival') {
          return itemB.date - itemA.date;
        } else if(this.currentSortId === 'price') {
          return itemA.price - itemB.price;
        }
      })

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
      this.searchCourse();
    },
    setProductList() {
      this.itemList = this.productList;
      this.searchCourse();
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
