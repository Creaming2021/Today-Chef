<template>
  <div>
    <div class="user-info">
      <img :src="qna.profile.profileImage || 'https://www.edmundsgovtech.com/wp-content/uploads/2020/01/default-picture_0_0.png'"/>
      <div>
        <div class="title">{{qna.title}} </div>
        <span class="writer">{{qna.profile.nickname}} </span>
        <span class="date">{{qna.date.substring(0,10)}}</span>
      </div>
    </div>
    <div class="content">
      <viewer ref="viewer"/>
    </div>
    <!-- <div class="content">
      {{qna.content}}
    </div> -->
    <template v-if="$route.params.item === 'course'">
      <ItemQnaAnswer 
        :comments="qna.comments"
        :qnaId="qna.courseQnaId"
        :courseId="Number($route.params.id)"
      />        
    </template>
  </div>
</template>

<script>
import { Viewer } from '@toast-ui/vue-editor';
import ItemQnaAnswer from '@/components/itemDetail/ItemQnaAnswer.vue';

export default{
  components:{
    viewer: Viewer,
    ItemQnaAnswer,
  },
  mounted(){
    this.settingViewerValue();
  },
  props: {
    qna: Object,
  },
  methods: {
    settingViewerValue(){
      this.$refs.viewer.invoke('setMarkdown', this.qna.content);
    },
  },
  watch: {
    content: function(){
      this.settingViewerValue();
    }
  }
}
</script>

<style scoped>
.user-info img{
  float: left;
  display: block;
  width: 70px;
  height: 70px;
  border-radius: 50%;
  margin: 0px 20px 20px 0px;
}
.user-info .title{
  font-weight: bold;
  font-size: 1.5rem;
}
.user-info .writer{
  font-size: 1rem;
}
.user-info .date{
  font-size: 0
  .8rem;
}
</style>