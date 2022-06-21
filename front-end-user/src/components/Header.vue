<template>
<section class="custom-header">
  <!--PreLoader-->
  <div class="loader">
    <div class="loader-inner">
      <div class="circle"></div>
    </div>
  </div>
  <!--PreLoader Ends-->

  <!-- header -->
  <div class="top-header-area" id="sticker">
    <div class="container">
      <div class="row">
        <div class="col-lg-12 col-sm-12 text-center">
          <div class="main-menu-wrap">
            <!-- logo -->
            <div class="site-logo">
<!--              <router-link to="/">-->
<!--                <img src="assets/img/logo.png" alt="">-->
<!--              </router-link>-->
              <a href="/">
                <img src="assets/img/img_1.png" alt="">
              </a>
            </div>
            <!-- logo -->

            <!-- menu start -->
            <nav class="main-menu">
              <ul>
                <li class="current-list-item"><a href="/">Home</a>
<!--                  <ul class="sub-menu">-->
<!--                    <li><a href="/">Static Home</a></li>-->
<!--                    <li><a href="index_2.html">Slider Home</a></li>-->
<!--                  </ul>-->
                </li>
                <li><a href="about">About</a></li>
                <li><a href="#">Pages</a>
                  <ul class="sub-menu">
                    <li><a href="404">404 page</a></li>
                    <li><a href="about">About</a></li>
                    <li><a href="cart">Cart</a></li>
                    <li><a href="checkout">Check Out</a></li>
                    <li><a href="contact">Contact</a></li>
                    <li><a href="news">News</a></li>
                    <li><a href="shop">Shop</a></li>
                  </ul>
                </li>
                <li><a href="news">News</a>
                  <ul class="sub-menu">
                    <li><a href="news">News</a></li>
                    <li><a href="single-news">Single News</a></li>
                  </ul>
                </li>
                <li><a href="contact">Contact</a></li>
                <li><a href="/shop">Shop</a>
                  <ul class="sub-menu">
                    <li><a href="shop">Shop</a></li>
                    <li><a href="checkout">Check Out</a></li>
                    <li><a href="single-product">Single Product</a></li>
                    <li><a href="cart">Cart</a></li>
                  </ul>
                </li>
                <li>
                  <div class="header-icons">
                    <a class="shopping-cart" href="/cart"><i class="fas fa-shopping-cart"></i></a>
                    <a class="mobile-hide search-bar-icon" href="#"><i class="fas fa-search"></i></a>
                    <a class="shopping-cart" href="/login" v-if="this.token ==null">Login</a>
                    <a v-if="this.token !=null"
                        @click="abrirMenu()"
                        ref="mini-photo-wrapper"
                        class="mini-photo-wrapper"
                    >
                      <img
                          class="mini-photo"
                          src="https://bootdey.com/img/Content/avatar/avatar7.png"
                          width="36"
                          height="36"/></a>
                    <div
                        ref="menucontainer"
                        class="menu-container"
                        :class="{ active : mostrarMenu  }">
                      <ul class="user-menu">
                        <li class="user-menu__item" style="float:left;"><a class="user-menu-link" href="/profile">Profile</a></li>
                        <li class="user-menu__item" style="float:left;"><a class="user-menu-link" :href="'/history'+accname">History</a></li>
                        <li class="user-menu__item" style="float: left;" ><a class="user-menu-link" href="/login">Logout</a></li>
                      </ul>
                    </div>
                  </div>
                </li>
              </ul>
            </nav>
            <a class="mobile-show search-bar-icon" href="#"><i class="fas fa-search"></i></a>
            <div class="mobile-menu"></div>
            <!-- menu end -->
          </div>
        </div>
      </div>
    </div>

  </div>
  <!-- end header -->

  <!-- search area -->
  <div class="search-area">
    <div class="container">
      <div class="row">
        <div class="col-lg-12">
          <span class="close-btn"><i class="fas fa-window-close"></i></span>
          <div class="search-bar">
            <div class="search-bar-tablecell">
              <h3>Search For:</h3>
              <input type="text" placeholder="Keywords">
              <button type="submit">Search <i class="fas fa-search"></i></button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- end search area -->

  <div class="hero-area hero-bg">
    <div class="container">
      <div class="row">
        <div class="col-lg-9 offset-lg-2 text-center">
          <div class="hero-text">
            <div class="hero-text-tablecell">
              <p class="subtitle">Fresh & Organic</p>
              <h1>Delicious Seasonal Fruits</h1>
