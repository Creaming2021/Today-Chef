import { basic, image } from '@/api/instance.js';

export const getEventList = async () => {
  basic.get('events')
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const postEvent = async ( event ) => {
  basic.post('events', event)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const getEvent = async ( eventId ) => {
  basic.get(`events/${eventId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const putEvent = async ( event ) => {
  basic.put(`events/${event.id}`, 
    {
      content : event.content,
      title : event.title,
    });
}

export const deleteEvent = async ( eventId ) => {
  basic.delete(`events/${eventId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const postEventImage = async ( event ) => {
  image.post(`events/${event.id}/image`,
    {
      file: event.image,
    }
  ).then(res => res)
    .catch(e => { console.log(e); });
}