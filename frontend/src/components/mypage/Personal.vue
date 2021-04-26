<template>
  <div class="container">
      <div class="personal-card">
        <div class="personal-header">프로필 이미지 관리</div>
        <div class="personal-content">
          <div><img :src="personalData.img" alt=""></div>
          <div>이름 : {{personalData.name}}</div>
        </div>
        <div><button @click="onClickChange('img')">수정</button></div>
      </div>
      <div class="personal-card">
        <div class="personal-header">연락처 관리</div>
        <div>연락처 이메일 : {{personalData.email}}</div>
        <div>휴대전화 : {{personalData.phone}}</div>
        <div><button @click="onClickChange('phone&email')">수정</button></div>
      </div>
      <div class="personal-card">
        <div class="personal-header">배송지 관리</div>
        <div>주소 : {{personalData.address}}</div>
        <div><button @click="onClickChange('address')">수정</button></div>
      </div>
  </div>
</template>

<script>

	export default {
		data(){
			return {
      }
		},
		props: {
      personalData : Object,
		},
		methods : {
      onClickChange(type) {
        if (type === 'img') {
          alert("이미지 관리")
        }
        else if (type === 'phone&email') {
          alert("핸드폰 번호, 이메일 관리")
        }
        else if (type === 'address') {
          alert('배송지 관리')
        }
      }
		},
	}
</script>

<style scoped>
.container {
  width: 80%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.personal-card {
  display: flex;
  flex-direction: column;
  padding: 20px 30px 30px 20px;
  width : 100%;
  border: 2px solid #e5e5e5;
  margin-bottom : 15px;
}
.personal-content {
  display: flex;
  align-items: center;
}
.personal-header {
  font-size: 18px;
  font-weight: 700;
}
.personal-card button {
  color: #111111;
  font-size: 15px;
  font-weight: 700;
  text-transform: uppercase;
  display: inline-block;
  border: 1px solid #e5e5e5;
  padding: 6px 25px;
  margin-top: 30px;
  cursor: pointer;
}
.personal-content img {
  width: 100px;
  height: 100px;
  border-radius: 100%;
  margin: 10px 50px 0px 0px;
}
</style>