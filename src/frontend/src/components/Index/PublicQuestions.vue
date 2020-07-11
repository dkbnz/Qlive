<template>
    <div>
        <el-card shadow="never">
            <div slot="header" class="clearfix">
                <question-search
                        @search="fetchResults"
                >
                </question-search>
            </div>
            <question-list
                    :questions="questions"
                    @question-selected="questionSelected"
                    v-loading="loadingQuestions"
            >
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
        data: function() {
            return {
                loadingQuestions: false,
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
                        votes: 100,
                        date: 1500000000
                    }
                ]
            }
        },
        methods: {
            fetchResults(query) {
                this.loadingQuestions = true
                // Do the query here
                console.log(query)
            },
            questionSelected(id) {
                this.$emit('question-select', id)
            }
        }
    }
</script>

<style scoped>

</style>