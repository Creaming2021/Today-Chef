<template>
  <div>
    <Nav/>
    <router-view/>
    <Footer/>
  </div>
</template>


<script>
import '@/assets/css/bootstrap.min.css';
import '@/assets/css/font-awesome.min.css';
import '@/assets/css/elegant-icons.css';
import '@/assets/css/magnific-popup.css';
import '@/assets/css/nice-select.css';
import '@/assets/css/owl.carousel.min.css';
import '@/assets/css/slicknav.min.css';
import '@/assets/css/style.css';
import Nav from './components/common/Nav.vue';
import Footer from './components/common/Footer.vue';

export default {
  components :{Nav, Footer},
}
</script>

<style>
#app {
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

@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&display=swap');
</style>
