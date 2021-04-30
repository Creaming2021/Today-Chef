<template>
  <div>
    <CourseDetail :creatorData="creatorData"/>
  </div>
</template>

<script>
import CourseDetail from '@/views/CourseDetail.vue';
	export default {
		components: {
			CourseDetail
	},
	data(){
		return {
		}
	},
  mounted () {
  },
	props: {
		 creatorData : Object,
	},
	methods : {

	}
}
</script>

<style scoped>

</style>