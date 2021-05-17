import { basic } from '@/api/instance.js';

export const getTopList = async ( params ) => {
  return await basic.get('products/rank', {
    params: {
      count: params.count,
      category: params.category,
    }
  })
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const getProductList = async () => {
  return await basic.get('products')
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const postProduct = async ( product ) => {
  return await basic.post('products', product)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const getProduct = async ( productId ) => {
  return await basic.get(`products/${productId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const putProduct = async ( product ) => {
  return await basic.put(`products/${product.id}`,
    {
      category: product.category,
      description: product.description,
      name: product.name,
      price: product.price,
    })
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const deleteProduct = async ( productId ) => {
  return await basic.delete(`products/${productId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}