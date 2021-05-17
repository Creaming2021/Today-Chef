<template>
  <div style="width:80%">
    <div class="container">
      <div class="student-info">
        <div class="left">
          <img class="profile" :src="student.profileImage"/>
          <div  class="info-box">
            <div class="name">{{student.nickname}}</div>
          </div>
        </div>
        <div class="right">
          <div class="address">{{student.phone}}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "Student",
  props: {
    student: {
      memberId: Number,
      nickname: String,
      phone: String,
      profileImage: String,
    },
  },
}
</script>

<style scoped>
.student-info {
  display: flex;
}
.left {
  width : 50%;
  display: flex;
  flex-direction: row;
  margin-right: 10px;
}
.right {
  width : 50%;
  display: flex;

}
.profile {
  width : 50px;
  height: 50px;
  align-self: center;
  margin-right: 30px;
}
.info-box {
  padding: 20px 5px 20px 5px;
  align-self: center;
}
.address {
  align-self: center;
}
</style>
