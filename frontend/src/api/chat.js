import { chat } from '@/api/instance.js';

export const signUpChat = async ( memberInfo ) => {
    return await chat.post('auth/register', memberInfo )
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const signInChat = async ( email ) => {
    return await chat.post('auth/login', email )
    .then(res => res)
    .catch(e => { console.log(e); });
}