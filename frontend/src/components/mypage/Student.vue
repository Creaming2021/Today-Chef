<template>
<div style="width:80%">
  <div class="row" v-for="(student, idx) in studentList[day]" :key="idx">
    <div class="container">
      <div class="student-info">
        <div class="left">
          <img class="profile" :src="student.profile"/>
          <div  class="info-box">
            <div class="name">{{student.name}}</div>
            <div class="phone">{{student.phone}}</div>
          </div>
        </div>
        <div class="right">
          <div class="address">{{student.address}}</div>
        </div>
      </div>
    </div>
  </div>
</div>
</template>


<script>

export default {
	name: "Student",
	components: {
	},
	data() {
		return {
      days : ['월','화','수','목','금','토','일'],
      studentList : [
        [
          { 
            id : '1',
            name : '이병훈',
            address : '경기 수원시 영통구 봉영로 1617',
            email : 'lbh@naver.com',
            phone : '010-0000-0000',
          },
          { 
            id : '2',
            name : '성진옥',
            address : '충북 청주시 청원구 오창읍 중심상업1로 20',
            email : '성진옥@naver.com',
            phone : '010-1111-1111',
          },
          { 
            id : '1',
            name : '이병훈',
            address : '경기 수원시 영통구 봉영로 1617',
            email : 'lbh@naver.com',
            phone : '010-0000-0000',
          },
          { 
            id : '2',
            name : '성진옥',
            address : '충북 청주시 청원구 오창읍 중심상업1로 20',
            email : '성진옥@naver.com',
            phone : '010-1111-1111',
          },
        ],
        [
          { 
            id : '1',
            name : '박성호',
            address : '대전 유성구 온천서로 2',
            email : '박성호@naver.com',
            phone : '010-0000-0000',
          },
          { 
            id : '2',
            name : '차민석',
            address : '대전 중구 대종로 486',
            email : '차민석@naver.com',
            phone : '010-1111-1111',
          },
          { 
            id : '1',
            name : '박성호',
            address : '대전 유성구 온천서로 2',
            email : '박성호@naver.com',
            phone : '010-0000-0000',
          },
          { 
            id : '2',
            name : '차민석',
            address : '대전 중구 대종로 486',
            email : '차민석@naver.com',
            phone : '010-1111-1111',
          },
        ],
        [
          { 
            id : '2',
            name : '김다윤',
            address : '충북 청주시 청원구 오창읍 과학산업2로 385',
            email : '김다윤@naver.com',
            phone : '010-1111-1111',
          },
          { 
            id : '2',
            name : '김다윤',
            address : '충북 청주시 청원구 오창읍 과학산업2로 385',
            email : '김다윤@naver.com',
            phone : '010-1111-1111',
          },
          { 
            id : '2',
            name : '김다윤',
            address : '충북 청주시 청원구 오창읍 과학산업2로 385',
            email : '김다윤@naver.com',
            phone : '010-1111-1111',
          },
        ],
        [
          { 
            id : '2',
            name : '홍길동',
            address : '홍길 동홍시 길동구 길동로 102',
            email : '홍길동@naver.com',
            phone : '010-1111-1111',
          },
        ],
        [],
        [],
        [],
      ]
		}
	},
	mounted() {
	},
	props: {
    day : Number,
	},
	methods : {
	}
}
</script>

<style scoped>
.student-info {
  display: flex;
}
.left {
  width : 30%;
  display: flex;
  flex-direction: row;
  margin-right: 10px;
}
.right {
  width : 60%;
  display: flex;

}
.profile {
  width : 50px;
  height: 50px;
  align-self: center;
}
.info-box {
  padding: 20px 5px 20px 5px;
  align-self: center;
}
.address {
  align-self: center;
}
</style>
