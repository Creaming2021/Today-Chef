import { basic } from '@/api/user.js';

export const getReviewList = async ( review ) => {
	basic.get(
		'reviews',
		{
			params: {
				courseId: review.courseId,
			}
		})
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const postReview = async ( review ) => {
	basic.post(
		'reviews',
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

export const getReview = async ( reviewId ) => {
	basic.get(`reviews/${reviewId}`)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const putReview = async ( review ) => {
	basic.put(
		`reviews/${review.reviewId}`,
		{
			content: review.content,
		})
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const postReviewComment = async ( review ) => {
	basic.post(
		`reviews/${review.reviewId}/comments`,
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

export const putReviewComment = async ( review ) => {
	basic.put(
		`reviews/${review.reviewId}/comments/${review.comentId}`,
		{
			content: review.content,
		})
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const deleteReviewComment = async ( review ) => {
	basic.delete(`reviews/${review.reviewId}/comments/${review.comentId}`)
		.then(res => res)
		.catch(e => { console.log(e); });
}