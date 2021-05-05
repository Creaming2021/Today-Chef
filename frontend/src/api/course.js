import { basic, image } from '@/api/instance.js';

export const getCourse = async ( courseId ) => {
	return await basic.get(`courses`, 
		{
			params : {
				courseId,
			}
		})
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const postCourse = async ( course ) => {
	return await basic.post('courses', course)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const putCourse = async ( course ) => {
	return await basic.put(`courses/${course.id}`)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const deleteCourse = async ( courseId ) => {
	return await basic.delete(`courses/${courseId}`)
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const postCourseImage = async ( course ) => {
	return await image.post(
		`courses/${course.courseId}/image`, 
		{ file : course.image })
		.then(res => res)
		.catch(e => { console.log(e); });
}

export const getCourseStudents = async ( courseId ) => {
	return await basic.get(`courses/${courseId}/students`)
		.then(res => res)
		.catch(e => { console.log(e); });
}