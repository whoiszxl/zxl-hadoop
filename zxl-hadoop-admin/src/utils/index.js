export function localGet (key) {
    const value = window.localStorage.getItem(key)
    try {
        return JSON.parse(window.localStorage.getItem(key))
    } catch (error) {
        return value
    }
}

export function localSet (key, value) {
    window.localStorage.setItem(key, JSON.stringify(value))
}

export function localRemove (key) {
    window.localStorage.removeItem(key)
}

export function setToken(key, token) {
    window.localStorage.setItem(key, token)
}

export const pathMap = {
    index: '首页',
    login: '登录',
    software: '组件管理',
    softwareAdd: '组件新增',
    server: '服务器管理',
    serverAdd: '服务器新增',
}