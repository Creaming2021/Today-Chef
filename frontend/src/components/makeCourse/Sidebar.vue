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
                  <li @click="changeFilter('info')">1. 기본 정보</li>
                  <li @click="changeFilter('thumbnail')">2. 제목 및 커버</li>
                  <li @click="changeFilter('course')">3. 클래스 소개</li>
                  <li @click="changeFilter('kit')">4. 키트 소개</li>
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
		}
	},
  mounted () {

  },
	props: {
	},
	methods : {
    changeFilter(type){
      this.$router.push({
        name: 'Creator',
        params: {
          type: type,
        }
      })
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
}
#course-make-filter li {
  cursor: pointer;
}
#course-make-filter li:hover, .coupon-nav li:hover {
  text-decoration: underline;
}

</style>