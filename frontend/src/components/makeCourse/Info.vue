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

		<div class="row" style="margin: 10px 0 20px 0;">
			<div class="col-lg-3" style="padding : 0;">
				<label :for="'type-date'">강의 시작 날짜 :</label>
			</div>
			<div class="col-lg-9" style="padding : 0;">
				<b-form-input v-model="data.classStartDate" :id="`type-date`" :type="'date'"></b-form-input>
			</div>
		</div>

		<div class="row" style="margin: 10px 0 20px 0;">
			<div class="col-lg-3" style="padding : 0;">
				<label :for="'type-date'">강의 종료 날짜 :</label>
			</div>
			<div class="col-lg-9" style="padding : 0;">
				<b-form-input v-model="data.classEndDate" :id="`type-date`" :type="'date'"></b-form-input>
			</div>
		</div>

		<div class="row" style="margin: 10px 0 20px 0;">
			<div class="col-lg-3" style="padding : 0;">
				<label>강의 진행 요일 :</label>
			</div>
			<div class="col-lg-9" style="padding : 0;">
				<b-form-select v-model="data.classDay" :options="dayOptions"></b-form-select>
			</div>
		</div>

		<div class="row" style="margin: 10px 0 20px 0;">
			<div class="col-lg-3" style="padding : 0;">
				<label :for="'type-time'">강의 시작 시간 :</label>
			</div>
			<div class="col-lg-9" style="padding : 0;">
				<b-form-input v-model="data.classStartTime" :id="`type-time`" :type="'time'"></b-form-input>
			</div>
		</div>

		<div class="row" style="margin: 10px 0 20px 0;">
			<div class="col-lg-3" style="padding : 0;">
				<label :for="'type-time'">강의 종료 시간 :</label>
			</div>
			<div class="col-lg-9" style="padding : 0;">
				<b-form-input v-model="data.classEndTime" :id="`type-time`" :type="'time'"></b-form-input>
			</div>
		</div>

		<div class="row" style="margin: 10px 0 20px 0;">
			<div class="col-lg-3" style="padding : 0;">
				<label :for="'type-number'">강의 수강료 :</label>
			</div>
			<div class="col-lg-9" style="padding : 0;">
				<b-form-input v-model="data.classPrice" :id="`type-number`" :type="'number'"></b-form-input>
			</div>
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
				categorySelected : null,
				categoryText : '',
				classStartDate : '',
				classEndDate : '',
				classDay : null,
				classStartTime : '',
				classEndTime : '',
				classPrice : '',
			},
			types: [
				'date',
				'time',
				'range',
				'color'
			],
			dayOptions : [
				{ value: null, text: '강의 진행 요일을 선택하세요' },
				{ value: '월요일', text: '월요일' },
				{ value: '화요일', text: '화요일' },
				{ value: '수요일', text: '수요일' },
				{ value: '목요일', text: '목요일' },
				{ value: '금요일', text: '금요일' },
				{ value: '토요일', text: '토요일' },
				{ value: '일요일', text: '일요일' },
				
			],
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
			console.log(data)
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