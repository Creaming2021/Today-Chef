import Vue from "vue";
import VueRouter from "vue-router";
import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue'
import Home from "@/views/Home.vue";
import Notice from "@/views/Notice.vue";
import Course from "@/views/Course.vue";
import CourseDetail from "@/views/CourseDetail.vue";
import Creator from "@/views/Creator.vue";
import Profile from "@/views/Profile.vue";
import Streaming from "@/views/Streaming.vue";
import Payment from "@/views/Payment.vue";
import VueDaumPostcode from "vue-daum-postcode"
import Sign from "@/views/Sign.vue";
import ErrorPage from "@/views/Error.vue";

Vue.use(BootstrapVue);
Vue.use(BootstrapVueIcons);
Vue.use(VueRouter, VueDaumPostcode);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/notice",
    name: "Notice",
    component: Notice,
  },
  {
    path: "/notice/:number",
    name: "NoticeDetail",
    component: Notice,
  },
  {
    path: "/course",
    name: "Course",
    component: Course,
  },
  {
    path: "/course/:type",
    name: "CourseDetail",
    component: CourseDetail,
  },
  {
    path: "/creator/:type",
    name: "Creator",
    component: Creator,
  },
  {
    path: "/me/:type",
    name: "Profile",
    component: Profile,
  },
  {
    path: "/class",
    name: "Streaming",
    component: Streaming,
  },
  {
    path: "/payment",
    name: "Payment",
    component: Payment,
  },
  {
    path: "/auth",
    name: "Sign",
    component: Sign,
  },
  {
    path: "/error",
    name: "Error",
    component: ErrorPage,
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
