<template>
  <section class="custom-chou">
    <!-- check out section -->
    <div class="checkout-section mt-150 mb-150">
      <div class="container">
        <div class="row">
          <div class="col-lg-8">
            <div class="checkout-accordion-wrap">
              <div class="accordion" id="accordionExample">
                <div class="card single-accordion">
                  <div class="card-header" id="headingOne">
                    <h5 class="mb-0">
                      <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        Billing Address
                      </button>
                    </h5>
                  </div>

                  <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
                    <div class="card-body">
                      <div class="billing-address-form">
                        <form action="index.html">
                          <p><input type="text" v-model="ship.shipName" placeholder="Ship Name">
                            <span id="error-shipname" style="color: red"></span></p>
                          <p><input type="text" v-model="ship.shipAddress" placeholder="Ship Address">
                            <span id="error-address" style="color: red"></span></p>
                          <p><input type="tel"  v-model="ship.shipPhone" placeholder="Ship Phone">
                            <span id="error-shipphone" style="color: red"></span></p>
                          <p><textarea name="bill" id="bill" cols="30" rows="10" v-model="ship.shipNote" placeholder="Ship note"></textarea>
                            <span id="error-shipnote" style="color: red"></span></p>
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="card single-accordion">
                  <div class="card-header" id="headingTwo">
                    <h5 class="mb-0">
                      <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                        Shipping Address
                      </button>
                    </h5>
                  </div>
                  <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
                    <div class="card-body">
                      <div class="shipping-address-form">
                        <p>Your shipping address form is here.</p>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="card single-accordion">
                  <div class="card-header" id="headingThree">
                    <h5 class="mb-0">
                      <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                        Card Details
                      </button>
                    </h5>
                  </div>
                  <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
                    <div class="card-body">
                      <div class="card-details">
                        <p>Your card details goes here.</p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

            </div>
          </div>

          <div class="col-lg-4">
            <div class="order-details-wrap">
              <table class="order-details">
                <thead>
                <tr>
                  <th>Your order Details</th>

                  <th>Price</th>
                </tr>
                </thead>
                <tbody class="order-details-body" >
                <tr v-for="order in this.cartData.orderDetails" v-bind:key="order">
                  <td>{{order.productName}}</td>

                  <td>{{new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(order.quantity*order.unitPrice) }}</td>
                </tr>
                </tbody>
                <tbody class="checkout-details">
                <tr>
                  <td>Subtotal</td>
                  <td v-if="this.cartData.totalPrice == undefined">{{new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(0) }}</td>
                  <td v-else>{{new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(this.cartData.totalPrice) }}</td>
                </tr>
                <tr>
                  <td>Shipping</td>
                  <td v-if="this.cartData.totalPrice > 500000">{{new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(0) }}</td>
                  <td v-else>{{new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(50000) }}</td>
                </tr>
                <tr>
                  <td>Total</td>
                  <td v-if="this.cartData.totalPrice == undefined ||this.cartData.totalPrice ==0">{{new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(0) }}</td>
                  <td v-else-if="this.cartData.totalPrice > 500000">{{new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(this.cartData.totalPrice + 0)}}</td>
                  <td v-else>{{new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(this.cartData.totalPrice + 50000)}}</td>
                </tr>
                </tbody>
              </table>
              <a href="#" @click.stop.prevent="handleSubmit" class="boxed-btn">Place Order</a>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- end check out section -->

  </section>
</template>

<script>
import OrderService from "../../service/OrderService";
export default {
  name: "CheckOut",
  data(){
    return{
      cartData:[],
      orderdata:[],
      params:{
        productId: undefined
      },
      productQuantity:{
        productId: undefined,
        quantity: undefined
      },
      ship:{
        shipName:undefined,
        shipAddress:undefined,
        shipPhone:undefined,
        shipNote:undefined
      }
    }
  },
  created() {
    this.getCartData()
  },
  methods:{
    getCartData() {
      OrderService.getAll().then(rs => {
        this.cartData = rs.data
        console.log(this.cartData)
      })
    },
    validPhonenumber(str){
      var pattern = new RegExp('^\\(?([0-9]{3})\\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$');
      return !!pattern.test(str);
    },
    validate(){
      var errorName = document.getElementById('error-shipname')
      var errorAddress = document.getElementById('error-address')
      var errorshipphone = document.getElementById('error-shipphone')
      let checkNumber =0;
      if(this.ship.shipName === undefined){
        errorName.innerText = 'Vui lòng nhập tên'
        checkNumber++;
      }else if(this.ship.shipName.length<7 ||this.ship.shipName.length >25){
        errorName.innerText = 'Tên chỉ được trong khoảng từ 7 đến 25 ký tự'
        checkNumber++;
      }
      else {
        errorName.innerText = ''
      }
      if (this.ship.shipAddress == undefined) {
        errorAddress.innerText = 'Vui lòng nhập địa chỉ'
        checkNumber++;
      }else if(this.ship.shipAddress.length<5){
        errorAddress.innerText = 'Địa chỉ phải lớn hơn 5 ký tự'
        checkNumber++;
      }else {
        errorAddress.innerText=''
      }
      if(this.ship.shipPhone === undefined){
        errorshipphone.innerText = 'Vui lòng nhập số điện thoại'
        checkNumber++;
      }else if(this.ship.shipPhone.length<10 || this.ship.shipPhone.length>10 ){
        errorshipphone.innerText = 'Số điện thoại phải là 10 ký tự'
        checkNumber++;
      }else if(!this.validPhonenumber(this.ship.shipPhone))
      {
        errorshipphone.innerText = 'Số điện thoại không chứa ký tự'
        checkNumber++;
      } else {
        errorshipphone.innerText=''
      }
      if (checkNumber == 0) {
        return true;
      }
      else {
        return false;
      }
    },
    handleSubmit(e){
      e.preventDefault()
      if(!this.validate()){
        console.log("Lỗi ở đây")
      }else {
        OrderService.submitOrder(this.ship).then(
            alert("Đặt hàng thành công"),
            this.$router.push('/shop')
        ).catch(error => {
          console.log(error)
        })
      }
    }
  }
}
</script>

<style>
.custom-chou{
  padding-top: 250px;
}
</style>