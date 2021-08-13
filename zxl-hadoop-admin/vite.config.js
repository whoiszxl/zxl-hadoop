import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'
import vitePluginImport from 'vite-plugin-babel-import'

//mode 读取 vite build --mode beta 后配置的参数，在base中通过判断mode参数来决定打什么包
export default ({ mode }) => defineConfig({
  plugins: [
    vue(),
    vitePluginImport([
      {
        libraryName: 'element-plus',
        libraryDirectory: 'es',
        style(name) {
          return `element-plus/lib/theme-chalk/${name}.css`;
        },
      }
    ])
  ],
  resolve: {
    //指定别名，@直接读取src下的，~读取当前目录下的
    alias: {
      '~': path.resolve(__dirname, './'),
      '@': path.resolve(__dirname, 'src')
    }
  },

  base: mode == 'dev' ? './' : (mode == 'test' ? '//zxl-hadoop.whoiszxl.com/test/' : './'),

  //服务代理配置
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:81/',
        changeOrigin: true,
        rewrite: path => path.replace(/^\/api/, '')
      }
    }
  }
})