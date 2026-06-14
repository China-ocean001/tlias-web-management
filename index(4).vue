<template>
  <div class="app-container">
    <!-- 查询条件 -->
    <el-form :inline="true" :model="queryForm" class="demo-form-inline">
      <el-form-item label="开始时间">
        <el-date-picker
          v-model="queryForm.begin"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          style="width: 200px"
        />
      </el-form-item>
      <el-form-item label="结束时间">
        <el-date-picker
          v-model="queryForm.end"
          type="datetime"
          placeholder="选择结束时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          style="width: 200px"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleQuery">查询</el-button>
        <el-button @click="handleReset">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 日志列表 -->
    <el-table :data="logList" border stripe style="width: 100%">
      <el-table-column prop="id" label="ID" width="60" />
      <el-table-column prop="operateUser" label="操作人ID" width="90" />
      <el-table-column prop="operateTime" label="操作时间" width="180" />
      <el-table-column prop="className" label="操作类" show-overflow-tooltip />
      <el-table-column prop="methodName" label="操作方法" width="160" />
      <el-table-column prop="methodParams" label="请求参数" show-overflow-tooltip />
      <el-table-column prop="returnValue" label="返回值" show-overflow-tooltip />
      <el-table-column prop="costTime" label="耗时(ms)" width="90" />
    </el-table>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'OperateLog',
  data() {
    return {
      queryForm: {
        begin: '',
        end: ''
      },
      logList: []
    }
  },
  created() {
    this.fetchLogs()
  },
  methods: {
    fetchLogs() {
      const params = {}
      if (this.queryForm.begin) params.begin = this.queryForm.begin
      if (this.queryForm.end) params.end = this.queryForm.end

      axios.get('/logs', {
        params,
        headers: { token: sessionStorage.getItem('token') }
      }).then(res => {
        if (res.data.code === 1) {
          this.logList = res.data.data
        } else {
          this.$message.error('查询失败：' + res.data.msg)
        }
      }).catch(() => {
        this.$message.error('网络请求失败')
      })
    },
    handleQuery() {
      this.fetchLogs()
    },
    handleReset() {
      this.queryForm.begin = ''
      this.queryForm.end = ''
      this.fetchLogs()
    }
  }
}
</script>

<style scoped>
.app-container {
  padding: 20px;
}
</style>
