import { basic } from '@/api/user.js';

export const checkUserDB =  async ( kakaoId ) => {
  console.log(`${kakaoId}로 db에 회원있는지 물어봅니다.`);
  return await true;
}

export const signUp = async ( userInfo ) => {
  basic.post('user/signup', userInfo)
    .then(res => res)
    .catch(e => { console.log(e); });

  // console.log(userInfo);
  // console.log('로 회원가입 시킵니다.');
  // return await true;
}

export const signIn = async ( kakaoId ) => {
  basic.post('user/login', kakaoId)
    .then(res => res)
    .catch(e => { console.log(e); });

  // console.log(`${kakaoId}로 로그인 합니다.`);
  // return await true;
}

export const checkNickname = async ( nickname ) => {
  basic.get(
    'user/check', 
    {
      params : { 
        nickname 
      }
    })
    .then(res => res)
    .catch(e => { console.log(e); });
}