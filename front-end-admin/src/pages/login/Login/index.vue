<template>
  <div class="container">
    <!-- Outer Row -->
    <div class="row justify-content-center">

      <div class="col-xl-10 col-lg-12 col-md-9">

        <div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
              <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
              <div class="col-lg-6">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
                  </div>
                  <form class="user" :model="form" @submit="handleSubmit" @submit.native.prevent>
                    <div class="form-group">
                      <input v-model="form.username" class="form-control form-control-user"
                             id="exampleInputEmail" aria-describedby="emailHelp"
                             placeholder="Enter Username">
                      <span id="error-username" style="color: red"></span>
                    </div>
                    <div class="form-group">
                      <input type="password" v-model="form.password" class="form-control form-control-user"
                             id="exampleInputPassword" placeholder="Password">
                    </div>
                    <span id="error-password" style="color: red"></span>

                    <div class="form-group" v-if="this.text==1">
                      <div class="custom-control custom-checkbox small">
                        <input type="checkbox" checked="checked" class="custom-control-input"  id="customCheck" @change="saveaccount(0)">
                        <label class="custom-control-label" for="customCheck">Remember
                          Me</label>
                      </div>
                    </div>
                    <div class="form-group" v-else>
                      <div class="custom-control custom-checkbox small">
                        <input type="checkbox"  class="custom-control-input"   @change="saveaccount(1)">
                        <label class="custom-control-label" for="customCheck">Remember
                          Me</label>
                      </div>
                    </div>
                    <span id="error-role" style="color: red"></span>
                    <button class="btn btn-primary btn-user btn-block" type="primary"
                            html-type="submit">
                      Login
                    </button>
                    <hr>
                    <a href="index.html" style="background:#ea4335" class="btn btn-google btn-user btn-block">
                      <i class="fab fa-google fa-fw"></i> Login with Google
                    </a>
                    <a href="index.html" style="background: #3b5998" class="btn btn-facebook btn-user btn-block">
                      <i class="fab fa-facebook-f fa-fw"></i> Login with Facebook
                    </a>
                  </form>
                  <hr>
                  <div class="text-center">
                    <a class="small" href="forgot-password.html">Forgot Password?</a>
                  </div>
                  <div class="text-center">
                    <a class="small" href="register.html">Create an Account!</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>

    </div>

  </div>
</template>
<script>
import LoginService from "../../../service/LoginService";
import "../../../use/css/sb-admin-2.css"
import "../../../use/vendor/fontawesome-free/css/all.min.css"
export default {
  data() {
    return {
      text:undefined,
      form: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    async handleSubmit(e) {
      e.preventDefault();
      if(!this.validate()){
        console.log("Lỗi validate")
        return
      }
      var errorrole = document.getElementById('error-role')
      /*document.getelementById là chung dùng được ở ngoài file khác*/
      const data = await LoginService.authenticate(this.form);
      if (data.status === 200) {
        console.log(data.status)
          localStorage.setItem('access_token', data.data.access_token);
          localStorage.setItem('username', data.data.username);
          localStorage.setItem('password',data.data.password);
          localStorage.setItem('role',data.data.role)
        let jwt = localStorage.getItem('access_token')
        let jwtData = jwt.split('.')[1]
        let decodedJwtJsonData = window.atob(jwtData)
        let decodedJwtData = JSON.parse(decodedJwtJsonData)
        console.log(decodedJwtData)
        console.log(decodedJwtData.role)
        let role = decodedJwtData.role
        if(role =='ADMIN') {
          this.$router.push({name: 'DashBoard'});
        }else {
          this.$router.push({name:'Login'})
          errorrole.innerText = "Sai mật khẩu hoặc tài khoản"
        }

      }else{
        this.$message.error(data.message)
      }
    },
    validate(){
      var errorusername = document.getElementById('error-username')
      var errorpassword =document.getElementById('error-password')
      let checknumber =0;
      if(this.form.username === ""){
        errorusername.innerText = "Xin mời nhập tài khoản"
        checknumber++;
      }else {
        errorusername.innerText=""
      }
      if(this.form.password === ""){
        errorpassword.innerText = "Xin mời nhập mật khẩu"
        checknumber++;
      }else {
        errorpassword.innerText=""
      }
      var errorrole = document.getElementById('error-role')
      errorrole.innerText=""
      if(checknumber ==0){
        return true;
      }else {
        return false;
      }
    },
  },
  saveaccount(check){
      if(check ==1){
        this.text =1;
        this.form.username =localStorage.getItem('username')
        this.form.password = localStorage.getItem('password')
      }else {
        this.text=0;
        this.form.username =""
        this.form.password =""
      }
  }
};
</script>
<style>
</style>
