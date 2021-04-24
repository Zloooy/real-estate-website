<template>
  <select class="dropdown-selector" v-model="selectedOption">
    <option v-for="(option, i) in options" :key="i" :value="option">
      {{ option[nameFieldTitle] }}
    </option>
  </select>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
@Options({
  name: "dropdown-selector",
  props: {
    nameFieldTitle: {
      type: String,
      default: "name"
    },
    options: []
  },
  emits: [
    "select"
  ],
  watch: {
    selectedOption(newValue){
      this.$emit("select", newValue)
    }
  }
})
export default class DropdownSelector extends Vue {
  selectedOption: any = null;
}
</script>