<template>
	<!-- Product Section Begin -->
	<section class="product spad">
			<div class="container">
				<div class="row">
						<div class="col-lg-12">
								<ul class='filter__controls'>
										<li 
											v-for="(tab, index) in tabList"
											v-bind:key="index">{{tab}}
										</li>
								</ul>
						</div>
				</div>
				<div class="row product__filter">
				<course-item 
						v-for="course in itemList" 
						v-bind:key="course.id" 
						:course="course"/>
				</div>
			</div>
	</section>
	<!-- Product Section End -->
</template>


<script>
import CourseItem from '@/components/CourseItem.vue';

export default {
	name: "TopList",
	components: {
		CourseItem,
	},
	data() {
		return {
			currentActive: '',
		}
	},
	mounted(){
		let tab = null;
		const tabs = document.querySelectorAll('.filter__controls');
		if (this.type === 'arrival') {
			tab = tabs[0];
		} else {
			tab = tabs[1];
		}

		tab.childNodes.forEach((node,idx) => {
			if (idx === 0) {
				node.setAttribute('class','active');
				this.currentActive = node;
			}
			node.addEventListener('click', (e) => {
				e.stopPropagation();
				this.currentActive.classList.remove('active');
				node.classList.add('active');
				this.currentActive = node;

				alert("요청 때립니다");
			})
		});
	},
	props: {
		tabList: [Array, Object],
		itemList: [Array, Object],
		type: String,
	}
}
</script>

<style>
</style>
