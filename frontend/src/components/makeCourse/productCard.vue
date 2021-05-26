<template>
  <div class="blog__item"
    @click="() => setProductItem(itemDetail.productId)">
    <div v-if="itemDetail.name !== ''">
      <img 
        v-if="itemDetail.images && itemDetail.images[0]"
        class="blog__item__pic set-bg" 
        :src="itemDetail.images[0].imageUrl || 'https://imgix.kotaku.com.au/content/uploads/sites/3/2016/02/17/whey2gwjqbx21xu023qo.gif?ar=16%3A9&auto=format&fit=crop&q=65&w=720&nrs=40&fm=gif'"/>
      <img 
        v-else-if="itemDetail.image || itemDetail.image !== ''"
        class="blog__item__pic set-bg" 
        :src="itemDetail.image || 'https://imgix.kotaku.com.au/content/uploads/sites/3/2016/02/17/whey2gwjqbx21xu023qo.gif?ar=16%3A9&auto=format&fit=crop&q=65&w=720&nrs=40&fm=gif'"/>
      <img
        v-else
        class="blog__item__pic set-bg" 
        :src="'https://imgix.kotaku.com.au/content/uploads/sites/3/2016/02/17/whey2gwjqbx21xu023qo.gif?ar=16%3A9&auto=format&fit=crop&q=65&w=720&nrs=40&fm=gif'"/>
      <div class="blog__item__text">
        <span>{{itemDetail.category}}</span>
        <h6>{{itemDetail.name}}</h6>
        <span> 별점 : {{itemDetail.rating}}</span>
        <h6>{{itemDetail.price.toLocaleString()}}원</h6>
      </div>
    </div>
    <div v-else class="default-message">
      제품을 선택해주세요.
    </div>
  </div>
</template>

<script>
export default {
  props: {
    itemDetail: Object,
    setProductItem: Function,
  }
}
</script>

<style>
.blog__item .default-message{
  text-align: center;
  width: 100%;
  font-size: 1.5rem;
  weight: bold;
  margin-bottom: 30px;
}

</style>