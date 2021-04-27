<template>
    <div class="blog__item">
      <img class="blog__item__pic set-bg" :src="course.thumbnail"/>
      <div class="blog__item__text">
          <!-- <span>{{course.category}} <img src="@/assets/img/icon/calendar.png" alt="">{{course.date}} / 좋아요 수 : {{course.likeCnt}}</span> -->
          <h6>{{course.name}}</h6>
          <span href="#">{{course.teacher}} / 별점 : {{course.rate}}</span>
          <h6>{{course.price}}원</h6>
          <a @click="onClickDetail">자세히 보기</a>
      </div>
      <b-modal 
        size="lg" 
        v-model="modalState" 
        hide-footer hide-header>
        <div class="container" style="min-height : 300px">
          <p @click="onCloseDetailModal" class="btn" style="display : flex;justify-content : flex-end">X</p>
          <div class="row" style="justify-content : space-around">
            <p class="modal-btn" @click="onClickUpdateClass">강의 정보 수정</p>
            <p class="modal-btn" @click="onClickReopenClass">강의 재오픈</p>
          </div>
          <div v-if="modalCourse">
            <div style="text-align : center; font-weight : 700; font-size : 20px">{{modalCourse.name}}</div>
            <div class="row" style="justify-content : center" id="day-filter">
              <span class="active" @click="onClickDay(0, $event)">월</span>
              <span @click="onClickDay(1, $event)">화</span>
              <span @click="onClickDay(2, $event)">수</span>
              <span @click="onClickDay(3, $event)">목</span>
              <span @click="onClickDay(4, $event)">금</span>
              <span @click="onClickDay(5, $event)">토</span>
              <span @click="onClickDay(6, $event)">일</span>
            </div>
            <div class="row" style="justify-content : center; height: 60vh;
">
              <Student :day="currentDay"/>
            </div>
          </div>
        </div>
        </b-modal>
    </div>
</template>

<script>
import Student from '@/components/mypage/Student.vue';
  export default {
    components: {
      Student,
  },
  data(){
    return {
      modalState : false,
      modalCourse : null,
      currentDay : 0,
    }
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
    type : String,
  },
  methods : {
    onClickDetail() {
      if (this.type === "myClass") {
        this.onOpenDetailModal(this.course);
      }
      if (this.type === "otherClass") {
        alert('강의 디테일로')
      }
    },
    onClickDay(dayNum,e) {
      e.target.classList.toggle('active')
      e.target.parentNode.childNodes[this.currentDay].classList.toggle('active')
      this.currentDay = dayNum;
    },
    onCloseDetailModal() {
      this.modalState = false;
      this.modalState = null;
    },
    onOpenDetailModal(course) {
      this.modalState = true;
      this.modalCourse = course;
    },
    onClickUpdateClass() {
      alert('강의 수정');
    },
    onClickReopenClass() {
      alert('강의 재오픈');
    }
  }
}
</script>

<style scoped>
.blog__item__text h6 {
  color: #0d0d0d;
  font-weight: 700;
}
.blog__item__text a {
  cursor: pointer;
}
.modal-btn {
  display: inline-block;
  font-weight: 400;
  background-color: sandybrown;
  text-align: center;
  vertical-align: middle;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  border: 1px solid transparent;
  padding: .375rem .75rem;
  font-size: 1rem;
  line-height: 1.5;
  border-radius: .25rem;
  transition: color .15s ease-in-out,background-color .15s ease-in-out,border-color .15s ease-in-out,box-shadow .15s ease-in-out;
}
.row span {
  margin-right: 10px;
}
.row span:hover {
  color: #111111;
  text-decoration: underline;
  cursor: pointer;
}
.active {
  color: #111111;
  font-weight: 700;
}
</style>