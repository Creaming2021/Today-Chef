<template>
  <div class="shop__sidebar">
    <div class="shop__sidebar__accordion">
      <div class="accordion" id="accordionExample">
        <div class="card">
          <div class="card-heading">
            <div>클래스 만들기</div>
          </div>
          <div id="filter" class="collapse show" data-parent="#accordionExample">
            <div class="card-body">
              <div class="shop__sidebar__price">
                <ul id="course-make-filter">
                  <li id="info" @click="changeFilter('info')">1. 기본 정보</li>
                  <li id="thumbnail" @click="changeFilter('thumbnail')">2. 제목 및 커버</li>
                  <li id="course" @click="changeFilter('course')">3. 클래스 소개</li>
                  <li id="kit" @click="changeFilter('kit')">4. 키트 소개</li>
                  <li id="chat" @click="changeFilter('chat')">5. 채팅방 생성하기</li>
                </ul>
              </div>
            </div>
          </div>
        </div>  
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
      tmpSaveFlag : false,
		}
	},
  watch : {
    currentAtiveFilter : function(to,from) {
      document.querySelector(`#${from}`).setAttribute('class','');
      document.querySelector(`#${to}`).setAttribute('class','active');
    }
  },
  mounted () {
  },
	props: {
    type : String,
    creatorData : Object,
	},
	methods : {
    onClickSaveBtn() {
      this.tmpSaveFlag = true;
    },
    isEmptyObject(obj) {
      for (const key of Object.keys(obj)) {
        if (typeof(obj[key]) === Object) {
          for (const v of Object.values(key)) {
            if (v === '') {
              return false;
            }
          }
        }
        else {
          if (obj[key] === '') {
            return false;
          }
        }
      }
      return true;
    },
    changeFilter(type){
      this.$router.push({
        name: 'Creator',
        params: {
          mode: this.$route.params.mode,
          type: type,
        }
      });
      // if (this.$route.params.type === 'preview') this.tmpSaveFlag = true
      // if (this.tmpSaveFlag) {
      //   this.tmpSaveFlag = type === 'preview' ? true : false
      //   if (type === 'preview') {
      //     if(!this.isEmptyObject(this.creatorData)) {
      //       alert('모든 내용을 채워주세요')
      //       return
      //     }
      //   }
      //   this.$router.push({
      //     name: 'Creator',
      //     params: {
      //       type: type,
      //     }
      //   })
      // }
      // else {
      //   alert('임시 저장을 해주세요')
      // }
    },
  }
}
</script>

<style scoped>
.shop__sidebar__accordion .card-heading div {
	color: #111111;
	font-size: 18px;
	font-weight: 700;
	text-transform: uppercase;
	display: block;
  cursor : default;
}
.shop__sidebar__accordion .card-heading div:after {
	content: "";
	font-family: "FontAwesome";
	font-size: 24px;
	font-weight: 700;
	color: #111111;
	position: absolute;
	right: 0;
	top: 2px;
	line-height: 20px;
}
.shop__sidebar__accordion .active { 
	color: #111111;
	font-weight: 700;
}
.shop__sidebar__price ul li div{
	color: #b7b7b7;
	font-size: 15px;
	line-height: 32px;
	-webkit-transition: all, 0.3s;
	-o-transition: all, 0.3s;
	transition: all, 0.3s;
	cursor: pointer;
}
.shop__sidebar__price ul li div:hover{
	color: #111111;
  text-decoration: underline;
}
.active {
	color: #111111;
	font-weight: 700;
  background-color: rgba(0,0,0,0.05);
}
#course-make-filter li {
  cursor: pointer;
}
#course-make-filter li:hover, .coupon-nav li:hover {
  text-decoration: underline;
}

</style>