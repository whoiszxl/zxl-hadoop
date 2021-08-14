<template>
  <div class="layout">
    <el-container v-if="showMenu" class="container">
      <el-aside class="aside">
        <div class="head">
          <div>
            <img src="//user-images.githubusercontent.com/5679180/79618120-0daffb80-80be-11ea-819e-d2b0fa904d07.gif" alt="logo">
            <span>zxl-hadoop</span>
          </div>
        </div>
        <div class="line" />
        <el-menu
            background-color="#222832"
            text-color="#fff"
            :router="true"
        >
          <el-submenu index="1">
            <template #title>
              <span>初始化管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/"><i class="el-icon-star-off" />首页</el-menu-item>
              <el-menu-item index="/init"><i class="el-icon-star-off" />初始化</el-menu-item>
              <el-menu-item index="/server"><i class="el-icon-star-off" />服务器管理</el-menu-item>
              <el-menu-item index="/software"><i class="el-icon-star-off" />组件管理</el-menu-item>
              <el-menu-item index="/script"><i class="el-icon-star-off" />脚本管理</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="2">
            <template #title>
              <span>系统监控管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/disk"><i class="el-icon-star-off" />磁盘监控</el-menu-item>
              <el-menu-item index="/network"><i class="el-icon-star-off" />网络监控</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

          <el-submenu index="3">
            <template #title>
              <span>管理员管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/admin"><i class="el-icon-star-off" />管理员列表</el-menu-item>
              <el-menu-item index="/admin/add"><i class="el-icon-star-off" />管理员新增</el-menu-item>
            </el-menu-item-group>
          </el-submenu>

        </el-menu>
      </el-aside>
      <el-container class="content">
        <Header />
        <div class="main">
          <router-view />
        </div>
        <Footer />
      </el-container>
    </el-container>
    <el-container v-else class="container">
      <router-view />
    </el-container>
  </div>
</template>

<script>
import { reactive, toRefs } from 'vue'
import { useRouter } from 'vue-router'
import Header from '@/components/Header.vue'
import Footer from '@/components/Footer.vue'
import { pathMap } from '@/utils'

export default {
  name: 'App',
  components: {
    Header,
    Footer
  },
  setup() {
    const noMenu = ['/login']
    const router = useRouter()
    const state = reactive({
      showMenu: true,
      defaultOpen: ['1'],
      currentPath: '/',
    })

    router.beforeEach((to, from, next) => {
      if (to.path === '/login') {
        // 如果路径是 /login 则正常执行
        next()
      } else {
        // 如果不是 /login，判断是否有 token
        if (!localStorage.getItem('token')) {
          // 如果没有，则跳至登录页面
          next({ path: '/login' })
        } else {
          // 否则继续执行
          next()
        }
      }
      state.showMenu = !noMenu.includes(to.path)
      state.currentPath = to.path
      document.title = pathMap[to.name]
    })

    return {
      ...toRefs(state)
    }
  }
}
</script>

<style scoped>

.el-menu-item.is-active {
  color: orange;
}

.layout {
  min-height: 100vh;
  background-color: #ffffff;
}
.container {
  height: 100vh;
}
.aside {
  width: 200px!important;
  background-color: #222832;
}
.head {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
}
.head > div {
  display: flex;
  align-items: center;
}

.head img {
  width: 50px;
  height: 50px;
  margin-right: 10px;
}
.head span {
  font-size: 20px;
  color: #ffffff;
}
.line {
  border-top: 1px solid hsla(0,0%,100%,.05);
  border-bottom: 1px solid rgba(0,0,0,.2);
}
.content {
  display: flex;
  flex-direction: column;
  max-height: 100vh;
  overflow: hidden;
}
.main {
  height: calc(100vh - 100px);
  overflow: auto;
  padding: 10px;
}
</style>

<style>
body {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
.el-menu {
  border-right: none!important;
}
.el-submenu {
  border-top: 1px solid hsla(0, 0%, 100%, .05);
  border-bottom: 1px solid rgba(0, 0, 0, .2);
}
.el-submenu:first-child {
  border-top: none;
}
.el-submenu [class^="el-icon-"] {
  vertical-align: -1px!important;
}
a {
  color: #409eff;
  text-decoration: none;
}
.el-pagination {
  text-align: center;
  margin-top: 20px;
}
.el-popper__arrow {
  display: none;
}
</style>