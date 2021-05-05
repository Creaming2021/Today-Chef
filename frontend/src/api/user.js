import { basic } from '@/api/instance.js';

export const signUp = async ( userInfo ) => {
  return await basic.post('users/signup', userInfo)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const signIn = async ( kakaoId ) => {
  return await basic.post('users/login', { kakaoId })
    .then(res => res)
    .catch(e => { console.log(e); });
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