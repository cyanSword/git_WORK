<template>
<el-container style="height: 500px; border: 1px solid #eee">
  <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
    <el-menu :default-openeds="['1', '3']">
      <el-submenu index="1">
        <template slot="title"><div v-on:click="setTile('java')"><i class="el-icon-menu"></i><em>java</em></div></template>
        <el-menu-item-group>
          <template slot="title">分组一</template>
          <el-menu-item index="1-1">选项1</el-menu-item>
          <el-menu-item index="1-2">选项2</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="分组2">
          <el-menu-item index="1-3">选项3</el-menu-item>
        </el-menu-item-group>
        <el-submenu index="1-4">
          <template slot="title">选项4</template>
          <el-menu-item index="1-4-1">选项4-1</el-menu-item>
        </el-submenu>
      </el-submenu>
      <el-submenu index="2">
        <template slot="title"><div v-on:click="setTile('demo')"><i class="el-icon-menu"></i>demo</div></template>
        <el-menu-item-group>
          <template slot="title">分组一</template>
          <el-menu-item index="2-1">选项1</el-menu-item>
          <el-menu-item index="2-2">选项2</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="分组2">
          <el-menu-item index="2-3">选项3</el-menu-item>
        </el-menu-item-group>
        <el-submenu index="2-4">
          <template slot="title">选项4</template>
          <el-menu-item index="2-4-1">选项4-1</el-menu-item>
        </el-submenu>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title"><div v-on:click="setTile('杂记')"><i class="el-icon-menu"></i>杂记</div></template>
        <el-menu-item-group>
          <template slot="title">分组一</template>
          <el-menu-item index="3-1">选项1</el-menu-item>
          <el-menu-item index="3-2">选项2</el-menu-item>
        </el-menu-item-group>
        <el-menu-item-group title="分组2">
          <el-menu-item index="3-3">选项3</el-menu-item>
        </el-menu-item-group>
        <el-submenu index="3-4">
          <template slot="title">选项4</template>
          <el-menu-item index="3-4-1">选项4-1</el-menu-item>
        </el-submenu>
      </el-submenu>
    </el-menu>
  </el-aside>
  
  <el-container>
    <el-header style="text-align: right; font-size: 12px">
      <el-dropdown>
        <i class="el-icon-setting" style="margin-right: 15px"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>查看</el-dropdown-item>
          <el-dropdown-item>新增</el-dropdown-item>
          <el-dropdown-item>删除</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <span>{{ tips }}</span>
    </el-header>
    
    <el-main>
      <el-container>
  <el-header>关于面向对象</el-header>
  <el-main>
    <p v-if="firstarti===null">
    请求失败。。。
    </p>
    <p class="content">
    {{ firstarti }}
	</p>
  </el-main>
</el-container>
    </el-main>
  </el-container>
</el-container>
</template>
<style>
p.content:first-letter{
    font-size:3em;      /*首字大小*/
    float:left;         /*向左浮动，实现首字下沉*/
    padding-left:24px;  /*与左边间距*/
    padding-right:5px;  /*与右边间距*/
    font-weight:bold;
}
.el-main>.el-container>.el-header{

  background-color: #409EFF;
    color: #333;
    line-height: 60px;

}

  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  
  .el-aside {
    color: #333;
  }
.el-submenu__title {
    vertical-align: middle;
    padding: 0px 0px!important;
}
</style>

<script>
  import api from './../fetch/api.js'
  export default {
  methods: { // 这里写所有的方法， 这些方法可以在 方法内部使用this.方法名调用，也可以在html 中使用@事件名 =‘函数名()’调用
    setTile: function (mess) {
      // `event` 是原生 DOM 事件
      if (event) {
        this.tips=mess
      }
    },
    send: function() {
      api.mineBaseMsgApi('/news/index', "")
      .then(res => {
        console.log(res);
        this.firstarti = res.data;
      });
    }
  },
    data() {
      return {
        tips : null,
        firstarti : null
      }
    },
    created() {
    this.send();
  }

  };


</script>