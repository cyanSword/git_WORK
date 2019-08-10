<template>
  <div>
    <h3>新建商品房成交统计</h3>
    <a-date-picker @change="onChangeDate" class="w-200" />
    <a-tabs defaultActiveKey="1" @change="callback">
      <a-tab-pane tab="商业" key="商业"></a-tab-pane>
      <a-tab-pane tab="商品住房" key="商品住房"></a-tab-pane>
      <a-tab-pane tab="写字楼" key="写字楼"></a-tab-pane>
      <a-tab-pane tab="其他" key="其他"></a-tab-pane>
      <a-tab-pane tab="合计" key="合计"></a-tab-pane>
    </a-tabs>
    <div v-for="(wy,wi) of data.keys">
      <!-- <h3>{{ wy }}</h3> -->
      <div class="flex row">
        <table class="full-width">
          <thead>
            <tr class="line-bottom fc-gray1">
              <th rowspan="2" class="w-300 text-left">区域名称</th>
              <th colspan="2" class="text-center">{{ data.days[0] || "周一" }}</th>
              <th colspan="2" class="text-center">{{ data.days[1] || "周二" }}</th>
              <th colspan="2" class="text-center">{{ data.days[2] || "周三" }}</th>
              <th colspan="2" class="text-center">{{ data.days[3] || "周四" }}</th>
              <th colspan="2" class="text-center">{{ data.days[4] || "周五" }}</th>
              <th colspan="2" class="text-center">{{ data.days[5] || "周六" }}</th>
              <th colspan="2" class="text-center">{{ data.days[6] || "周日" }}</th>
              <th colspan="2" class="text-center">合计</th>
            </tr>
            <tr class="line-bottom fc-gray1">
              <th class="w-100 text-center">面积</th>
              <th class="w-80 text-center">套数</th>
              <th class="w-100 text-center">面积</th>
              <th class="w-80 text-center">套数</th>
              <th class="w-100 text-center">面积</th>
              <th class="w-80 text-center">套数</th>
              <th class="w-100 text-center">面积</th>
              <th class="w-80 text-center">套数</th>
              <th class="w-100 text-center">面积</th>
              <th class="w-80 text-center">套数</th>
              <th class="w-100 text-center">面积</th>
              <th class="w-80 text-center">套数</th>
              <th class="w-100 text-center">面积</th>
              <th class="w-80 text-center">套数</th>
              <th class="w-120 text-center">面积</th>
              <th class="w-80 text-center">套数</th>
            </tr>
          </thead>
          <tbody>
            <!-- <tr v-for="(areaItem,ai) of wyItem.areaList" class="flex row line-bottom fc-gray1 fs-10">
        <th class="c2 text-left">{{ areaItem.name }}</th>
      <th v-for="(dayItem,di) of areaItem.dayList" class="c1 text-right mr-2 fs-10">
    {{ dayItem.area }}
      </th>
       <th v-for="(dayItem,di) of areaItem.dayList" class="c1 text-right mr-2 fs-10">
    {{ dayItem.area }}
      </th>
            </tr>-->
            <tr v-for="(areaItem,ai) of data[wy]" class="line-bottom">
              <td v-for="(dayItem,ai) of areaItem" class="fc-gray1 fs-10 text-left">{{ dayItem }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import reqwest from "reqwest";

export default {
  name: "XJSPFCJTJ",
  props: {
    msg: String
  },
  mounted() {
    this.fetchTaskList({ at: this.at, city: this.city, wylx: this.wylx });
  },
  data() {
    return {
      data: { keys: [], days: [] },
      wylx: "商业",
      at: "",
      city: "武汉市",
      parentMessage: "Parent",
      items: [{ message: "Foo" }, { message: "Bar" }]
    };
  },
  methods: {
    onChangeDate(date, dateString) {
      console.log(date, dateString);
      this.at = dateString;
      this.fetchTaskList({ at: this.at, city: this.city, wylx: this.wylx });
    },
    callback(key) {
      // console.log(key);
      this.wylx = key;
      this.fetchTaskList({ at: this.at, city: this.city, wylx: this.wylx });
    },
    fetchTaskList(params = {}) {
      console.log("params:", params);
      reqwest({
        url: "https://dev-api.whzhdc.com/sonar.gov/api/xjspfcj/query",
        method: "post",
        data: JSON.stringify({
          //   results: 10,
          ...params
        }),
        crossOrigin: true,
        type: "json",
        contentType: "application/json"
      }).then(data => {
        console.log("Data", data);
        const wyList = data.model.wyList;
        const categoryList = { keys: [], days: [] };
        for (let i = 0; i < wyList.length; i++) {
          const areaItems = [];
          for (let j = 0; j < wyList[i].areaList.length; j++) {
            const dayItems = [];
            const areaItem = wyList[i].areaList[j];
            dayItems.push(areaItem.name);
            for (let k = 0; k < areaItem.dayList.length; k++) {
              if (categoryList.days.length < 7) {
                categoryList.days.push(areaItem.dayList[k].at);
              }
              dayItems.push(areaItem.dayList[k].area);
              dayItems.push(areaItem.dayList[k].count);
            }
            areaItems.push(dayItems);
          }
          categoryList.keys.push(wyList[i].name);
          categoryList[wyList[i].name] = areaItems;
        }

        this.data = categoryList;
      });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 0 0 20px;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
table {
  border: 2px solid #ddd;
  border-collapse: collapse;
}
th {
  font-size: 8px;
  padding: 4px 8px;
  border: 1px solid #dddddd;
  background-color: #eeeeee;
}
td {
  font-size: 13px;
  padding: 4px 8px;
  text-align: right;
  border: 1px solid #dddddd;
}
td:nth-child(1) {
  text-align: left;
}
tr:nth-child(even) {
  background-color: #eeffff;
}
tbody tr:hover {
  background-color: #ffffee;
  color: #000000;
  border: 1px solid red;
}
</style>
