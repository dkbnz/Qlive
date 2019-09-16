<template>
    <div id="index">
      <el-row type="flex" justify="center">
        <h1>Enter code</h1>
      </el-row>
      <el-row type="flex" justify="center">
        <el-col :xs="24" :sm="15" :md="13" :lg="11" :xl="9">
          <code-input @code-submit="fetchQuiz"></code-input>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center">
        <el-col :xs="24" :sm="15" :md="13" :lg="11" :xl="9">
          <el-alert
            v-if="showError"
            :title="errorText"
            type="error"
            @close="showError = false"
            center>
          </el-alert>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center">
        <h2>or</h2>
      </el-row>
      <el-row type="flex" justify="center">
        <router-link :to="{ name: 'Create' }">
          <el-button type="primary" icon="el-icon-edit-outline">Create Quiz</el-button>
        </router-link>
      </el-row>
    </div>
</template>
<script>
    import CodeInput from "./CodeInput";
    export default {
      name: "Index",
      data() {
        return {
          showError: false,
          errorText: "An error has occurred."
        }
      },
      methods: {

        fullScreenLoading() {
          return this.$loading({
            lock: true,
            background: 'rgba(0, 0, 0, 0.5)'
          });
        },

        fetchQuiz(code) {
          this.showError = false;
          const loader = this.fullScreenLoading();
          let self = this;
          this.axios.get('question/' + code)
            .then(response => {
              this.$router.push({
                name: 'Vote',
                params: {
                  question: response.data
                }
              });
            })
            .catch(function (error) {
              self.errorText = error.message;
              self.showError = true;
            });
          loader.close();
        }
      },
      components: {CodeInput}
    }
</script>

<style scoped>
  .el-row {
    margin-bottom: 20px;
    &:last-child {
       margin-bottom: 0;
     }
  }
</style>
