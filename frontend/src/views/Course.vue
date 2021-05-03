<template>
  <div>
    <!-- Shop Section Begin -->
    <section class="shop spad disableselect" >
      <div class="container">
        <div class="row">
          <CourseSearch
            :searchCourse="searchCourse"
            :searchKeyword="searchKeyword"
            :currentCategory="currentCategory"
            :currentFilter="currentFilter"
            @setKeyword="setKeyword"
            @setCategory="setCategory"
            @setFilter="setFilter"/>
          <CourseList
            :courseFilterList="courseFilterList"
            :currentSortValue="currentSortValue"
            :currentSortId="currentSortId"
            @setSortValue="setSortValue"/>
        </div>
      </div>
    </section>
    <!-- Shop Section End -->
  </div>
</template>


<script>
// import HomeItem from '@/components/HomeItem.vue';
import CourseSearch from '@/components/course/courseSearch.vue';
import CourseList from '@/components/course/courseList.vue';

export default {
  components: {
    // HomeItem
    CourseSearch,
    CourseList,
  },
  created() {
    this.checkQuery();
  },
  mounted() {
    // this.setCategory();
    // this.setCategoryCollapse();
    // this.setSort();/
  },
  data() {
    return {
      search: false,
      searchKeyword: '',
      currentCategory: 'all',
      currentFilter: 'all',
      currentSortId: 'review',
      currentSortValue: '리뷰순',
      searchCourseValue : '',
      categoryList : {},
      currentShowingList : [],
      courseFilterList: [
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
        {
          id: 4,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '양식',
          date: '2021.04.06',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '32000',
          likeCnt: '153',
          rate: '3.2',
        },
        {
          id: 5,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '양식',
          date: '2021.02.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '20000',
          likeCnt: '1223',
          rate: '3.1',
        },
        {
          id: 6,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '184',
          rate: '4.1',
        },
        {
          id: 7,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10200',
          likeCnt: '151',
          rate: '1.8',
        },
        {
          id: 8,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2020.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '12100',
          likeCnt: '531',
          rate: '4.6',
        },
        {
          id: 9,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 10,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.03.26',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '5823',
          rate: '3.9',
        },
        {
          id: 11,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.02.06',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 12,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 13,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 14,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 15,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 16,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 17,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '123',
          rate: '3.5',
        },
        {
          id: 18,
          thumbnail: 'https://m.cj.co.kr/images/theKitchen/PHON/0000001571/0000005660/0000001571.jpg',
          category: '일식',
          date: '2021.04.16',
          name: '쉽게 배워보는 갈비찜',
          teacher: '김한식',
          price: '10000',
          likeCnt: '123',
          rate: '3.5',
        },
      ]
    }
  },
  methods : {
    searchCourse() {
      console.log('Search Value', this.searchKeyword);
      console.log('currentSortValue', this.currentSortValue);
      console.log('currentFilter', this.currentFilter);
      console.log('currentCategory', this.currentCategory);
    },
    // setSort() {
    //   const sortList = document.querySelectorAll('.nice-select')[0];
    //   sortList.addEventListener('click', () => {
    //     sortList.setAttribute('class', 'nice-select open');
    //     this.currentSortType.classList.add('focus');
    //   });

    //   sortList.querySelectorAll('li').forEach((node, idx) => {
    //     if(idx === 0) {
    //       node.setAttribute('class', 'option focus selected');
    //       this.currentSortType = node;
    //       this.currentSortValue = node.innerText;
    //     } else {
    //       node.setAttribute('class', 'option');
    //     }
    //     node.addEventListener('click', (e) => {
		// 			e.stopPropagation();

    //       this.currentSortType.classList.remove('selected');
    //       this.currentSortType.classList.remove('focus');
    //       sortList.setAttribute('class', 'nice-select');
    //       node.classList.add('selected');

    //       this.currentSortType = node;
    //       this.currentSortValue = node.innerText;
    //     })
    //   });
    // },
    checkQuery() {
      this.searchCourseValue = 
        this.$route.params.category ? this.$route.params.category : 'korea';
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
    }
  },
  watch: { 
    $route(to, from) { 
      if (to.path != from.path) { 
        this.checkQuery();
      } 
    },
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
