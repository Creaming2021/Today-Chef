import { chat } from '@/api/instance.js';

export const signUpInChat = async ( memberInfo ) => {
    return await chat.post('auth/register', memberInfo )
    .then(res => res)
    .catch(e => { console.log(e); });
}