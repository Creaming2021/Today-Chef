<template>
  <div>
    <div class="container">
      <div class="row">
        <div class="col-lg-3"><Sidebar/></div>
        <div v-if="currentAtiveFilter === 'info'" class="col-lg-9"><Info/></div>
        <div v-if="currentAtiveFilter === 'thumbnail'" class="col-lg-9"><Thumbnail/></div>
        <div v-else-if="currentAtiveFilter === 'course'" class="col-lg-9"><Class/></div>
        <div v-else-if="currentAtiveFilter === 'kit'" class="col-lg-9"><Kit/></div>
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


export default {
  components : {
    Sidebar,
    Class,
    Kit,
    Thumbnail,
    Info,
  },
  data() {
    return {
      currentAtiveFilter : '',
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
    }
  }
}
</script>

<style scoped>


</style>
