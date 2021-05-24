<template>
  <div>
    <div class="tracker-header goToDetail"
        @click="changeModalType('detail')">
      <ion-icon ios="ios-backspace" md="md-backspace" class="icon"></ion-icon>
      <p>상세보기로 돌아가기</p>
    </div>
    <div class="tracker-body">
      <h3>{{deliveryTrack.state.text}}</h3>
      <div class="tracker-body-company">
        <div>{{deliveryTrack.carrier.name}}</div>
        <div>{{deliveryTrack.carrier.tel}}</div>
      </div>
    </div>

    <div class="shopping__cart__table">
      <table>
        <thead>
          <tr>
            <th>시간</th>
            <th>현재 위치</th>
            <th>배송 상태</th>
          </tr>
        </thead>
        <tbody>
          <template v-for="(progress, idx) in deliveryTrack.progresses">
            <tr :key="idx">
              <td>{{progress.time.substring(0,10)}} {{progress.time.substring(11,19)}}
              </td>
              <td>{{progress.location.name}}</td>
              <td>{{progress.status.text}}</td>
            </tr>
          </template>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    changeModalType: Function,
    deliveryTrack: Object,
  }
}
</script>

<style scoped>
table{
  width: 100%;
}
.tracker-header {
  display: flex;
}
.tracker-header p{
  font-size: 1rem;
  font-weight: bold;
}
.goToDetail:hover {
  cursor: pointer;
}
.tracker-body {
  text-align: center;
}
.tracker-body-company {
  display: inline-block;
  padding: 5px 0;
}
.tracker-body-company div{
  display: inline;
  margin: 0 10px;
}
.shopping__cart__table {
  margin-top: 60px;
}
.shopping__cart__table table tbody tr td ion-icon {
  padding: 3px;
}
.shopping__cart__table table tbody tr td {
    padding-bottom: 20px;
    padding-top: 20px;
}
.shopping__cart__table table tr {
  text-align: center;
}
</style>