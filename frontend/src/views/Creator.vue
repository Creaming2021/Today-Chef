<template>
  <div>
    <div class="container">
      <div class="row">

        <div class="col-lg-3">
          <Sidebar ref="sidebar" :currentAtiveFilter="currentAtiveFilter" :creatorData="creatorData"/>
        </div>

        <div v-if="currentAtiveFilter === 'info'" class="col-lg-9">
          <Info ref="info" @data="onReceiveData" :creatorData="creatorData"/>
        </div>

        <div v-else-if="currentAtiveFilter === 'thumbnail'" class="col-lg-9">
          <Thumbnail ref="thumbnail" @data="onReceiveData" :creatorData="creatorData"/>
        </div>

        <div v-else-if="currentAtiveFilter === 'course'" class="col-lg-9">
          <Class ref="course" @data="onReceiveData" :creatorData="creatorData"/>
        </div>

        <div v-else-if="currentAtiveFilter === 'kit'" class="col-lg-9">
          <Kit ref="kit" @data="onReceiveData" :creatorData="creatorData"/>
        </div>

        <div v-else-if="currentAtiveFilter === 'preview'" class="col-lg-9">
          <Preview ref="preview" @data="onReceiveData" :creatorData="creatorData"/>
        </div>

      </div>
    </div>
    <div class="container" style="padding-top : 40px; padding-bottom : 40px">
      <div class="row">
        <div class="col-lg-3"></div>
        <div class="col-lg-9">
          <div style="display : flex; justify-content : center;">
            <button v-if="currentAtiveFilter !== 'preview'" @click="onClickTempSave">임시저장</button>
            <button v-else @click="onClickSave">클래스 개설하기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
// Components import
import Sidebar from '@/components/makeCourse/Sidebar.vue';
import Class from '@/components/makeCourse/Class.vue';
import Kit from '@/components/makeCourse/Kit.vue';
import Thumbnail from '@/components/makeCourse/Thumbnail.vue';
import Info from '@/components/makeCourse/Info.vue';
import Preview from '@/components/makeCourse/Preview.vue';


export default {
  components : {
    Sidebar,
    Class,
    Kit,
    Thumbnail,
    Info,
    Preview,
  },
  data() {
    return {
      currentAtiveFilter : '',
      creatorData : {
        info : '',
        thumbnail : '',
        course : '',
        kit : '',
      }
    }
  },
  watch : {
    $route(to, from) { 
      if (to.path != from.path) { 
        this.checkQuery();
      } 
    }
  },
  created () {
    this.checkQuery();
  },
  mounted() {
  },
  methods : {
    checkQuery() {
      this.currentAtiveFilter = this.$route.params.type ? this.$route.params.type : 'info';
    },
    onClickTempSave() {
      this.$refs['sidebar'].onClickSaveBtn();
      this.$refs[this.$route.params.type].onClickSaveBtn();
    },
    onClickSave() {
      alert('클래스 개설')
    },
    onReceiveData(res) {
      console.log(res)
      this.creatorData[res.type] = res.data
      console.log(this.creatorData)
    }
  }
}
</script>

<style scoped>
</style>
