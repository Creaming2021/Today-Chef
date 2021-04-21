<template>
  <div class="col-lg-3 course-right-bar-container">
		오른쪽바
  </div>
</template>


<script>


export default {
  props: {
    courseInfo: Object,
  }

}
</script>

<style>
.course-right-bar-container{
  display: inline-block;
  width: 30%;
  height: 500px;
  padding: 10px;
}

</style>
