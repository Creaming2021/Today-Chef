import { basic, image } from '@/api/user.js';

export const getQnaList = async () => {
	basic.get('qna')
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const postQna = async ( qna ) => {
	basic.post('qna', qna)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const getQna = async ( qnaId ) => {
	basic.get(`qna/${qnaId}`)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const putQna = async ( qna ) => {
	basic.put(
			`qna/${qna.id}`,
			{
				content: qna.content,
				courseId: qna.courseId,
				memberId: qna.memberId,
				rating: qna.rating,
				title: qna.title,
			}
		)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const deleteQna = async ( qnaId ) => {
	basic.delete(`qna/${qnaId}`)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const getQnaCommentList = async ( qnaId ) => {
	basic.get(`qna/${qnaId}/comments`)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const putQnaComment = async ( qna ) => {
	basic.post(
		`qna/${qna.id}/comments`,
		{
			content: qna.content,
			courseId: qna.courseId,
			memberId: qna.memberId,
			rating: qna.rating,
			title: qna.title,
		}
	)
	.then(res => res)
	.catch(e => { console.log(e); });
}

export const putQnaComment = async ( qna ) => {
	basic.put(
		`qna/${qna.id}/comments/${qna.commentId}`,
		{
			content: qna.content,
		}
	)
	.then(res => res)
	.catch(e => { console.log(e); });
}

export const deleteQnaComment = async ( qna ) => {
	basic.delete(`qna/${qna.id}/comments/${qna.commentId}`)
	.then(res => res)
	.catch(e => { console.log(e); });
}

