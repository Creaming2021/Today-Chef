<template>
  <div class="item-detail-container">
    <div class="body-header">
      <img src="@/assets/img/banner/banner-notice.png"/>
    </div>
    <div class="item-detail" v-if="event.title">
      <div class="header">
        <span class="back" @click="goBack()">
          <b-icon icon="arrow-left"/>
        </span>
        <span class="title">{{event.title}}</span>
        <span class="date">{{event.date.substring(0, 10)}}</span>
      </div>
      <hr/>
      <img :src="event.image"/>
      <viewer ref="viewer"/>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import { Viewer } from '@toast-ui/vue-editor';

export default {
  components: {
    Viewer,
  },
  created(){
    this.getNoticeInfo();
  },
  computed: {
    ...mapState({
      event: state => state.event.event,
    }),
  },
  mounted() {
    this.settingViewerValue();
  },
  methods: {
    goBack(){
      this.$router.go(-1);
    },
    settingViewerValue() {
      this.$refs.viewer && this.$refs.viewer.invoke('setMarkdown', this.event.content);
    },
    getNoticeInfo(){
      this.$store.dispatch('GET_EVENT', this.$route.params.number);
    },
  },
  watch: {
    event: function(){
      this.settingViewerValue();
    }
  }
}
</script>

<style scoped>
.item-detail{
  width: 60%;
  margin: auto;
  padding: 10px 10px 100px 10px;
  text-align: left;
}

.item-detail-container img{
  display: block;
  width: 100%;
  height: auto;
}

.item-detail-container .date{ 
  display: inline-block;
  width: 10%;
  text-align: right;
  font-size: 1rem;
  color: darkgray;
  font-weight: bold;
}

.item-detail-container .header{
  text-align: left;
}

.item-detail-container .back{
  display: inline-block;
  width: 10%;
  cursor: pointer;
  color: black;
  font-size: 1.5rem;
  font-weight: bold;
}

.item-detail-container .title{
  display: inline-block;
  width: 80%;
  text-align: center;
  font-weight: bold;
  font-size: 1.5rem;
  color: black
}
</style>