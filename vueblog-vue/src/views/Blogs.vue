<template>
  <div>
    <Header></Header>
    <div class="content">
      <el-timeline v-for="blog in blogs">
        <el-timeline-item :timestamp="blog.created" placement="top">
          <el-card>
            <h4>
              <router-link :to="{name:'BlogDetail',params:{blogId:blog.id}}">
                {{ blog.title }}
              </router-link>
            </h4>
            <p>{{ blog.description }}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
      <el-pagination
          class="m-page"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="this.currentPage"
          :page-sizes="[3, 5, 10]"
          :page-size="this.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="this.total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header";

export default {
  name: "Blogs",
  components: {Header},
  data() {
    return {
      blogs: {},
      currentPage: 1,
      total: 0,
      pageSize: 3
    }
  },
  methods: {
    page() {
      const _this = this;
      this.$axios.get("/blog/find?currentPage=" + this.currentPage + "&pageSize=" + this.pageSize).then(res => {
        console.log(res);
        _this.currentPage = res.data.data.current;
        _this.total = res.data.data.total;
        _this.blogs = res.data.data.records;
      });
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val;
      this.page();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
      this.page()
    }

  },

  created() {
    this.page();

  }
}
</script>

<style scoped>
.content {
  max-width: 960px;
  margin: 0 auto;
}

.m-page {
  margin: 0 auto;
  text-align: center;
}


</style>