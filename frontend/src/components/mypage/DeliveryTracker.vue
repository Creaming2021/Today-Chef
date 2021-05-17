<template>
  <div>
    <p 
      class="goToDetail"
      @click="changeModalType('detail')">상세보기로 돌아가기</p>

    <div>{{deliveryTrack.state.text}}</div>
    <div>
      <div>{{deliveryTrack.carrier.name}}</div>
      <div>{{deliveryTrack.carrier.tel}}</div>
    </div>

    <div>
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
              <td>{{progress.time}}</td>
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

<style>
table{
  width: 100%;
}

.goToDetail:hover {
  cursor: pointer;
}
</style>