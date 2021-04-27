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
      <!-- Text slides with image -->
      <b-carousel-slide img-src="https://github.com/wlsdhr0831/wlsdhr0831/blob/main/event%20(1).png?raw=true"/>

      <!-- Slides with custom text -->
      <b-carousel-slide img-src="https://github.com/wlsdhr0831/wlsdhr0831/blob/main/event%20(2).png?raw=true"/>

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
    methods: {
      onSlideStart() {
        this.sliding = true;
      },
      onSlideEnd() {
        this.sliding = false;
      }
    }
  }
</script>