<template>
  <div>
    <Home/>
  </div>
</template>


<script>
import Home from '@/views/Home.vue'
import '@/assets/css/bootstrap.min.css';
import '@/assets/css/font-awesome.min.css';
import '@/assets/css/elegant-icons.css';
import '@/assets/css/magnific-popup.css';
import '@/assets/css/nice-select.css';
import '@/assets/css/owl.carousel.min.css';
import '@/assets/css/slicknav.min.css';
import '@/assets/css/style.css';


// import "@/assets/js/jquery-3.3.1.min.js"
// import "@/assets/js/bootstrap.min.js"
// import "@/assets/js/jquery.nice-select.min.js"
// import "@/assets/js/jquery.nicescroll.min.js"
// import "@/assets/js/jquery.magnific-popup.min.js"
// import "@/assets/js/jquery.countdown.min.js"
// import "@/assets/js/jquery.slicknav.js"
// import "@/assets/js/mixitup.min.js"
// import "@/assets/js/owl.carousel.min.js"
// import "@/assets/js/main.js"

export default {
  components :{Home},
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
