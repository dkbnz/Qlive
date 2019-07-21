<template>
  <div>
    <el-row type="flex" justify="center">
      <el-col :xs="24" :sm="15" :md="13" :lg="11" :xl="9">
        <h1>{{question.questionText}}</h1>
      </el-col>
    </el-row>

    <el-row type="flex" justify="center">
      <small v-if="question.multiselect">You may select multiple</small>
      <small v-else>You may select one</small>
    </el-row>

    <el-row type="flex" justify="center">
      <el-col :xs="24" :sm="15" :md="13" :lg="11" :xl="9">
        <question-select :question="question" :selectedOptions="selectedOptions"></question-select>
      </el-col>
    </el-row>


    <el-row type="flex" justify="center">
      <el-button type="primary" icon="el-icon-edit-outline" @click="vote">Vote!</el-button>
    </el-row>
  </div>
</template>

<script>
  import QuestionSelect from "./QuestionSelect";
  export default {
    name: "Vote",
    components: {QuestionSelect},
    props: ['question'],
    data: function() {
      return {
        selectedOptions: []
      }
    },
    methods: {
      vote() {
        this.axios.post('question/' + this.question.id + '/vote', this.selectedOptions)
          .then(response => {
            this.$router.push({
              name: 'ViewResults',
              params: {
                question: response.data
              }
            });
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
      }
    }
  }
</script>

<style scoped>
  .el-row {
    margin-bottom: 10px;
  }

  /deep/ input {
    text-align: center;
  }
</style>
