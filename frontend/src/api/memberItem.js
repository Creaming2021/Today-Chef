import { basic } from '@/api/instance.js';

export const setCourseLike = async ( course ) => {
  return await basic.put(`members/${course.memberId}/courses/${course.courseId}/like`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const getLikeCourseList = async ( memberId ) => {
  return await basic.get(`members/${memberId}/courses/like`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const getRegisteredCourseList = async ( memberId ) => {
  return await basic.get(`members/${memberId}/courses/student`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const getTeachedCourseList = async ( memberId ) => {
  return await basic.get(`members/${memberId}/courses/teacher`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const getRegisterList = async ( memberId ) => {
  return await basic.get(`members/${memberId}/registers`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const postRegister = async ( register ) => {
  return await basic.post(`members/${register.memberId}/registers`,
    {
      paidPrice: register.paidPrice,
      courseId: register.courseId,
    }
  ). then(res => res)
    .catch(e => { console.log(e); });
}

export const getRegister = async ( register ) => {
  return await basic.get(`members/${register.memberId}/registers/${register.registerId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const deleteRegister = async ( register ) => {
  return await basic.delete(`members/${register.memberId}/registers/${register.registerId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}