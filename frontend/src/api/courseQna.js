import { basic } from '@/api/instance.js';

export const getQnaList = async (courseId) => {
  return await basic.get('course-qna',
    {
      params: {
        courseId,
      }
    })
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const postQna = async ( qna ) => {
  return await basic.post('course-qna', qna)
    .then(res => res)
    .catch(e => { console.log(e); });
}

// export const getQna = async ( qnaId ) => {
//   return await basic.get(`course-qna/${qnaId}`)
//     .then(res => res)
//     .catch(e => { console.log(e); });
// }

export const putQna = async ( qna ) => {
  return await basic.put(
      `course-qna/${qna.qnaId}`,
      {
        content: qna.content,
        isSecret: qna.isSecret,
        courseId: qna.courseId,
        title: qna.title,
        memberId: qna.memberId,
      }
    )
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const deleteQna = async ( qnaId ) => {
  return await basic.delete(`course-qna/${qnaId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

// export const getQnaCommentList = async ( qnaId ) => {
//   basic.get(`course-qna/${qnaId}/comments`)
//     .then(res => res)
//     .catch(e => { console.log(e); });
// }

export const postQnaComment = async ( qna ) => {
  return await basic.post(
    `course-qna/${qna.qnaId}/comments`,
    {
      content: qna.content,
      memberId: qna.memberId,
    }
  )
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const putQnaComment = async ( qna ) => {
  return await basic.put(
    `course-qna/${qna.qnaId}/comments/${qna.commentId}`,
    {
      content: qna.content,
    }
  )
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const deleteQnaComment = async ( qna ) => {
  return await basic.delete(`course-qna/${qna.qnaId}/comments/${qna.commentId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

