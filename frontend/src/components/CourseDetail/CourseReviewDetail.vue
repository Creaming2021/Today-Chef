<template>
  <div class="course-review-detail-container">
    <div class="header">
      <div class="review-info">
        <img 
          class="profile"
          :src="reviewInfo.profile"/>
        <div>
          <div class="writer">{{reviewInfo.writer}}</div><br/>
          <div class="date">{{reviewInfo.date}}</div>
        </div>
      </div>
      <div class="title">{{reviewInfo.title}}</div>
      <div class="comment-size">
        <b-icon icon="chat-square"/>
        {{reviewInfo.commentList.length}}</div>
    </div>
    <hr/>
    <div class="content">{{reviewInfo.content}}</div><hr/>
    <div class="comment-container">
      <div>댓글 {{reviewInfo.commentList.length}}개</div>
      <div v-for="comment in reviewInfo.commentList" v-bind:key="comment.id">
        <img 
          class="profile"
          :src="comment.profile"/>
        <div>
          <div class="writer">{{comment.writer}}</div><br/>
          <div class="date">{{comment.date}}</div>
        </div>
        <div class="content">{{comment.content}}</div>
      </div>
    </div>
  </div>
</template>


<script>
export default {
  data() {
    return {
      reviewInfo: {
				id: 1,
				profile:'https://img.sbs.co.kr/newsnet/etv/upload/2021/03/05/30000673929_1280.jpg',
				title: '리뷰 제목',
				content: '리뷰 내용',
				writer: '작성자 닉네임',
				date: '2021년 4월 21일',
				commentList: [
          {
            id: 1,
            writer: '리뷰 작성자',
            date: '2021년 4월 21일',
            profile: 'https://mblogthumb-phinf.pstatic.net/MjAyMDA0MDZfMjg0/MDAxNTg2MTgwNjMxODE1.B22_kATsB4cyKg2yr9x6GoRk8OuBXutbaQdRG2wxbAMg.ZIGXNEYc2xDiH61A18wLGcNeFNl4RsDTCrK1pMY0cnIg.JPEG.b-seol/46858675_511354619382565_4518962982505897027_n(1).jpg?type=w800',
            content: '리뷰 내용',
          },
          {
            id: 2,
            writer: '리뷰 작성자',
            date: '2021년 4월 21일',
            profile: 'https://i1.sndcdn.com/artworks-WzFnntNp1E7dHw7y-RqzlrQ-t500x500.jpg',
            content: '리뷰 내용',
          }
        ],
      }
    }
  },
  created() {
    this.checkQuery();
		// this.getReviewInfo(this.reviewInfo.id);
  },
  methods: {
    checkQuery() {
      this.reviewInfo.id = this.$route.params.id;
    },
		getReviewInfo(id) {
			alert(`이제 ${id} 로 요청때립니다.`);
		}
  },
}
</script>

<style>
.course-review-detail-container{
  padding: 10px;
}

.course-review-detail-container img{
  display: inline-block;
  border-radius: 50%;
  width: 50px;
  height: 50px;
  object-fit: cover;
}

.course-review-detail-container .writer{ 
  display: inline;
  font-size: 1rem;
  font-weight: bold;
  color: #696969;
}

.course-review-detail-container .date{ 
  display: inline;
  font-size: 0.8rem;
  color: #979797;
}

.course-review-detail-container .header{
  text-align: left;
}

.course-review-detail-container .header .review-info{
  float: left;
  display: block;
  height: 80px;
  margin-left: 10px;
}

.course-review-detail-container .header .review-info > div, 
.course-review-detail-container .comment-container > div > div{
  display: inline-block;
  height: 80px;
  margin-left: 10px;
}

.course-review-detail-container .header .comment-size{
  float: right;
  display: block;
  font-weight: bold;
  font-size: 1.25rem;
  color: darkgray
}

.course-review-detail-container .title{
  text-align: center;
  font-weight: bold;
  font-size: 1.5rem;
  color: black
}

.course-review-detail-container .comment-container {
  text-align: left;
}

.course-review-detail-container .comment-container .content{
  text-align: center;
}
</style>
