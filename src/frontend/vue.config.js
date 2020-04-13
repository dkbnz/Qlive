module.exports = {
  outputDir: 'target/dist/',

  devServer: {
    proxy: 'http://localhost:9000'
  },

  assetsDir: 'static',

  chainWebpack: config => {
    config
        .plugin('html')
        .tap(args => {
          args[0].title = 'Qlive - Live questionnaire app'
          return args
        })
  }
}
