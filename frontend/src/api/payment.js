import { basic } from './instance.js';

// 결제하기
export const payment = async ( amount ) => {
  await basic.get(
    'kakao-pay', 
    {
      'params': { amount }
    }
	).then(response => {
		window.location.href = response.data.url;
	}).finally(() => (response.data))
}