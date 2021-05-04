import { basic } from '@/api/instance.js';

export const getReviewList = async ( productId ) => {
	basic.get('product-reviews', {
			params: {
				productId,
			}
		})
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const postReview = async ( review ) => {
	basic.post('product-reviews', review)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const putReview = async ( review ) => {
	basic.put(`product-reviews/${review.productReviewId}`, 
			{
				content: review.content,
				rating: review.rating,
				title: review.title,
			})
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const deleteReview = async ( review ) => {
	basic.delete(`product-reviews/${review.productReviewId}`)
		.then(res => res)
		.catch(e => { console.log(e); });
}
