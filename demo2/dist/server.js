const express = require('express');
//history工作模式
// const history = require('connect-history-api-fallback');

const app =express();
//history工作模式 bu
// app.use(history());

app.use(express.static(__dirname+'/public'));
// app.get('/person',(req,res)=>{
//     res.send({
//         name:'Tom',
//         sex:'male'
//     })
// })
app.listen('8080',(err)=>{
    if(!err){
        console.log('服务器启动成功了！')
    }
})