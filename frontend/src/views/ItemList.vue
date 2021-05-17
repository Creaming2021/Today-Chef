<template>
  <div>
    <div class="body-header">
      <img v-if="item === 'course'" src="@/assets/img/banner/banner-course.png" />
      <img v-else-if="item === 'product'" src="@/assets/img/banner/banner-product.png" />
    </div>
    <section class="shop disableselect item-list-container" >
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
    itemList: function(){
      return this.setItemList();
    }
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
    }
  },
  methods : {
    searchCourse() {
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
      
      this.filteredItemList.sort((itemA, itemB) => { // 정렬
        if(this.currentSortId === 'review'){
          return itemA.reviewCnt - itemB.reviewCnt;
        } else if(this.currentSortId === 'rank') {
          return itemB.rating - itemA.rating;
        } else if(this.currentSortId === 'arrival') {
          return itemA.date - itemB.date;
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
    setItemList() {
      return this.item === 'course' ? this.courseList : this.productList;
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
    itemList: function(){
      this.searchCourse();
    },
  }
}
</script>

<style scoped>
.item-list-container{
  margin-bottom: 50px;
}

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
