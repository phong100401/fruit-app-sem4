<template>
  <section id="dashboard">
    <mdb-card class="mb-4">
      <mdb-card-body class="d-sm-flex justify-content-between">
        <h4 class="mb-sm-0 pt-2">
          <a href="https://mdbootstrap.com/material-design-for-bootstrap/" target="_blank">Home Page</a><span>/</span><span>Dashboard</span>
        </h4>
      </mdb-card-body>
    </mdb-card>
    <section>
      <mdb-row class="mt-5">
        <mdb-col md="9" class="mb-4">
          <mdb-card>
            <mdb-card-body>
              <div style="display: block">
                <mdb-bar-chart :data="barChartData" :options="barChartOptions" :height="500"/>
              </div>
            </mdb-card-body>
          </mdb-card>
        </mdb-col>
        <mdb-col md="3" class="mb-4">
          <mdb-card class="mb-4">
            <mdb-card-header class="text-center"> Pie chart </mdb-card-header>
            <mdb-card-body>
              <div style="display: block">
                <mdb-pie-chart :data="pieChartData" :options="pieChartOptions" :height="500"/>
              </div>
            </mdb-card-body>
          </mdb-card>
        </mdb-col>
      </mdb-row>
    </section>
    <section>
      <mdb-row class="mt-3">
        <mdb-col md="12" lg="12" class="mb-4">
          <mdb-card class="mb-4">
            <mdb-card-header> Line chart </mdb-card-header>
            <mdb-card-body>
              <div style="display: block">
                <mdb-line-chart :data="lineChartData" :options="lineChartOptions" :height="200"/>
              </div>
            </mdb-card-body>
          </mdb-card>
        </mdb-col>
      </mdb-row>
    </section>
  </section>
</template>

<script>
import {mdbBarChart, mdbCard, mdbCardBody, mdbCardHeader, mdbCol, mdbLineChart, mdbPieChart, mdbRow,} from 'mdbvue'
import ProductService from "../../../service/ProductService";


