import { basic } from '@/api/instance.js';

export const getReviewList = async ( courseId ) => {
  basic.get('course-reviews', {
      params: {
        courseId,
      }
    })
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const postReview = async ( review ) => {
  basic.post('course-reviews', review)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const getReview = async ( reviewId ) => {
  basic.get(`course-reviews/${reviewId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const putReview = async ( review ) => {
  basic.put(`course-reviews/${review.reviewId}`,
    {
      content: review.content,
      courseId: review.courseId,
      memberId: review.memberId,
      rating: review.rating,
      title: review.title,
    })
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const deleteReview = async ( review ) => {
  basic.delete(`course-reviews/${review.reviewId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const getReviewCommentList = async ( review ) => {
  basic.get(`course-reviews/${review.reviewId}/comments`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const postReviewComment = async ( review ) => {
  basic.post(`course-reviews/${review.reviewId}/comments`,
    {
      content: review.content,
      memberId: review.memberId,
    })
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const putReviewComment = async ( review ) => {
  basic.put(`course-reviews/${review.reviewId}/comments/${review.commentId}`,
    {
      content: review.content,
    })
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const deleteReviewComment = async ( review ) => {
  basic.delete(`course-reviews/${review.reviewId}/comments/${review.commentId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}
