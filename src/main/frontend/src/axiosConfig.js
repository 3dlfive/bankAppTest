import axios from 'axios';

const isDevelopment = process.env.NODE_ENV === 'development';
const apiUrl = isDevelopment ? '/api' : '';

const instance = axios.create({
    baseURL: apiUrl,
});

instance.interceptors.request.use((config) => {
    config.url = `${config.url}`;
    return config;
});

export default instance;
