<template>
  <div>
    <a-card>
      <a-table :columns="columns" :pagination="false" rowKey="id" :data-source="data">
        <div slot="role" slot-scope="text">
          <span v-if="text ==1" style="color: #0ba360">USER</span>
          <span v-if="text==2" style="color: #a71d2a">ADMIN</span>
        </div>
        <div slot="status" slot-scope="text">
          <span v-if="text ==1" style="color: #0ba360">Đã kích hoạt</span>
          <span v-else style="color: #a71d2a">Chưa kích hoạt</span>
        </div>
<!--        <div slot="action" slot-scope="text,record">
          <a class="button" :href="'/accounts/detail/'+ record.id ">
            <a-icon style="font-size: 25px" type="info-circle"/>
          </a>
        </div>-->
      </a-table>
      <div style="padding-top: 15px">
        <a-pagination
            :default-current="this.params.pageSize"
            :total="this.totalRecords"
            show-size-changer
            @showSizeChange="onShowSizeChange"
            @change="onChange"
        />
      </div>
    </a-card>
  </div>
</template>
<script>
import AccountService from "../../../service/AccountService";


const columns = [
  {
    title: 'ID',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: 'User name',
    dataIndex: 'username',
    key: 'username',
  },
  {
    title: 'Role',
    dataIndex: 'role',
    key: 'role',
    scopedSlots: {customRender: 'role'},
  },
  {
    title: 'Name',
    dataIndex: 'name',
    key: 'name',
    ellipsis: false,
  },
  {
    title: 'Phone',
    dataIndex: 'phone',
    key: 'phone',
    ellipsis: false,
  },
  {
    title: 'Address',
    dataIndex: 'address',
    key: 'address',
    ellipsis: false,
  },
  {
    title: 'Status',
    dataIndex: 'status',
    key: 'status',
    ellipsis: false,
    scopedSlots: {customRender: 'status'},
  },
 /* {
    title: 'Action',
    dataIndex: 'action',
    key: 'action',
    ellipsis: true,
    scopedSlots: {customRender: 'action'},
  },*/


];

export default {
  data() {
    return {
      data: [],
      form: {
        quantity: 1
      },
      columns,
      body:{
        quantity: 1
      },
      totalRecords: undefined,
      params: {
        pageSize: 10,
        page: 1,
        name: undefined,
        sortType: undefined
      }
    };
  },
  created() {
    this.getAccounts()
  },
  methods: {
    getAccounts() {
      AccountService.getAll(this.params).then(
          res => {
            this.data = res.data.data;
            this.totalRecords = res.data.pagination.totalItems;
          }
      )
    },
    onShowSizeChange(current, pageSize) {
      this.params.pageSize = pageSize;
      this.params.page = current;
      this.getCategorise();
    },
    onChange(page, limit) {
      this.params.page = page;
      this.params.pageSize = limit;
      this.getCategories();
    },
  }
};
</script>
<style scoped>
.category__search-form{

  display: grid;
  gap: 2rem;
  grid-template-columns: repeat(auto-fit,minmax(200px,1fr));
}

</style>