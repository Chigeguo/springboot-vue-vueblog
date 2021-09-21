<template>
  <div id="app">
    <div id="nav">
      <!--<router-link to="/login">登录</router-link> |-->
    </div>
    <router-view/>
  </div>
</template>

<script>
export default {

  created() {
    if (sessionStorage.getItem("userinfo")) {
      this.$store.commit("SET_USERINFO", JSON.parse(sessionStorage.getItem("userinfo")));

    }
  }
};
</script>

<style>
html,body,#app {
  width: 100%;
  height: 100%;
}
</style>
