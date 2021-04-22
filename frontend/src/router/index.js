import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Notice from "../views/Notice.vue";
import NoticeDetail from "../views/CourseDetail.vue";
import Course from "../views/Course.vue";
import CourseDetail from "../views/CourseDetail.vue";
import Creator from "../views/Creator.vue";
import Profile from "../views/Profile.vue";
import Streaming from "../views/Streaming.vue";
import Payment from "../views/Payment.vue";
import VueDaumPostcode from "vue-daum-postcode"
import Sign from "../views/Sign.vue";

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
    path: "/noticeDetail",
    name: "NoticeDetail",
    component: NoticeDetail,
  },
  {
    path: "/course",
    name: "Course",
    component: Course,
  },
  {
    path: "/courseDetail",
    name: "CourseDetail",
    component: CourseDetail,
  },
  {
    path: "/creator",
    name: "Creator",
    component: Creator,
  },
  {
    path: "/me",
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
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
