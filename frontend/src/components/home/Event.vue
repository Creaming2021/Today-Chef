<template>
  <div>
    <b-carousel
      id="carousel-1"
      v-model="slide"
      :interval="4000"
      controls
      indicators
      background="#ababab"
      img-width="1024"
      img-height="300"
      style="text-shadow: 1px 1px 2px #333;"
      @sliding-start="onSlideStart"
      @sliding-end="onSlideEnd"
    >
      <a 
        v-for="image in imageList" 
        :key="image.eventId"
        :href="`notice/${image.eventId}`" >
        <b-carousel-slide 
          class="event-image"
          :img-src="image.image"/>
      </a>

      <!-- Slides with img slot -->
      <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->
      <!-- <b-carousel-slide>
        <template #img>
          <img
            class="d-block img-fluid w-100"
            width="1024"
            height="300"
            src="https://picsum.photos/1024/300/?image=55"
            alt="image slot"
          >
        </template>
      </b-carousel-slide> -->
    </b-carousel>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        slide: 0,
        sliding: null
      }
    },
    props: {
      imageList: Array,
    },
    methods: {
      onSlideStart() {
        this.sliding = true;
      },
      onSlideEnd() {
        this.sliding = false;
      },
      // goToNoticeDetail(e) {
      //   console.log(e);
      //   // this.$router.push({
      //   //   name: 'NoticeDetail',
      //   //   params: { number: id },
      //   // });
      // },
    }
  }
</script>

<style scoped>
.event-image{
  cursor: pointer;
}
</style>