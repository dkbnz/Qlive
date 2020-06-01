<template>
    <div>
        <el-row @click.native="$emit('question-selected', question.id)"
                v-bind:key="question.id"
                class="grid-content"
                type="flex"
                justify="space-between"
                align="middle"
                v-for="question in questions">
            <el-col>
                <h5>{{question.questionText}}</h5>
            </el-col>
            <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="2">
                <el-row align="middle">
                    <small><i class="el-icon-time"></i> {{ parseDate(question.date) }}</small>
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
        data () {
            return {
                questions: [
                    {
                        id: "TS4D5A",
                        questionText: "This is some question text",
                        votes: 0,
                        date: 1591047363
                    },
                    {
                        id: "HGWX5L",
                        questionText: "This is some question text, maybe even a little longer.",
                        votes: 1,
                        date: 1501049082
                    },
                    {
                        id: "DHP3A9",
                        questionText: "This is some question text, maybe even a little longer. Possibly even a little longer than that",
                        votes: 15000,
                        date: 1500000000
                    }
                ]
            }
        },
        methods: {
            parseDate(timestamp) {
                timestamp = this.$moment(timestamp*1000).calendar()
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

    .grid-content:hover {
        background: rgba(178, 201, 226, 0.96);
    }
</style>