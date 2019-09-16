<template>
  <div>
    <el-row justify="center" type="flex">
      <el-col :lg="11" :md="13" :sm="15" :xl="9" :xs="24">
        <el-input placeholder="What do you want to ask?"
                  v-model="question.questionText">
        </el-input>
      </el-col>
    </el-row>

    <el-row justify="center" type="flex">
      <el-switch
        active-text="Allow multiple selections"
        inactive-text="Single selection only"
        v-model="question.multiselect">
      </el-switch>
    </el-row>


    <el-row justify="center" type="flex">
      <el-col :lg="11" :md="13" :sm="15" :xl="9" :xs="24">
        <el-row justify="center" type="flex" v-bind:key="i" v-for="i in question.questionOptions.length">
          <el-input :disabled="i !== question.questionOptions.length"
                    placeholder="Add option..."
                    v-model="question.questionOptions[i-1].optionText">
          </el-input>
        </el-row>
      </el-col>
    </el-row>


    <el-row justify="center" type="flex">
      <el-button @click="removeOption" circle icon="el-icon-minus" type="primary"></el-button>
      <el-button @click="submit" icon="el-icon-edit-outline" type="primary">Create Quiz</el-button>
      <el-button @click="addOption" circle icon="el-icon-plus" type="primary"></el-button>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "Create",
    data() {
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
        const loading = this.$loading({
          lock: true,
          background: 'rgba(0, 0, 0, 0.5)'
        });

        this.axios.post('question', this.question)
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

        loading.close();
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