<!--              <div class="hero-btns">-->
<!--                <a href="shop.html" class="boxed-btn">Fruit Collection</a>-->
<!--                <a href="contact.html" class="bordered-btn">Contact Us</a>-->
<!--              </div>-->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- end hero area -->

  <!-- features list section -->
  <div class="list-section pt-80 pb-80">
    <div class="container">

      <div class="row">
        <div class="col-lg-4 col-md-6 mb-4 mb-lg-0">
          <div class="list-box d-flex align-items-center">
            <div class="list-icon">
              <i class="fas fa-shipping-fast"></i>
            </div>
            <div class="content">
              <h3>Free Shipping</h3>
              <p>When order over {{new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(500000) }}</p>
            </div>
          </div>
        </div>
        <div class="col-lg-4 col-md-6 mb-4 mb-lg-0">
          <div class="list-box d-flex align-items-center">
            <div class="list-icon">
              <i class="fas fa-phone-volume"></i>
            </div>
            <div class="content">
              <h3>24/7 Support</h3>
              <p>Get support all day</p>
            </div>
          </div>
        </div>
        <div class="col-lg-4 col-md-6">
          <div class="list-box d-flex justify-content-start align-items-center">
            <div class="list-icon">
              <i class="fas fa-sync"></i>
            </div>
            <div class="content">
              <h3>Refund</h3>
              <p>Get refund within 3 days!</p>
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>
  <!-- end features list section -->
</section>
</template>

<script type="text/babel">
import OrderService from "@/service/OrderService";

export default {
  name: "Header",
  data(){
    return{
      token : localStorage.getItem('access_token'),
      mostrarMenu: false,
      data3: [],
      params: {
        pageSize: 100,
        page: 1,
        from: "",
        to: "",
        status: undefined,
        accountName: undefined,
        phoneAccount: undefined,
        id: undefined
      },
      totalRecords: undefined,
      accname:"",
    }
  },
  created() {
    this.getAccount()
  },
  methods:{
    abrirMenu(){
      this.mostrarMenu ? this.mostrarMenu = false : this.mostrarMenu = true
    },
    async getAccount() {
      await OrderService.getAllAcount(this.params).then(rs => {
        this.data3 = rs.data.data;
        this.totalRecords = rs.data.pagination.totalItems;
      })
      let jwt = this.token;
      let jwtData = jwt.split('.')[1]
      let decodedJwtJsonData = window.atob(jwtData)
      let decodedJwtData = JSON.parse(decodedJwtJsonData)
      for(let i=0;i<this.data3.length;i++){
        if(this.data3[i].username == decodedJwtData.sub) {
          this.accname = this.data3[i].name
        }
      }
    },
  }
}
</script>

<style>
.custom-header{
  height: 700px;

}
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

body {
  background-color: #e2e1e0;
  font-family: sans-serif;
}


ul {
  list-style: none;
}


.mini-photo-wrapper {
  display: block;
}

.mini-photo {
  border-radius: 50%;
}

.menu-container {
  visibility: hidden;
  opacity: 0;
}

.menu-container.active {
  visibility: visible;
  opacity: 1;
  transition: all .3s ease-in-out;
}

.user-menu {
  position: absolute;
  right: 0px;
  background-color: #fff;
  width: 70px;
  border-radius: 4px;
  border: 1px solid rgba(0,0,0,0.15);
  padding-top: 5px;
  padding-bottom: 5px;
  margin-top: 10px;
}

.user-menu .user-menu-link {
  display: block;
  text-decoration: none;
  color: #444;
  font-size: 14px;
  padding: 5px 10px;

}

.user-menu__item:hover {
  background-color: #4183c4;
  color: #fff;
}

.user-menu:before {
  position: absolute;
  top: -16px;
  display: inline-block;
  content: "";
  border: 8px solid transparent;
  border-bottom-color: #ccc;
  border-bottom-color: rgba(0,0,0,0.15);
}

.user-menu:after {
  position: absolute;
  top: -14px;
  display: inline-block;
  content: "";
  border: 7px solid transparent;
  border-bottom-color: #fff;
}
</style>