<template>
<div>
<Header></Header>
  <div class="mblog">
<h2>{{ blog.title}}</h2>
    <router-link :to="{name:'BlogEdit',params:{blogId:blog.id}}">
    <el-button  v-if="show" size="mini" type="primary" icon="el-icon-edit">
    </el-button>
    </router-link>
    <el-divider></el-divider>
    <div class="markdown-body" v-html="blog.content"></div>
  </div>
</div>
</template>

<script>
import Header from "@/components/Header";
import 'github-markdown-css';

export default {
  name: "BlogDetail",
  components: {Header},
  data() {
    return {
      blog: {
        id: '',
        title: '',
        content: '',
      },
      show: false

    };
  },

  created() {
    const _this = this
    //从路由中拿到
    const blogId = this.$route.params.blogId;
    if (blogId) {
      this.$axios.get("/blog/" + blogId).then(res => {
        const blog = res.data.data;
        console.log(blog);
        _this.blog.id = blog.id;
        _this.blog.title = blog.title;

        let MarkdownIt = require("markdown-it");
        let markdownIt = new MarkdownIt();
        _this.blog.content = markdownIt.render(blog.content);

      //  展示编辑按钮
        _this.show = (blog.userId===_this.$store.getters.GET_USERINFO.id)
      });
    }
  }
}
</script>

<style scoped>
.mblog {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  width: 70%;
  height: 600px;
  margin: 50px auto;
  padding: 20px 15px;
}

</style>