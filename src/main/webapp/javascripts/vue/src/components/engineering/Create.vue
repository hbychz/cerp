<template>
  <div class="box box-default">
      <div v-if="loading" class="overlay">
          <i class="fa fa-refresh fa-spin"></i>
      </div>
    <div class="box-header with-border">
      <h3 class="box-title">
          新增项目
      </h3>
    </div><!-- /.box-header -->
    <div class="box-body">
        <form role="form" @submit.prevent="onSave">
            <form-content :engineering="engineering" />
        <button type="submit" style="display: none">保存</button>
        </form>
    </div><!-- /.box-body -->
      <div class="box-footer">
          <button @click="$router.back()" title="返回" type="button" class="btn btn-default btn-sm">
              <i class="fa fa-chevron-left"></i>
          </button>
          <button @click="onSave" type="button" class="btn btn-primary btn-sm">
              保存
          </button>
      </div>
  </div><!-- /.box -->
</template>

<script>
import { mapState } from 'vuex'
import FormContent from './FormContent.vue'

export default {
    props: {
    },
    data: function () {
        return {
            loading: false,
            engineering: { }
        }
    },
    components: {
        FormContent
    },
    created: function () {
        this.initEngineering();
    },
    computed: mapState([
        'resourceUrl'
    ]),
    mounted: function () {
        $('#name').focus();
    },
    methods: {
        initEngineering: function () {
            this.engineering = { name: '', errors: { },
                address: { country: '', province: '', city: '', district: '', street: '', lat: null, lng: null, errors: { } }
            };
        },
        onSave: function () {
            this.loading = true;
            var vm = this;
            $.post(this.resourceUrl.engineering.create.href,
                    $.param(this.engineering).replace(/%5B/g, '.').replace(/%5D/g, '')).done(function( data ) {
                vm.engineering = data.engineering;
                swal({ title: "新建成功", type: "success", timer: 2000, showConfirmButton: true }, function() {
                    vm.$router.replace({ path: '/engineering/show', query: { id: vm.engineering.id } })
                });
            }).fail(function(a, b, c) {
                vm.$set(vm.engineering, 'errors', a.responseJSON.errors);
            }).always(function(data) {
                vm.loading = false;
            });
        }
    }
}
</script>
