<template>
  <div class="col-lg-9">
    <div class="shop__product__option">
      <div class="row">
        <div class="col-lg-6 col-md-6 col-sm-6">
          <div class="shop__product__option__left">
            <p>Showing 1–{{itemList.length}} of {{itemList.length}} results</p>
          </div>
        </div>
        <div class="col-lg-6 col-md-6 col-sm-6">
          <div class="shop__product__option__right">
            <div 
              v-bind:class="open ? 'nice-select open' : 'nice-select'"
              @click="openCategory">
              <span class="current">{{currentSortValue}}</span>
              <ul class="list">
                <template v-if="item === 'course'">
                  <li 
                    v-for="sort in sortCourseList" v-bind:key="sort.id"
                    @click="() => setSortValue(sort.id, sort.value)"
                    v-bind:class="currentSortId === sort.id ? 'focus selected option' : 'option'">
                      {{sort.value}}
                  </li>
                </template>
                <template v-else-if="item === 'product'">
                  <li 
                    v-for="sort in sortProductList" v-bind:key="sort.id"
                    @click="() => setSortValue(sort.id, sort.value)"
                    v-bind:class="currentSortId === sort.id ? 'focus selected option' : 'option'">
                      {{sort.value}}
                  </li>
                </template>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div 
        class="col-lg-4 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals" 
        v-for="(itemDetail,idx) in itemList" :key="idx">
          <ItemCard 
            :item="item"
            :itemDetail="itemDetail" 
            :idx="idx" 
            :type="'otherClass'"/>
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
import ItemCard from '@/components/common/ItemCard.vue';

export default {
  data(){
    return {
      open: false,
      sortCourseList: [
        { id: 'review', value: '리뷰 많은 순'},
        { id: 'rank', value: '별점 높은 순'},
        { id: 'arrival', value: '최신순'}
      ],
      sortProductList: [
        { id: 'review', value: '리뷰 많은 순'},
        { id: 'rank', value: '별점 높은 순'},
        { id: 'price', value: '가격 낮은 순'},
      ],
    }
  },
  components : {
    ItemCard,
  },
  props: {
    item: String,
    itemList: Array,
    currentSortId: String,
    currentSortValue: String,
  },
  methods: {
    openCategory() { 
      this.open = !this.open;
    },
    setSortValue(id, value){
      this.$emit('setSortValue', { id, value });
    },
  }
}
</script>

<style>

</style>