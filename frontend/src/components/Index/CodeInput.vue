<template>
  <div>
    <el-form label-position="top" ref="codeForm" :model="code" :rules="rules">
      <el-row type="flex" :gutter="5" class="row-bg" justify="center">
        <el-col :span="5" :offset="1">

          <el-form-item prop="first">
          <el-input ref="firstSegment" placeholder="XX"
                    v-model="code.first"
                    maxlength="2"
                    @input="text => checkInput(text, 0)">
          </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="5">

          <el-form-item prop="second">
          <el-input ref="secondSegment" placeholder="XX"
                    v-model="code.second"
                    maxlength="2"
                    @input="text => checkInput(text, 1)">
          </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="5">

          <el-form-item prop="third">
          <el-input ref="thirdSegment" placeholder="XX"
                    v-model="code.third"
                    maxlength="2"
                    @input="text => checkInput(text, 2)"
                    @keyup.native.enter="submit">
          </el-input>
          </el-form-item>
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
        },
        rules: {
          first: [{ required: true, pattern: /^[A-Za-z0-9]{2}$/, message: ' ', trigger: 'blur' }],
          second: [{ required: true, pattern: /^[A-Za-z0-9]{2}$/, message: ' ', trigger: 'blur' }],
          third: [{ required: true, pattern: /^[A-Za-z0-9]{2}$/, message: ' ', trigger: 'blur' }]
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
