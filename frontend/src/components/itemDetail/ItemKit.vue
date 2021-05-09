<template>
  <div>
    <ItemCard
      :item="'product'"
      :itemDetail="product"
      :type="'otherClass'"/>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import ItemCard from '@/components/common/ItemCard.vue';

export default {
  components: {
    ItemCard,
  },
  computed: {
    ...mapState({
       product: state => state.course.course.product,
      }),
  }
}
</script>

<style>
</style>
