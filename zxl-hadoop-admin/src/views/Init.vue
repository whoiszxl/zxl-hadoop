<template>
  <el-card class="introduce">
    <div class="order">


      <!-- 1. hosts配置 -->
      <el-card class="order-item">
        <template #header>
          <div class="card-header"> <span>hosts配置</span> </div>
        </template>


        <el-row>
          <el-popconfirm title="确定配置吗？" confirmButtonText='确定' cancelButtonText='取消' @confirm="handleHostsConfig">
            <template #reference>
              <el-button size="small" type="primary" icon="el-icon-star-off" plain>一键配置</el-button>
            </template>
          </el-popconfirm>


          <el-popconfirm title="确定重新配置吗？" confirmButtonText='确定' cancelButtonText='取消' @confirm="handleHostsConfig">
            <template #reference>
              <el-button size="small" type="danger" icon="el-icon-star-off" plain>重新配置</el-button>
            </template>
          </el-popconfirm>

          <el-button @click="handleViewFile" size="small" type="danger" icon="el-icon-star-off" plain>查看配置文件</el-button>

        </el-row>
      </el-card>


      <!-- 2. 免密登录配置 -->
      <el-card class="order-item">
        <template #header>
          <div class="card-header"> <span>免密登录配置</span> </div>
        </template>


        <el-row>
          <el-popconfirm title="确定配置吗？" confirmButtonText='确定' cancelButtonText='取消' @confirm="handleSSHConfig">
            <template #reference>
              <el-button size="small" type="primary" icon="el-icon-star-off" plain>一键配置</el-button>
            </template>
          </el-popconfirm>


          <el-popconfirm title="确定重新配置吗？" confirmButtonText='确定' cancelButtonText='取消' @confirm="handleSSHConfig">
            <template #reference>
              <el-button size="small" type="danger" icon="el-icon-star-off" plain>重新配置</el-button>
            </template>
          </el-popconfirm>

        </el-row>
      </el-card>

      <!-- 3. 脚本同步配置 -->
      <el-card class="order-item">
        <template #header>
          <div class="card-header"> <span>脚本同步配置</span> </div>
        </template>


        <el-row>
          <el-popconfirm title="确定配置吗？" confirmButtonText='确定' cancelButtonText='取消' @confirm="handleScriptConfig">
            <template #reference>
              <el-button size="small" type="primary" icon="el-icon-star-off" plain>一键配置</el-button>
            </template>
          </el-popconfirm>


          <el-popconfirm title="确定重新配置吗？" confirmButtonText='确定' cancelButtonText='取消' @confirm="handleScriptConfig">
            <template #reference>
              <el-button size="small" type="danger" icon="el-icon-star-off" plain>重新配置</el-button>
            </template>
          </el-popconfirm>

        </el-row>
      </el-card>

      <!-- 4. 组件同步配置 -->
      <el-card class="order-item">
        <template #header>
          <div class="card-header"> <span>组件同步配置</span> </div>
        </template>


        <el-row>
          <el-popconfirm title="确定配置吗？" confirmButtonText='确定' cancelButtonText='取消' @confirm="handleSoftwareSync">
            <template #reference>
              <el-button size="small" type="primary" icon="el-icon-star-off" plain>一键同步</el-button>
            </template>
          </el-popconfirm>


          <el-popconfirm title="确定重新配置吗？" confirmButtonText='确定' cancelButtonText='取消' @confirm="handleSoftwareSync">
            <template #reference>
              <el-button size="small" type="danger" icon="el-icon-star-off" plain>重新同步</el-button>
            </template>
          </el-popconfirm>

        </el-row>
      </el-card>


    </div>




    <div class="order">

      <!-- 1. JDK安装 -->
      <el-card class="order-item">
        <template #header>
          <div class="card-header"> <span>JDK安装</span> </div>
        </template>


        <el-row>
          <el-popconfirm title="确定安装吗？" confirmButtonText='确定' cancelButtonText='取消' @confirm="handleJDKConfig">
            <template #reference>
              <el-button size="small" type="primary" icon="el-icon-star-off" plain>一键安装</el-button>
            </template>
          </el-popconfirm>


          <el-popconfirm title="确定重新安装吗？" confirmButtonText='确定' cancelButtonText='取消' @confirm="handleJDKConfig">
            <template #reference>
              <el-button size="small" type="danger" icon="el-icon-star-off" plain>重新安装</el-button>
            </template>
          </el-popconfirm>

        </el-row>
      </el-card>


      <!-- 免密登录配置 -->
      <el-card class="order-item">
        <template #header>
          <div class="card-header"> <span>免密登录配置</span> </div>
        </template>


        <el-row>
          <el-popconfirm title="确定配置吗？" confirmButtonText='确定' cancelButtonText='取消' @confirm="handleSSHConfig">
            <template #reference>
              <el-button size="small" type="primary" icon="el-icon-star-off" plain>一键配置</el-button>
            </template>
          </el-popconfirm>


          <el-popconfirm title="确定重新配置吗？" confirmButtonText='确定' cancelButtonText='取消' @confirm="handleSSHConfig">
            <template #reference>
              <el-button size="small" type="danger" icon="el-icon-star-off" plain>重新配置</el-button>
            </template>
          </el-popconfirm>

        </el-row>
      </el-card>


      <!-- JDK配置 -->
      <el-card class="order-item">
        <template #header>
          <div class="card-header"> <span>JDK配置</span> </div>
        </template>


        <el-row>
          <el-popconfirm title="确定配置吗？" confirmButtonText='确定' cancelButtonText='取消' @confirm="handleJDKConfig">
            <template #reference>
              <el-button size="small" type="primary" icon="el-icon-star-off" plain>一键配置</el-button>
            </template>
          </el-popconfirm>


          <el-popconfirm title="确定重新配置吗？" confirmButtonText='确定' cancelButtonText='取消' @confirm="handleJDKConfig">
            <template #reference>
              <el-button size="small" type="danger" icon="el-icon-star-off" plain>重新配置</el-button>
            </template>
          </el-popconfirm>

        </el-row>
      </el-card>

      <!-- JDK配置 -->
      <el-card class="order-item">
        <template #header>
          <div class="card-header"> <span>JDK配置</span> </div>
        </template>


        <el-row>
          <el-popconfirm title="确定配置吗？" confirmButtonText='确定' cancelButtonText='取消' @confirm="handleJDKConfig">
            <template #reference>
              <el-button size="small" type="primary" icon="el-icon-star-off" plain>一键配置</el-button>
            </template>
          </el-popconfirm>


          <el-popconfirm title="确定重新配置吗？" confirmButtonText='确定' cancelButtonText='取消' @confirm="handleJDKConfig">
            <template #reference>
              <el-button size="small" type="danger" icon="el-icon-star-off" plain>重新配置</el-button>
            </template>
          </el-popconfirm>

        </el-row>
      </el-card>


    </div>
    <div id="zoom"></div>
  </el-card>
