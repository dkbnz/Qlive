<template>
  <div>
    <el-row justify="center" type="flex">
      <el-col :lg="11" :md="13" :sm="15" :xl="9" :xs="24">
        <h1>{{question.questionText}}</h1>
      </el-col>
    </el-row>

    <el-row justify="center" type="flex">
      <el-col :lg="11" :md="13" :sm="15" :xl="9" :xs="24">
        <h3>Navigate to qlive.dougbarrett.nz and enter the code {{question.id}} to answer</h3>
      </el-col>
    </el-row>

    <el-row justify="center" type="flex">
      <small v-if="question.multiselect">Users may select multiple</small>
      <small v-else>Users may select one</small>
    </el-row>

    <el-row justify="center" type="flex">
      <el-col :lg="11" :md="13" :sm="15" :xl="9" :xs="24">
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
    data: function () {
      return {
        timer: ''
      }
    },
    created: function () {
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
