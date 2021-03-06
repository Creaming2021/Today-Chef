import { basic } from '@/api/instance.js';

export const getQnaList = async ( productId ) => {
  return await basic.get('product-qna',
      {
        params: {
          productId
        }
      }
    )
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const postQna = async ( qna ) => {
  return await basic.post('product-qna', qna)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const putQna = async ( qna ) => {
  return await basic.put(`product-qna/${qna.id}`, {
      content: qna.content,
      isSecret: qna.isSecret,
      title: qna.title,
    })
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const deleteQna = async ( id ) => {
  return await basic.delete(`product-qna/${id}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}
