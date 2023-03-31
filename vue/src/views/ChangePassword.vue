<template>
  <div id="change">
    <el-form
      :model="user"
      :rules="resetFormRules"
      ref="resetForm"
      status-icon
      label-width="100px"
    >
      <!-- <el-form-item label="Current Password：" prop="password">
        <el-input 
         type="password" v-model="user.password" auto-complete="off" show-password></el-input>
      </el-form-item> -->
      <el-form-item label="New Password：" prop="newPassword">
        <el-input type="password" v-model="user.newPassword" auto-complete="off" show-password></el-input>
      </el-form-item>
      <el-form-item label="Confirm Password：" prop="newPassword1">
        <el-input type="password" v-model="user.newPassword1" auto-complete="off" show-password></el-input>
      </el-form-item>
      <el-form-item>
        <el-button class="stylebutton" type="primary" @click.prevent="submit">Submit</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import AuthService from '../services/AuthService';

export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (!value ) {
        callback(new Error("Please enter new password"));
      } else if (value.toString().length < 6 || value.toString().length > 18) {
        callback(new Error("Password length should be between 6 and 18"));
      }  else{
        callback();
      }
    };
    var validatePass1 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("Please enter new password again"));
      } else if (value !== this.user.newPassword) {
        callback(new Error("The password you entered is not matching!"));
      } else {
        callback();
      }
    };
    //    var validatePass = (rule, value, callback) => {
    //    if (value!== this.$store.state.oldPassword) {
    //      callback(new Error("The password you typed is not right"));
    //    }  
    //     else {
    //      callback();
    //    }
    //  };
    return {
      user: {
        id: this.$store.state.user.id,
       // password: "",
        newPassword: "",
        newPassword1: "",
      
      
      },
      resetFormRules: {
        //  password: [
        //     { required: true, message: "hi", trigger: 'blur' }
        // ],  
        newPassword: [
            { required: true, validator: validatePass, trigger: 'blur' }
        ],
        newPassword1: [
          { required: true, validator: validatePass1, trigger: "blur" }
        ]
      }
    };
  },
  created() {
      AuthService.getOldPassword().then((response) => {
        if(response.status===200){
          this.$store.commit("SET_OLD_PASSWORD",response.data)
        }
      })
  },
  methods: {
   submit() {
                 
                 this.$refs['resetForm'].validate((valid) => {
                   if(valid) {

                       AuthService.updateUserPwd(this.user)
                  .then((response) => {
                      if(response.status===200) {
                        this.$store.commit("SET_AUTH_TOKEN", response.data.token);
                          this.$router.push({name: "login"});
                      }
                  }

                  )
                   }
                 })
            
                 
              
          
              
          
      }
  }

}

</script>

<style scoped>
.el-form {
  
  width: 60%;
  margin: 50px auto 0;
  text-align: center; 
 
}
.stylebutton {
   width: 100px;
  height: 30px;
  background-color: transparent;
}

</style>