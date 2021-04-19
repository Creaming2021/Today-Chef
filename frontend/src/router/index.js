import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Course from "../views/Course.vue";
import CourseDetail from "../views/CourseDetail.vue";
import Creator from "../views/Creator.vue";
import Profile from "../views/Profile.vue";
import Streaming from "../views/Streaming.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
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
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
