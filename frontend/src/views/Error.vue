<template>
  <div class="error-container">
    <div class="icon-container"> 
      <img src="https://uploads.sitepoint.com/wp-content/uploads/2015/12/1450973046wordpress-errors.png"/>
    </div>
    <div class="error-message">잘못된 접근입니다.</div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.error-container{
  width: 100%;
  height: 500px;
  padding: 30px;
}

.error-container .icon-container{
  text-align: center;
  margin: auto;
}

.error-container img{
  width: 500px;
  height: auto;
}

.error-container .error-message{
  text-align: center;
  font-size: 2rem;
  font-weight: bold;
}
</style>