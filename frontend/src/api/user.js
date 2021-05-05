import { basic } from '@/api/instance.js';

export const signUp = async ( userInfo ) => {
  return await basic.post('users/signup', userInfo)
    .then(res => res)
    .catch(e => { console.log(e); });

  // console.log(userInfo);
  // console.log('로 회원가입 시킵니다.');
  // return await true;
}

export const signIn = async ( kakaoId ) => {
  return await basic.post('users/login', { kakaoId })
    .then(res => res)
    .catch(e => { console.log(e); });

  // console.log(`${kakaoId}로 로그인 합니다.`);
  // return await true;
}

export const checkEmail = async ( email ) => {
  return await basic.get(
    'users/check', 
    {
      params : { 
        email 
      }
    })
    .then(res => res)
    .catch(e => { console.log(e); });
}