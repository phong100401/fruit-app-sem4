<template>
  <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" >
    <a-form-item label="Product Name">
      <a-input v-model="currentData.name"/>
      <span id="error-name" style="color: red"></span>
    </a-form-item>
    <a-form-item label="Price">
      <a-input v-model="currentData.price"/>
      <span id="error-price" style="color: red"></span>
    </a-form-item>
    <a-form-item label="Category">
      <a-select v-model="currentData.categoryId">
        <a-select-option v-for="item in data" :key="item.id" :value="item.id">
          {{item.name}}
        </a-select-option>
      </a-select>
      <span id="error-category" style="color:red;"></span>
    </a-form-item>
    <a-form-item label="Description" >
      <a-input v-model="currentData.description" />
      <span id="error-description" style="color: red"></span>
    </a-form-item>
    <a-form-item label="Quantity">
      <a-input v-model="currentData.quantity"/>
      <span id="error-quantity" style="color: red"></span>
    </a-form-item>
    <a-form-item label="Thumbnail">
      <a-input v-model="currentData.thumbnail"/>
      <span id="error-thumbnail" style="color: red"></span>
    </a-form-item>
    <a-form-item label="Status" hidden>
      <a-input v-model="currentData.status"/>
      <span id="error-status" style="color: red"></span>
    </a-form-item>
    <a-form-item id="app" style="height: auto" label="Detail">
      <ckeditor v-model="currentData.detail" :config="editorConfig"
                tag-name="textarea"
                :disabled="editorDisabled"
                @ready="onEditorReady"
                @focus="onEditorFocus"
                @blur="onEditorBlur"
                @input="onEditorInput"
                @destroy="onEditorDestroy" ></ckeditor>
      <!--      <a-input type="ckeditor" v-model="form.detail"/>-->
      <span id="error-detail" style="color: red"></span>
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
import ProductService from "@/service/ProductService";

export default {
   data(){
     return{
       editorData: '',
       editorConfig: {
         /*// The configuration of the editor.
         toolbar: [ 'heading', '|', 'bold', 'italic', 'link', 'bulletedList', 'numberedList', '|', 'insertTable', '|', 'imageUpload', 'mediaEmbed', '|', 'undo', 'redo' ],
         table: {
           toolbar: [ 'tableColumn', 'tableRow', 'mergeTableCells' ]
         },*/
         language: 'vi',
         defaultLanguage: 'vi'
       },
       data:[],
       currentData:{
         id:null,
         name: undefined,
         price: undefined,
         thumbnail: undefined,
         categoryId: undefined,
         quantity: undefined,
         detail: undefined,
         description: undefined,
         status: 'ACTIVE'
       }
     };
   },
  created() {
    this.getCategorise()
    this.getProducts(this.$route.params.id)
  },
  methods: {
    getCategorise() {
      CategoryService.getAll().then(
          res => {
            this.data = res.data
          }
      )
    },
    getProducts(id){
      ProductService.detail(id).then(response =>{
       this.currentData =response.data;
       console.log(response.data);
      })
    },
    checkrealnumber(n){
      let flag = true;
      if(Math.ceil(n) != Math.floor(n)){
        flag =false;
      }
      return flag;
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
      var errorPrice = document.getElementById('error-price')
      var errorCategoryId = document.getElementById('error-category')
      var errordescription = document.getElementById('error-description')
      var errorquantity = document.getElementById('error-quantity')
      var errorthumbnail = document.getElementById('error-thumbnail')
      var errordetail = document.getElementById('error-detail')
      // var errorCategory = document.getElementById('error-category')
      // eslint-disable-next-line no-unused-vars
      let checkNumber =0;
      if(this.currentData.name === undefined){
        errorName.innerText = 'Vui lòng nhập tên sản phẩm'
        checkNumber++;
      }else if(this.currentData.name.length<7 ||this.currentData.name.length >25){
        errorName.innerText = 'Tên chỉ được trong khoảng từ 7 đến 25 ký tự'
        checkNumber++;
      }
      else {
        errorName.innerText = ''
      }
      if (this.currentData.price === undefined) {
        errorPrice.innerText = 'Vui lòng nhập giá tiền lớn hơn 0'
        checkNumber++;
      } else if (this.currentData.price <= 0) {
        errorPrice.innerText = 'Giá tiền phải là 1 số lớn hơn 0'
        checkNumber++;
      } else if (isNaN(this.currentData.price)) {
        errorPrice.innerText = 'Giá tiền không được là ký tự'
        checkNumber++;
      } else if (!this.checkrealnumber(this.currentData.price)) {
        errorPrice.innerText = 'Giá tiền phải là số nguyên'
        checkNumber++;
      } else {
        errorPrice.innerText = ''
      }
      if (this.currentData.categoryId === undefined) {
        errorCategoryId.innerText = 'Vui lòng chọn mùa'
        checkNumber++;
      } else {
        errorCategoryId.innerText = ''
      }
      if (this.currentData.description == undefined) {
        errordescription.innerText = 'Vui lòng nhập mô tả'
        checkNumber++;
      } else {
        errordescription.innerText = ''
      }
      if (this.currentData.quantity == undefined) {
        errorquantity.innerText = 'Vui lòng nhập số lượng'
        checkNumber++;
      } else if (!this.checkrealnumber(this.currentData.quantity)) {
        errorquantity.innerText = 'Số lượng phải là số nguyên'
        checkNumber++;
      } else if (this.currentData.quantity <= 0) {
        errorquantity.innerText = 'Số lượng phải là 1 số lớn hơn 0'
        checkNumber++;
      } else if (isNaN(this.currentData.quantity)) {
        errorquantity.innerText = 'Số lượng không được là ký tự'
        checkNumber++;
      } else {
        errorquantity.innerText = ''
      }
      if (this.currentData.thumbnail == undefined) {
        errorthumbnail.innerText = 'Vui lòng nhập ảnh'
        checkNumber++;
      }else if(!this.validURL(this.currentData.thumbnail)){
        errorthumbnail.innerText = 'Ảnh phải là một đường link'
        checkNumber++;
      }else {
        errorthumbnail.innerText=''
      }
      if (this.currentData.detail == undefined) {
        errordetail.innerText = 'Vui lòng nhập chi tiết'
        checkNumber++;
      }else {
        errordetail.innerText=''
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
        return "lỗi validate kìa thằng đần"
      }
      ProductService.edit(this.currentData.id,this.currentData).then(response=> {
        console.log(response.data);
            this.$router.push("/products/list")
          }
      ).catch(error => {
        console.log(error)
      })
    }
  },
};
</script>