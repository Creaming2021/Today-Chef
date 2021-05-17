import { basic } from '@/api/instance.js';

export const getReviewList = async ( review ) => {
  return await basic.get('course-reviews',
    {
      params: {
        courseId: review.courseId,
      }
    })
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const postReview = async ( review ) => {
  return await basic.post('course-reviews',
    {
      content: review.content,
      isSecret: review.isSecret,
      productId: review.productId,
      title: review.title,
      userId: review.userId,
    })
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const getReview = async ( reviewId ) => {
  return await basic.get(`course-reviews/${reviewId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const putReview = async ( review ) => {
  return await basic.put(`course-reviews/${review.reviewId}`,
    {
      content: review.content,
      isSecret: review.isSecret,
      title: review.title,
    })
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const getReviewComment = async ( reviewId ) => {
  return await basic.post(`course-reviews/${reviewId}/comments`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const postReviewComment = async ( review ) => {
  return await basic.post(`course-reviews/${review.reviewId}/comments`,
    {
      content: review.content,
      isSecret: review.isSecret,
      productId: review.productId,
      title: review.title,
      userId: review.userId,
    })
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const putReviewComment = async ( review ) => {
  return await basic.put(`course-reviews/${review.reviewId}/comments/${review.comentId}`,
    {
      content: review.content,
      isSecret: review.isSecret,
      title: review.title,
    })
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const deleteReviewComment = async ( review ) => {
  return await basic.delete(`course-reviews/${review.reviewId}/comments/${review.comentId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}