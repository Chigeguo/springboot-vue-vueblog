<template>
  <div class="m-content">
    <h3>欢迎来到我的博客</h3>
    <div class="block">
      <el-avatar :size="50" :src="user.avatar"></el-avatar>
      <div>{{user.username}}</div>
    </div>
    <div class="partition">
      <span><el-link href="/blogs" >主页</el-link></span>
      <el-divider direction="vertical"></el-divider>
      <span><el-link href="/blog/add" type="success" >发表博客</el-link></span>
      <el-divider direction="vertical"></el-divider>
      <span v-show="!isLogin"><el-link href="/login" type="info">登录</el-link></span>
      <span v-show="isLogin"><el-link @click="logout" type="danger">退出</el-link></span>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      user: {
        username: '请先登录',
        avatar: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      },
      isLogin: false
    };
  },
  methods: {
    logout() {
      const _this = this;
      console.log("退出");
      _this.$axios.get("/logout", {
        headers: {
          "Authorization": localStorage.getItem("token")
        }
      }).then(res => {
        _this.$store.commit("REMOVE_INFO");
        _this.$router.push("/login")
      });
    }
  },
  created() {
    if (this.$store.getters.GET_USERINFO.username) {
      this.user.username = this.$store.getters.GET_USERINFO.username;
      this.user.avatar = this.$store.getters.GET_USERINFO.avatar;
      this.isLogin = true;
    }
  }
}
</script>

<style scoped>
.m-content {
  max-width: 960px;
  margin: 0 auto;
  text-align: center;
}
.partition {
  margin: 8px ;
}


</style>