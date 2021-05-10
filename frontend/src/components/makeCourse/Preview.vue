<template>
  <div>
    <ItemDetail :courseInfo="courseInfo"/>
  </div>
</template>

<script>
import ItemDetail from '@/views/ItemDetail.vue';
	export default {
		components: {
			ItemDetail
	},
	data(){
		return {
		}
	},
  mounted () {
  },
	props: {
		 courseInfo : Object,
	},
	methods : {

	}
}
</script>

<style scoped>

</style>