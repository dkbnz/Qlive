<template>
  <div id="index">
    <el-row justify="center" type="flex">
      <h1>Enter a code</h1>
    </el-row>
    <el-row justify="center" type="flex">
      <el-col :lg="11" :md="13" :sm="15" :xl="9" :xs="24">
        <code-input @code-submit="fetchQuiz"></code-input>
      </el-col>
    </el-row>
    <el-row justify="center" type="flex">
      <el-col :lg="11" :md="13" :sm="15" :xl="9" :xs="24">
        <el-alert
          :title="errorText"
          @close="showError = false"
          center
          type="error"
          v-if="showError">
        </el-alert>
      </el-col>
    </el-row>
    <el-row justify="center" type="flex">
      <h2>or</h2>
    </el-row>
    <el-row justify="center" type="flex">
      <router-link :to="{ name: 'Create' }">
        <el-button icon="el-icon-edit-outline" type="primary">Create Quiz</el-button>
      </router-link>
    </el-row>
    <el-row justify="center">
      <public-questions>
      </public-questions>
    </el-row>
  </div>
</template>
<script>
  import CodeInput from "./CodeInput";
  import PublicQuestions from "./PublicQuestions";

  export default {
    name: "Index",
    data() {
      return {
        showError: false,
        errorText: "An error has occurred."
      }
    },
    methods: {
      fetchQuiz(code) {
        this.showError = false;

        const loader = this.$loading({
          lock: true,
          background: 'rgba(0, 0, 0, 0.5)'
        });

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
    components: {PublicQuestions, CodeInput}
  }
</script>

<style scoped>
  .el-row {
    margin-bottom: 20px;
  }

  .el-row:last-child {
    margin-bottom: 0;
  }
</style>
