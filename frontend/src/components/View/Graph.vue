<template>
    <div>
      <el-row v-for="i in question.questionOptions.length" v-bind:key="i" type="flex" justify="center" align="middle">
        <el-col :span="24">
          <div class="grid-content bg-purple">
            {{question.questionOptions[i-1].optionText}}: {{question.questionOptions[i-1].voteCount}}
          </div>
        </el-col>
      </el-row>
    </div>
</template>

<script>
    export default {
      props : ['question'],
      name: "Graph"
    }
</script>

<style scoped>
  .el-row {
    margin-bottom: 10px;
  }
</style>
