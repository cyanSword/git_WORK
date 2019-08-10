<template>
  <div>

    <a-modal
      v-model="visitVisible"
      title="到访同步"
      width=1200px
       @ok="handleVisitOk"
    > 
    <div class="flex row">
      <div class="flex col">
        <div class="flex row grid-cc m-20">
          <div class="w-96">companyId</div>
          <div class="ml-20 flex row">
            <a-input class="w-144" v-model="visitCompanyId" />
          </div>
          <div class="ml-64 w-96">companyName</div>
          <div class="ml-20 w-144">
            <a-input v-model="visitCompanyName"/>
          </div>
        </div>
        <div class="flex row grid-cc m-20">
          <div class="w-96">channelId</div>
          <div class="ml-20 w-144">
            <a-input v-model="visitChannelId"/>
          </div>
          <div class="ml-64 w-96">channelName</div>
          <div class="ml-20 w-144">
            <a-input v-model="visitChannelName"/>
          </div>
        </div>
      </div>
      <a-button type="default" class="fc-blue grid-cr mt-20 mr-20" @click="()=>updateReco(2)">修改</a-button>
    </div>
    
    <div>
       <span style="margin-left: 8px">
        <template v-if="hasSelected">
          {{`Selected ${selectedRowKeys.length} items`}}
        </template>
      </span>
      <a-table
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        :columns="visitList.columns"
        :rowKey="record => record.id"
        :dataSource="visitList.data"
        :loading="visitList.loading"
        :pagination="visitList.pagination"
        @change="handleTableChange"
        >
      </a-table>
    </div>
      
    </a-modal>

    <a-modal
      v-model="recoVisible"
      title="报备同步"
      width=1200px
       @ok="handleRecoOk"
    > 
    <div class="flex row">
      <div class="flex col">
        <div class="flex row grid-cc m-20">
          <div class="w-96">companyId</div>
          <div class="ml-20 flex row">
            <a-input class="w-144" v-model="recoCompanyId" />
          </div>
          <div class="ml-64 w-96">companyName</div>
          <div class="ml-20 w-144">
            <a-input v-model="recoCompanyName"/>
          </div>
        </div>
        <div class="flex row grid-cc m-20">
          <div class="w-96">channelId</div>
          <div class="ml-20 w-144">
            <a-input v-model="recoChannelId"/>
          </div>
          <div class="ml-64 w-96">channelName</div>
          <div class="ml-20 w-144">
            <a-input v-model="recoChannelName"/>
          </div>
        </div>
      </div>
      <a-button type="default" class="fc-blue grid-cr mt-20 mr-20" @click="()=>updateReco(1)">修改</a-button>
    </div>
    
    <div>
       <span style="margin-left: 8px">
        <template v-if="hasSelected">
          {{`Selected ${selectedRowKeys.length} items`}}
        </template>
      </span>
      <a-table
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        :columns="recoList.columns"
        :rowKey="record => record.id"
        :dataSource="recoList.data"
        :loading="recoList.loading"
        :pagination="recoList.pagination"
        @change="handleTableChange"
        >
      </a-table>
    </div>
      
    </a-modal>
    <a-modal
      v-model="brokVisible"
      title="小蓝绑定"
       @ok="handleOk"
      okText="绑定"
    >
      <p>公司名称 ：{{selectCompany.name}}</p>
      <p>工商代码 ：{{selectCompany.creditCode}}</p>
      <p>公司地址 ：{{selectCompany.regLocation}}</p>
    </a-modal>
    <a-drawer
      title="小蓝公司列表"
      placement="right"
      width=550
      :closable="false"
      @close="onClose"
      :visible="brokDrawerVisible"
    > 
      <div class="flex row">
