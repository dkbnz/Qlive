<template>
    <div id="index">
      <el-row type="flex" justify="center">
        <h1>Enter code</h1>
      </el-row>
      <el-row type="flex" justify="center">
        <el-col :xs="24" :sm="15" :md="13" :lg="11" :xl="9">
          <code-input @code-submit="runCommand"></code-input>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center">
        <h2>or</h2>
      </el-row>
      <el-row type="flex" justify="center">
        <el-button type="primary" icon="el-icon-edit-outline">Create Quiz</el-button>
      </el-row>
    </div>
</template>

<script>
    import CodeInput from "./CodeInput";
    export default {
        name: "Index",
      methods: {
          runCommand(code) {
            console.log(code)
          }
      },
      components: {CodeInput}
    }
</script>

<style scoped>
</style>
