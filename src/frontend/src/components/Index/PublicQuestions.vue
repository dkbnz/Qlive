<template>
    <div>
        <el-card shadow="never">
            <div slot="header" class="clearfix">
                <question-search
                        @search="fetchInitial"
                >
                </question-search>
            </div>
            <question-list
                    :questions="questions"
                    @question-selected="questionSelected"
                    v-loading="loadingQuestions"
            >
            </question-list>
            <p v-if="!moreToFetch">No more results... <el-link target="_blank" @click.native="scrollToTop">Back to top</el-link></p>
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
                questions: [],
                pageNumber: 0,
                moreToFetch: true
            }
        },
        mounted: function () {
            this.$nextTick(function () {
                this.fetchInitial('')
            })
        },
        created() {
            window.addEventListener('scroll', () => {
                if (this.bottomVisible()) {
                    this.fetchMore();
                }
            })
        },
        methods: {
            bottomVisible() {
                const scrollY = window.scrollY
                const visible = document.documentElement.clientHeight
                const pageHeight = document.documentElement.scrollHeight
                const bottomOfPage = visible + scrollY >= pageHeight
                return bottomOfPage || pageHeight < visible
            },

            fetchMore() {
                if(this.moreToFetch && !this.loadingQuestions) {
                    this.pageNumber += 1
                    this.fetchResults(this.query, this.pageNumber)
                }
            },

            fetchInitial(query) {
                this.query = query;
                this.pageNumber = 0;
                this.questions = [];
                this.moreToFetch = true;
                this.fetchResults(this.query, this.pageNumber)
            },

            fetchResults(query, pageNumber) {
                this.loadingQuestions = true
                this.axios.get('question', {
                    params: {
                        query: query,
                        pageNo: pageNumber
                    }
                }).then(response => {
                    if(!response.data.length) {
                        this.moreToFetch = false;
                        console.log(this.moreToFetch)
                    }
                    this.questions = this.questions.concat(response.data)
                    this.loadingQuestions = false;
                })
                .catch(function (error) {
                    console.log(error);
                });
            },

            scrollToTop() {
                window.scrollTo(0,0);
            },

            questionSelected(id) {
                this.$emit('question-select', id)
            }
        }
    }
</script>

<style scoped>

</style>