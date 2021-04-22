<template>
  <div>로그인 요청중</div>
</template>

<script>
import { getKakaoToken } from '@/api/kakao.js';

export default {
  data(){
    return {
      code: '',
    }
  },
  created(){
    this.checkQuery();
  },
  methods: {
    checkQuery() {
      this.code = this.$route.query.code;
      // console.log(this.code);
      console.log(this.code);
      if(this.code) getKakaoToken(this.code);
    },
	}
}
</script>
