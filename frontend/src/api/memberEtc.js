import { basic, image } from '@/api/instance.js';

export const getMemberInfo = async ( memberId ) => {
  return await basic.get(`members/${memberId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const putMemberInfo = async ( member ) => {
  return await basic.put(
      `members/${member.id}`, 
      {
        address: member.address,
        nickname: member.nickname,
        phone: member.phone,
      })
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const getCartList = async ( memberId ) => {
  return await basic.get(`members/${memberId}/carts`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const postCartList = async ( cart ) => {
  return await basic.post(`members/${cart.memberId}/carts`,
    {
      amount: cart.amount,
      productId: cart.productId,
    })
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const deleteCartList = async ( cart ) => {
  return await basic.delete(`members/${cart.memberId}/carts/${cart.productId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const getCouponList = async ( memberId ) => {
  return await basic.get(`members/${memberId}/coupons`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const putCoupon = async ( coupon ) => {
  return await basic.put(`members/${coupon.memberId}/coupons/${coupon.couponId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const setCoupon = async ( coupon ) => {
  return await basic.post(`members/${coupon.memberId}/coupons/${coupon.couponId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const postProfileImage = async ( member ) => {
  return await image.post(`members/${member.memberId}/image`, {
      image: member.profileImage,
    })
    .then(res => res)
    .catch(e => { console.log(e); });
}