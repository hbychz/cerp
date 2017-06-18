import Vue from 'vue'
import App from './components/App.vue'
import store from './store'
import router from './router'

new Vue({
    el: '#app',
    store,
    router,
    render: h => h(App),
    mounted: function () {
        var vm = this;
        $.get( "resourceUrl", {} ).done(function(data) {
            vm.$store.commit('changeResourceUrl', data[0]);
        }).fail(function(a, b, c) {

        }).always(function(data) {
            
        });
    }
})