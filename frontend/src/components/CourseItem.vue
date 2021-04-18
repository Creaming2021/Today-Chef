<template>
	<div class="col-lg-4 col-md-6 col-sm-6">
		<div class="blog__item">
				<img class="blog__item__pic set-bg" :src="course.thumbnail"/>
				<div class="blog__item__text">
						<span>{{course.category}} <img src="@/assets/img/icon/calendar.png" alt="">{{course.date}} / 좋아요 수 : {{course.likeCnt}}</span>
						<h5>{{course.name}}</h5>
						<h6 href="#">{{course.teacher}} / 별점 : {{course.rate}}</h6>
						<h5>{{course.price}}원</h5>
						<a href="#">자세히 보기</a>
				</div>
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