<template>
  <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" >
    <a-form-item label="Product Name">
      <a-input v-model="form.name"/>
      <span id="error-name" style="color: red"></span>
    </a-form-item>
    <a-form-item label="Thumbnail">
      <a-input v-model="form.thumbnail"/>
      <span id="error-thumbnail" style="color: red"></span>
    </a-form-item>
    <a-form-item label="Product Description">
      <a-input v-model="form.description"/>
      <span id="error-description" style="color: red"></span>
    </a-form-item>
    <a-form-item label="Status" hidden>
      <a-input v-model="form.status"/>
      <span id="error-status" style="color: red"></span>
    </a-form-item>
    <a-form-item :wrapper-col="{ span: 12, offset: 5 }">
      <a-button type="primary" @click.stop.prevent="handleSubmit">
        Submit
      </a-button>
    </a-form-item>
  </a-form>
</template>
<script src="../node_modules/ckeditor4/ckeditor.js"></script>
<script src="../node_modules/ckeditor4-vue/dist/ckeditor.js"></script>
<script>
import CategoryService from "@/service/CategoryService";
export default {
  data() {
    return {
      form:{
        name: undefined,
        thumbnail: undefined,
        description:undefined,
        status: 'ACTIVE'
      },

    };
  },
  created() {
    this.getCategories(this.$route.params.id)
  },
  methods: {
    getCategories(id) {
      CategoryService.detail(id).then(response =>{
        this.form =response.data;
        console.log(response.data);
      })
    },
    validURL(str) {
      var pattern = new RegExp('^(https?:\\/\\/)?'+ // protocol
          '((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.)+[a-z]{2,}|'+ // domain name
          '((\\d{1,3}\\.){3}\\d{1,3}))'+ // OR ip (v4) address
          '(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*'+ // port and path
          '(\\?[;&a-z\\d%_.~+=-]*)?'+ // query string
          '(\\#[-a-z\\d_]*)?$','i'); // fragment locator
      return !!pattern.test(str);
    },

    validate(){
      var errorName = document.getElementById('error-name')
      var errorthumbnail = document.getElementById('error-thumbnail')
      var errordescription = document.getElementById('error-description')
      let checkNumber =0;
      if(this.form.name === undefined){
        errorName.innerText = 'Vui lòng nhập tên'
        checkNumber++;
      }else if(this.form.name.length<7 ||this.form.name.length >25){
        errorName.innerText = 'Tên chỉ được trong khoảng từ 7 đến 25 ký tự'
        checkNumber++;
      }
      else {
        errorName.innerText = ''
      }
      if (this.form.thumbnail == undefined) {
        errorthumbnail.innerText = 'Vui lòng nhập ảnh'
        checkNumber++;
      }else if(!this.validURL(this.form.thumbnail)){
        errorthumbnail.innerText = 'Ảnh phải là một đường link'
        checkNumber++;
      }else {
        errorthumbnail.innerText=''
      }
      if(this.form.description === undefined){
        errordescription.innerText = 'Vui lòng nhập tên'
        checkNumber++;
      }else if(this.form.description.length<7){
        errorName.innerText = 'Mô tả phải lớn hơn 7 ký tự'
        checkNumber++;
      }else {
        errordescription.innerText=''
      }
      if (checkNumber == 0) {
        return true;
      } else {
        return false;
      }
    },
    handleSubmit(e){
      e.preventDefault()
      if(!this.validate()){
        console.log("lỗi ở đây")
        return
      }
      CategoryService.edit(this.form.id,this.form).then(response=> {
            console.log(response.data);
            this.$router.push("/categories/list")
          }
      ).catch(error => {
        console.log(error)
      })
    }
  },
};

</script>

<style scoped>

</style>