<a-input-search
        size="large"
        placeholder="渠道名称关键词"
        style="margin-bottom: 10px"
        :value="outCompanyList.keyword"
        @change="onChangeSearchOutCompanyList"
        @search="fetchBrokListByName"
      />
      <div class="flex row">
        <div class="w-64 ">
          <a-popconfirm title="确认未注册渠道?" @confirm="changeTaskState(2)" okText="Yes" cancelText="No">
            <a href="#" class="ml-8" style="color:red;text-align: center;line-height:36px">未注册</a>
          </a-popconfirm>
          
        </div>
          <!-- <a-button
            type="danger"
            class="mt-4"
            @click="()=>mergeBrok()"
            >合并</a-button> -->
      </div>

      </div>
      
      <a-table
        :columns="brokCompanyList.columns"
        :rowKey="record => record.id"
        :dataSource="brokCompanyList.data"
        :loading="brokCompanyList.loading"
        :pagination="brokCompanyList.pagination"
        @change="handleTableChange"
        >
             <template slot="action" slot-scope="record">
                  <div class="flex row">
                    <a-button type="default" class="fc-blue" @click="()=>bindingBrokCompany(record)">绑定</a-button>
                    <a-popconfirm class="w-64" title="确认设为主店合并?" @confirm="mergeBrokCompany(record)" okText="Yes" cancelText="No">
                    <a href="#" class="ml-4" style="color:red;text-align: center;line-height:36px">合并</a>
                  </a-popconfirm>
                  </div>
                </template>
      </a-table>
    </a-drawer>

    <a-modal
      v-model="modalVisible"
      title="详细信息"
      @ok="handleOk"
      okText="绑定"
    >
      <p>公司名称 ：{{selectCompany.name}}</p>
      <p>工商代码 ：{{selectCompany.creditCode}}</p>
      <p>公司地址 ：{{selectCompany.regLocation}}</p>
    </a-modal>
    <h1>任务列表</h1> 
    <a-drawer
      title="公司列表"
      placement="right"
      width=550
      :closable="false"
      @close="onClose"
      :visible="outCompanyVisible"
    > 
      <div class="flex row">
<a-input-search
        size="large"
        placeholder="渠道名称关键词"
        style="margin-bottom: 10px"
        :value="outCompanyList.keyword"
        @change="onChangeSearchOutCompanyList"
        @search="onSearchOutCompanyList"
      />
      <div class="w-64">
        <a-popconfirm title="确认无关联渠道?" @confirm="changeTaskState(1)" okText="Yes" cancelText="No">
          <a href="#" class="ml-4" style="color:red;text-align: center;line-height:36px">无关联</a>
        </a-popconfirm>
      </div>

      </div>
      
      <a-table
        :columns="selectCompanyList.columns"
        :rowKey="record => record.id"
        :dataSource="selectCompanyList.data"
        :loading="selectCompanyList.loading"
        :pagination="selectCompanyList.pagination"
        @change="handleTableChange"
        :customRow="record => {
                  return {
                      on: { 
                        click: () => showModal(record.name),  
                      },
                  };
                }"
        >
             <!-- <template slot="name" slot-scope="name">
                <div @click="()=>showModal(name)">{{name }}</div>
              </template> -->
      </a-table>
    </a-drawer>

    <div id="components-layout-demo-basic">
      <a-layout>
        <a-layout-sider>
          <div class="col">
            <a-input-search
              size="large"
              placeholder="任务关键词"
              style="width: 200px; margin-bottom: 10px"
              @search="onSearch"
            />
            <a-table
              :columns="taskList.columns"
              :rowKey="record => record.id"
              :dataSource="taskList.data"
              :loading="taskList.loading"
              :pagination="taskList.pagination"
              @change="handleTableChange"
              :customRow="record => {
                  return {
                      on: { 
                        click: () => queryListByName(record.name),  
                      },
                  };
                }"
            >
              <!-- <template slot="name" slot-scope="name">
                <div @click="()=>queryListByName(name)">{{name }}</div>
              </template> -->
              <template slot="status" slot-scope="status">
                <div v-if="status==0" style="color:gray">{{"未开始"}}</div>
                <div v-else-if="status==1" style="color:green">{{"已完成"}}</div>
                <div v-else style="color:red">{{"有问题"}}</div>
              </template>
            </a-table>
          </div>
        </a-layout-sider>
        <a-layout>
          <!-- <a-layout-header>Header</a-layout-header> -->
          <a-layout-content>
            <div class="col ml-40">
              <div class="flex row">
                <a-input-search
                size="large"
                placeholder="渠道名称关键词"
                style="width: 400px; margin-bottom: 10px"
                :value="companyList.keyword"
                @change="onChangeSearchCompany"
                @search="onSearchCompany"
                /> 
                <a-tag v-if="this.task.task1==2" class="ml-10" style="height:40px;text-align: center;line-height:36px" color="red">{{"小绿未建党"}}</a-tag>
                <a-tag v-if="this.task.task2==1" class="ml-10" style="height:40px;text-align: center;line-height:36px" color="green">{{"工商已录入"}}</a-tag>
                <a-tag v-if="this.task.task2==2" class="ml-10" style="height:40px;text-align: center;line-height:36px" color="red">{{"工商异常"}}</a-tag>
                <a-tag v-if="this.task.task4==1" class="ml-10" style="height:40px;text-align: center;line-height:36px" color="blue">{{"小蓝已绑定"}}</a-tag>
                <a-tag v-if="this.task.task4==2" class="ml-10" style="height:40px;text-align: center;line-height:36px" color="red">{{"小蓝未注册"}}</a-tag>
                <a-tag v-if="this.task.task6==1" class="ml-10" style="height:40px;text-align: center;line-height:36px" color="blue">{{"报备已同步"}}</a-tag>
                <a-tag v-if="this.task.task8==1" class="ml-10" style="height:40px;text-align: center;line-height:36px" color="blue">{{"到访已同步"}}</a-tag>
              </div>
              
              <a-table
                :columns="companyList.columns"
                :rowKey="record => record.id"
                :dataSource="companyList.data"
                :loading="companyList.loading"
                :pagination="companyList.pagination"
                @change="handleTableChange"
                
              >
                <template slot="action" slot-scope="record">
                  <div>
                    <a-button type="default" class="fc-green" @click="()=>queryOutListByName(record)">小绿</a-button>
                    <a-button type="default" class="ml-4 fc-primary" @click="()=>queryBrokListByName(record)">小蓝</a-button>
                    <!-- <a-button type="default" class="ml-4" @click="()=>queryChipListByName(record)">认筹</a-button> -->
                    <a-button type="default" class="ml-4" @click="()=>queryRecoListByName(record)">报备</a-button>
                    <a-button type="default" class="ml-4" @click="()=>queryVisitListByName(record)">到访</a-button>
                    <!-- <a-button
                      type="danger"
                      class="ml-4"
                      @click="()=>queryPurchaseListByName(name)"
                    >认购</a-button> -->
                  </div>
                </template>
                <template slot="companyType" slot-scope="companyType">
                  <div v-if="companyType==0" style="color:blue">{{"分销"}}</div>
                  <div v-else-if="companyType==1" style="color:red">{{"分店"}}</div>
                  <div v-else style="color:green">{{"加盟"}}</div>
                </template>
              </a-table>
            </div>
          </a-layout-content>
          <!-- <a-layout-footer>Footer</a-layout-footer> -->
        </a-layout>
      </a-layout>
    </div>
  </div>
