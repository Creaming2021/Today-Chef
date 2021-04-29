<template>
  <div>
    미리보기입니다
  </div>
</template>

<script>
	export default {
		components: {
	},
	data(){
		return {
		}
	},
  mounted () {
    console.log(this.creatorData)
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