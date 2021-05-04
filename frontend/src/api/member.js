import { basic, image } from '@/api/instance.js';

export const getMemberInfo = async ( memberId ) => {
	basic.get(`members/${memberId}`)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const putMemberInfo = async ( member ) => {
	basic.put(
			`members/${member.id}`, 
			{
				content: memberId.content,
			})
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const getMemberCoupon = async ( memberId ) => {
	basic.get(`members/${memberId}/coupons`)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const useMemberCoupon = async ( member ) => {
	basic.put(`members/${member.id}/coupons/${member.couponId}`)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const setCourseLike = async ( member ) => {
	basic.put(`members/${member.id}/courses/${member.courseId}`)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const getCourseStudentList = async ( memberId ) => {
	basic.get(`members/${memberId}/courses/student`)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const getCourseTeacherList = async ( memberId ) => {
	basic.get(`members/${memberId}/courses/teacher`)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const postMemberImage = async ( member ) => {
	image.get(
			`members/${member.id}/image`,
			{
				file: member.image
			}
		)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const getRegisterList = async ( memberId ) => {
	basic.get(`members/${memberId}/registers`)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const postRegister = async ( member ) => {
	basic.post(
			`members/${memberId}/registers`,
			{
				content: member.content,
				courseId: member.courseId,
				memberId: member.id,
				rating: member.rating,
				title: member.title
			}
	). then(res => res)
		.catch(e => { console.log(e); });
}

export const getRegister = async ( member ) => {
	basic.get(`members/${member.id}/registers/${member.registerId}`)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const deleteRegister = async ( member ) => {
	basic.delete(`members/${member.id}/registers/${member.registerId}`)
		.then(res => res)
		.catch(e => { console.log(e); });
}
