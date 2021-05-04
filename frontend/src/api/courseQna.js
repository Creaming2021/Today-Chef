import { basic } from '@/api/user.js';

export const getQnaList = async (qna) => {
  basic.get('course-qna',
    {
      params: {
        courseId: qna.courseId,
      }
    })
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const postQna = async ( qna ) => {
  basic.post('course-qna', qna)
    .then(res => res)
    .catch(e => { console.log(e); });
}

// export const getQna = async ( qnaId ) => {
//   basic.get(`course-qna/${qnaId}`)
//     .then(res => res)
//     .catch(e => { console.log(e); });
// }

export const putQna = async ( qna ) => {
  basic.put(
      `course-qna/${qna.id}`,
      {
        content: qna.content,
        isSecret: qna.isSecret,
        productId: qna.productId,
        title: qna.title,
        userId: qna.userId,
      }
    )
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const deleteQna = async ( qnaId ) => {
  basic.delete(`course-qna/${qnaId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

// export const getQnaCommentList = async ( qnaId ) => {
//   basic.get(`course-qna/${qnaId}/comments`)
//     .then(res => res)
//     .catch(e => { console.log(e); });
// }

export const postQnaComment = async ( qna ) => {
  basic.post(
    `course-qna/${qna.id}/comments`,
    {
      content: qna.content,
      isSecret: qna.isSecret,
      productId: qna.productId,
      title: qna.title,
      userId: qna.userId,
    }
  )
  .then(res => res)
  .catch(e => { console.log(e); });
}

export const putQnaComment = async ( qna ) => {
  basic.put(
    `course-qna/${qna.id}/comments/${qna.commentId}`,
    {
      content: qna.content,
      isSecret: qna.isSecret,
      title: qna.title,
    }
  )
  .then(res => res)
  .catch(e => { console.log(e); });
}

export const deleteQnaComment = async ( qna ) => {
  basic.delete(`course-qna/${qna.id}/comments/${qna.commentId}`)
  .then(res => res)
  .catch(e => { console.log(e); });
}

