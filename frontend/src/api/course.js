import { basic, image } from '@/api/user.js';

export const postCourse = async ( course ) => {
	basic.post('courses', course)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const putCourse = async ( course ) => {
	basic.put(`courses/${course.id}`)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const deleteCourse = async ( courseId ) => {
	basic.delete(`courses/${courseId}`)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const postCourseImage = async ( course ) => {
	image.post(
		`courses/${course.courseId}/image`, 
		{ file : courseId.image })
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const getCourseStudents = async ( courseId ) => {
	basic.get(`courses/${courseId}/students`)
		.then(res => res)
		.catch(e => { console.log(e); });
}