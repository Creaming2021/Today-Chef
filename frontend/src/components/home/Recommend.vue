<template><!-- Product Section Begin -->
	<section class="product spad">
			<div class="container">
				<div class="row">
						<div class="col-lg-12">
								<div class="section-title">
										<span>Coming Soon</span>
										<h2>오픈 예정 강의</h2>
								</div>
						</div>
				</div>
				<div class="row product__filter">
				<div class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals" v-for="(item,idx) in list" :key="idx">
						<HomeItem :item="item" :idx="idx" />
				</div>
				</div>
			</div>
	</section>
	<!-- Product Section End -->
</template>


<script>
import HomeItem from '@/components/HomeItem.vue';
export default {
  name: "Recommend",
  components: {
    HomeItem,
  },
	props: {
		list: Array,
	}
}
</script>

<style>
</style>
