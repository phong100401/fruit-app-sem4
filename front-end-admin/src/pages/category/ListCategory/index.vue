<template>
  <div>
    <a-card>
      <div style="padding-bottom: 20px; padding-top: 20px">
        <router-link to="/categories/create">
          <a-button type="primary" icon="plus">
            Add category
          </a-button>
        </router-link>
      </div>
      <a-table :columns="columns" :pagination="false" rowKey="id" :data-source="data">
        <span slot="thumbnail" slot-scope="text">
             <img :src="text" style="width: 70%">
         </span>
        <div slot="action" slot-scope="text,record">
          <a class="button" @click="showDeleteConfirm(record.id)">
            <a-icon style="font-size: 25px" type="delete"/>
          </a> |
          <a class="button" :href="'/categories/edit/'+ record.id ">
            <a-icon style="font-size: 25px" type="edit"/>
          </a> |
          <a class="button" :href="'/categories/detail/'+ record.id ">
            <a-icon style="font-size: 25px" type="info-circle"/>
          </a>
        </div>
      </a-table>
<!--      <div style="padding-top: 15px">
        <a-pagination
            :default-current="this.params.pageSize"
            :total="this.totalRecords"
            show-size-changer
            @showSizeChange="onShowSizeChange"
            @change="onChange"
        />
      </div>-->
    </a-card>
  </div>
</template>
<script>
import CategoryService from "@/service/CategoryService";


const columns = [
  {
    title: 'ID',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: 'Category Name',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: 'Image',
    dataIndex: 'thumbnail',
    key: 'thumbnail',
    ellipsis: true,
    scopedSlots: {customRender: 'thumbnail'}
  },
  {
    title: 'Description',
    dataIndex: 'description',
    key: 'description',
  },
  {
    title: 'Status',
    dataIndex: 'status',
    key: 'status',
    ellipsis: true,
  },
  {
    title: 'Action',
    dataIndex: 'action',
    key: 'action',
    ellipsis: true,
    scopedSlots: {customRender: 'action'},
  },


];

export default {
  data() {
    return {
      data: [],
      form: {
        categoryId: undefined,
        quantity: 1
      },
      columns,
      body:{
        categoryId:undefined,
        quantity: 1
      },
      totalRecords: undefined,
      categories:[],
      params: {
        pageSize: 10,
        page: 1,
        name: undefined,
        sortType: undefined
      }
    };
  },
  created() {
    this.getCategories()
  },
  methods: {
    getCategories(){
      CategoryService.getAll(this.params).then(
          res => {
            this.data = res.data;
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
    showDeleteConfirm(pId) {
      this.$confirm({
        title: 'Do you want delete this category',
        onOk: () => {
          this.deletecategory(pId)
        },
        onCancel() {
          console.log("Cancel")
        }
      })
    },
    deletecategory(pId) {
      CategoryService.delete(pId);
      this.getCategories();
    },
    handleSearch(e){
      e.preventDefault();
      this.params.page = 1;
      console.log(this.params);
      this.getCategories();
    },
    resetButton(){
      this.params = {
        categoryId: undefined,
        maxPrice: undefined,
        minPrice: undefined,
        name: undefined,
        page: undefined,
      }
    }
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