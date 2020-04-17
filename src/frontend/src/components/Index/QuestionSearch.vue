<template>
    <div>
        <el-input placeholder="Search public questions..." v-model="questionSearchQuery">
            <el-button slot="append" icon="el-icon-arrow-right"></el-button>
        </el-input>
    </div>
</template>

<script>
    export default {
        name: "QuestionSearch",
        data: function() {
            return {
                questionSearchQuery: ""
            }
        }
    }
</script>

<style scoped>
</style>