import { basic } from '@/api/instance.js';

export const getQnaList = async (courseId) => {
  basic.get('course-qna',
    {
      params: {
        courseId,
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
  basic.put(
    `course-qna/${qna.qnaId}/comments/${qna.commentId}`,
    {
      content: qna.content,
    }
  )
  .then(res => res)
  .catch(e => { console.log(e); });
}

export const deleteQnaComment = async ( qna ) => {
  basic.delete(`course-qna/${qna.qnaId}/comments/${qna.commentId}`)
  .then(res => res)
  .catch(e => { console.log(e); });
}

