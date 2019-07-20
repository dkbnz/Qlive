<template>
  <div>
    <el-form label-position="top" ref="codeForm">
      <el-row type="flex" :gutter="5" class="row-bg" justify="center">
        <el-col :span="5" :offset="1">
          <el-input ref="firstSegment" placeholder="XX"
                    v-model="code.first"
                    maxlength="2"
                    pattern="[A-Za-z0-9]{2}"
                    @input="text => checkInput(text, 0)">
          </el-input>
        </el-col>
        <el-col :span="5">
          <el-input ref="secondSegment" placeholder="XX"
                    v-model="code.second"
                    maxlength="2"
                    pattern="[A-Za-z0-9]{2}"
                    @input="text => checkInput(text, 1)">
          </el-input>
        </el-col>
        <el-col :span="5">
          <el-input ref="thirdSegment" placeholder="XX"
                    v-model="code.third"
                    maxlength="2"
                    pattern="[A-Za-z0-9]{2}"
                    @input="text => checkInput(text, 2)"
                    @keyup.native.enter="submit">
          </el-input>
        </el-col>
        <el-col :span="1">
          <el-button ref="submitCode" @click="submit" icon="el-icon-right" circle></el-button>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
  export default {
    name: "CodeInput",
    data() {
      return {
        code: {
          first: '',
          second: '',
          third: ''
        }
      }
    },
    methods: {

      /**
       * Checks the text of the specified input and determines what code segment to switch focus to.
       *
       * @param text        Text value of input that called the function.
       * @param inputNum    Value to identify input field
       */
      checkInput(text, inputNum) {
        if (text.length !== 1) {
          switch(inputNum) {
            case 0:
              if (text.length === 2) this.$refs.secondSegment.focus();
              break;
            case 1:
              (text.length === 2) ? this.$refs.thirdSegment.focus() : this.$refs.firstSegment.focus();
              break;
            case 2:
              if (text.length === 0) this.$refs.secondSegment.focus();
          }
        }
      },
      submit() {
        this.$emit('code-submit', (this.code.first + this.code.second + this.code.third).toUpperCase())
      },
    }
  }
</script>

<style scoped>
  /* Apply this style to children input boxes */
  /deep/ input {
    text-align: center;
    text-transform: uppercase;
  }
</style>
