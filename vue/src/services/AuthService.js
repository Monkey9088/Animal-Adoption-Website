import axios from 'axios';

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },
  updateUserPwd(user){
    return axios.put('/changepassword', user)
  },
  getOldPassword(){
    return axios.get('/changepassword')
  }

}
