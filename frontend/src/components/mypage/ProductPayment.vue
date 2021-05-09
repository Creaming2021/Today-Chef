<template>
  <tr>
    <td class="product__cart__item">
        <div class="product__cart__item__text">
            <h6>{{payment.date}}-{{payment.orderId}}</h6>
        </div>
    </td>
    <td class="quantity__item">
        <div class="quantity">
            <div class="pro-qty-2" v-for="detail in payment.orderDetails" :key="detail.product.id">
              <h5>{{detail.product.category}}</h5>
              <img :src="detail.product.image" />
              <h5>{{detail.product.name}}</h5>
              <h5>{{detail.product.price}}원</h5>
              <h5>{{detail.amount}}개</h5>
            </div>
        </div>
    </td>
    <td class="cart__price">{{payment.totalPrice}}원</td>
    <td class="cart__price">
      <div 
        v-if="type === 'product'"
        class="payment-detail" 
        @click="onClickPaymentDetail">상세 내역</div>
    </td>
    <b-modal 
      size="lg" 
      v-model="modalState" 
      hide-footer hide-header>
      <div class="container" style="min-height : 300px">
        <p @click="onCloseDetailModal" class="btn" style="display : flex;justify-content : flex-end">X</p>
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
            carrierId: 'kr.epost',
            trackId: this.payment.delivery.number,
            // trackId: '1111111111111',
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
.product__cart__item__pic img {
  width : 150px;
  height: 150px;
  object-fit: cover;
}
.payment-detail {
  cursor: pointer;
}
</style>