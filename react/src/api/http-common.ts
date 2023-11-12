import axios from 'axios';

const URL_NODEJS = 'http://localhost:5000/';
const URL_SPRING_BOOT = 'http://localhost:8080/';

export default axios.create({
  baseURL: URL_SPRING_BOOT,
  headers: {
    'Content-type': 'application/json',
    'Access-Control-Allow-Origin': '*',
  },
});
