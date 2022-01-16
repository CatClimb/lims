import animationData1 from '../anim/bj.json'
import animationData2 from '../anim/person.json'
import axios from 'axios';
import lottie from 'lottie-web';
import jwtDecode from 'jwt-decode'
export const login_huihe = {

    data() {
      return {
        formData: {
          loginName: "",
          password: "",
        },
        rules: {
          userName: [
            { required: true, message: "请输入用户名", trigger: "blur" },
            //{ validator: handlerValidate, trigger: 'blur' },
            {
              min: 1,
              max: 20,
              message: "长度在 1 到 20 个字符",
              trigger: "blur",
            },
          ],
          password: [
            { required: true, message: "请输入密码", trigger: "blur" },
            {
              min: 6,
              max: 20,
              message: "长度在 6 到 20 个字符",
              trigger: "blur",
            },
          ],
        },
        
      };
    },
  
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
              if (valid) {
                this.handlerRequest();
                // let captcha = new TencentCaptcha("123123", (res) => {
                //   if (res.ret === 0) {
                //     this.handlerRequest();
                //   } else {
                //      this.$message({
                //     message: "认证失败，请重新认证",
                //     type: "error",
                //     center: true,
                //   });
                //   }
                // });
                // captcha.show();
              } else {
      
                return false;
              }
            });
          },
  
      handlerLottie(idName, anim) {
        lottie.loadAnimation({
          container: document.getElementById(idName), // 包含动画的那个容器
          renderer: "svg", // 可选值svg或canvas
          loop: true,
          autoplay: true, // 是否循环执行
          animationData: anim, // json文件的路径，可以是相对路径或绝对路径
        });
      },
    },
    mounted() {
      this.handlerLottie("animation1", animationData1);
      this.handlerLottie("animation2", animationData2);
    },
  }
  
  export const login_huihe2={
    methods:{
      handlerRequest() {
        axios.post(this.url, this.formData).then(
          (response) => {
            if (response.data.code === 2000) {
              this.$message({
                message: response.data.msg,
                type: "success",
                center: true,
              });
              localStorage.setItem("token", response.data.data.token);
              sessionStorage.setItem("isLogin",true);
              this.$router.push({
                name: 'Home',
                
                
              })
              
            } else {
              this.$message({
                message: response.data.msg,
                type: "error",
                center: true,
              });
            }
          },
          (error) => {
            this.$message({
              message: error.response.data.msg,
              type: "error",
              center: true,
            });
            
          }
        );
      },
    }
  }

 