<template>
  <div>
    <ItemDetail :creatorData="creatorData"/>
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
		 creatorData : Object,
	},
	methods : {

	}
}
</script>

<style scoped>

</style>