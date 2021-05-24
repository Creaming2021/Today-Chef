<template>
  <div>
    <div class="info-header">
      <div class="info-header-main">
        어떤 걸 사용 하시나요?
      </div>
    
      <div class="info-header-sub">
        클래스에서 사용하는 밀키트를 선택해 주세요. 
      </div>
    </div>

    <ProductCard 
      :itemDetail="product"
      :setProductItem="setProductItem"/>

    <input 
      class="search-product-name"
      v-model="keyword"
      @keyup.enter="setFilteredList"
      placeholder="밀키트 이름을 검색하세요."/><br/>
    
    <div class="row selected-product">
      <div 
        class="col-lg-3 col-md-4 col-sm-4" 
        v-for="item in filteredList" 
        :key="item.productId">
          <ProductCard 
            :itemDetail="item" 
            :setProductItem="setProductItem"/>
      </div>
    </div>

  </div>
</template>

<script>
import { mapState } from 'vuex';
import ProductCard from '@/components/makeCourse/productCard.vue';
  
export default {
  components: {
    ProductCard,
  },
  data(){
    return {
      keyword: '',
      filteredList: [],
    }
  },
  created(){
    this.getProductList();
  },
  computed : {
    ...mapState({
      productList: state => state.product.productList,
      product: state => state.product.product,
    }),
  },
  props: {
    courseInfo : Object,
  },
  methods: {
    getProductList(){
      this.$store.dispatch('GET_PRODUCT_LIST');
    },
    getProduct(){
      this.$store.dispatch('GET_PRODUCT', this.courseInfo.productId);
    },
    setFilteredList(){
      this.filteredList = this.productList.filter(item => item.name.includes(this.keyword));
    },
    setProductItem(id){
      this.courseInfo.productId = id;
      this.getProduct();
    }
  },
  watch: {
    productList: function(){
      this.setFilteredList();
    },
  },
}
</script>

<style scoped>
.imageEditorApp {
  width: 1000px;
  height: 800px;
}
.tui-image-editor-save-btn {
    position: absolute;
    left: 0;
    right: 0;
    display: inline-block;
    top: 0;
    bottom: 0;
    width: 100%;
    cursor: pointer;
    opacity: 1;
}
.search-product-name{
  display: block;
  margin-bottom: 30px;
  border-radius: 25px;
  border: 1px solid darkgray;
  width: 100%;
  height: 50px;
  padding-left: 20px;
}
.selected-product{
  max-height: 1500px;
  overflow: auto;
}
</style>