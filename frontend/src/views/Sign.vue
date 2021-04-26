<template>
  <div>로그인 요청중
    {{email}} / {{profile.nickname}}
  </div>
</template>

<script>
export default {
  // data(){
  //   return {
  //     code: '',
  //     email: '',
  //     profile: null,
  //   }
  // },
  // created(){
  //   this.checkQuery();
  // },
  // methods: {
  //   checkQuery() {
  //     this.code = this.$route.query.code;
  //     this.code = this.$route.query.code;
  //     this.code = this.$route.query.code;
  //     // console.log(this.code);
  //     console.log(this.code);
  //     if(this.code) {
  //       window.Kakao.Auth.setAccessToken(this.code);
  //     }else if(this.email && this.profile){
  //       console.log(this.email, this.profile);
  //     }
  //   },
	// }
}
</script>
