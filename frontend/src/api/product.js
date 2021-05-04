import { basic } from '@/api/instance.js';

export const getProductList = async () => {
  basic.get('products')
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const postProduct = async ( product ) => {
  basic.post('products', product)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const getProduct = async ( productId ) => {
  basic.get(`products/${productId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}

export const putProduct = async ( product ) => {
  basic.put(`products/${product.id}`,
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
  basic.delete(`products/${productId}`)
    .then(res => res)
    .catch(e => { console.log(e); });
}