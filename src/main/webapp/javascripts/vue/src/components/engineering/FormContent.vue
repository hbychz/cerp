<template>
    <div>
    <div v-bind:class="[ engineering.errors && engineering.errors.name ? 'has-error' : '' ]" class="form-group">
        <label for="name">项目名称</label>
        <input class="form-control" id="name" v-model="engineering.name">
        <span v-if="engineering.errors && engineering.errors.name" class="help-block">{{engineering.errors.name}}</span>
    </div>
    <div v-bind:class="[ engineering.errors && engineering.errors['address.country'] ? 'has-error' : '' ]" class="form-group">
        <label for="addressCountry">国家</label>
        <input class="form-control" id="addressCountry" v-model="engineering.address.country">
        <span v-if="engineering.errors && engineering.errors['address.country']" class="help-block">{{engineering.errors['address.country']}}</span>
    </div>
    </div>
</template>

<script>
    export default {
        props: [ 'engineering' ]
    }
</script>
