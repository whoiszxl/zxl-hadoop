<template>
  <el-card class="software-container">
    <template #header>
      <div class="header">
        <el-button @click="handleAdd" style="margin-left:3px;" type="danger" size="mini" icon="el-icon-search">新增组件</el-button>
      </div>
    </template>

    <Table
        action='/software'
        ref="table"
    >
      <template #column>
        <el-table-column type="selection" width="50"></el-table-column>

        <el-table-column width="50" prop="id" label="ID"/>
        <el-table-column width="100" prop="softwareName" label="组件名"/>
        <el-table-column width="200" prop="softwareFilename" label="组件文件名"/>
        <el-table-column width="150" prop="softwarePath" label="组件文件路径"/>
        <el-table-column width="150" prop="installPath" label="安装地址"/>
        <el-table-column prop="envPath" label="环境变量文件"/>
        <el-table-column width="100" label="是否安装">
          <template #default="scope">
            {{ scope.row.installStatus == 1 ? '未安装' : '已安装' }}
          </template>
        </el-table-column>


        <el-table-column width="300" label="操作">
          <template #default="scope">
            <span style="margin-left:2px;">
            <el-button @click="handleEdit(scope.row.id)" type="primary" size="small" icon="el-icon-star-off">一键安装</el-button>
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
      router.push({ path: '/software/add'});
    }

    const handleEdit = (id) => {
      router.push({ path: '/software/add', query: { id } })
    }

    const handleDelete = (id) => {

      axios.delete(`/software/` + id, {
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
.software-container {
  min-height: 100%;
}
.el-card.is-always-shadow {
  min-height: 100%!important;
}
</style>