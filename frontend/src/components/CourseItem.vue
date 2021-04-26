<template>
		<div class="blog__item">
				<img class="blog__item__pic set-bg" :src="course.thumbnail"/>
				<div class="blog__item__text">
						<!-- <span>{{course.category}} <img src="@/assets/img/icon/calendar.png" alt="">{{course.date}} / 좋아요 수 : {{course.likeCnt}}</span> -->
						<h6>{{course.name}}</h6>
						<span href="#">{{course.teacher}} / 별점 : {{course.rate}}</span>
						<h6>{{course.price}}원</h6>
						<a href="#">자세히 보기</a>
				</div>
		</div>
</template>

<script>

	export default {
		data(){
			return {}
		},
		props: {
			course: {
				id: Number,
				thumbnail: String,
				category: String,
				date: String,
				name: String,
				teacher: String,
				price: Number,
				likeCnt: Number,
				rate: Number,
			},
		}
	}
</script>

<style scoped>
.blog__item__text h6 {
	color: #0d0d0d;
	font-weight: 700;
}
</style>