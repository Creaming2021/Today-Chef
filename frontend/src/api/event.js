import { basic, image } from '@/api/instance.js';

export const getEventList = async () => {
  return await basic.get('events')
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const postEvent = async ( event ) => {
  return await basic.post('events', event)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const getEvent = async ( eventId ) => {
  return await basic.get(`events/${eventId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const putEvent = async ( event ) => {
  return await basic.put(`events/${event.id}`, 
    {
      content : event.content,
      title : event.title,
    });
}

export const deleteEvent = async ( eventId ) => {
  return await basic.delete(`events/${eventId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const postEventImage = async ( event ) => {
  return await image.post(`events/${event.id}/image`,
      {
        file: event.image,
      }
    )
    .then(res => res)
    .catch(e => { console.log(e); });
}