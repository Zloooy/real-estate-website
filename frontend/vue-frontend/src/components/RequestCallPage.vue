<template>
  <div class="request-call-page">
<request-call-form
show-intent-select
@sent="$router.back()"
/>
  </div>
</template>

<script lang="ts">
import RequestCallForm from "@/components/RequestCallForm.vue";

export default {
components: {
RequestCallForm
},
name: "request-call-page"
}
</script>

<style scoped>
.request-call-page {
  text-align: center;
  padding-top: 70px;
}
</style>