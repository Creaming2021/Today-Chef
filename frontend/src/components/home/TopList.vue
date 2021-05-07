<template>
  <!-- Product Section Begin -->
  <section class="product spad">
      <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <ul class='filter__controls'>
                    <li 
                      v-for="(tab, index) in tabList"
                      v-bind:key="index">{{tab}}
                    </li>
                </ul>
            </div>
        </div>
        <div class="row product__filter">
          <div class="col-lg-4 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals" 
            v-for="(course,idx) in itemList" :key="idx">
              <!-- <course-item 
                  v-for="course in itemList" 
                  v-bind:key="course.id" 
                  :course="course"/> -->
                <ItemCard :course="course" :idx="idx" />
            </div>
        </div>
      </div>
  </section>
  <!-- Product Section End -->
</template>


<script>
import ItemCard from '@/components/common/ItemCard.vue';

export default {
  name: "TopList",
  components: {
    ItemCard,
  },
  data() {
    return {
      currentActive: '',
    }
  },
  mounted() {
    this.setChangeTabEvent();
  },
  props: {
    tabList: [Array, Object],
    itemList: [Array, Object],
    type: String,
  },
  watch: {
    currentActive: function () {
      this.$emit("currentActiveChanged", { 
        tab: this.currentActive.innerText,
        type: this.type,
      });
    }
  },
  methods : {
    setChangeTabEvent() {
      let tab = null;
      const tabs = document.querySelectorAll('.filter__controls');
      tab = this.type === 'arrival' ? tabs[0] : tabs[1];

      tab.childNodes.forEach((node,idx) => {
        if (idx === 0) {
          node.setAttribute('class','active');
          this.currentActive = node;
        }
        node.addEventListener('click', (e) => {
          e.stopPropagation();
          this.currentActive.classList.remove('active');
          node.classList.add('active');
          this.currentActive = node;

          alert("요청 때립니다");
        })
      },
      );
    }
  }
}
</script>

<style>
</style>
