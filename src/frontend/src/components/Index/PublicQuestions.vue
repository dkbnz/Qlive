<template>
    <div>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <question-search>
                </question-search>
            </div>
            <question-list>
            </question-list>
        </el-card>
    </div>
</template>

<script>
    import QuestionList from "./QuestionList";
    import QuestionSearch from "./QuestionSearch";
    export default {
        name: "PublicQuestions",
        components: {QuestionSearch, QuestionList}
    }
</script>

<style scoped>

</style>