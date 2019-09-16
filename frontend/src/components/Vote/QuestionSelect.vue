<template>
  <div>

  </div>
</template>

<script>
  export default {
    props : ['question', 'selectedOptions'],
    name: "QuestionSelect",
    data: function () {
      return {

      }
    },
    methods: {
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
</style>
