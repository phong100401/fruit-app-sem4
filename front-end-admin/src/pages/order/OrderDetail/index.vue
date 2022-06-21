<template>
  <div>
  <body>
  <section class="ftco-section">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="table-wrap">
            <table class="table" :pagination="false" >
              <thead class="thead-primary">
              <tr>
                <th>ShipName</th>
                <th>ShipAddress</th>
                <th>ShipPhone</th>
                <th>ShipNote</th>
                <th>&nbsp;</th>
                <th>Product</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>total</th>
                <th>&nbsp;</th>
              </tr>
              </thead>
              <tbody v-for="te in this.data" v-bind:key="te">
              <tr class="alert" role="alert" v-for="product in te.orderDetails" v-bind:key="product.productId">
                <td><span>{{te.shipName}}</span></td>
                <td><span>{{te.shipAddress}}</span></td>
                <td><span>{{te.shipPhone}}</span></td><td>
                <span>{{te.shipNote}}</span></td>
                <td><span><img class="img" :src="product.thumbnailProduct"/></span></td>
                <td>
                  <div class="name">
                    <span>{{product.productName}}</span>
                  </div>
                </td>
                <td>{{new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(product.unitPrice)}}</td>
                <td class="quantity">
                  <div class="input-group">
                    <span name="quantity" class="quantity form-control input-number" >{{product.quantity}}</span>
                  </div>
                </td>
                <td>{{new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(product.unitPrice*product.quantity)}}</td>
              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </section>
  </body>
  </div>
</template>
<script>
import OrderService from "../../../service/OrderService";

export default {
  data() {
    return {
      data: [],
      listorder:[],
      form: {
        Id: undefined,
        quantity: 1
      },
      body:{
        Id:undefined,
        quantity: 1
      },
      totalRecords: undefined,
      params: {
        pageSize: 10,
        page: 1,
      }
    };
  },
  created() {
   this.getOrders(this.$route.params.id)
  },
  methods: {
    async getOrders(id){
      await OrderService.getOrderItems(id).then(
          rs=>{
            try {
              this.data = rs.data.data;
              this.totalRecords = rs.data.pagination?.totalIems;
              console.log(this.data)

            }
            catch (e){
              console.log(e);
            }
          })
    },
    onShowSizeChange(current, pageSize) {
      this.params.pageSize = pageSize;
      this.params.page = current;
      this.getProducts();
    },
    onChange(page, limit) {
      this.params.page = page;
      this.params.pageSize = limit;
      this.getProducts();
    },
  }
};
</script>
<style scoped>
/*table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;

}*/
img {
  margin: 0 auto;
  display: block;
  margin-top: 20%;
}
/*td, th {
  border-right: black solid 1px;

  text-align: center;
  padding: 8px;
}*/
</style>