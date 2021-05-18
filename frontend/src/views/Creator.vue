<template>
  <div>
    <div class="body-header">
      <img src="@/assets/img/banner/banner-teacher.png"/>
    </div>
    <div class="container">
      <div class="row">
        
        <div class="col-lg-3">
          <Sidebar 
            ref="sidebar" 
            :type="type" 
            :courseInfo="courseInfo"/>
        </div>

        <div v-if="type === 'info'" class="col-lg-9">
          <Info 
            ref="info" 
            :courseInfo="courseInfo"/>
        </div>

        <div v-else-if="type === 'thumbnail'" class="col-lg-9">
          <Thumbnail 
            ref="thumbnail" 
            :courseInfo="courseInfo"/>
        </div>

        <div v-else-if="type === 'course'" class="col-lg-9">
          <Class 
            ref="course" 
            :courseInfo="courseInfo"/>
        </div>

        <div v-else-if="type === 'kit'" class="col-lg-9">
          <Kit 
            ref="kit" 
            :courseInfo="courseInfo"/>
        </div>

        <div v-else-if="type === 'chat'" class="col-lg-9">
          <CreateChat 
            ref="chat"
            :courseInfo="courseInfo"/>
        </div>
      </div>
    </div>

    <div class="container" style="padding-top : 40px; padding-bottom : 40px">
      <div class="row">
        <div class="col-lg-3"></div>
        <div class="col-lg-9">
          <div style="display : flex; justify-content : center;">
            <button 
              v-if="this.step !== 0" 
              @click="goToBefore">이전 단계 넘어가기</button>
            <button 
              v-if="this.step !== 4"
              @click="goToNext">다음 단계 넘어가기</button>
            <button 
              v-if="this.step === 4"
              @click="onClickSave">클래스 개설하기</button>
            <button @click="onClickOpenModal">미리보기</button>
          </div>
        </div>
      </div>
    </div>

    <b-modal v-model="modalState" size="xl" hide-footer hide-header>
    <p @click="onClickCloseModal" class="btn" style="display : flex;justify-content : flex-end">X</p>
      <Preview 
        ref="preview"
        :courseInfo="courseInfo"/> 
    </b-modal>
  </div>
</template>

<script>
// Components import
import Sidebar from '@/components/makeCourse/Sidebar.vue';
import Class from '@/components/makeCourse/Class.vue';
import Kit from '@/components/makeCourse/Kit.vue';
import Thumbnail from '@/components/makeCourse/Thumbnail.vue';
import Info from '@/components/makeCourse/Info.vue';
import Preview from '@/components/makeCourse/Preview.vue';
import CreateChat from '@/components/chat/chat/CreateChat.vue';
import { chat } from '@/api/instance.js';

export default {
  components : {
    Sidebar,
    Class,
    Kit,
    Thumbnail,
    Info,
    Preview,
    CreateChat,
  },
  data() {
    return {
      modalState : false,
      step: 0,
      mode: '',
      type : '',
      courseInfo : {
        category: null,
        date: '',
        startTime: '',
        endTime: '',
        price: '',
        name: '',
        descriptions: '',
        images: ['','','','','','','','','',''],
        productId: '',
        roomName: '',
        password: '',
      },
      typeList: ['info', 'thumbnail', 'course', 'kit', 'chat'],
    }
  },
  watch : {
    $route(to, from) { 
      if (to.path != from.path) { 
        this.checkQuery();
      } 
    }
  },
  created () {
    if(this.$store.state.user.memberId === ''){
      this.$router.push({
        name: "Error",
      })
    }
    this.checkQuery();
  },
  methods : {
    onClickOpenModal(){
      this.modalState = true;
    },
    onClickCloseModal() {
      this.modalState = false;
    },
    checkQuery() {
      this.mode = this.$route.params.mode;
      this.type = this.$route.params.type;
      this.step = this.typeList.indexOf(this.type);
    },
    onClickSave() {
      // console.log(this.courseInfo);
      this.$store.dispatch("POST_COURSE", {
          category: this.courseInfo.category,
          date: this.courseInfo.date,
          descriptions: this.courseInfo.descriptions,
          memberId: this.$store.state.user.memberId,
          name: this.courseInfo.name,
          price: this.courseInfo.price,
          productId: this.courseInfo.productId,
          time: this.courseInfo.startTime + "-" + this.courseInfo.endTime,
          images: this.courseInfo.images,
          roomName: this.courseInfo.roomName,
          password: this.courseInfo.password,
        })
        .then((res) => {
          this.handleCreateRoom(res);
        });
    },
    goToBefore(){
      this.step = this.step === 0 ? 0 : this.step - 1;
      this.$router.push({
        name: 'Creator',
        params: {
          mode: this.$route.params.mode,
          type: this.typeList[this.step],
        }
      });
    },
    goToNext(){
      this.step = this.step === 4 ? 4 : this.step + 1;
      this.$router.push({
        name: 'Creator',
        params: {
          mode: this.$route.params.mode,
          type: this.typeList[this.step],
        }
      });
    },
    handleCreateRoom(id) {
      chat
        .post('/room', {
          room_name: this.courseInfo.roomName,
          user: this.$store.state.chat.authUser,
          password: this.courseInfo.password
        })
        .then(res => {
          if (res.data.errors) {
            for (const error of res.data.errors) {
              const [value] = Object.values(error);
              this.$swal.fire({
                icon: 'error',
                text: value,
              });
            }
          } else {
            this.$store.dispatch('addRoom', res.data);
            this.getSocket.emit('roomAdded', res.data);
            this.$router.push({
              name: "ItemDetail",
              params: {
                item: 'course',
                category: this.courseInfo.category,
                id: id,
                type: 'introduction',
              }
            });
          }
        })
        .catch(err => {
          console.log(err);
        });

      setTimeout(() => {
        this.errors = [];
      }, 1500);
    },
  }
}
</script>

<style scoped>
.modal-lg {
  max-width: 80%,!important;
}

.container button{
  padding: 20px;
  margin: 10px;
  border-radius: 10px;
  border: none;
  background: #f3f2ee;
  font-weight: bold;
}

.container button:hover{
  cursor: pointer;
  background: #e53637;
  color: white;
}
</style>
