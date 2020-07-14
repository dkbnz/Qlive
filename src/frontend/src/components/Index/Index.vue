<template>
  <div id="index">
    <el-row justify="center" type="flex">
      <el-col :lg="11" :md="13" :sm="15" :xl="9" :xs="24" justify="center" >
        <el-card class="box-card" shadow="always">
          <h1>Enter quiz code</h1>
          <code-input @code-submit="fetchQuiz"></code-input>
          <el-alert
                  :title="errorText"
                  @close="showError = false"
                  center
                  type="error"
                  v-if="showError">
          </el-alert>
          <h2>or</h2>
          <router-link :to="{ name: 'Create' }">
            <el-button icon="el-icon-edit-outline" type="primary">Create Quiz</el-button>
          </router-link>
          <el-divider></el-divider>
          <public-questions @question-select="fetchQuiz"></public-questions>
        </el-card>
      </el-col>
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

      /**
       * Fetch a quiz from the api.
       * If successful, move to the vote page.
       * Otherwise, display relevant errors.
       *
       * @param id    Question id to fetch.
       */
      fetchQuiz(id) {
        this.showError = false;

        const loader = this.$loading({
          lock: true,
          background: 'rgba(0, 0, 0, 0.5)'
        });

        let self = this;
        this.axios.get('question/' + id)
          .then(response => {
            this.$router.push({
              name: 'Vote',
              params: {
                question: response.data
              }
            });
            loader.close();
          })
          .catch(function (error) {
            self.errorText = error.message;
            self.showError = true;
            loader.close();
          });
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
