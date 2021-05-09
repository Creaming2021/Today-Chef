<template>
    <div class="blog__item">
      <img class="blog__item__pic set-bg" 
        :src="itemDetail.image || (itemDetail.images && itemDetail.images[0].imageUrl) || 'https://imgix.kotaku.com.au/content/uploads/sites/3/2016/02/17/whey2gwjqbx21xu023qo.gif?ar=16%3A9&auto=format&fit=crop&q=65&w=720&nrs=40&fm=gif'"/>
      <div class="blog__item__text">
        <span>{{itemDetail.category}}</span>
        <span v-if="item === 'course'"><img src="@/assets/img/icon/calendar.png" alt="">{{itemDetail.date}}</span>
        <h6>{{itemDetail.name}}</h6>
        <span v-if="item === 'course'" href="#">{{itemDetail.profile.nickname}} </span><span> 별점 : {{itemDetail.rating}}</span>
        <h6>{{itemDetail.price}}원</h6>
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
          <div style="text-align : center; font-weight : 700; font-size : 20px">{{this.itemDetail.name}}</div>
          <div class="row" style="justify-content : center; height: 60vh;"
            v-for="student in studentList" :key="student.memberId">
              <Student :student="student"/>
          </div>
        </div>
      </b-modal>
    </div>
</template>

<script>
import { mapState } from 'vuex';
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
  computed : {
    ...mapState({
      studentList: state => state.course.studentList,
    }),
  },
  props: {
    item: String,
    itemDetail: {
      courseId: Number,
      image: String,
      category: String,
      date: String,
      name: String,
      price: Number,
      profile: {
        memberId: Number,
        nickname: String,
        phone: String,
        profileImage: String,
      },
      rating: Number,
      time: String,
    },
    type : String,
  },
  methods : {
    onClickDetail() {
      if (this.type === "teacher") {
        this.$store.dispatch('GET_COURSE', this.course.courseId);
        this.$store.dispatch('GET_COURSE_STUDENT_LIST', this.course.courseId);
        this.onOpenDetailModal(this.course);
      } else if (this.type === "student") {
        this.$router.push({
          name: 'ItemDetail',
          params: {
            item: 'course',
            category: this.itemDetail.category.toLowerCase(),
            id: this.itemDetail.courseId,
            type: 'introduction',
          }
        })
      } else {
        this.$router.push({
          name: 'ItemDetail',
          params: {
            item: this.item,
            category: this.itemDetail.category.toLowerCase(),
            id: this.item === 'course' ? this.itemDetail.courseId : this.itemDetail.productId,
            type: 'introduction',
          }
        })
      }
    },
    onClickDay(dayNum,e) {
      e.target.classList.toggle('active')
      e.target.parentNode.childNodes[this.currentDay].classList.toggle('active')
      this.currentDay = dayNum;
    },
    onCloseDetailModal() {
      this.modalState = false;
    },
    onOpenDetailModal() {
      this.modalState = true;
    },
    onClickUpdateClass() {
      this.$router.push({
        name: 'Creator',
        params: {
          mode: 'update',
          type: 'info',
        }
      });
    },
    onClickReopenClass() {
      this.$router.push({
        name: 'Creator',
        params: {
          mode: 'reopen',
          type: 'info',
        }
      });
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