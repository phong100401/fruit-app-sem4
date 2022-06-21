<template>
  <div class="custom-de">
    <!-- single product -->
    <div class="single-product mt-150 mb-150">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="table-wrap">
              <table class="table table-striped">
                <thead>
                <tr style="background: #008800">
                  <th style="color: black">Ngày Đặt</th>
                  <th style="color: black">Trạng thái</th>
                  <th style="color: black">Thanh toán</th>
                  <th style="color: black">Tổng tiền</th>
                  <th style="color: black">Action</th>
                </tr>
                </thead>
                <tbody v-for="order in data" v-bind:key="order.id" style="background: whitesmoke">
                <tr v-if="order.shoppingCart ==false">
                  <td >
                    {{ order.createdAt.split("-")[2] }}-{{ order.createdAt.split("-")[1] }}-{{ order.createdAt.split("-")[0] }}
                  </td>

                  <td>
                      <span v-if="order.status==1" style="color: blue">
                     Đã xử lý
                      </span>
                    <span v-else style="color: green">
                        Đang xử lý
                      </span></td>
                  <td>
                    <span v-if=" order.checkout == true"  style="color: blue">
                    Đã thanh toán
                    </span>
                    <span v-else  style="color: green">
                    Chưa thanh toán
                    </span>
                  </td>

                  <td>{{new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(order.totalPrice)}}
                  </td>
                  <td ><a class="button" :href="'detail/'+ order.id " style="text-decoration: none">
                      Chi tiết
                  </a></td>
                </tr>
                </tbody>
              </table>
              <div style="padding-top: 15px">
                <a-pagination
                    :default-current="this.params.pageSize"
                    :total="this.totalRecords"
                    show-size-changer
                    @showSizeChange="onShowSizeChange"
                    @change="onChange"
                />
              </div>
        </div>
      </div>
    </div>
  </div>
    </div>
  </div>
</template>
<script>
import OrderService from "../../service/OrderService";

export default {
  data() {
    return {
      data: [],
      data1: [],
      data2: [],
      data3: [],
      data4: [],
      datastatus: [],
      form: {
        id: undefined,
        quantity: 1
      },
      price: undefined,
      price1: undefined,
      price2: undefined,
      price3: undefined,
      body: {
        id: undefined,
        quantity: 1
      },
      totalRecords: undefined,
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
      params1: {
        pageSize: 10,
        page: undefined,
        name: ""
      },
      params2: {
        pageSize: 10,
        page: undefined,
        name: ""
      },
      params3: {
        pageSize: 10,
        page: undefined,
        name: ""
      },
    };
  },
  created() {
    this.getOrder(),
        this.getAccount()
  },
  methods: {
    async getOrder() {
      this.params.accountName =this.$route.params.name;
      await OrderService.getAllOrder(this.params).then(
          rs => {
            try {
              this.data = rs.data.data;
              this.totalRecords = rs.data.pagination.totalItems;
            } catch (e) {
              console.log(e)
            }
          }
      )

    },
    async getAccount() {
      await OrderService.getAllAcount(this.params).then(rs => {
        this.data3 = rs.data.data;
        this.totalRecords = rs.data.pagination.totalItems;
      })
      console.log(this.data3)
    },
    onShowSizeChange(current, pageSize) {
      this.params.pageSize = pageSize;
      this.params.page = current;
      this.getOrder();
    },
    onChange(page, limit) {
      this.params.page = page;
      this.params.pageSize = limit;
      this.getOrder();
    },
  }
}
</script>
<style  scoped>
.custom-de{
  padding-top: 250px;
}
</style>