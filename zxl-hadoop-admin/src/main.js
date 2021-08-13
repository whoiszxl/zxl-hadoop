import {createApp} from 'vue'
import {
    ElAside,
    ElButton,
    ElCard,
    ElCascader,
    ElCheckbox,
    ElContainer,
    ElDialog,
    ElForm,
    ElFormItem,
    ElInput,
    ElMenu,
    ElMenuItem,
    ElMenuItemGroup,
    ElMessageBox,
    ElOption,
    ElPagination,
    ElPopconfirm,
    ElPopover,
    ElRadio,
    ElRadioGroup,
    ElSelect,
    ElSubmenu,
    ElTable,
    ElTableColumn,
    ElTag,
    ElUpload
} from 'element-plus'
import App from './App.vue'
import router from '@/router'

import '~/theme/index.css'

const app = createApp(App) // 生成 Vue 实例 app

// 全局方法
app.config.globalProperties.$filters = {
    prefix(url) {
        if (url && url.startsWith('http')) {
            return url
        } else {
            url = `http://localhost:81${url}`
            return url
        }
    }
}

app.config.globalProperties.goTop = function () {
    const main = document.querySelector('.main')
    main.scrollTop = 0
}

app.use(router) // 引用路由实例

app.use(ElButton)
    .use(ElContainer)
    .use(ElAside)
    .use(ElMenu)
    .use(ElSubmenu)
    .use(ElMenuItemGroup)
    .use(ElMenuItem)
    .use(ElForm)
    .use(ElFormItem)
    .use(ElCheckbox)
    .use(ElInput)
    .use(ElPopover)
    .use(ElTag)
    .use(ElCard)
    .use(ElTable)
    .use(ElTableColumn)
    .use(ElPopconfirm)
    .use(ElUpload)
    .use(ElDialog)
    .use(ElPagination)
    .use(ElCascader)
    .use(ElRadioGroup)
    .use(ElRadio)
    .use(ElSelect)
    .use(ElOption)
    .use(ElMessageBox)
app.mount('#app') // 挂载到 #app
