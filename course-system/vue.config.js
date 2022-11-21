const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})
module.exports = {
  devServer: {
    historyApiFallback: true,
    allowedHosts: 'all'
  }
}
// module.exports = {
//   productionSourceMap: false,
//   devServer: {
//     historyApiFallback: true,
//     watchOptions: {
//       aggregateTimeout: 300,
//       poll: 1000
//     },
//     disableHostCheck: true
//   }
// }