export default {
  name: 'Dashboard',
  components: {
    mdbRow,
    mdbCol,
    mdbCard,
    mdbCardBody,
    mdbCardHeader,
/*    mdbIcon,*/
    mdbBarChart,
    mdbPieChart,
    mdbLineChart,
 /*   mdbRadarChart,
    mdbDoughnutChart,*/
/*    mdbListGroup,
    mdbListGroupItem,
    mdbBadge,*/
  },
  data () {
    return {
      pieChartData: {},
      lineChartData:{},
      barChartData:{},
      datachart:[],
      datachart1:[],
      top1:undefined,
      top2:undefined,
      top3:undefined,
      top4:undefined,
      top5:undefined,
      showFrameModalTop: false,
      showFrameModalBottom: false,
      showSideModalTopRight: false,
      showSideModalTopLeft: false,
      showSideModalBottomRight: false,
      showSideModalBottomLeft: false,
      showCentralModalSmall: false,
      showCentralModalMedium: false,
      showCentralModalLarge: false,
      showCentralModalFluid: false,
      showFluidModalRight: false,
      showFluidModalLeft: false,
      showFluidModalTop: false,
      showFluidModalBottom: false,
      barChartOptions: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          xAxes: [{
            barPercentage: 1,
            gridLines: {
              display: true,
              color: 'rgba(0, 0, 0, 0.1)'
            }
          }],
          yAxes: [{
            gridLines: {
              display: true,
              color: 'rgba(0, 0, 0, 0.1)'
            },
            ticks: {
              beginAtZero: true
            }
          }]
        }
      },
      pieChartOptions: {
        responsive: true,
        maintainAspectRatio: false
      },
      lineChartOptions: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          xAxes: [{
            gridLines: {
              display: true,
              color: 'rgba(0, 0, 0, 0.1)'
            }
          }],
          yAxes: [{
            gridLines: {
              display: true,
              color: 'rgba(0, 0, 0, 0.1)'
            }
          }]
        }
      }

    }
  },
  // created() {
  //   this.getChart()
  // },
  created() {
    this.getChart()
  },
  methods: {
    async getChart() {
      await ProductService.getchart().then(
          rs => {
            try {
              this.datachart = rs.data;
              console.log(this.datachart)
            } catch (e) {
              console.log(e);
            }
            let i;
            let labelArr = [];
            let dataArr = [];
            for (i = 0; i < 5; i++) {
              labelArr.push(this.datachart[i].name)
              dataArr.push(this.datachart[i].quantity)
              // console.log(this.datachart[i])
              // this.datachart1[i] = this.datachart[i]
              //     this.pieChartData.labels[i] = this.datachart[i].name
            }
            labelArr.push("Còn lại")
            dataArr.push(1)
            this.createPieChart(labelArr, dataArr)
            console.log(this.pieChartData.labels);
          }
      )
      await ProductService.getlinechart().then(
          rs => {
            try {
              this.datachart1 = rs.data;
              console.log(this.datachart1)
            } catch (e) {
              console.log(e);
            }
            let i;
            let labelArr = [];
            let dataArr1 = [];
            for (i = 0; i < this.datachart1.length; i++) {
              labelArr.push(this.datachart1[i].month)
              dataArr1.push(this.datachart1[i].total_price)
            }
            this.createLineChart(dataArr1)
            this.createBarchar(dataArr1)
          }
      )
    },
    createPieChart(label, data) {
      this.pieChartData = {
        labels: label,
        datasets: [
          {
            data: data,
            backgroundColor: ['#F7464A', '#46BFBD', '#FDB45C', '#949FB1', '#4D5360', '#ac64ad'],
            hoverBackgroundColor: ['#FF5A5E', '#5AD3D1', '#FFC870', '#A8B3C5', '#616774', '#da92db']
          }
        ]
      };
    },
    createLineChart(data) {
      this.lineChartData = {
        labels: ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"],
        datasets: [
          {
            label: '2022',
            backgroundColor: 'rgba(245, 74, 85, 0.5)',
            data: data
          },
          {
            label: '2021',
            backgroundColor: 'rgba(90, 173, 246, 0.5)',
            data: [120000, 4200000, 1210000, 5600000, 2400000, 120000, 20000, 300000, 400000, 500000, 100000, 200000]
          },
          {
            label: '2020',
            backgroundColor: 'rgba(245, 192, 50, 0.5)',
            data: [200000, 1230000, 154000, 7600000, 540000, 230000, 500000, 300000, 400000, 500000, 60000, 400000]
          }
        ]
      }
    },
    createBarchar( data) {
      this.barChartData = {
        labels: ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"],
        datasets: [
          {
            label: '2022',
            data: data,
            backgroundColor: 'rgba(245, 74, 85, 0.5)',
            borderWidth: 1
          }, {
            label: '2021',
            data: [560000, 240000, 500000, 160000, 450000, 240000, 800000,300000, 400000, 500000, 100000, 200000],
            backgroundColor: 'rgba(90, 173, 246, 0.5)',
            borderWidth: 1
          }, {
            label: '2020',
            data: [120000, 250000, 540000, 300000, 150000, 440000, 3000000,300000,400000,600000,900000,1000000],
            backgroundColor: 'rgba(245, 192, 50, 0.5)',
            borderWidth: 1
          }
        ]
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.cascading-admin-card {
  margin: 20px 0;
}
.cascading-admin-card .admin-up {
  margin-left: 4%;
  margin-right: 4%;
  margin-top: -20px;
}
.cascading-admin-card .admin-up .fas,
.cascading-admin-card .admin-up .far {
  box-shadow: 0 2px 9px 0 rgba(0, 0, 0, 0.2), 0 2px 13px 0 rgba(0, 0, 0, 0.19);
  padding: 1.7rem;
  font-size: 2rem;
  color: #fff;
  text-align: left;
  margin-right: 1rem;
  border-radius: 3px;
}
.cascading-admin-card .admin-up .data {
  float: right;
  margin-top: 2rem;
  text-align: right;
}
.admin-up .data p {
  color: #999999;
  font-size: 12px;
}
.classic-admin-card .card-body {
  color: #fff;
  margin-bottom: 0;
  padding: 0.9rem;
}
.classic-admin-card .card-body p {
  font-size: 13px;
  opacity: 0.7;
  margin-bottom: 0;
}
.classic-admin-card .card-body h4 {
  margin-top: 10px;
}
</style>
