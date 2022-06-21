<template>
<div class="custom-de">
  <!-- single product -->
  <div class="single-product mt-150 mb-150">
    <div class="container">
      <div class="row">
        <div class="col-md-5">
          <div class="single-product-img">
            <img :src="currentData.thumbnail" alt="">
          </div>
        </div>
        <div class="col-md-7">
          <div class="single-product-content">
            <h3>{{ currentData.name}}</h3>
            <p class="single-product-pricing">{{new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(currentData.price) }}/<span>Per Kg</span></p>
            <p>{{currentData.description}}</p>
            <p>{{currentData.detail}}</p>
            <div class="single-product-form">
              <a @click="addToCart(currentData.id)" class="cart-btn"><i class="fas fa-shopping-cart"></i> Add to Cart</a>
              <p :v-model="this.getCategorise(currentData.categoryId)"><strong>Categories: </strong>{{this.data.name}}</p>
            </div>
            <h4>Share:</h4>
            <ul class="product-share">
              <li><a href=""><i class="fab fa-facebook-f"></i></a></li>
              <li><a href=""><i class="fab fa-twitter"></i></a></li>
              <li><a href=""><i class="fab fa-google-plus-g"></i></a></li>
              <li><a href=""><i class="fab fa-linkedin"></i></a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import CategoryService from "@/service/CategoryService";
import ProductService from "@/service/ProductService";
import OrderService from "@/service/OrderService";
export default {
  name: "Detail",
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
      cartItem:{
        productId: undefined,
        quantity: 1
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

    addToCart(productId) {
      let jwt = localStorage.getItem('access_token');
      if(jwt ==null){
        this.$router.push({name:'Login'});
      }else {
        console.log(this.cartItem)
        this.cartItem.productId = productId;
        OrderService.addToCart(this.cartItem).then(
            rs => {
              console.log(rs.data)
              if (rs.data.status === 1) {
                alert("Đã thêm sản phẩm vào giỏ hàng")
              } else {
                console.log(rs.data.status)
                alert("Thêm sản phẩm thất bại")
              }
            }
        )
      }
    }
  },
}
</script>
<style scoped>
.custom-de{
  padding-top: 200px;
}
</style>