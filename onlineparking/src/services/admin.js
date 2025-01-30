import httpClient from '../http-common';
const login = (data) => {
    debugger
  return httpClient.post('/User/login', data);
};
const register = (data) => {
    // debugger
    console.log(data)
  return httpClient.post('/User/register', data);
};
const get = (id) => {
  return httpClient.get(`${id}`);
};
const update = (data) => {
  return httpClient.put('', data);
};
const remove = (id) => {
  return httpClient.delete(`/${id}`);
};
export default { login, register, get, update, remove };