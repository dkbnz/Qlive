<template>
  <div>
    <el-row justify="center" type="flex">
      <el-col :lg="11" :md="13" :sm="15" :xl="9" :xs="24">
        <h1>{{question.questionText}}</h1>
      </el-col>
    </el-row>

    <el-row justify="center" type="flex">
      <small v-if="question.multiselect">You may select multiple</small>
      <small v-else>You may select one</small>
    </el-row>

    <el-row justify="center" type="flex">
      <el-col :lg="11" :md="13" :sm="15" :xl="9" :xs="24">
        <el-row @click.native="selectOrUnselect(question.questionOptions[i-1])"
                align="middle" justify="center" type="flex"
                v-bind:key="i" v-for="i in question.questionOptions.length">
          <el-col :span="24">
            <div
              class="grid-content"
              v-bind:class="{ selected: selectedOptions.includes(question.questionOptions[i-1].id) }">
              {{question.questionOptions[i-1].optionText}}
            </div>
          </el-col>
        </el-row>
      </el-col>
    </el-row>

    <el-row justify="center" type="flex">
      <el-button @click="vote" icon="el-icon-edit-outline" type="primary">Vote!</el-button>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "Vote",
    props: ['question'],
    data: function () {
      return {
        selectedOptions: []
      }
    },
    methods: {
      vote() {
        const loading = this.$loading({
          lock: true,
          background: 'rgba(0, 0, 0, 0.5)'
        });

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

        loading.close();
      },
      selectOrUnselect(clickedOption) {
        if (this.question.multiselect) {
          let itemIndex = this.selectedOptions.indexOf(clickedOption.id);
          if (itemIndex !== -1) {
            this.selectedOptions.splice(itemIndex, 1);
          } else {
            this.selectedOptions.push(clickedOption.id)
          }
        } else {
          this.selectedOptions = [clickedOption.id];
        }
        console.log(clickedOption);
      }
    }
  }
</script>

<style scoped>
  .el-row {
    margin-bottom: 10px;
  }

  .grid-content {
    background: #d3dce6;
    border-radius: 6px;
    min-height: 36px;
    display: flex;
    justify-content: center;
    align-content: center;
    flex-direction: column; /* column | row */
  }

  .selected {
    border-width: 3px;
    border-style: solid;
    border-color: #99a9bf;
  }

  /deep/ input {
    text-align: center;
  }
</style>
