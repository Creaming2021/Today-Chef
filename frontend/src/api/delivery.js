import axios from 'axios';

export const getDeliveryTracker = async ( delivery ) => {
	return await axios.get(
		`https://apis.tracker.delivery/carriers/${delivery.carrierId}/tracks/${delivery.trackId}`)
		.then(res => res)
		.catch(e => { console.log(e); });
}