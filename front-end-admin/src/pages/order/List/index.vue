<template>
  <div>
    <section class="ftco-section">
      <div class="container">
        <div class="row">
          <div>
            <a-form @submit="handleSearch" class="product__search-form">
              <a-form-item>
                <a-input-search v-model="params.id" placeholder="Search by ID">
                  <a-icon type="search"/>
                </a-input-search>
              </a-form-item>
              <a-form-item>
                <a-input-search v-model="params.accountName" placeholder="Search by name">
                  <a-icon type="search"/>
                </a-input-search>
              </a-form-item>
              <a-form-item>
                <a-input-search v-model="params.phoneAccount" placeholder="Search by phone">
                  <a-icon type="search"/>
                </a-input-search>
              </a-form-item>
              <a-form-item>
                <a-range-picker
                    :ranges="{ Today: [moment(), moment()], 'This Month': [moment(), moment().endOf('month')] }"
                    @change="selectDate"/>
              </a-form-item>
              <a-form-item>
                <a-select placeholder="Search by Status" v-model="params.status">
                  <a-select-option :value="1">
                    Đã xử lý
                  </a-select-option>
                  <a-select-option :value="0">
                    Đang xử lý
                  </a-select-option>
                </a-select>
              </a-form-item>
            </a-form>
            <a-button type="primary" html-type="submit" @click="handleSearch">
              Search
            </a-button>
            <a-button type="primary" html-type="submit" @click="resetButton" style="margin-left: 20px">
              Reset
            </a-button>
            <!--            <a-button  type="primary" html-type="submit" @click="updateallstatus(1)" style="margin-left: 20px">
                          SelectAllStatus
                        </a-button>-->
          </div>

          <div>
            <div id="price" style="float: left" class="col-md-5">
              <span style="align-content: center">Tổng tiền đơn hàng: {{new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(this.price)}}<br></span>
              <span style="align-content: center">Số tiền đã nhận: {{new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(this.price1)}}</span>
            </div>
          </div>
          <div class="col-md-12">
            <div class="table-wrap">
              <table class="table table-striped">
                <thead>
                <tr style="background: red">
                  <th style="color: black">ID</th>
                  <th style="color: black">Name</th>
                  <th style="color: black">Phone</th>
                  <th style="color: black">Total Price</th>
                  <th style="color: black">Created At</th>
                  <th style="color: black">Status<input type="checkbox" id="checkAll" @click="checkAll(1)"/></th>
                  <th style="color: black">CheckOut</th>
                  <th style="color: black">Action</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="order in data" v-bind:key="order.id">
                  <td scope="row" v-if="order.shoppingCart ==false"><span>{{ order.id }}</span></td>
                  <td v-if="order.shoppingCart ==false"><span v-for="account in data3" v-bind:key="account"><span
                      v-if="order.accountId == account.id">{{ account.name }}</span></span></td>
                  <td v-if="order.shoppingCart ==false"><span v-for="account in data3" v-bind:key="account.id"><span
                      v-if="order.accountId == account.id">{{ account.phone }}</span></span></td>
                  <td v-if="order.shoppingCart ==false">{{new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(order.totalPrice)}}
                  </td>
                  <td v-if="order.shoppingCart ==false">
                    {{ order.createdAt.split("-")[2] }}-{{ order.createdAt.split("-")[1] }}-{{ order.createdAt.split("-")[0] }}
                  </td>
                  <td v-if="order.shoppingCart ==false">
                      <span v-if="order.status==1" style="color: green">
                     Đã xử lý
                      </span>
                    <span v-else style="color: blue">
                        Đang xử lý
                      </span>
                    <span v-if="order.status==1">
                       <input type="checkbox" checked="checked" @change="updatestatus(order.id,0)"/>
                      <label style="text-align: center"></label>
                      </span>
                    <span v-else>
                      <input type="checkbox" id="uncheckbox" class="subCheckBox"   @change="updatestatus(order.id,1)"/>
                      <label style="text-align: center"></label>
                      </span></td>
                  <td v-if="order.shoppingCart ==false"><span v-if="order.checkout ==false" style="color: red">
                    Chưa thanh toán
                    </span>
                    <span v-else style="color: green">
                    Đã thanh toán
                    </span>
                    <span v-if="order.checkout ==true">
           <input type="checkbox" checked="checked" @change="updatecheckout(order.id,false)"/>
           <label style="text-align: center"></label>
             </span>
                    <span v-else>
             <input type="checkbox" @change="updatecheckout(order.id,true)"/>
           <label style="text-align: center"></label>
           </span></td>
                  <td v-if="order.shoppingCart ==false"><a class="button" :href="'detail/'+ order.id ">
                    <a-icon style="font-size: 25px" type="info-circle"/>
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
          <div>
            <div id="price1" class="col-md-6" style="float: right">
              <span style="align-content: center">Tổng tiền đơn hàng trang {{ this.params.page }} : {{new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(this.price2)}}<br></span>
              <span style="align-content: center">Số tiền đã nhận trang {{ this.params.page }} : {{new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(this.price3)}}</span>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import moment from 'moment';
import OrderService from "../../../service/OrderService";

