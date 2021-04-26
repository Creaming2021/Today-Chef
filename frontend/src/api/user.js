// import { basic } from '@/api/user.js';

export const checkUserDB =  async ( kakaoId ) => {
  console.log(`${kakaoId}로 db에 회원있는지 물어봅니다.`);
  return await false;
}

export const signUp = async ( userInfo ) => {
  console.log(userInfo);
  console.log('로 회원가입 시킵니다.');
  return await true;
}

export const signIn = async ( kakaoId ) => {
  console.log(`${kakaoId}로 로그인 합니다.`);
  return await true;
}