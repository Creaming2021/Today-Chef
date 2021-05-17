import { basic } from './instance.js';

// 결제하기
export const payment = async ( amount ) => {
  return await basic.get(
    'kakao-pay', 
    {
      'params': { amount }
    }
	).catch(error => console.log(error));
}