<template>
  <div>
    <el-row type="flex" justify="center">
      <el-col :xs="24" :sm="15" :md="13" :lg="11" :xl="9">
        <el-input placeholder="What do you want to ask?"
                  v-model="question.questionText">
        </el-input>
      </el-col>
    </el-row>

    <el-row type="flex" justify="center">
      <el-switch
        v-model="question.multiselect"
        active-text="Allow multiple selections"
        inactive-text="Single selection only">
      </el-switch>
    </el-row>


    <el-row type="flex" justify="center">
      <el-col :xs="24" :sm="15" :md="13" :lg="11" :xl="9">
        <el-row v-for="i in question.questionOptions.length" v-bind:key="i" type="flex" justify="center">
          <el-input placeholder="Add option..."
                    v-model="question.questionOptions[i-1].optionText"
                    :disabled="i !== question.questionOptions.length">
          </el-input>
        </el-row>
      </el-col>
    </el-row>


    <el-row type="flex" justify="center">
      <el-button type="primary" icon="el-icon-minus" @click="removeOption" circle></el-button>
      <el-button type="primary" icon="el-icon-edit-outline" @click="submit">Create Quiz</el-button>
      <el-button type="primary" icon="el-icon-plus" @click="addOption" circle></el-button>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "Create",
    data () {
      return {
        question: {
          questionText: "",
          multiselect: false,
          questionOptions: [{
            optionText: ""
          }]
        }
      }
    },
    methods: {
      addOption() {
        if (this.question.questionOptions.length < 10) {
          this.question.questionOptions.push({
            optionText: ""
          })
        } else {
          this.$message({
            showClose: true,
            message: 'Maximum of 10 options.',
            type: 'warning'
          });
        }
      },
      removeOption() {
        this.question.questionOptions.pop();
      },
      submit() {
        this.question.questionOptions.push({
          optionText: ""
        })
      }
    }
  }
</script>

<style scoped>
  .el-row {
    margin-bottom: 10px;
  }
</style>
