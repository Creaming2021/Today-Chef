<template>
  <tr>
    <td class="quantity__item">
      <div class="product__cart__item__text">
        <h6>{{payment.date.substring(0,10)}}-{{payment.orderId}}</h6>
      </div>
    </td>
    <td class="product__cart__item">
      <div class="quantity">
        <div class="pro-qty-2" v-for="detail in payment.orderDetails" :key="detail.product.id">
          <div class="product-img">
            <b-badge variant="primary">{{detail.product.category}}</b-badge><br/>
            <img :src="detail.product.image" />
          </div>
          <div class="product-detail">
            <div class="name">{{detail.product.name}}</div>
            <div class="price">{{detail.product.price.toLocaleString()}}원</div>
            <div class="amount">{{detail.amount}}개</div>
          </div>
        </div>
      </div>
    </td>
    <td class="cart__price centered">{{payment.totalPrice.toLocaleString()}}원</td>
    <td class="cart__price">
      <div 
        class="payment-detail centered" 
        @click="onClickPaymentDetail">상세 내역</div>
    </td>
    <b-modal 
      size="lg" 
      v-model="modalState" 
      hide-footer hide-header>
      <div class="container" style="min-height : 300px">
        <p @click="onCloseDetailModal" class="btn" style="display: flex; justify-content: flex-end; width: 100%;">X</p>
        <DeliveryDetail 
          v-if="modalType === 'detail'"
          :payment="payment"
          :deliveryDetail="deliveryDetail"/>
        <DeliveryTracker 
          v-else-if="modalType === 'delivery'" 
          :changeModalType="changeModalType"
          :deliveryTrack="deliveryTrackList"/>
      </div>
    </b-modal>
  </tr>
</template>

<script>
import * as delivery from '@/api/delivery.js';
import DeliveryDetail from '@/components/mypage/DeliveryDetail.vue';
import DeliveryTracker from '@/components/mypage/DeliveryTracker.vue';

  export default {
    data(){
      return {
        modalState : false,
        deliveryTrackList: [],
        modalType: '',
      }
    },
    components: {
      DeliveryDetail,
      DeliveryTracker,
    },
    props: {
      payment : Object,
      type: String,
    },
    methods : {
      onClickPaymentDetail() {
        this.onOpenDetailModal();
      },
      onCloseDetailModal() {
        this.modalState = false;
      },
      onOpenDetailModal() {
        this.modalState = true;
        this.changeModalType('detail');
      },
      deliveryDetail() {
        delivery.getDeliveryTracker({
            carrierId: this.payment.delivery.company,
            trackId: this.payment.delivery.number,
          }
        ).then(({ data }) => {
          this.deliveryTrackList = data;
        })
        .finally(() => { 
          this.changeModalType('delivery');
        });
      },
      changeModalType(newType){
        this.modalType = newType;
      },
    }
  }
</script>

<style scoped>
.product__cart__item img {
  width : 150px;
  height: 150px;
  object-fit: cover;
  display: inline-block;
}
.payment-detail {
  cursor: pointer;
}
.centered{
  text-align: center;
}
.product-img{
  display: inline-block;
  width: 150px;
}
.product-detail{
  display: inline-block;
  width: 200px;
  padding: 0px 0px 0px 20px;
}
.product-detail .name{
  margin: 20px 0px 10px 0px;
  font-weight: bold;
  font-size: 1.5rem;
}
.product-detail .price{
  font-weight: 600;
  font-size: 1rem;
  margin-bottom: 5px;
}
.product-detail .amount{
  font-size: 0.8rem;
}
.pro-qty-2{
  margin-bottom: 20px;
}
</style>