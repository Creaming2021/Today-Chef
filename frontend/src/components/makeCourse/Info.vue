<template>
  <div>
    <div class="info-header">
      <div class="info-header-main">
        간단하게 어떤 클래스인지 알려주세요
      </div>
    
      <div class="info-header-sub">
        언젠가 이런 걸 가르쳐봐야지 생각해본 적이 있으신가요?
      </div>
      <div class="info-header-sub">
        간단히 크리에이터님이 알려줄 수 있는 카테고리를 설정해봐요.
      </div>
    </div>

    <div class="category">
      <span>카테고리</span>
      <b-form-select 
        v-model="courseInfo.category" 
        :options="categoryOptions"/>
    </div>

    <div class="row" style="margin: 10px 0 20px 0;">
      <div class="col-lg-3" style="padding : 0;">
        <label :for="'type-date'">강의 날짜 :</label>
      </div>
      <div class="col-lg-9" style="padding : 0;">
        <b-form-input 
          v-model="courseInfo.date" 
          :id="`type-date`" 
          :type="'date'"/>
      </div>
    </div>

    <div class="row" style="margin: 10px 0 20px 0;">
      <div class="col-lg-3" style="padding : 0;">
        <label :for="'type-time'">강의 시작 시간 :</label>
      </div>
      <div class="col-lg-9" style="padding : 0;">
        <b-form-input 
          v-model="courseInfo.startTime" 
          :id="`type-time`" 
          :type="'time'"/>
      </div>
    </div>

    <div class="row" style="margin: 10px 0 20px 0;">
      <div class="col-lg-3" style="padding : 0;">
        <label :for="'type-time'">강의 종료 시간 :</label>
      </div>
      <div class="col-lg-9" style="padding : 0;">
        <b-form-input 
          v-model="courseInfo.endTime" 
          :id="`type-time`" 
          :type="'time'"/>
      </div>
    </div>

    <div class="row" style="margin: 10px 0 20px 0;">
      <div class="col-lg-3" style="padding : 0;">
        <label :for="'type-number'">강의 수강료 :</label>
      </div>
      <div class="col-lg-9" style="padding : 0;">
        <b-form-input 
          v-model="courseInfo.price" 
          :id="`type-number`" 
          :type="'number'"/>
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
      categoryOptions: [
        { value: null, text: '클래스 분야를 선택하세요' },
        { value: 'KOREA', text: '한식' },
        { value: 'JAPAN', text: '일식' },
        { value: 'WESTERN', text: '양식' },
        { value: 'CHINA', text: '중식' },
        { value: 'DESSERT', text: '디저트' },
        { value: 'DRINK', text: '음료' },
        { value: 'WORLD', text: '세계 음식' },
        { value: 'ETC', text: '기타' },
      ]
    }
  },
  props: {
    courseInfo : Object,
  },
}
</script>

<style>
.info-header {
  margin : 20px 0 40px 0;
}
.info-header-main {
  font-size: 20px;
  font-weight: 700;
  margin : 0 0 10px 0;
}
.info-header-sub {
  color: rgb(162,162,162);
  font-size: 14px;
}
.category {
  margin: 10px 0 20px 0;
}
</style>