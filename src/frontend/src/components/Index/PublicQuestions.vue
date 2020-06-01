<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <question-search @search="fetchResults">
                </question-search>
            </div>
            <question-list @question-selected="questionSelected">
            </question-list>
        </el-card>
    </div>
</template>

<script>
    import QuestionList from "./QuestionList";
    import QuestionSearch from "./QuestionSearch";
    export default {
        name: "PublicQuestions",
        components: {QuestionSearch, QuestionList},
        methods: {
            fetchResults(query) {
                console.log(query)
            },
            questionSelected(id) {
                console.log(id)
            }
        }
    }
</script>

<style scoped>

</style>