</template>

<script>
import {onMounted, onUnmounted, toRefs} from 'vue'
import {ElMessage, ElMessageBox} from "element-plus";
import axios from "axios";

let myChart = null
export default {
  name: 'Index',
  setup() {
    onMounted(() => {

    })
    onUnmounted(() => {

    })

    const handleHostsConfig = () => {

      axios.post(`/init/configHosts`, {
      }).then(() => {
        ElMessage.success('配置成功')
      })

    }

    const handleViewFile = () => {

      axios.post(`/init/viewFile`, {
        absolutePath: '/etc/hosts',
        serverId: 1
      }).then((res) => {
        ElMessageBox.alert(res.data.data, '/etc/hosts的内容', {
          confirmButtonText: 'OK',
          customClass: 'msgbox-class'
        });
      })

    }

    const handleSSHConfig = () => {

      axios.post(`/init/configSshNoPasswordLogin`, {
      }).then(() => {
        ElMessage.success('配置成功')
      })
    }

    const handleSoftwareSync = () => {

      axios.post(`/init/syncSoftware`, {
      }).then(() => {
        ElMessage.success('同步成功')
      })
    }


    const handleScriptConfig = () => {
      axios.post(`/init/syncScript`, {
      }).then(() => {
        ElMessage.success('配置成功')
      })

    }

    const handleJDKConfig = () => {
      //TODO 暂时写死传入服务器id，要修改为动态传入
      axios.post(`/install/jdk`, [1,2,3]).then(() => {
        ElMessage.success('JDK安装成功')
      })

    }

    return {
      handleHostsConfig,
      handleSSHConfig,
      handleScriptConfig,
      handleJDKConfig,
      handleViewFile,
      handleSoftwareSync
    }
  }
}
</script>

<style scoped>
.introduce .order {
  display: flex;
  margin-bottom: 60px;
}
.introduce .order .order-item {
  flex: 1;
  margin-right: 20px;
}
.introduce .order .order-item:last-child{
  margin-right: 0;
}
#zoom {
  min-height: 300px;
}

.msgbox-class {
  width: 300px;
}
</style>