</template>


<script>
import reqwest from "reqwest";
// const taskStates = {'0': '未开始', '1': '已完成', '2': '有问题'};
// const statusTaskMap = {'0': 'default', '1': 'success', '2': 'error'}
const taskColumns = [
  {
    title: "任务名称",
    dataIndex: "name",
    width: "20%",
    scopedSlots: { customRender: "name" }
  },

  {
    title: "状态",
    dataIndex: "status",
    width: "10%",
    scopedSlots: { customRender: "status" }
  }
];

const selectCompanyColumns = [
  {
    title: "名称",
    dataIndex: "name",
    width: "100%",
    scopedSlots: { customRender: "name" }
  }
];

const brokCompanyColumns = [
  {
    title: "名称",
    dataIndex: "name",
    width: "80%",
    scopedSlots: { customRender: "name" }
  },
  {
    title: "业务操作",
    key: "action",
    align: "center",
    scopedSlots: { customRender: "action" },
    width: "20%"
  }
];

const companyColumns = [
  {
    title: "企业名称",
    dataIndex: "companyName",
    sorter: true,
    width: "30%",
    scopedSlots: { customRender: "companyName" }
  },
  {
    title: "企业类型",
    dataIndex: "companyType",
    width: "10%",
    scopedSlots: { customRender: "companyType" }
  },
  {
    title: "企业简称",
    dataIndex: "shortName",
    width: "10%"
  },
  {
    title: "业务操作",
    key: "action",
    align: "center",
    scopedSlots: { customRender: "action" },
    width: "40%"
  },
];
const recoColumns = [
  {
    title: "companyId",
    dataIndex: "companyId",
    sorter: true,
    width: "20%",
    scopedSlots: { customRender: "companyId" }
  },
  {
    title: "companyName",
    dataIndex: "companyName",
    width: "30%",
    scopedSlots: { customRender: "companyName" }
  },
  {
    title: "channelId",
    dataIndex: "channelId",
    width: "20%"
  },
  {
    title: "channelName",
    dataIndex: "channelName",
    scopedSlots: { customRender: "action" },
    width: "30%"
  },
];

