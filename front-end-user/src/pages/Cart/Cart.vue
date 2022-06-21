<template>
  <div class="custom-ca">
    <!-- cart -->
    <div class="cart-section mt-150 mb-150">
      <div class="container">
        <div class="row">
          <div class="col-lg-8 col-md-12">
            <div class="cart-table-wrap">
              <table class="cart-table">
                <thead class="cart-table-head">
                <tr class="table-head-row">
                  <th class="product-remove"></th>
                  <th class="product-image">Product Image</th>
                  <th class="product-name">Name</th>
                  <th class="product-price">Price</th>
                  <th class="product-quantity">Quantity</th>
                  <th class="product-total">Total</th>
                </tr>
                </thead>
                <tbody v-for="od in cartData.orderDetails" :key="od.id">
                <tr class="table-body-row">
                  <td class="product-remove"><a @click="confirmRemove(od.id.productId)"><i class="far fa-window-close"></i></a></td>
                  <td class="product-image"><img :src="od.thumbnailProduct" alt=""></td>
                  <td style="width: 125px" class="product-name">{{ od.productName }}</td>
                  <td class="product-price">{{new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(od.unitPrice) }}</td>
                  <td class="product-quantity">
                    <!--                    <button @click="decreaseProductQuantity(od.id.productId)" ><i class="fa fa-minus"></i></button>-->
                    <input type="number" min="1" disabled class="quantity" :value="od.quantity" />
                    <!--                    <button @click="increaseProductQuantity(od.id.productId)" ><i class="fa fa-plus"></i></button>-->
                  </td>
                  <td class="product-total">{{new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(od.unitPrice * od.quantity) }}</td>
                </tr>
                </tbody>
              </table>
            </div>
          </div>

          <div class="col-lg-4">
            <div class="total-section">
              <table class="total-table">
                <thead class="total-table-head">
                <tr class="table-total-row">
                  <th>Total</th>
                  <th>Price</th>
                </tr>
                </thead>
                <tbody>
                <tr class="total-data">
                  <td><strong>Subtotal: </strong></td>
                  <td v-if="this.cartData.totalPrice == undefined">{{new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(0) }}</td>
                  <td v-else>{{new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(this.cartData.totalPrice) }}</td>
                </tr>
                <tr class="total-data">
                  <td><strong>Shipping: </strong></td>
                  <td v-if="this.cartData.totalPrice > 500000">{{new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(0) }}</td>
                  <td v-else>{{new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(50000) }}</td>
                </tr>
                <tr class="total-data">
                  <td><strong>Total: </strong></td>
                  <td v-if="this.cartData.totalPrice == undefined ||this.cartData.totalPrice ==0">{{new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(0) }}</td>
                  <td v-else-if="this.cartData.totalPrice > 500000">{{new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(this.cartData.totalPrice + 0)}}</td>
                  <td v-else>{{new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(this.cartData.totalPrice + 50000)}}</td>
                </tr>
                </tbody>
              </table>
              <div class="cart-buttons">
                <!--                <a href="cart.html" class="boxed-btn">Update Cart</a>-->
                <a v-if="this.cartData.totalPrice >0" href="/checkout" class="boxed-btn black">Check Out</a>
              </div>
            </div>

            <!--            <div class="coupon-section">-->
            <!--              <h3>Apply Coupon</h3>-->
            <!--              <div class="coupon-form-wrap">-->
            <!--                <form action="index.html">-->
            <!--                  <p><input type="text" placeholder="Coupon"></p>-->
            <!--                  <p><input type="submit" value="Apply"></p>-->
            <!--                </form>-->
            <!--              </div>-->
            <!--            </div>-->
          </div>
        </div>
      </div>
    </div>
    <!-- end cart -->

  </div>
</template>

<script>
import OrderService from "../../service/OrderService";

export default {
  name: "Cart",
  data(){
    return{
      cartData:[],
      cartItem:[],
      productId: undefined,
      productQuantity:{
        productId: undefined,
        isIncrease: undefined
      }
    }
  },
  created() {
    this.getCartData()
  },
  methods:{
    getCartData(){
      OrderService.getAll().then(rs => {
        this.cartData = rs.data
        // console.log(this.accountId)
        // console.log(rs.data)
      })
    },
    removeItem(productId){
      this.productId = productId
      console.log(this.productId)
      OrderService.removeItem(this.productId)
    },
    confirmRemove(id){
      const isConfirm = confirm("Bạn có muốn xóa sản phẩm này khỏi giỏ hàng")
      if (isConfirm === true){
        this.removeItem(id)
        window.location.reload();
      }

    },
    increaseProductQuantity(id){

      this.productQuantity.productId = id;
      this.productQuantity.isIncrease=1;
      OrderService.getvalue(this.productQuantity)
    },
    decreaseProductQuantity(id){
      this.productQuantity.productId = id;
      this.productQuantity.isIncrease=0;
      /*   if(this.productQuantity.quantity <0){
           this.productQuantity.quantity =0;
         }*/
      OrderService.getvalue(this.productQuantity)
      this.getCartData()

    }
  }
}
</script>

<style>
.custom-ca{
  padding-top: 200px;
}
</style>