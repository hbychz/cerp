<template>
  <div class="box box-default">
      <div v-if="loading" class="overlay">
          <i class="fa fa-refresh fa-spin"></i>
      </div>
    <div class="box-header with-border">
      <h3 class="box-title">
          项目列表
      </h3>
      <div class="box-tools pull-right">
          <div class="has-feedback">
              <input type="text" class="form-control input-sm" placeholder="搜索项目">
              <span class="glyphicon glyphicon-search form-control-feedback"></span>
          </div>
      </div><!-- /.box-tools -->
    </div><!-- /.box-header -->
    <div class="box-body table-responsive no-padding ">
            <div class="mailbox-controls">
            <button v-if="resourceUrl.engineering.create" @click="$router.push('/engineering/create')" title="新增项目"
                    type="button" class="btn btn-default btn-sm checkbox-toggle">
                <i class="fa fa-plus"></i>
            </button>
            <button @click="loadEngineeringList(pagination.currentPage)" title="刷新" type="button" class="btn btn-default btn-sm checkbox-toggle">
                <i class="fa fa-refresh"></i>
            </button>
            <div class="pull-right">
                <select class="input-sm" @change="loadEngineeringList(parseInt($event.target.value))">
                    <option v-for="n in pagination.totalPage" :value="n" :selected="n === pagination.currentPage">{{ n }} / {{pagination.totalPage}}</option>
                </select>
                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-default btn-sm" :disabled="pagination.currentPage === 1" @click="loadEngineeringList(pagination.currentPage - 1)">
                        <i class="fa fa-chevron-left"></i>
                    </button>
                    <button type="button" class="btn btn-default btn-sm" :disabled="pagination.currentPage === pagination.totalPage" @click="loadEngineeringList(pagination.currentPage + 1)">
                        <i class="fa fa-chevron-right"></i>
                    </button>
                </div>
            </div>
        </div>
        <table class="table table-hover table-striped">
            <tbody>
            <tr>
                <th>
                    <a href="#" @click.stop.prevent="sortEngineeringList('name')">
                        项目名称
                        <i v-if="sort.orderBy === 'name'" v-bind:class="[ sort.order === 'asc' ? 'fa-sort-asc' : 'fa-sort-desc' ]" class="fa"></i>
                        <i v-else class="fa fa-sort"></i>
                    </a>
                </th>
                <th></th>
            </tr>
            <tr v-for="engineering in engineeringList">
                <td>
                    <router-link :to="{ path: '/engineering/show', query: { id: engineering.id } }" >{{engineering.name}}</router-link>
                </td>
                <td><div class="box-tools pull-right">
                    <a href="#" title="删除" data-toggle="tooltip" @click.stop.prevent="onDelete(engineering)"><i class="fa fa-minus text-black"></i></a>
                </div></td>
            </tr>
            </tbody>
        </table>
    </div><!-- /.box-body -->
      <div class="box-footer clearfix">
          <div class="pull-right">
              <select class="input-sm" @change="loadEngineeringList(parseInt($event.target.value))">
                  <option v-for="n in pagination.totalPage" :value="n" :selected="n === pagination.currentPage">{{ n }} / {{pagination.totalPage}}</option>
              </select>
              <div class="btn-group" role="group">
                  <button type="button" class="btn btn-default btn-sm" :disabled="pagination.currentPage === 1" @click="loadEngineeringList(pagination.currentPage - 1)">
                      <i class="fa fa-chevron-left"></i>
                  </button>
                  <button type="button" class="btn btn-default btn-sm" :disabled="pagination.currentPage === pagination.totalPage" @click="loadEngineeringList(pagination.currentPage + 1)">
                      <i class="fa fa-chevron-right"></i>
                  </button>
              </div>
          </div>
      </div>
  </div><!-- /.box -->
</template>

<script>
import { mapState } from 'vuex';

export default {
    props: {
        title: String
    },
    data: function () {
        return {
            loading: false,
            engineeringList: [ ],
            pagination: { max: 10, currentPage: 1, totalPage: 0 },
            sort: { orderBy: null, order: 'asc' },
            newEngineering: { },
            currentEngineering: { }
        }
    },
    computed: mapState([
        'resourceUrl'
    ]),
    created: function () {
        this.loadEngineeringList(this.pagination.currentPage);
    },
    mounted: function () {
    },
    methods: {
        navigateTo: function (path) {
            this.$router.push(path);
        },
        sortEngineeringList: function (property) {
            if (this.sort.orderBy === property) {
                this.sort.order = this.sort.order === 'asc' ? 'desc' : 'asc';
            } else {
                this.sort.orderBy = property;
            }
            this.loadEngineeringList(this.pagination.currentPage);
        },
        loadEngineeringList: function(page) {
            var vm = this;
            vm.loading = true;
            vm.pagination.currentPage = page;
            var offset = (page - 1) * vm.pagination.max;
            var params = { offset: offset, max: vm.pagination.max };
            if (this.sort.orderBy) {
                params.sort = this.sort.orderBy;
                params.order = this.sort.order;
            }
            $.get( "engineering/index", params ).done(function(data) {
                vm.engineeringList =  data.engineeringList;
                vm.pagination.currentPage = parseInt(page);
                vm.pagination.totalPage = Math.ceil( data.count / vm.pagination.max );
                console.log( "second success" );
            }).fail(function(a, b, c) {
                console.log( "error " );
            }).always(function(data) {
                vm.loading = false;
                console.log( "complete" );
            });
        },
        onShow: function (engineering) {
            this.$router.push({ path: '/engineering/show', params: { id: engineering.id } });
        },
        onDelete: function (engineering) {
            var vm = this;
            swal({ title: "确定删除?", type: "warning", showCancelButton: true,
                confirmButtonText: "确定", cancelButtonText: "取消", closeOnConfirm: false },
                    function(){
                        $.ajax({
                            type: 'DELETE',
                            url: vm.resourceUrl.engineering.delete.href + '/' + engineering.id + '?version=' + engineering.version
                        }).done(function(data) {
                            swal({ title: "删除成功", type: "success", timer: 2000, showConfirmButton: true });
                            vm.loadEngineeringList(vm.pagination.currentPage);
                        }).fail(function(a, b, c) {
                            vm.newEngineering.errors = a.responseJSON.errors;
                            console.log( "error" );
                        }).always(function(data) {
                            console.log( "comple" );
                        });
                    }
            );

        }
    }
}
</script>
