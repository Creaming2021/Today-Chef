<template>
  <div class="course-qna-container">
    <div class="qna-container" v-for="qna in qnaList" v-bind:key="qna.id">
      <div class="user-info">
        <img :src="qna.profile"/>
        <div>
          <div class="writer">{{qna.writer}}</div>
          <div class="date">{{qna.date}}</div>
        </div>
      </div>
      <div class="content">
        {{qna.content}}
      </div>
      <div class="answer-list-container">
        <div class="answer-container" v-for="answer in qna.answerList" v-bind:key="answer.id">
          <div class="user-info">
            <img :src="answer.profile"/>
            <div>
              <div class="writer">{{answer.writer}}</div>
              <div class="date">{{answer.date}}</div>
            </div>
          </div>
          <div class="content">
            {{answer.content}}
          </div>
        </div>
        <input placeholder="답변을 입력하세요."/> 
      </div>
      <hr/>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    qnaList: Array,
  }
}
</script>

<style>
.course-qna-container {
  text-align: left;
}

.course-qna-container .user-info {
  margin-bottom: 10px;
}

.course-qna-container .user-info .writer {
  font-weight: bold;
  color: #474747;
}

.course-qna-container .user-info .date {
  color: #666666;
}

.course-qna-container .user-info > img{
  display: inline-block;
  margin: 10px;
  border-radius: 50%;
  width: 50px;
  height: 50px;
  object-fit: cover;
}

.course-qna-container .user-info > div{
  display: inline-block;
  font-size: 1rem;
}

.course-qna-container .answer-list-container{
  margin: 20px 0px 20px 0px;
}

.course-qna-container .answer-list-container .answer-container{
  margin-bottom: 20px;
  background-color: #f3f2ee;
  padding: 20px;
  border-radius: 10px;
}

.course-qna-container .answer-list-container .user-info > img{
  display: inline-block;
  margin: 10px;
  border-radius: 50%;
  width: 30px;
  height: 30px;
  object-fit: cover;
}

.course-qna-container .answer-list-container .user-info > div{
  display: inline-block;
  font-size: 0.8rem;
  font-weight: normal;
  color: #666666;
}

.course-qna-container .answer-list-container > input{
  display: block;
  margin: 20px 0px 20px 0px;
  border-radius: 25px;
  border: 1px solid darkgray;
  width: 100%;
  height: 50px;
  padding-left: 20px;
}

</style>
