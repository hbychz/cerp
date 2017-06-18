<template>
  <div class="box box-default">
      <div v-if="loading" class="overlay">
          <i class="fa fa-refresh fa-spin"></i>
      </div>
        <div class="box-header with-border">
          <h3 class="box-title">
              项目详情
          </h3>
        </div>
        <div class="box-body table-responsive">
            <table class="table table-hover">
                <tbody>
                <tr>
                    <th style="width:30%">项目名称</th>
                    <td>{{engineering.name}}</td>
                </tr>
                <tr>
                    <th>国家</th>
                    <td>{{engineering.address.country}}</td>
                </tr>
                </tbody>
            </table>
        </div><!-- /.box-body -->
        <div class="box-footer">
            <button @click="$router.back()" title="返回" type="button" class="btn btn-default btn-sm">
                <i class="fa fa-chevron-left"></i>
            </button>
            <button @click="$router.push({ path: '/engineering/edit', query: { id: engineering.id } })" type="button" class="btn btn-primary btn-sm">
                编辑
            </button>
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
            loading: true,
            engineering: { }
        }
    },
    created: function () {
        this.initEngineering(this.$route.query.id);
    },
    computed: mapState([
        'resourceUrl'
    ]),
    mounted: function () {
    },
    methods: {
        navigateTo: function (path) {
            this.$router.push(path);
        },
        initEngineering: function (id) {
            var vm = this;
            $.get(this.resourceUrl.engineering.show.href, { id: id }).done(function( data ) {
                vm.engineering = data.engineering;
            }).fail(function(a, b, c) {
                console.log( "error" );
            }).always(function(data) {
                vm.loading = false;
            });
        }
    }
}
</script>
