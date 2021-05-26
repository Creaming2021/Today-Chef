<template>
  <div class="shopping__cart__table">
    <div class="order">주문 상세정보</div>
    <div class="order-detail">
      <div class="order-detail-info"> 주문 일자 {{payment.date.substring(0, 10)}}</div> |
      <div class="order-detail-info"> 주문번호 {{payment.date.substring(0, 10)}}-{{payment.orderId}} </div>
    </div>
    <div class="info-title">상품 정보</div>
    <div class="order-info-details">
        <div class="pro-qty-2" v-for="detail in payment.orderDetails" :key="detail.product.id">
          <img :src="detail.product.image" class="order-info-image"/>
          <div class="order-info-detail">
            <div><strong>카테고리</strong> : {{detail.product.category}}</div>
            <div><strong>상품명</strong> : {{detail.product.name}}</div>
            <div><strong>상품금액 (수량)</strong> : {{detail.product.price.toLocaleString()}}원 ({{detail.amount}}개)</div>
          </div>
        </div>
    </div>

    <div class="info-title">배송지 정보</div>
    <table class="delivery-info">
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
</template>

<script>
export default {
  props: {
    deliveryDetail: Function,
    payment: Object,
  }
}
</script>

<style>
.delivery-detail {
  cursor: pointer;
}
.delivery-detail:hover {
  text-decoration: underline;
}
.order {
  font-size: 1.5rem;
  font-weight: bold;
  margin: 0 0 5px 4px;
}
.order-detail {
  border: 4px solid lightgrey;
  padding: 18px 15px 13px 15px;
  display: flex;
  font-size: 1rem;
  font-weight: bold;
}
.order-detail-info {
  padding: 0 10px 3px 10px;
}
.info-title {
  font-size: 1.3rem;
  font-weight: bold;
  margin: 30px 0 10px 4px;
}
.order-info-image {
  display: inline;
  width: 200px;
}
.order-info-detail {
  position: absolute;
  display: inline-block;
  padding: 0 1rem;
}
.order-info-detail div {
  position: relative;
  left: 5px;
  top: 10px;
  padding-top: 10px;
}
.delivery-info {
  margin: 8px;
}
td {
  font-size: 14px;
}
</style>