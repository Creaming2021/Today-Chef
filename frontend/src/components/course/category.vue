<template>
  <div class="card">
    <div class="card-heading">
      <a 
        data-toggle="collapse" 
        data-target="#categories"
        @click="openCategory">
          {{categoryTitle}}</a>
    </div>
    <div 
      id="categories" 
      v-bind:class="open ? 'collapse show' : 'collapse'" 
      data-parent="#accordionExample">
      <div class="card-body">
        <div class="shop__sidebar__categories">
          <ul>
            <li 
              v-for="(category, index) in categoryList" 
              v-bind:key="index">
                <a 
                  v-bind:id="category.id"
                  v-bind:class="currentFilter === category.id && 'selected'"
                  @click="setCategory">
                  {{category.value}}</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return {
      open: false,
    }
  },
  props: {
    categoryTitle: String,
    categoryList: Array,
    currentFilter: String,
    type: String,
  },
  methods: {
    openCategory() { 
      this.open = !this.open;
    },
    setCategory(e){
      this.$emit('setCategory', e.target.id);
    }
  }
}
</script>

<style>
.card .selected{
  color: black;
  font-weight: bold;
}
</style>