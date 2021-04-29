<template>
  <select v-if="this.options.length && selectedOption && selectedOption[this.nameFieldTitle]" class="dropdown-selector" v-model="selectedOption">
    <option :selected="equals(option, selectedOption)" v-for="(option, i) in options" :key="i" :value="option">
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

  private equals(a: Object, b: Object){
    let keys: string[] = Object.keys(a)
    if (keys.length!== Object.keys(b).length) {
      return false;
    }
    return keys.map(k=>a[k]==b[k]).reduce((x,y)=>x&&y);
  }
  mounted() {
    //@ts-ignore
    this.selectedOption = this.$props.options[this.$props.firstSelectedOptionIndex];
  }
}
</script>