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
            /**
             * Return true if the user is currently at the bottom of the page, false otherwise.
             */
            bottomVisible() {
                const scrollY = window.scrollY
                const visible = document.documentElement.clientHeight
                const pageHeight = document.documentElement.scrollHeight
                const bottomOfPage = visible + scrollY >= pageHeight
                return bottomOfPage || pageHeight < visible
            },


            /**
             * Fetch more public questions iff there are more to fetch and questions aren't currently being loaded.
             */
            fetchMore() {
                if(this.moreToFetch && !this.loadingQuestions) {
                    this.pageNumber += 1
                    this.fetchResults(this.query, this.pageNumber)
                }
            },


            /**
             * Make an initial fetch to find the public questions.
             * Triggered when the user performs a new search.
             *
             * @param query     String of the query to perform.
             */
            fetchInitial(query) {
                this.query = query;
                this.pageNumber = 0;
                this.questions = [];
                this.moreToFetch = true;
                this.fetchResults(this.query, this.pageNumber)
            },

            /**
             * Create a GET request to query the question api.
             * Append any results on to the question array.
             *
             * @param query         String of the query to perform.
             * @param pageNumber    Page number to query for pagination.
             */
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
                    }
                    this.questions = this.questions.concat(response.data)
                    this.loadingQuestions = false;
                })
                .catch(function (error) {
                    console.log(error);
                });
            },


            /**
             * Scroll the viewport to the top of the screen.
             */
            scrollToTop() {
                window.scrollTo(0,0);
            },


            /**
             * Notify the parent component that a question has been selected along with the id of said question.
             *
             * @param id    The question id that has been selected.
             */
            questionSelected(id) {
                this.$emit('question-select', id)
            }
        }
    }
</script>

<style scoped>

</style>