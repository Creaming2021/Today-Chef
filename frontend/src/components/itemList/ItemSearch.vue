<template>
  <div class="col-lg-3">
    <div class="shop__sidebar">
      <div class="shop__sidebar__search">
        <div>
          <input 
            type="text" 
            placeholder="Search..." 
            v-model="keyword" 
            @input="changeKeyword"
            @keyup.enter="searchCourse">
          <button 
            @click="searchCourse">
              <span class="icon_search"></span></button>
        </div>
      </div>
      <div class="shop__sidebar__accordion">
        <div class="accordion" id="accordionExample">
          <Category
            :type="'category'"
            :categoryTitle="'Categories'"
            :categoryList="categoryList"
            :currentFilter="currentCategory"
            @setCategory="setCategory"/>
          <Category
            v-if="item === 'course'"
            :type="'filter'"
            :categoryTitle="'Filter'"
            :categoryList="filterList"
            :currentFilter="currentFilter"
            @setCategory="setFilter"/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Category from '@/components/itemList/category.vue';

export default {
  components: {
    Category,
  },
  data(){
    return {
      keyword: '',
      categoryList: [
        {id: 'all', value: '모두'}, 
        {id: 'korea', value: '한식'}, 
        {id: 'japan', value: '일식'},
        {id: 'western', value: '양식'}, 
        {id: 'china', value: '중식'}, 
        {id: 'dessert', value: '디저트'}, 
        {id: 'drink', value: '음료'}, 
        {id: 'world', value: '세계 음식'}, 
        {id: 'etc', value: '기타'},
      ],
      filterList: [
        {id: 'all', value: '모두'}, 
        {id: 'done', value: '수강완료'}, 
        {id: 'before', value: '수강 시작 전'},
      ],
    }
  },
  props: {
    searchKeyword: String,
    searchCourse: Function,
    currentCategory: String,
    currentFilter: String,
    item: String,
  },
  methods: {
    setFilter(filter){
      this.$emit('setFilter', filter);
    },
    setCategory(category){
      this.$emit('setCategory', category);
    },
    changeKeyword(){
      this.$emit('setKeyword', this.keyword);
    }
  }
}

</script>

<style>
.shop__sidebar__search div {
  position: relative;
}

.shop__sidebar__search div input {
  width: 100%;
  font-size: 15px;
  color: #b7b7b7;
  padding-left: 20px;
  border: 1px solid #e5e5e5;
  height: 42px;
}

.shop__sidebar__search div input::-webkit-input-placeholder {
  color: #b7b7b7;
}

.shop__sidebar__search div input::-moz-placeholder {
  color: #b7b7b7;
}

.shop__sidebar__search div input:-ms-input-placeholder {
  color: #b7b7b7;
}

.shop__sidebar__search div input::-ms-input-placeholder {
  color: #b7b7b7;
}

.shop__sidebar__search div input::placeholder {
  color: #b7b7b7;
}

.shop__sidebar__search div button {
  color: #b7b7b7;
  font-size: 15px;
  border: none;
  background: transparent;
  position: absolute;
  right: 0;
  padding: 0 15px;
  top: 0;
  height: 100%;
}
</style>