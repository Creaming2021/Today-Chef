<template>
  <div>
		<div class="info-header">
			<div class="info-header-main">
				간단하게 어떤 클래스인지 알려주세요
			</div>
		
			<div class="info-header-sub">
				언젠가 이런 걸 가르쳐봐야지 생각해본 적이 있으신가요? 간단히 크리에이터님이 알려
				줄 수 있는 카테고리를 설정해봐요. 저장하기 버튼을 꼭 눌러주세요.
			</div>
		</div>
		<div class="category">
			<span>카테고리</span>
			<b-form-select v-model="data.categorySelected" :options="categoryOptions"></b-form-select>
		</div>
		<div class="category">
			<span>세부 카테고리</span>
			<b-form-input v-model="data.categoryText" placeholder="예시) 김치볶음밥, 마라샹궈, 팟타이, 카페모카, 스콘"></b-form-input>
		</div>
	</div>
</template>

<script>
	export default {
		components: {
	},
	data(){
		return {
			data : {
				categorySelected : '',
				categoryText : '',
			},
			categoryOptions: [
				{ value: null, text: '클래스 분야를 선택하세요' },
				{ value: '한식', text: '한식' },
				{ value: '일식', text: '일식' },
				{ value: '양식', text: '양식' },
				{ value: '중식', text: '중식' },
				{ value: '디저트', text: '디저트' },
				{ value: '음료', text: '음료' },
				{ value: '세계 음식', text: '세계 음식' },
				{ value: '기타', text: '기타' },
			]
		}
	},
  mounted () {
		this.data = this.creatorData[this.$route.params.type] ?  this.creatorData[this.$route.params.type] : this.data
  },
	props: {
		 creatorData : Object,
	},
	methods : {
		onClickSaveBtn() {
			const type = this.$route.params.type
			const data = this.data
			this.$emit('data',{type,data})
		}
	}
}
</script>

<style>
.info-header {
	margin : 20px 0 40px 0;
}
.info-header-main {
	font-size: 20px;
	font-weight: 700;
	margin : 0 0 10px 0;
}
.info-header-sub {
	color: rgb(162,162,162);
	font-size: 14px;
}
.category {
	margin: 10px 0 20px 0;
}
</style>