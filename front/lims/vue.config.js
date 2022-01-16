module.exports = {
    devServer:{
        proxy:{
            '/back':{
                target:'http://localhost:8888',
                pathRewrite:{'^/back':''},
                
            }
        },
        port:8080
    },
    
}