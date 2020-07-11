<template>
    <div>
        <el-row v-bind:key="question.id"
                class="grid-content"
                type="flex"
                justify="space-between"
                align="middle"
                v-for="question in questions">
            <el-col @click.native="$emit('question-selected', question.id)">
                <el-link target="_blank">{{ question.questionText }}</el-link>
            </el-col>
            <el-col :xs="11" :sm="8" :md="8" :lg="7" :xl="7">
                <el-row align="middle">
                    <small><i class="el-icon-date"></i> {{ parseDate(question.date) }}</small>
                </el-row>
                <el-row align="middle">
                    <small><i class="el-icon-time"></i> {{ parseTime(question.date) }}</small>
                </el-row>
                <el-row align="middle">
                    <small><i class="el-icon-s-custom"></i> {{ question.votes }} vote{{ question.votes !== 1 ? 's' : '' }}</small>
                </el-row>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "QuestionList",
        props: {
            questions: Array
        },
        data () {
            return {
                selected: ""
            }
        },
        methods: {
            parseDate(timestamp) {
                timestamp = this.$moment(timestamp*1000).format('L')
                return timestamp
            },
            parseTime(timestamp) {
                timestamp = this.$moment(timestamp*1000).format('LT');
                return timestamp
            }
        }
    }
</script>

<style scoped>
    .grid-content {
        background: #d3dce6;
        border-radius: 6px;
        min-height: 36px;
        margin-bottom: 10px;
    }
</style>