const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer:{
    proxy:{
        '/back':{
            target:'http://localhost:8888',
            pathRewrite:{'^/back':''},
            
        }
    },
    port:8080
},
lintOnSave: false
})
