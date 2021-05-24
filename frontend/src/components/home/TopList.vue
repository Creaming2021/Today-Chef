<template>
  <!-- Product Section Begin -->
  <section class="product spad">
    <div class="row">
      <div class="col-lg-12">
        <ul class='filter__controls'>
          <li 
            v-for="tab in tabList"
            :key="tab.value"
            :class="currentTab === tab.value && 'active'"
            @click="() => setCurrentTab(tab.value)">{{tab.name}}
          </li>
        </ul>
      </div>
    </div>
    <div class="container" v-if="itemList.length > 0">  
      <div class="row product__filter">
        <div class="col-lg-4 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals" 
          v-for="itemDetail in itemList" :key="item === 'course' ? itemDetail.courseId : itemDetail.productId">
            <ItemCard 
              :item="item"
              :itemDetail="itemDetail" 
              :id="item === 'course' ? itemDetail.courseId : itemDetail.productId" 
              :type="'home'"/>
        </div>
      </div>
    </div>
    <div v-else class="default-message">
      <img src="@/assets/img/empty/search.png"/>
    </div>
  </section>
  <!-- Product Section End -->
</template>


<script>
import ItemCard from '@/components/common/ItemCard.vue';

export default {
  name: "TopList",
  components: {
    ItemCard,
  },
  data() {
    return {
      currentTab: 'KOREA',
    }
  },
  props: {
    item: String,
    tabList: Array,
    itemList: Array,
  },
  methods : {
    setCurrentTab(newTab) {
      this.currentTab = newTab;
    }
  },
  watch: {
    currentTab: function () {
      this.$emit("changeTab", { 
        tab: this.currentTab,
        item: this.item,
      });
    },
  }
}
</script>

<style>
.product .default-message{
  width: 30%;
  height: auto;
  margin: auto;
}
</style>
