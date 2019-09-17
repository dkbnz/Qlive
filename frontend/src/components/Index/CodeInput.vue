<template>
  <div>
    <el-form :model="code" :rules="rules" label-position="top" ref="codeForm">
      <el-row :gutter="5" class="row-bg" justify="center" type="flex">

        <el-col :offset="1" :span="5">
          <el-form-item prop="first">
            <el-input @input="text => checkInput(text, 0)" maxlength="2"
                      placeholder="XX"
                      ref="firstSegment"
                      v-model="code.first">
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item prop="second">
            <el-input @input="text => checkInput(text, 1)" maxlength="2"
                      placeholder="XX"
                      ref="secondSegment"
                      v-model="code.second">
            </el-input>
          </el-form-item>
        </el-col>

        <el-col :span="5">
          <el-form-item prop="third">
            <el-input @input="text => checkInput(text, 2)" @keyup.native.enter="submit"
                      maxlength="2"
                      placeholder="XX"
                      ref="thirdSegment"
                      v-model="code.third">
            </el-input>
          </el-form-item>
        </el-col>

        <el-col :span="1">
          <el-button @click="submit" circle icon="el-icon-right" ref="submitCode"></el-button>
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
        },
        rules: {
          first: [{required: true, pattern: /^[A-Za-z0-9]{2}$/, message: ' ', trigger: 'blur'}],
          second: [{required: true, pattern: /^[A-Za-z0-9]{2}$/, message: ' ', trigger: 'blur'}],
          third: [{required: true, pattern: /^[A-Za-z0-9]{2}$/, message: ' ', trigger: 'blur'}]
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
          switch (inputNum) {
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


      /**
       * Validate the form, if everything is valid, emit the code-submit event to the parent component.
       */
      submit() {
        this.$refs['codeForm'].validate((valid) => {
          if (valid) {
            this.$emit('code-submit', (this.code.first + this.code.second + this.code.third).toUpperCase())
          }
        });
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
