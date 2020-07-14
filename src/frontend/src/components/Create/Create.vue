<template>
  <div>
    <el-form :model="question" ref="question">

      <el-row justify="center" type="flex">

        <el-col :lg="11" :md="13" :sm="15" :xl="9" :xs="24">

          <el-row justify="center" type="flex">
            <el-form-item
              prop="questionText"
              :rules="{ required: true, message: 'You must enter a question', trigger: 'blur' }"
              style="width: 100%"
            >
              <el-input placeholder="What do you want to ask?"
                        v-model="question.questionText">
              </el-input>
            </el-form-item>
          </el-row>

          <el-row justify="center" type="flex">
            <el-checkbox v-model="question.multiselect">Allow multiple selections</el-checkbox>
            <el-checkbox v-model="question.public">List question publicly</el-checkbox>
          </el-row>

          <el-form-item
            class="ma-0 pa-0"
            v-bind:key="i" v-for="(questionOption, i) in question.questionOptions"
            :prop="'questionOptions[' + i + '].optionText'"
            :rules="{ required: true, message: 'Please enter an option or remove if uneccessary', trigger: 'blur' }"
            style="width: 100%"
          >
            <el-input :disabled="i !== (question.questionOptions.length - 1)"
                      placeholder="Add option..."
                      required
                      v-model="questionOption.optionText">
            </el-input>
          </el-form-item>

          <el-row justify="center" type="flex">
            <el-alert
              :title="error.message"
              :type="error.type"
              @close="error.show = false"
              center
              v-if="error.show">
            </el-alert>
          </el-row>

          <el-row justify="center" type="flex">
            <el-button @click="removeOption" circle icon="el-icon-minus" type="primary"></el-button>
            <el-button @click="submit" icon="el-icon-edit-outline" type="primary">Create Quiz</el-button>
            <el-button @click="addOption" circle icon="el-icon-plus" type="primary"></el-button>
          </el-row>

        </el-col>


      </el-row>
    </el-form>
  </div>
</template>

<script>
  export default {
    name: "Create",
    data() {
      return {
        error: {
          show: false,
          message: "An error has occurred.",
          type: 'error'
        },
        question: {
          questionText: "",
          multiselect: false,
          public: false,
          questionOptions: [{
            optionText: ""
          }]
        }
      }
    },
    methods: {
      /**
       * Add an option to the quiz.
       */
      addOption() {
        this.error.show = false;
        if (this.question.questionOptions.length < 10) {
          this.$refs['question'].validate((valid) => {
            if (valid) {
              this.question.questionOptions.push({
                optionText: ""
              })
            }
          });
        } else {
          this.error = {
            show: true,
            message: 'Maximum of 10 options.',
            type: 'warning'
          };
        }
      },


      /**
       * Remove most recently added option from the list.
       */
      removeOption() {
        this.error.show = false;
        if (this.question.questionOptions.length > 1) {
          this.question.questionOptions.pop();
        }
      },


      /**
       * Attempt to create a new quiz.
       * Perform input validation.
       * If valid, send request, otherwise display relevant error message.
       */
      submit() {
        this.error.show = false;
        let self = this;
        this.$refs['question'].validate((valid) => {
          if (valid) {
            if (this.question.questionOptions.length > 1) {
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
                  loading.close();
                })
                .catch(function (error) {
                  self.error = {
                    show: true,
                    message: error.message,
                    type: 'error'
                  };
                  loading.close();
                });

            } else {
              this.error = {
                show: true,
                message: 'You must have at least two options.',
                type: 'error'
              };
            }
          }
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
