import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        currentPage: 'engineering',
        resourceUrl: []
    },
    mutations: {
        changeCurrentPage (state, nextPage) {
            state.currentPage = nextPage;
        },
        changeResourceUrl(state, resourceUrl) {
            state.resourceUrl = resourceUrl;
        }
    }
})