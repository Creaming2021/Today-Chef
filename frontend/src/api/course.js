import { basic, image } from '@/api/instance.js';

export const getTopList = async ( count ) => {
  return await basic.get('courses/rank', {
    params: {
      count
    }
  })
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const getCourseList = async () => {
  return await basic.get('courses')
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const postCourse = async ( course ) => {
  return await basic.post('courses', course)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const getCourse = async ( courseId ) => {
  return await basic.get(`courses/${courseId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const putCourse = async ( course ) => {
  return await basic.put(`courses/${course.courseId}`, 
    {
      category: course.category,
      date: course.date,
      descriptions: course.descriptions,
      materials: course.materials,
      name: course.name,
      price: course.price,
      time: course.time,
    }
  )
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

export const getCourseStudentList = async ( courseId ) => {
  return await basic.get(`courses/${courseId}/students`)
    .then(res => res)
    .catch(e => { console.log(e); });
}