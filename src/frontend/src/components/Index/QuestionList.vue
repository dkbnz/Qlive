<template>
    <div>
        <el-row @click.native="count+=1"
                v-bind:key="i"
                class="grid-content"
                type="flex"
                justify="space-around"
                v-for="i in count">
            <el-col :span="20">
                <h5>List group item heading</h5>
            </el-col>
            <el-col :span="4" class="p">
                <small>{{ 10 + i }} Jan '20 | </small>
                <small><i class="el-icon-s-custom"></i> {{i}} votes</small>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "QuestionList",
        data () {
            return {
                count: 10
            }
        },
        methods: {
            load () {
                this.count += 2
            }
        }
    }
</script>

<style scoped>
    .grid-content {
        background: #d3dce6;
        border-radius: 6px;
        min-height: 36px;
    }

    .grid-content:hover {
        background: rgba(178, 201, 226, 0.96);
    }

    .el-row {
        margin-bottom: 10px;
    }
</style>