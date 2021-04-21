<template>
  <div class="course-detail-container">
    <CourseThumbnail :thumbnailList="thumbnailList"/>
    <div class="row">
      <CourseDetailMain/>
      <CourseRightbar :courseInfo="courseInfo"/>
    </div>
  </div>
</template>


<script>
import CourseThumbnail from '@/components/CourseDetail/CourseThumbnail.vue';
import CourseDetailMain from '@/components/CourseDetail/CourseDetailMain.vue';
import CourseRightbar from '@/components/CourseDetail/CourseRightbar.vue';

export default {
  data(){
    return {
      thumbnailList: [
        'https://www.paris.co.kr/wp-content/uploads/200220_%E1%84%89%E1%85%A2%E1%86%BC%E1%84%84%E1%85%A1%E1%86%AF%E1%84%80%E1%85%B5-%E1%84%83%E1%85%B3%E1%86%B7%E1%84%88%E1%85%AE%E1%86%A8-1280x1280.jpg',
        'http://img.vogue.co.kr/vogue/2020/12/style_5fc60ec7705fe.jpg',
        'https://lh3.googleusercontent.com/proxy/6D65tlQRgRPDrlESXPO51GNonBRy2sn3ZgTiuOdiGWl6XCMkuqDmi0YhbHQ9HgwwLBW6Jjz48VcQab07mtNcLpXiOvh9mNrrV8Z7GpuqQnlcHkSvIsHyhY0JfqZJSX2VEf4MGo657bUdmVg',
        'https://cdn.class101.net/images/a89a90ff-baa4-4ba2-b7d5-317f09e84a71',
        'https://i.pinimg.com/564x/0e/ef/38/0eef38624526b44f1741e7b663179125.jpg',
        'https://dimg.donga.com/ugc/CDB/WOMAN/Article/5f/f8/06/41/5ff806410c12d2738de6.jpg',
      ],
      courseInfo : {
        id: 1,
        category: '한식',
        date: '2021.04.16',
        name: '쉽게 배워보는 갈비찜',
        teacher: '김한식',
        price: '50000',
        likeCnt: '123',
        rate: '3.5',
      },
    }
  },
  components: {
    CourseThumbnail,
    CourseDetailMain,
    CourseRightbar,
  },
}
</script>

<style>
.course-detail-container{
  width: 70%;
  text-align: center;
  margin: auto;
}
</style>
