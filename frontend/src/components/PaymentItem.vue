<template>
  <tr>
    <td class="product__cart__item">
        <div class="product__cart__item__pic">
            <img :src="payment.thumbnail" alt="">
        </div>
        <div class="product__cart__item__text">
            <h6>{{payment.name}}</h6>
            <span>{{payment.teacher}} / 별점 : {{payment.rate}}</span>
        </div>
    </td>
    <td class="quantity__item">
        <div class="quantity">
            <div class="pro-qty-2">
              <h5>{{payment.date}}</h5>
            </div>
        </div>
    </td>
    <td class="cart__price">{{payment.price}}원</td>
    <td class="cart__price"><div class="payment-detail" @click="onClickPaymentDetail">상세 내역</div></td>
    <b-modal 
    size="lg" 
    v-model="modalState" 
    hide-footer hide-header>
    <div class="container" style="min-height : 300px">
      <p @click="onCloseDetailModal" class="btn" style="display : flex;justify-content : flex-end">X</p>
      <!-- {{payment}} -->
      <div class="shopping__cart__table">
        <table>
          <thead>
            <tr>
              <th>상품정보</th>
              <th>상품금액</th>
              <th>배송조회</th>
              <th>배송요청사항</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{{payment.name}}</td>
              <td>{{payment.price}}</td>
              <td @click="deliveryDetail" class="delivery-detail">{{payment.deliveryCorp}}</td>
              <td>{{payment.deliveryReq}}</td>
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
    },
    mounted() {
      let price = this.payment.price
      console.log(price)
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