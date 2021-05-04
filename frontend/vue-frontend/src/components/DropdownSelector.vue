<template>
  <select v-if="this.options.length && selectedOption && selectedOption[this.nameFieldTitle]" class="dropdown-selector" v-model="selectedOption">
    <option :selected="equals(option, selectedOption)" v-for="(option, i) in options" :key="i" :value="option">
      {{ option[nameFieldTitle] }}
    </option>
  </select>
</template>

<script lang="ts">
import {Options, Vue} from "vue-class-component";
import {compareObjects} from "@/utils";
@Options({
  name: "dropdown-selector",
  props: {
    nameFieldTitle: {
      type: String,
      default: "name"
    },
    firstSelectedOptionIndex: {
      type: Number,
      default: 0
    },
    options: {
      type: Array,
      default: []
    }
  },
  emits: [
    "select"
  ],
  watch: {
    selectedOption(newValue){
      this.$emit("select", newValue)
    },
    options(newVal){
      this.selectedOption = newVal[this.$props.firstSelectedOptionIndex];
    }
  }
})
export default class DropdownSelector extends Vue {
  selectedOption: any = null;
  mounted() {
    //@ts-ignore
    this.selectedOption = this.$props.options[this.$props.firstSelectedOptionIndex];
  }
  equals(o1:any, o2:any): boolean{
    return compareObjects(o1, o2);
}
}
</script>