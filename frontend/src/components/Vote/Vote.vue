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
        <el-row v-for="i in question.questionOptions.length" @click.native="selectOrUnselect(question.questionOptions[i-1])" v-bind:key="i" type="flex" justify="center" align="middle">
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


    <el-row type="flex" justify="center">
      <el-button type="primary" icon="el-icon-edit-outline" @click="vote">Vote!</el-button>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "Vote",
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
      },
      selectOrUnselect(clickedOption) {
        if(this.question.multiselect) {
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
    justify-content:center;
    align-content:center;
    flex-direction:column; /* column | row */
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