export default {
  name: "PipeTaskList",
  props: {},
  mounted() {
    this.fetchTaskList({ keyword: "" });
  },
  data() {
    return {
      selectedRowKeys: [],
      outCompanyVisible:false,
      loading: false,
      modalVisible:false,
      brokVisible:false,
      recoVisible:false,
      visitVisible:false,
      brokDrawerVisible:false,
      taskName:"",
      task:[],
      taskList: {
        data: [],
        pagination: { simple: true },
        loading: false,
        columns: taskColumns,
        keyword: ""
      },
      companyList: {
        data: [],
        pagination: { simple: true },
        loading: false,
        columns: companyColumns,
        keyword: ""
      },
      outCompanyList: {
        data: [],
        loading: false,
        keyword: "",
        companyName:"",
        id:0,
      },
      selectCompanyList: {
        data: [],
        loading: false,
        keyword: "",
        columns: selectCompanyColumns,
      },
      brokCompanyList: {
        data: [],
        loading: false,
        keyword: "",
        columns: brokCompanyColumns,
      },
      recoList: {
        id:0,
        name:"",
        data: [],
        loading: false,
        keyword: "",
        columns: recoColumns,
      },
      visitList: {
        id:0,
        name:"",
        data: [],
        loading: false,
        keyword: "",
        columns: recoColumns,
      },
      recoCompanyId:0,
      recoCompanyName:"",
      recoChannelId:0,
      recoChannelName:"",
      visitCompanyId:0,
      visitCompanyName:"",
      visitChannelId:0,
      visitChannelName:"",
      selectCompany:{},
      visible: false
      // taskStates,
      // statusTaskMap
    };
  },
  methods: {
    hasSelected() {
      return this.selectedRowKeys.length > 0
    },

    showModal(name) {
      this.selectCompanyNo({ name: name });
      this.modalVisible = true;
    },
    handleOk(e) {
      this.creditCodeBinding(1);
      this.modalVisible = false;
      this.onClose();
    },
    handleRecoOk(e){
      this.selectedRowKeys=[]
      this.creditCodeBinding(2);
      this.recoVisible = false;
    },
    handleVisitOk(e){
      this.selectedRowKeys=[]
      this.creditCodeBinding(3);
      this.visitVisible=false
    },
    handleCancel(e) {
      this.modalVisible = false;
    },

    showDrawer() {
      this.outCompanyVisible = true;
    },
    onClose() {
      this.outCompanyVisible = false;
      this.brokDrawerVisible = false;
    },

    onSelectChange (selectedRowKeys) {
      console.log('selectedRowKeys changed: ', selectedRowKeys);
      this.selectedRowKeys = selectedRowKeys
    },

    queryVisitListByName(record){
      this.visitVisible = true;
      this.visitCompanyId=record.id
      this.visitCompanyName=this.companyList.keyword
      this.visitChannelId=record.id
      this.visitChannelName=this.companyList.keyword
      this.visitList.id=record.id
      this.visitList.name=this.companyList.keyword
      this.loadRecoList(2)
    },

    queryRecoListByName(record){
      this.recoVisible = true;
      this.recoCompanyId=record.id
      this.recoCompanyName=this.companyList.keyword
      this.recoChannelId=record.id
      this.recoChannelName=this.companyList.keyword
      this.recoList.id=record.id
      this.recoList.name=this.companyList.keyword
      this.loadRecoList(1)
    },

    onSearch(value) {
      this.taskList.keyword = value;
      this.fetchTaskList({ keyword: value });
    },
    onChangeSearchCompany(e) {
      const { value } = e.target;
      console.log(e);
      this.companyList.keyword = value;
    },
    onSearchCompany(value) {
      console.log(value);
      this.companyList.keyword = value;
      this.fetchCompanyList({ companyName: value });
      // this.taskKeyword = value;
      // this.fetch({ keyword: value });
    },

    onChangeSearchOutCompanyList(e) {
      const { value } = e.target;
      console.log(e);
      this.outCompanyList.keyword = value;
    },
    //搜索第三方公司
    onSearchOutCompanyList(value) {
      console.log(value);
      this.outCompanyList.keyword = value;
      this.fetchOutCompanyList({ companyName: value });
      // this.taskKeyword = value;
      // this.fetch({ keyword: value });
    },

    handleTableChange(filters, sorter) {
      // console.log(pagination);
      // const pager = { ...this.pagination };
      // pager.current = pagination.current;
      // this.pagination = pager;
      // this.fetch({
      //   results: pagination.pageSize,
      //   page: pagination.current,
      //   sortField: sorter.field,
      //   sortOrder: sorter.order,
      //   ...filters
      // });
    },
    updateReco(type){
      let companyId=0
      let companyName=""
      let channelId=0
      let channelName=""
      if(type==1){
        companyId=this.recoCompanyId
        companyName=this.recoCompanyName
        channelId=this.recoChannelId
        channelName=this.recoChannelName
      }else{
        companyId=this.visitCompanyId
        companyName=this.visitCompanyName
        channelId=this.visitChannelId
        channelName=this.visitChannelName
      }
      reqwest({
        url: "https://dev-api.whzhdc.com/pipe/api/reco_update",
        method: "post",
        data: JSON.stringify({
          //   results: 10,
          type,
          ids:this.selectedRowKeys,
          companyId,
          companyName,
          channelId,
          channelName
        }),
        headers: {
        'devcode': 'ZHJM'
        },
        crossOrigin: true,
        type: "json",
        contentType: "application/json"
      }).then(data => {
        this.selectedRowKeys=[]
        this.loadRecoList(type);
      });
    },

    loadRecoList(type){
      let id = 0
      let name = ""
      if(type==1){
        id = this.recoList.id
        name = this.recoList.name
      }
      if(type==2){
        id = this.visitList.id
        name = this.visitList.name
      }
      reqwest({
        url: "https://dev-api.whzhdc.com/pipe/api/reco_list",
        method: "post",
        data: JSON.stringify({
          //   results: 10,
          id:id,
          name:name,
          type:type
        }),
        headers: {
        'devcode': 'ZHJM'
        },
        crossOrigin: true,
        type: "json",
        contentType: "application/json"
      }).then(data => {
        if(type==1){
          this.recoList.data=data.model.companyList;
        }else{
          console.log(111,data.model.companyList)
          this.visitList.data=data.model.companyList;
          console.log(222,this.visitList.data)
        }
      });
    },
    mergeBrokCompany(record){
      let companyId=record.id
      const name = this.outCompanyList.keyword;
      reqwest({
        url: "https://dev-api.whzhdc.com/pipe/api/brok_company_merge",
        method: "post",
        data: JSON.stringify({
          //   results: 10,
          companyId,name
        }),
        headers: {
        'devcode': 'ZHJM'
        },
        crossOrigin: true,
        type: "json",
        contentType: "application/json"
      }).then(data => {
        console.log(123)
        this.fetchBrokListByName();
      });
    },

    bindingBrokCompany(record){
      reqwest({
        url: "https://dev-api.whzhdc.com/pipe/api/brok_company_binding",
        method: "post",
        data: JSON.stringify({
          //   results: 10,
          companyId:this.outCompanyList.id,
          brokId:record.id,
          taskId:this.task.id
        }),
        headers: {
        'devcode': 'ZHJM'
        },
        crossOrigin: true,
        type: "json",
        contentType: "application/json"
      }).then(data => {
        this.onClose()
        this.fetchTaskList({ keyword: "" });
      });
    },
    creditCodeBinding(type){
      const company=this.selectCompany
      console.log("this.task.id",this.task.id)
      reqwest({
        url: "https://dev-api.whzhdc.com/pipe/api/pipe_task_binding",
        method: "post",
        data: JSON.stringify({
          //   results: 10,
          ...company,
          companyId:this.outCompanyList.id,
          taskId:this.task.id,
          type:type
        }),
        headers: {
        'devcode': 'ZHJM'
        },
        crossOrigin: true,
        type: "json",
        contentType: "application/json"
      }).then(data => {
        this.onClose()
        this.fetchTaskList({ keyword: "" });
      });
    },
    changeTaskState(type){
      const taskId=this.task.id
       reqwest({
        url: "https://dev-api.whzhdc.com/pipe/api/pipe_task_update",
        method: "post",
        data: JSON.stringify({
          //   results: 10,
          "taskId":taskId,
          "type":type
        }),
        headers: {
        'devcode': 'ZHJM'
        },
        crossOrigin: true,
        type: "json",
        contentType: "application/json"
      }).then(data => {
        this.onClose()
        this.fetchTaskList({ keyword: "" });
      });
    },
    fetchTaskList(params = {}) {
      console.log("params:", params);
      this.taskList.loading = true;
      reqwest({
        url: "https://dev-api.whzhdc.com/pipe/api/pipe_task/list",
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
        const pagination = { ...this.taskList.pagination };
        // Read total count from server
        pagination.total = data.model.pipeTaskList.length;
        this.taskList.loading = false;
        this.taskList.data = data.model.pipeTaskList;
        this.taskList.pagination = pagination;
      });
    },
    // 搜索文件
    fetchCompanyList(params = {}) {
      console.log("params:", params);
      this.companyList.loading = true;
      reqwest({
        url: "https://dev-api.whzhdc.com/pipe/api/qdbzh/query",
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
        const pagination = { ...this.companyList.pagination };
        // Read total count from server
        pagination.total = data.model.companyList.length;
        this.companyList.loading = false;
        this.companyList.data = data.model.companyList;
        this.companyList.pagination = pagination;
      });
    },

    //搜索第三方接口
    fetchOutCompanyList(params = {}) {
      console.log("params:", params);
      this.selectCompanyList.loading = true;
      reqwest({
        url: "https://dev-api.whzhdc.com/pipe/api/tyc_demo",
        method: "post",
        data: JSON.stringify({
          //   results: 10,
          ...params
        }),
        crossOrigin: true,
        headers: {
        'devcode': 'ZHJM'
        },
        type: "json",
        contentType: "application/json"
      }).then(data => {
        console.log("Data", data);
        this.selectCompanyList.loading = false;
        if(data.model.companyList){
          this.selectCompanyList.data = data.model.companyList;
        }
      });
      console.log("params:222", params);
    },

    selectCompanyNo(params = {}) {
      console.log("params:", params);
      //this.selectCompany.loading = true;
      reqwest({
        url: "https://dev-api.whzhdc.com/pipe/api/tyc_company",
        method: "post",
        data: JSON.stringify({
          //   results: 10,
          ...params
        }),
        crossOrigin: true,
        headers: {
        'devcode': 'ZHJM'
        },
        type: "json",
        contentType: "application/json"
      }).then(data => {
        console.log("Data111", data);
        this.selectCompany = data.model.company;
        //this.selectCompany.loading = false;
      });
      console.log("params:222", params);
    },

    //搜索小蓝列表
    fetchBrokListByName() {
      const name = this.outCompanyList.keyword;
      reqwest({
        url: "https://dev-api.whzhdc.com/pipe/api/brok_company_query",
        method: "post",
        data: JSON.stringify({
          //   results: 10,
          name:name
        }),
        crossOrigin: true,
        headers: {
        'devcode': 'ZHJM'
        },
        type: "json",
        contentType: "application/json"
      }).then(data => {
        this.brokCompanyList.data = data.model.companyList;
      });
    },

    //查找第三方接口
    queryOutListByName(record) {
      this.showDrawer();
      this.outCompanyList.keyword = this.companyList.keyword;
      this.outCompanyList.companyName = record.companyName 
      this.outCompanyList.id = record.id 
      this.fetchOutCompanyList({ companyName: this.companyList.keyword  });
    },

    //根据渠道名称关键词搜索文件
    queryListByName(company) {
      console.log("click", company);
      this.taskName=company
      const task=this.taskList.data.filter(m=>m.name==company)[0]
      this.task=task
      this.companyList.keyword = company;
      this.fetchCompanyList({ companyName: company });
    },

    //点击小蓝按钮
    queryBrokListByName(record) {
      console.log("click:", record);
      this.brokDrawerVisible=true
      this.outCompanyList.keyword = this.companyList.keyword;
      this.outCompanyList.id = record.id 
      this.fetchBrokListByName();
    }
  }
};
</script>  


<style scoped>
h3 {
  margin: 40px 20px;
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

.ml-40 {
  margin-left: 40px;
}

.col {
  display: -webkit-flex; /* Safari */
  display: flex;
  flex-direction: column;
}

#components-layout-demo-basic {
  text-align: center;
}
#components-layout-demo-basic .ant-layout-header,
#components-layout-demo-basic .ant-layout-footer {
  background: #7dbcea;
  color: #fff;
}
#components-layout-demo-basic .ant-layout-footer {
  line-height: 1.5;
}
#components-layout-demo-basic .ant-layout-sider {
  background: #ffffff;
  color: #fff;
  line-height: 120px;
}
#components-layout-demo-basic .ant-layout-content {
  background: #ffffff;
  color: #000000;
  min-height: 120px;
  /* line-height: 120px; */
}
#components-layout-demo-basic > .ant-layout {
  margin-bottom: 48px;
}
#components-layout-demo-basic > .ant-layout:last-child {
  margin: 0;
}
.ant-table-pagination.ant-pagination {
  float: none;
}
</style>

