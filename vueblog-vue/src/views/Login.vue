<template>
  <div class="main-wrapper">
    <el-container>
      <el-header>
        <img class="logo" src="../assets/img.png">
      </el-header>
      <el-main>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="用户名" prop="username">
            <el-input @keyup.enter.native="submitForm('ruleForm')" v-model="ruleForm.username" maxlength="12"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input @keyup.enter.native="submitForm('ruleForm')" v-model="ruleForm.password" type="password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
            <el-button @click="resetForm('ruleForm')">清空</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      name: 'Login',
      ruleForm: {
        username: 'tsin',
        password: '',
      },
      rules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'blur'},
          {min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'change'}
        ],

      }
    };
  },
  methods: {

    submitForm(formName) {
      const _this = this;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post('http://localhost:8081/login', this.ruleForm)
              .then((response) => {
                const jwt = response.headers['authorization'];
                const userinfo = response.data.data

                console.log("jwt====="+jwt)
                console.log(userinfo)

                //调用方法  共享数据
                _this.$store.commit("SET_TOKEN", jwt);
                _this.$store.commit("SET_USERINFO",userinfo);

                console.log(_this.$store.getters.GET_USERINFO);
                _this.$router.push("/blogs");
              });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>
.demo-ruleForm {
  margin: 0 auto;
  margin-top: 80px;

  width: 500px;

}

.el-container {
  height: 100%;
}

.main-wrapper {
  height: 100%;
}

.logo {
  margin-top: 10px;
}

.el-header, .el-footer {
  background-color: #242628;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #8eb3e7;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #ffffff;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>