export default {
  data() {
    return {
      data: [],
      data1: [],
      data2: [],
      data3: [],
      data4: [],
      datastatus:[],
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
        pageSize: 10,
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
    this.caculatorTotalPrice()
    this.caculatorCheckout()
    this.updatecheckout()
    this.updatestatus()
  },
  methods: {
    async getOrder() {
      await OrderService.getAll(this.params).then(
          rs => {

            try {
              let price2 = 0;
              let price3 = 0;
              this.data = rs.data.data;
              this.totalRecords = rs.data.pagination.totalItems;
              for (let i = 0; i < this.data.length; i++) {
                if (this.data[i].status == 1) {
                  price2 = price2 + this.data[i].totalPrice;
                }
              }
              this.price2 = price2;
              for (let i = 0; i < this.data.length; i++) {
                if (this.data[i].checkout == true) {
                  price3 = price3 + this.data[i].totalPrice;
                }
              }
              this.price3 = price3
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
    },
    async caculatorTotalPrice() {
      let price = 0;
      let check = true;
      let page = 1;
      this.params1.page = page;
      do {
        await OrderService.getAll(this.params1).then(
            rs => {
              try {
                this.data1 = rs.data.data;
                if (this.data1.length > 0) {
                  for (let i = 0; i < this.data1.length; i++) {
                    if (this.data1[i].status == 1) {
                      price = price + this.data1[i].totalPrice;
                    }
                  }
                  page = this.params1.page;
                  page = page + 1;
                  this.params1.page = page;
                } else {
                  check = false
                }
              } catch (e) {
                console.log(e)
              }
            }
        )
      } while (check)
      {
        this.price = price;
      }
    },

    async caculatorCheckout() {
      let price = 0;
      let check = true;
      let page = 1;
      this.params2.page = page;
      do {
        await OrderService.getAll(this.params2).then(
            rs => {
              try {
                this.data2 = rs.data.data;
                if (this.data2.length > 0) {
                  for (let i = 0; i < this.data2.length; i++) {
                    if (this.data2[i].checkout == true) {
                      price = price + this.data2[i].totalPrice;
                    }
                  }
                  page = this.params2.page;
                  page = page + 1;
                  this.params2.page = page;
                } else {
                  check = false
                }
              } catch (e) {
                console.log(e)
              }
            }
        )
      } while (check)
      {
        this.price1 = price;
      }
    },

    selectDate(dates, dateStrings) {
      this.params.from = dateStrings[0];
      this.params.to = dateStrings[1];
      this.getOrder();
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
    updatestatus(id, status) {
      if(status ==1){
        this.$confirm({
          title: 'Bạn có xác nhận đơn hàng này không',
          onOk: () => {
            OrderService.updateStatus(id, status).then(
                rs => {
                  console.log(rs.data)
                  this.getOrder();
                  this.caculatorTotalPrice();
                })
          },
          onCancel() {
            console.log("Cancel")
            document.getElementById("uncheckbox").checked = false;
          }
        })
        }else {
        OrderService.updateStatus(id, 0).then(
            rs => {
              console.log(rs.data)
              this.getOrder();
              this.caculatorTotalPrice();

            })

      }
    },
    /*async updateallstatus(status){
       let check =true;
       let page=1;
       this.params3.page = page;
       do {
       await   OrderService.getAll(this.params3).then(
             rs => {
               try {
                 this.data4 = rs.data.data;
                 if(this.data4.length>0) {
                   for (let i = 0; i < this.data4.length; i++) {
                     if(this.data4[i].status==0) {
                       this.updatestatus(this.data4[i].id, status)
                     }
                   }
                   page = this.params3.page;
                   page = page+1;
                   this.params3.page = page;
                 }else {check=false}
               } catch (e) {
                 console.log(e)
               }
             }
       )
       }while (check){
         console.log("Bye")
       }
     },*/

    updatecheckout(id, isCheck) {
      OrderService.updateCheckout(id, isCheck).then(
          rs => {
            this.getOrder();
            this.caculatorCheckout()
            console.log(rs)
          }
      )
    },
    handleSearch(e) {
      e.preventDefault();
      this.params.page = 1;
      this.getOrder()
    },
    resetButton() {
      this.params = {
        pageSize: 10,
        page: 1,
        name: "",
      }
      this.getOrder();
    },
    checkAll(status) {
      let checkAll = document.getElementById("checkAll")
      console.log(checkAll.checked)
      let subCheckBox = document.getElementsByClassName("subCheckBox");
      for (let element of subCheckBox) {
        element.checked = checkAll.checked;
        console.log(element.value)
        console.log(status)
      }
    },
    moment: function () {
      return moment();
    },
  }
}
</script>

<style lang="scss" scoped>
$color-primary: #4c4c4c;
$color-secondary: #a6a6a6;
$color-highlight: #ff3f40;
#price {
  margin: 1.5em 0;
  color: $color-primary;
  font-size: 1.2em;

  span {
    padding-left: 0.15em;
    font-size: 1.2em;
  }
}

#price1 {
  margin: 1.5em 0;
  color: $color-highlight;
  font-size: 1.2em;

  span {
    padding-left: 0.15em;
    font-size: 1.2em;
  }
}

.product__search-form {
  display: grid;
  gap: 1rem;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
}

</style>