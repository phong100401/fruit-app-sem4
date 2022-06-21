<template>
  <section class="custom-sh">
    <!-- products -->
    <div class="product-section mt-150 mb-150">
      <div class="container">

        <div class="row">
          <div class="col-md-12">
            <div class="product-filters">
              <ul @click="reset()"><li class="active1" id="abc">All</li></ul>
              <ul v-for="categori in cateData" :key="categori.id">
                <li class="active1" :id="categori.id" @click="filterProductByCategory(categori.id)">{{ categori.name }}</li>
              </ul>
            </div>
          </div>
        </div>

        <div class="row" >
          <template v-for="product in productData">
            <div class="col-lg-4 col-md-6 text-center strawberry" v-bind:key="product.id">
              <div class="single-product-item">
                <div class="product-image">
                  <a  class="button" :href="'/detail'+product.id"><img :src="product.thumbnail" alt=""></a>
                </div>

                <h3>{{ product.name }}</h3>
                <p class="product-price">
                  <span v-if="product.categoryId == '1'">Per Kg</span>
                  <span v-else></span>
                  {{new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(product.price) }}
                </p>
                <a @click="addToCart(product.id)" class="cart-btn"><i class="fas fa-shopping-cart"></i> Add to Cart</a>
              </div>
            </div>
          </template>

        </div>

        <div class="row">
          <div class="col-lg-12 text-center">
            <div class="pagination-wrap">
              <ul>
                <li><a href="#">Prev</a></li>
                <li><a href="#">1</a></li>
                <li><a class="active" href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">Next</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- end products -->

    <!-- logo carousel -->
    <div class="logo-carousel-section">
      <div class="container">
        <div class="row">
          <div class="col-lg-12">
            <div class="logo-carousel-inner">
              <div class="single-logo-item">
                <img src="assets/img/company-logos/1.png" alt="">
              </div>
              <div class="single-logo-item">
                <img src="assets/img/company-logos/2.png" alt="">
              </div>
              <div class="single-logo-item">
                <img src="assets/img/company-logos/3.png" alt="">
              </div>
              <div class="single-logo-item">
                <img src="assets/img/company-logos/4.png" alt="">
              </div>
              <div class="single-logo-item">
                <img src="assets/img/company-logos/5.png" alt="">
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- end logo carousel -->
  </section>
</template>

<script>

import ProductService from "../../service/ProductService";
import CategoryService from "../../service/CategoryService";
import OrderService from "../../service/OrderService";

export default {
  name: "Shop",
  data() {
    return{
      productData:[],
      cateData:[],
      totalRecords: undefined,
      params: {
        pageSize: 6,
        page: 1,
        name: undefined,
        categoryId: undefined,
        minPrice: undefined,
        maxPrice: undefined,
      },
      cartItem:{
        productId: undefined,
        quantity: 1
      },
      accountId: 1
    }
  },
  created() {
    this.getCategories()
    this.getProducts()
  },
  methods: {
    getProducts() {
      ProductService.getAll(this.params).then(rs => {
        // console.log(rs.data.data)
        this.productData = rs.data.data
      })
    },
    getCategories() {
      CategoryService.getAll().then(rs => {
        // console.log(rs.data)
        this.cateData = rs.data
      })
    },
    filterProductByCategory(id) {
      this.params.categoryId = id
      ProductService.getAll(this.params).then(rs => {
        // console.log(rs.data.data)
        this.productData = rs.data.data
      })
      let liQ = document.getElementsByClassName("active1")
      for (let i = 0; i < liQ.length; i++) {
        liQ[i].classList.remove("active")
      }
      console.log(liQ.length)
      // liQ.classList.remove('active')
      let colorCategory = document.getElementById(id)
      colorCategory.classList.add('active')
    },
    reset() {
      ProductService.getAll().then(
          rs => {
            // console.log(rs.data.data)
            this.productData = rs.data.data
          }
      )
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
  }
}
</script>

<style>
.custom-sh{
  padding-top: 250px;
}
.product-filters ul {
  display: inline;
}
</style>