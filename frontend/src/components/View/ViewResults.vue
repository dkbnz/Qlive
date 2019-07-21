<template>
  <div>
    <el-row type="flex" justify="center">
      <el-col :xs="24" :sm="15" :md="13" :lg="11" :xl="9">
        <h1>{{question.questionText}}</h1>
      </el-col>
    </el-row>

    <el-row type="flex" justify="center">
      <el-col :xs="24" :sm="15" :md="13" :lg="11" :xl="9">
        <h3>Navigate to arstarst.com and enter the code {{question.id}} to answer</h3>
      </el-col>
    </el-row>

    <el-row type="flex" justify="center">
      <small v-if="question.multiselect">Users may select multiple</small>
      <small v-else>Users may select one</small>
    </el-row>

    <el-row type="flex" justify="center">
      <el-col :xs="24" :sm="15" :md="13" :lg="11" :xl="9">
        <graph :question="question"></graph>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import Graph from "./Graph";
  export default {
    name: "ViewResults",
    components: {Graph},
    props: ['question'],
    data: function() {
      return {
        timer: ''
      }
    },
    created: function() {

      this.fetchQuestion();
      this.timer = setInterval(this.fetchQuestion, 1000)

    },
    methods: {
      fetchQuestion() {
        this.axios.get('question/' + this.question.id)
          .then(response => {
            this.question = response.data;
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
      }
    },
    beforeDestroy() {
      clearInterval(this.timer)
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
