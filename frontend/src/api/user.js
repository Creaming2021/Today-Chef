// import { basic } from '@/api/user.js';

export const checkUser = async ( kakaoId ) => {
  await console(`${kakaoId}로 db에 회원있는지 물어봅니다.`);
  return true;
}