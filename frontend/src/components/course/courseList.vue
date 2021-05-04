<template>
  <div class="col-lg-9">
    <div class="shop__product__option">
      <div class="row">
        <div class="col-lg-6 col-md-6 col-sm-6">
          <div class="shop__product__option__left">
            <p>Showing 1–15 of {{courseFilterList.length}} results</p>
          </div>
        </div>
        <div class="col-lg-6 col-md-6 col-sm-6">
          <div class="shop__product__option__right">
            <div 
              v-bind:class="open ? 'nice-select open' : 'nice-select'"
              @click="openCategory">
              <span class="current">{{currentSortValue}}</span>
              <ul class="list">
                <li 
                  v-for="sort in sortList" v-bind:key="sort.id"
                  @click="() => setSortValue(sort.id, sort.value)"
                  v-bind:class="currentSortId === sort.id ? 'focus selected option' : 'option'">
                    {{sort.value}}
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div 
        class="col-lg-4 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals" 
        v-for="(course,idx) in courseFilterList" :key="idx">
          <CourseItem :course="course" :idx="idx" />
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
</template>

<script>
import CourseItem from '@/components/common/CourseItem.vue';

export default {
  data(){
    return {
      open: false,
      sortList: [
        { id: 'review', value: '리뷰순'},
        { id: 'rank', value: '별점순'},
        { id: 'likeCnt', value: '좋아요 순'},
        { id: 'arrival', value: '최신순'}
      ]
    }
  },
  components : {
    CourseItem
  },
  props: {
    courseFilterList: Array,
    currentSortId: String,
    currentSortValue: String,
  },
  methods: {
    openCategory() { 
      this.open = !this.open;
    },
    setSortValue(id, value){
      this.$emit('setSortValue', { id, value });
    }
  }
}
</script>

<style>

</style>