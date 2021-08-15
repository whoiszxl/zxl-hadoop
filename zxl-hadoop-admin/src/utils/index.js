export function localGet (key) {
    const value = localStorage.getItem(key)
    try {
        return JSON.parse(localStorage.getItem(key))
    } catch (error) {
        return value
    }
}

export function localSet (key, value) {
    localStorage.setItem(key, JSON.stringify(value))
}

export function localRemove (key) {
    localStorage.removeItem(key)
}

export function setToken(key, token) {
    localStorage.setItem(key, token)
}

export const pathMap = {
    index: '首页',
    init: '组件安装',
    login: '登录',
    software: '组件管理',
    softwareAdd: '组件新增',
    softwareConfig: '组件配置管理',
    softwareConfigDetail: '组件配置详情',
    server: '服务器管理',
    serverAdd: '服务器新增',
    script: '脚本管理',
    scriptAdd: '脚本新增',
}