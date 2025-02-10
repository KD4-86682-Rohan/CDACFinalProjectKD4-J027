import httpClient from "../http-common";

const login = (data) => {
    debugger;
    return httpClient.post("/User/login", data);
};

const register = (data) => {
    console.log(data);
    return httpClient.post("/User/register", data);
};

const get = (id) => {
    return httpClient.get(`${id}`);
};

const update = (data) => {
    return httpClient.put("", data);
};

const remove = (id) => {
    return httpClient.delete(`/${id}`);
};

// Forgot Password API
const forgotPassword = (data) => {
    return httpClient.post("/User/forgot-password", data);
};

// Reset Password API
const resetPassword = (data) => {
    return httpClient.post("/User/reset-password", data);
};

export default { login, register, get, update, remove, forgotPassword, resetPassword };
