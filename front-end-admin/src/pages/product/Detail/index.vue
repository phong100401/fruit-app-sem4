<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<template>
  <section class="product" style="display: grid">
    <div class="ant-col-sm-18" id="main_product" >
      <div class="photo-container">
          <img class="photo-main" :src="currentData.thumbnail" alt="green apple slice">
      </div>
    </div>
    <div class="ant-col-sm-18" >
      <div class="title">
        <h1>{{ currentData.name}}</h1>
        <span>Status: {{currentData.status}}</span>
      </div>
      <div class="category" :v-model="this.getCategorise(currentData.categoryId)">
        <span>Loại mùa: {{this.data.name}} </span>
      </div>
      <div class="price">
         <span>{{new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(currentData.price) }}</span>
      </div>

      <div class="description">
        <h3>Description</h3>
        <span>{{currentData.description}}</span>

      </div>
      <div class="description">
        <h4>Detail</h4>
        <span v-html="currentData.detail"></span>
      </div>
      <div slot="action">
        <router-link :to="'/products/edit/'+currentData.id">
        <button class="buy--btn">Update Product</button>
        </router-link>
      </div>
    </div>
  </section>
</template>
<script>
//import '../node_modules/bootstrap/dist/css/bootstrap.min.css';  //works, needs full relative paths
import CategoryService from "@/service/CategoryService";
import ProductService from "@/service/ProductService";
export default {
  data(){
    return{
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
      },
    };
  },
  created() {
    this.getCategorise(this.currentData.categoryId)
    this.getProducts(this.$route.params.id)
  },
  methods: {
    getCategorise(id) {
      CategoryService.detail(id).then(
          res => {
            this.data = res.data
            console.log(this.data)
          }
      )
    },
    getProducts(id){
      ProductService.detail(id).then(response =>{
        this.currentData =response.data;
        console.log(response.data);
      })
    },
  },
};
</script>
<style lang="scss" scoped>

$color-primary: #4c4c4c;
$color-secondary: #a6a6a6;
$color-highlight: #ff3f40;

/* ----- Global ----- */
* {
  box-sizing: border-box;
}

html,
body {
  height: 100%;
}

body {
  display: grid;
  grid-template-rows: 1fr;
  font-family: "Raleway", sans-serif;
  background-color: #01e37f;
}

h3 {
  font-size: 1.7em;
  letter-spacing: 1.2px;
  color: $color-secondary;
}
h4{
  font-size: 1.7em;
  letter-spacing: 1.2px;
  color: $color-secondary;
}

img {
  max-width: 100%;
  filter: drop-shadow(1px 1px 3px $color-secondary);
}

/* ----- Product Section ----- */
.product {
  display: grid;
  grid-template-columns: 0.9fr 1fr;
  margin: auto;
  padding: 2.5em 0;
  min-width: 600px;
  background-color: white;
  border-radius: 5px;
}

/* ----- Photo Section ----- */
.product__photo {

  position: relative;
}

.photo-container {
  left: 2.5em;
  display: grid;
  grid-template-rows: 1fr;
  width: 100%;
  height: 100%;
  border-radius: 6px;
}
#main_product{

 /* grid-template-columns: repeat(auto-fit,minmax(200px,1fr));*/
}
.photo-main {
  padding-bottom: 2.5em;
  padding-left: 2.5em;

  border-radius: 6px 6px 0 0;
  left: 2.5em;
  top: 0em;
  max-width: 100%;
  max-height: 100%;
  filter: saturate(100%) contrast(100%) hue-rotate(10deg)
  drop-shadow(1px 20px 10px rgba(0, 0, 0, 0.3));

.controls {
  display: flex;
  justify-content: space-between;
  padding: 0.8em;
  color: #fff;

i {
  cursor: pointer;
}
}

img {

}
}

.photo-album {
  padding: 0.7em 1em;
  border-radius: 0 0 6px 6px;
  background-color: #fff;

ul {
  display: flex;
  justify-content: space-around;
}

li {
  float: left;
  width: 55px;
  height: 55px;
  padding: 7px;
  border: 1px solid $color-secondary;
  border-radius: 3px;
}
}

/* ----- Informations Section ----- */
.product__info {
  margin-left: 10%;
  padding: 2.8em 0;
}
.title {
h1 {
  margin-bottom: 0.1em;
  color: $color-primary;
  font-size: 1.5em;
  font-weight: 900;
}

span {
  font-size: 0.7em;
  color: $color-secondary;
}
}

.price {
  margin: 1.5em 0;
  color: $color-highlight;
  font-size: 1.2em;

span {
  padding-left: 0.15em;
  font-size: 2.9em;
}
}

.variant {
  overflow: auto;

h3 {
  margin-bottom: 1.1em;
}

li {
  float: left;
  width: 35px;
  height: 35px;
  padding: 3px;
  border: 1px solid transparent;
  border-radius: 3px;
  cursor: pointer;

&:first-child,
&:hover {
   border: 1px solid $color-secondary;
 }
}

li:not(:first-child) {
  margin-left: 0.1em;
}
}

.description {
  clear: left;
  margin: 2em 0;

h3 {
  margin-bottom: 1em;
}

ul {
  font-size: 0.8em;
  list-style: disc;
  margin-left: 1em;
}

li {
  text-indent: -0.6em;
  margin-bottom: 0.5em;
}
}

.buy--btn {
  padding: 1.5em 3.1em;
  border: none;
  border-radius: 7px;
  font-size: 0.8em;
  font-weight: 700;
  letter-spacing: 1.3px;
  color: #fff;
  background-color: $color-highlight;
  box-shadow: 2px 2px 25px -7px $color-primary;
  cursor: pointer;

&:active {
   transform: scale(0.97);
 }
}

/* ----- Footer Section ----- */
footer {
  padding: 1em;
  text-align: center;
  color: #fff;

a {
  color: $color-primary;

&:hover {
   color: $color-highlight;
 }
}
}

</style>