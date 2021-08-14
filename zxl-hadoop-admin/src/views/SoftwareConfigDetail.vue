<template>
  <el-card class="server-container">
  <template #header>
    <div class="header">
      <el-button @click="back" style="margin-left:3px;" type="danger" size="mini" icon="el-icon-search">返回上一页</el-button>
    </div>
  </template>

  <div class="add">
    <el-card class="add-container">
      <el-form :model="serverForm" :rules="rules" ref="serverRef" label-width="100px" class="serverForm">
        <el-form-item label="组件ID">
          <el-input style="width: 300px" v-model="serverForm.softwareId"/>
        </el-form-item>
        <el-form-item label="组件名称">
          <el-input style="width: 300px" v-model="serverForm.softwareName"/>
        </el-form-item>
        <el-form-item label="配置名称">
          <el-input style="width: 300px" v-model="serverForm.configName"/>
        </el-form-item>
        <el-form-item label="配置路径">
          <el-input style="width: 300px" v-model="serverForm.configPath"/>
        </el-form-item>
        <el-form-item label="配置模板">
          <el-input style="width: 600px" type="textarea" autosize v-model="serverForm.configTemplate"/>
        </el-form-item>
        <el-form-item label="配置模板参数">
          <el-input style="width: 600px" type="textarea" autosize v-model="serverForm.configTemplateParams"/>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
  </el-card>
</template>

<script>
import { reactive, ref, toRefs, onMounted, onBeforeUnmount, getCurrentInstance } from 'vue'
import axios from '@/utils/axios'
import { ElMessage } from 'element-plus'
import { useRoute, useRouter } from 'vue-router'
export default {
  name: 'SoftwareConfigDetail',
  setup() {
    const { proxy } = getCurrentInstance()
    console.log('proxy', proxy)
    const serverRef = ref(null)
    const route = useRoute()
    const router = useRouter()
    const { id } = route.query
    const state = reactive({
      token: localStorage.getItem('token') || '',
      id: id,
      serverForm: {
        softwareId: '',
        softwareName: '',
        configName: '',
        configPath: '',
        configTemplate: '',
        configTemplateParams: ''
      }
    })

    const back = () => {
      router.go(-1);
    }

    onMounted(() => {
      if (id) {
        axios.get(`/software-config/${id}`).then(res => {
          state.serverForm = {
            softwareId: res.data.data.softwareId,
            softwareName: res.data.data.softwareName,
            configName: res.data.data.configName,
            configPath: res.data.data.configPath,
            configTemplate: res.data.data.configTemplate,
            configTemplateParams: res.data.data.configTemplateParams
          }
        })
      }
    })
    onBeforeUnmount(() => {
    })

    return {
      ...toRefs(state),
      serverRef: serverRef,
      back
    }
  }
}
</script>

<style scoped>
.add {
  display: flex;
}
.add-container {
  flex: 1;
  height: 100%;
}
.avatar-uploader {
  width: 100px;
  height: 100px;
  color: #ddd;
  font-size: 30px;
}
.avatar-uploader-icon {
  display: block;
  width: 100%;
  height: 100%;
  border: 1px solid #e9e9e9;
  padding: 32px 17px;
}
</style>