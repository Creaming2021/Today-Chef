<template>
  <div class="col-lg-9 course-detail-main-container">
    <div class="tab">
      <span 
        v-if="this.item === 'course'" 
        :class="type === 'introduction' ? 'active' : 'inactive'"
        @click="onChangeType('introduction')">클래스 소개</span>
      <span 
        :class="item === 'course' 
          ? type === 'kit' ? 'active' : 'inactive'
          : type === 'introduction' ? 'active' : 'inactive'"
        @click="item === 'course' ? onChangeType('kit') : onChangeType('introduction')">키트 소개</span>
      <span 
        :class="type === 'review' ? 'active' : 'inactive'"
        @click="onChangeType('review')">리뷰</span>
      <span 
        :class="type === 'qna' ? 'active' : 'inactive'"
        @click="onChangeType('qna')">QnA</span>
      <!-- <span @click="onChangeType('refund')">환불 정책</span> -->
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
    <!-- <ItemRefund 
      v-else-if="this.type === 'refund'"/> -->
  </div>
</template>

<script>
import { mapState } from 'vuex';
import ItemViewer from '@/components/itemDetail/ItemViewer.vue';
import ItemKit from '@/components/itemDetail/ItemKit.vue';
import List from '@/components/common/List.vue';
import ListDetail from '@/components/common/ListDetail.vue';
import ItemQnA from '@/components/itemDetail/ItemQnA.vue';
// import ItemRefund from '@/components/itemDetail/ItemRefund.vue';

export default {
  data() {
    return {
      id: '',
      item: '',
      type: '',
      reviewId: null,
    }
  },
  computed: {
    ...mapState({
      course: state => state.course.course,
      product: state => state.course.product,
    }),
    itemDetail: function(){
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
    // ItemRefund,
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
      this.reviewId = this.$route.params.reviewId;
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

<style scoped>
.course-detail-main-container{
  display: inline-block;
  width: 70%;
  height: inherit;
  padding: 10px;
}

.course-detail-main-container .tab{
  word-spacing: 5px;
  margin: auto;
  margin-bottom: 40px;
  display: table;
  width: 80%;
}

.course-detail-main-container span{
  display: table-cell;
}

.course-detail-main-container .tab:hover{
  cursor: pointer;
}

.active{
  display: inline-block;
  width: 120px;
  border-radius: 10px 10px 0px 0px;
  border-width: 3px 3px 0px 3px;
  border-style: solid;
  border-color: #f3f2ee;
  padding: 5px 10px 5px 10px;
}

.inactive{
  display: inline-block;
  width: 120px;
  border-width: 0px 0px 3px 0px;
  border-style: solid;
  border-color: #f3f2ee;
  margin: 0px -3px 0px -3px;
  padding: 5px 10px 5px 10px;
}
</style>
