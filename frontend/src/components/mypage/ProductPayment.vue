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
        <div class="shopping__cart__table">
          <div>{{payment.date}} 주문 ({{payment.date}}-{{payment.orderId}})</div>
          <div>주문 상세</div>
          <div class="quantity">
              <div class="pro-qty-2" v-for="detail in payment.orderDetails" :key="detail.product.id">
                <h5>{{detail.product.category}}</h5>
                <img :src="detail.product.image" />
                <h5>{{detail.product.name}}</h5>
                <h5>{{detail.product.price}}원</h5>
                <h5>{{detail.amount}}개</h5>
              </div>
          </div>

          <div>배송지 정보</div>
          <table>
            <thead>
              <tr>
                <th>받는 사람</th>
                <th>연락처</th>
                <th>받는 주소</th>
                <th>운송장 번호</th>
                <th>배송요청사항</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>{{payment.delivery.address.name}}</td>
                <td>{{payment.delivery.address.phoneNumber}}</td>
                <td>{{payment.delivery.address.address}}</td>
                <td @click="deliveryDetail" class="delivery-detail">{{payment.delivery.number}}</td>
                <td>{{payment.delivery.note}}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </b-modal>
  </tr>
</template>

<script>
  export default {
    data(){
      return {
        modalState : false,
      }
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
      },
      deliveryDetail() {
        alert('배송조회')
      }
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
.delivery-detail {
  cursor: pointer;
}
.delivery-detail:hover {
  text-decoration: underline;
}
</style>