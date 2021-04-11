import { ComponentCustomProperties } from 'vue';
import { Store } from "./index";


declare module '@vue/runtime-core' {

    interface ComponentCustomProperties {
        store: Store
    }
}