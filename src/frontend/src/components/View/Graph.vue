<template>
    <div>
        <apexchart :options="options" :series="series" width="100%"></apexchart>
    </div>
</template>

<script>
    export default {
        props: ['question'],
        name: "Graph",
        computed: {
            series() {
                let seriesTemplate = [{
                    name: 'series-1',
                    data: []
                }];

                for (let i = 0; i < this.question.questionOptions.length; i++) {
                    seriesTemplate[0].data.push(this.question.questionOptions[i].voteCount);
                }

                return seriesTemplate;
            },
            options() {
                let chartOptions = {
                    legend: {
                      show: false
                    },
                    chart: {
                        type: 'bar',
                        toolbar: {
                            show: false
                        }
                    },
                    plotOptions: {
                        bar: {
                            barHeight: '100%',
                            distributed: true,
                            horizontal: true,
                            dataLabels: {
                                position: 'bottom'
                            },
                        }
                    },
                    colors: ['#33b2df', '#d4526e', '#13d8aa', '#A5978B', '#2b908f', '#f9a3a4', '#90ee7e', '#f48024', '#69d2e7', '#546E7A'],
                    dataLabels: {
                        enabled: true,
                        textAnchor: 'start',
                        style: {
                            fontSize: '18px',
                            fontFamily: 'Helvetica, Arial, sans-serif',
                            colors: ['#000']
                        },
                        formatter: function (val, opt) {
                            return opt.w.globals.labels[opt.dataPointIndex] + ": " + val
                        },
                        offsetX: 0,
                        dropShadow: {
                            enabled: true
                        }
                    },
                    stroke: {
                        width: 1,
                        colors: ['#fff']
                    },
                    xaxis: {
                        categories: [],
                        labels: {
                            show: false
                        },
                        tickAmount: 1
                    },
                    yaxis: {
                        labels: {
                            show: false
                        }
                    },
                    tooltip: {
                        theme: 'dark',
                        x: {
                            show: false
                        },
                        y: {
                            title: {
                                formatter: function () {
                                    return ''
                                }
                            }
                        }
                    }
                }

                for (let i = 0; i < this.question.questionOptions.length; i++) {
                    chartOptions.xaxis.categories.push(this.question.questionOptions[i].optionText);
                }

                return chartOptions
            }
        }
    }
</script>

<style scoped>
    .el-row {
        margin-bottom: 10px;
    }
</style>

