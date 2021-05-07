<template>
  <div class="col-lg-9 course-detail-main-container">
    <div class="tab">
      <span v-if="this.item === 'course'" @click="onChangeType('introduction')">클래스 소개</span> |
      <span @click="item === 'course' ? onChangeType('kit') : onChangeType('introduction')">키트 소개</span> |
      <span @click="onChangeType('review')">리뷰</span> |
      <span @click="onChangeType('qna')">QnA</span> |
      <span @click="onChangeType('refund')">한불 정책</span>
    </div>
    <ItemViewer 
      v-if="this.type === 'introduction'"/>
    <ItemKit 
      v-if="this.type === 'kit'"/>
    <List 
      v-else-if="this.type === 'review'"/>
    <ListDetail
      v-else-if="this.type === 'reviewDetail'"/>
    <ItemQnA 
      v-else-if="this.type === 'qna'"/>
    <ItemRefund 
      v-else-if="this.type === 'refund'"/>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import ItemViewer from '@/components/itemDetail/ItemViewer.vue';
import ItemKit from '@/components/itemDetail/ItemKit.vue';
import List from '@/components/common/List.vue';
import ListDetail from '@/components/common/ListDetail.vue';
import ItemQnA from '@/components/itemDetail/ItemQnA.vue';
import ItemRefund from '@/components/itemDetail/ItemRefund.vue';

export default {
  data() {
    return {
      id: '',
      item: '',
      type: '',
    }
  },
  computed: {
    ...mapState({
      course: state => state.course.course,
      product: state => state.course.product,
    }),
    itemDetail: function(){
      console.log(this.setItemDetail());
      return this.setItemDetail();
    }
  },
  created() {
    this.checkQuery();
  },
  components: {
    ItemViewer,
    ItemKit,
    List,
    ListDetail,
    ItemQnA,
    ItemRefund
  },
  methods: {
    onChangeType( clickType ){
      this.$router.push({
        name: 'ItemDetail',
        params: { 
          item: this.$route.params.item,
          categoty: this.$route.params.category,
          id: this.id,
          type: clickType,
        }
      });
    },
    checkQuery() {
      this.item = this.$route.params.item;
      this.type = this.$route.params.type;
      this.id = this.$route.params.id;
    },
    setItemDetail() {
      if(this.item === 'course'){
        return this.course;
      } else if(this.item === 'product'){
        return this.product;
      }
      return [];
    }
  },
  watch: { 
    $route(to, from) { 
      if (to.path != from.path) { 
        this.checkQuery();
      } 
    },
  },
}
</script>

<style>
.course-detail-main-container{
  display: inline-block;
  width: 70%;
  height: inherit;
  padding: 10px;
}

.course-detail-main-container .tab{
  word-spacing: 5px;
  margin-bottom: 40px;
}

.course-detail-main-container .tab:hover{
  cursor: pointer;
}
</style>
