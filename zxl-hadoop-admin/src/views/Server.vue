<template>
  <el-card class="server-container">
    <template #header>
      <div class="header">
        <el-button @click="handleAdd" style="margin-left:3px;" type="danger" size="mini" icon="el-icon-search">新增服务器</el-button>
      </div>
    </template>

    <Table
        action='/server'
        ref="table"
    >
      <template #column>
        <el-table-column type="selection" width="50"></el-table-column>

        <el-table-column width="50" prop="id" label="ID"/>
        <el-table-column width="100" prop="serverName" label="服务器名"/>
        <el-table-column width="200" prop="serverOuterIp" label="外网地址"/>
        <el-table-column width="150" prop="serverInnerIp" label="内网地址"/>
        <el-table-column width="150" prop="serverPort" label="连接端口"/>
        <el-table-column width="150" prop="serverUsername" label="用户名"/>
        <el-table-column width="200" prop="serverPassword" label="密码"/>
        <el-table-column width="200" prop="updatedAt" label="更新时间"/>

        <el-table-column label="操作">
          <template #default="scope">
            <span style="margin-left:2px;">
            <el-button @click="handleEdit(scope.row.id)" type="primary" size="small" icon="el-icon-star-on">编辑</el-button>
            <el-button @click="handleDelete(scope.row.id)" type="primary" size="small" icon="el-icon-delete">删除</el-button>
            </span>
          </template>
        </el-table-column>
      </template>
    </Table>
  </el-card>
</template>

<script>
import {ref} from 'vue'
import Table from '@/components/Table.vue'
import {ElMessage} from 'element-plus'
import axios from '@/utils/axios'
import {useRouter} from 'vue-router'

export default {
  name: 'Software',
  components: {
    Table
  },
  setup() {
    const table = ref(null)
    const router = useRouter()

    const search = () => {
      table.value.getList();
    }

    const handleAdd = () => {
      router.push({ path: '/server/add'});
    }

    const handleEdit = (id) => {
      router.push({ path: '/server/add', query: { id } })
    }

    const handleDelete = (id) => {

      axios.delete(`/server/` + id, {
      }).then(() => {
        ElMessage.success('删除成功')
        table.value.getList()
      })

    }

    return {
      search,
      handleAdd,
      handleEdit,
      handleDelete,
      table

    }
  }
}
</script>

<style scoped>
.server-container {
  min-height: 100%;
}
.el-card.is-always-shadow {
  min-height: 100%!important;
}
</style>