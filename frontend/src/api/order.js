import { basic } from '@/api/instance.js';

export const getOrderList = async ( memberId ) => {
  return await basic.get('orders', {
      params: {
        memberId,
      }
    })
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const postOrdert = async ( order ) => {
  return await basic.post('orders', order)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const deleteOrder = async ( orderId ) => {
  return await basic.get(`orders/${orderId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}