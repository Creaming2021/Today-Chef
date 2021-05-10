import Vue from "vue";
import VueRouter from "vue-router";
import { BootstrapVue, BootstrapVueIcons } from 'bootstrap-vue'
import Home from "@/views/Home.vue";
import Notice from "@/views/Notice.vue";
import ItemList from "@/views/ItemList.vue";
import ItemDetail from "@/views/ItemDetail.vue";
import MyCourse from "@/views/MyCourse.vue";
import Creator from "@/views/Creator.vue";
import Profile from "@/views/Profile.vue";
import Payment from "@/views/Payment.vue";
import VueDaumPostcode from "vue-daum-postcode"
import Sign from "@/views/Sign.vue";
import ErrorPage from "@/views/Error.vue";

Vue.use(BootstrapVue);
Vue.use(BootstrapVueIcons);
Vue.use(VueRouter, VueDaumPostcode);

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location)
    .catch(err => {
      if (err.name !== 'NavigationDuplicated') throw err;
    });
};


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
    path: "/me/:type",
    name: "Profile",
    component: Profile,
  },
  {
    path: "/creator/list",
    name: "MyCourse",
    component: MyCourse,
  },
  {
    path: "/creator/:mode/:type",
    name: "Creator",
    component: Creator,
  },
  {
    path: "/:item/:category",
    name: "ItemList",
    component: ItemList,
  },
  {
    path: "/:item/:category/:id/:type/:reviewId",
    name: "ItemDetail1",
    component: ItemDetail,
  },
  {
    path: "/:item/:category/:id/:type",
    name: "ItemDetail",
    component: ItemDetail,
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
