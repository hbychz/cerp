import Vue from 'vue'
import VueRouter from 'vue-router'
import EngineeringList from '../components/engineering/List.vue'
import CreateEngineering from '../components/engineering/Create.vue'
import ShowEngineering from '../components/engineering/Show.vue'
import EditEngineering from '../components/engineering/Edit.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/engineering/list', component: EngineeringList },
    { path: '/engineering/create', component: CreateEngineering },
    { path: '/engineering/show', component: ShowEngineering },
    { path: '/engineering/edit', component: EditEngineering }
]

export default new VueRouter({
    routes
})